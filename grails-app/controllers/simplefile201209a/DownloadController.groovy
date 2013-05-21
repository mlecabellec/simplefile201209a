package simplefile201209a

import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher

import org.bouncycastle.jce.provider.*
import javax.crypto.CipherOutputStream
import org.apache.tika.Tika
import javax.crypto.CipherInputStream


class DownloadController {

    def index() { }
   
    
    def downloadfile_v1 = {
        
        
        
        SecretKeySpec key= servletContext["key"] 
        String basePath = servletContext["basePath"] 
        long maxStorage = servletContext["maxStorage"]     
        long usedStorage = servletContext["usedStorage"]
        int cryptPass = servletContext["cryptPass"]        
       
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);        

        String fileId = params["fileid"]
        String filename = params["filename"]
        String givenDownloadKey = params["dkey"]
        
        if(fileId == null || filename == null)
        {
            flash.message = "Bad parameters."
            render(view:"missing")
            return
        }

        long id
        int downloadKey
        try{
            id = Long.parseLong(fileId)
            downloadKey = Integer.parseInt(givenDownloadKey)
        }catch(NumberFormatException nfe01)
        {
            flash.message = "Bad parameters."
            render(view:"missing")
            return
        }
        
        AvailableFile cFile = AvailableFile.get(id)
        if(cFile == null )
        {
            flash.message = "Record not found."
            render(view:"missing")
            return
        }
        
        if(cFile.downloadKey != downloadKey)
        {
            flash.message = "Bad download key. A download link can be use only one time."
            render(view:"missing")
            return
        }        
        
        File targetFile = new File(cFile.getRepositoryPath())
        if(!targetFile.exists() || !targetFile.isFile() || !targetFile.canRead())
        {
            flash.message = "Data not found."
            render(view:"missing")
            return            
        }
        
        
        
        FileInputStream fis = new FileInputStream(targetFile)


        int byteCount = 0         
        int maxBurstSize = 10 * 1024 
        int burstSize = 0
        
        
        int mimeDetectBufferSize = 2 * 1024 * 1024
        int mimeDetectByteCount = 0
        ByteArrayOutputStream mimeBaos = new ByteArrayOutputStream()
        byte[] mimeDetectBuffer
        
        //read data unitl end of stream of until the mimeDetectBuffer have enought data
        while((burstSize > -1) && (byteCount < mimeDetectBufferSize))
        {
            
            int localByteCount = 0
            byte[] buffer = new byte[maxBurstSize]
            
            //fill a full burst or giving up if we reached the end of the stream
            while((burstSize > -1) && (localByteCount < maxBurstSize))
            {
                burstSize = fis.read(buffer,localByteCount,maxBurstSize-localByteCount) 
                if(burstSize > -1)
                {
                    localByteCount += burstSize
                }
            }
         
            byteCount += localByteCount
            
            byte[] decipheredBuffer 
            
            //use update of finalize in end of stream
            if( burstSize > -1)
            {
                decipheredBuffer = cipher.update(buffer)
                if(byteCount + burstSize < mimeDetectBufferSize)
                {               
                    mimeBaos.write(decipheredBuffer)
                
                }//if(byteCount + burstSize < mimeDetectBufferSize)
            }
            else
            {
                decipheredBuffer = cipher.update(buffer)
                if(byteCount + burstSize < mimeDetectBufferSize)
                {               
                    mimeBaos.write(decipheredBuffer)
                
                }//if(byteCount + burstSize < mimeDetectBufferSize)
            }            

        }//while(burstSize > -1)

        
        mimeDetectBuffer = mimeBaos.toByteArray()
        
        
        Tika tika = new Tika()
        String mimeType = tika.detect(mimeDetectBuffer,cFile.fileName)        
        
        fis.close()
        
        
        if(!mimeType.matches(cFile.mimeType))
        {
            flash.message = "MIME don't match. AES decoding problem ?"
            render(view:"missing")
            return
        }

        
        
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);                
        
        fis = new FileInputStream(targetFile)
                
        OutputStream sos = response.getOutputStream()
        response.setContentType(cFile.mimeType)
        
        long download_t0 = System.currentTimeMillis()
        
        int downloadRate = cFile.downloadRate1
        
        int originalSize = cFile.size
        
        //reset status data
        byteCount = 0
        burstSize = 0
        
        //read data unitl end of stream 
        while((burstSize > -1))
        {
            
            //System.out.println("CP01, byteCount = " + byteCount)
            
            int localByteCount = 0
            byte[] buffer = new byte[maxBurstSize]
            
            //fill a full burst or giving up if we reached the end of the stream
            while((burstSize > -1) && (localByteCount < maxBurstSize))
            {
                
                //System.out.println("CP02, localByteCount = " + localByteCount)
                //System.out.println("CP02, burstSize = " + burstSize)
                
                burstSize = fis.read(buffer,localByteCount,maxBurstSize-localByteCount) 
                
                //System.out.println("CP03, burstSize = " + burstSize)
                
                if(burstSize > -1)
                {
                    localByteCount += burstSize
                    //System.out.println("CP04, localByteCount = " + localByteCount)

                }
            }
         
            byteCount += localByteCount 
            
            byte[] decipheredBuffer 
            
            //System.out.println("CP05, buffer.length = " + buffer.length)
            //System.out.println("CP05, byteCount = " + byteCount)
            //System.out.println("CP05, localByteCount = " + localByteCount)
            
            //use update of finalize in end of stream
            if( burstSize > -1)
            {
                decipheredBuffer = cipher.update(buffer)
                //System.out.println("CP06, decipheredBuffer.length = " + decipheredBuffer.length)
                //System.out.println("CP06, byteCount = " + byteCount)
                //System.out.println("CP06, originalSize = " + originalSize)
                
                if(byteCount > originalSize)
                {
                    int lastBurstSize = localByteCount - (byteCount-originalSize) 
                    //System.out.println("CP07A, WRITING decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP07A, WRITING localByteCount = " + localByteCount)
                    //System.out.println("CP07A, WRITING byteCount = " + byteCount)
                    //System.out.println("CP07A, WRITING originalSize = " + originalSize)
                    //System.out.println("CP07A, WRITING lastBurstSize = " + lastBurstSize)
                    sos.write(decipheredBuffer,0,lastBurstSize)
                    sos.flush()
                    //System.out.println("CP07B, WRITED decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP07B, WRITED lastBurstSize = " + lastBurstSize)
                }
                else
                {
                    int lastBurstSize = localByteCount - (byteCount-originalSize) 
                    //System.out.println("CP07C, WRITING decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP07C, WRITING localByteCount = " + localByteCount)
                    //System.out.println("CP07C, WRITING byteCount = " + byteCount)
                    //System.out.println("CP07C, WRITING originalSize = " + originalSize)
                    //System.out.println("CP07C, WRITING lastBurstSize = " + lastBurstSize)
                    sos.write(decipheredBuffer)
                    sos.flush()
                    //System.out.println("CP07D, WRITED decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP07D, WRITED lastBurstSize = " + lastBurstSize)
                }


            }
            else if(localByteCount > 0)
            {//TODO: test burstSize AND localByteCount AND buffer
                //decipheredBuffer cipher.doFinal(buffer)
                //trim end of the last burst (assume it's garbage data ?)
                
                decipheredBuffer = cipher.update(buffer)
                //System.out.println("CP08, decipheredBuffer.length = " + decipheredBuffer.length)
                                
                if(byteCount > originalSize)
                {
                    int lastBurstSize = localByteCount - (byteCount-originalSize) 
                    //System.out.println("CP09A, WRITING decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP09A, WRITING localByteCount = " + localByteCount)
                    //System.out.println("CP09A, WRITING byteCount = " + byteCount)
                    //System.out.println("CP09A, WRITING originalSize = " + originalSize)
                    //System.out.println("CP09A, WRITING lastBurstSize = " + lastBurstSize)
                    sos.write(decipheredBuffer,0,lastBurstSize)
                    sos.flush()
                    //System.out.println("CP09B, WRITED decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP09B, WRITED lastBurstSize = " + lastBurstSize)
                }
                else
                {
                    int lastBurstSize = localByteCount - (byteCount-originalSize) 
                    //System.out.println("CP09C, WRITING decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP09C, WRITING localByteCount = " + localByteCount)
                    //System.out.println("CP09C, WRITING byteCount = " + byteCount)
                    //System.out.println("CP09C, WRITING originalSize = " + originalSize)
                    //System.out.println("CP09C, WRITING lastBurstSize = " + lastBurstSize)
                    sos.write(decipheredBuffer)
                    sos.flush()
                    //System.out.println("CP09D, WRITED decipheredBuffer.length = " + decipheredBuffer.length)
                    //System.out.println("CP09D, WRITED lastBurstSize = " + lastBurstSize)
                }
                
            }else{
                //System.out.println("CP10, Here ? ")
                //System.out.println("CP10, byteCount = " + byteCount)
                //System.out.println("CP10, localByteCount = " + localByteCount)
                //System.out.println("CP10, burstSize = " + burstSize)
                //System.out.println("CP10, buffer.length = " + buffer.length)
                //System.out.println("CP10, decipheredBuffer.length = " + decipheredBuffer.length)
            }
            

            
        }//while(burstSize > -1)
        
        
        cFile.downloadCount = cFile.downloadCount + 1
        cFile.downloadKey = new Random().nextInt(1000000)
        cFile.save(flush:true)
    }    
}
