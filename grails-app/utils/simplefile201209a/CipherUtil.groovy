/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author vortigern
 */
class CipherUtil {
    
    SecretKeySpec key 
    String basePath
    long maxStorage 
    long usedStorage 
    int cryptPass 
    
    public CipherUtil(  SecretKeySpec key,
        String basePath,
        long maxStorage,
        long usedStorage,
        int cryptPass)
    {
        this.key = key
        this.basePath = basePath
        this.maxStorage = maxStorage
        this.usedStorage = usedStorage
        this.cryptPass = cryptPass
    }
	

    public String detectMimeType()
    {
        return null
    }
    
    public AvailableFile encodeStream(String filename,InputStream ris)
    {
        //int result = ManipulationFichierService.writeDataIntoFichier(dossier,params.qqfile,request.getInputStream())
    
        int mimeDetectBufferSize = 2 * 1024 * 1024
        ByteArrayOutputStream mimeBaos = new ByteArrayOutputStream(mimeDetectBufferSize)
        byte[] mimeDetectBuffer 
        
        
        //String filename = params.qqfile
       
        //SecretKeySpec key= servletContext["key"] 
        //String basePath = servletContext["basePath"] 
        //long maxStorage = servletContext["maxStorage"]     
        //long usedStorage = servletContext["usedStorage"]
        //int cryptPass = servletContext["cryptPass"]
        
        
        int pathSeedTune = 100000 
        long pathSeed = System.currentTimeMillis() * pathSeedTune + new Random().nextInt(pathSeedTune)
        String finalDirectory = basePath
        for(int ctBytePath = 0;ctBytePath < 6 ; ctBytePath++)
        {
            byte cByte = pathSeed & 0x00000000000000ff
            pathSeed = pathSeed >> 2
            
            finalDirectory += File.separator + String.format("%02x",cByte)
        }
        
        File finalDstDir = new File(finalDirectory)
        finalDstDir.mkdirs()
        File dstFile = new File(finalDstDir,Long.toString(pathSeed))
        dstFile.createNewFile()
        
        FileOutputStream fos = new FileOutputStream(dstFile)
        
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);        
        

        
        int byteCount = 0         
        int maxBurstSize = 10 * 1024 
        int burstSize = 0 
        
        //InputStream ris = request.getInputStream()
        
        //main loop iterated until end of stream
        while(burstSize > -1)
        {
            
            int localByteCount = 0
            byte[] buffer = new byte[maxBurstSize]
            
            //fill a full burst or giving up if we reached the end of the stream
            while((burstSize > -1) && (localByteCount < maxBurstSize))
            {
                burstSize = ris.read(buffer,localByteCount,maxBurstSize-localByteCount) 
                if(burstSize > -1)
                {
                    localByteCount += burstSize
                }
            }
         
            byteCount += localByteCount
            
            byte[] cipheredBuffer 
            
            //use update of finalize in end of stream
            if( burstSize > -1)
            {
                cipheredBuffer = cipher.update(buffer)
                fos.write(cipheredBuffer)

                if(byteCount + burstSize < mimeDetectBufferSize)
                {               
                    mimeBaos.write(buffer)
                
                }//if(byteCount + burstSize < mimeDetectBufferSize)
                
                
            }
            else
            {
                //TODO: test burstSize AND localByteCount AND buffer
                
                if((buffer != null) && (localByteCount > 0))
                {
                    cipheredBuffer = cipher.update(buffer)
                    fos.write(cipheredBuffer)                    
                }
                
            }
            

        }//while(burstSize > -1)
        fos.flush()
        mimeBaos.flush()
        
        mimeDetectBuffer = mimeBaos.toByteArray()
        
        Tika tika = new Tika()
        String mimeType = tika.detect(mimeDetectBuffer,filename)
        
        AvailableFile newFile = new AvailableFile()
        newFile.setRepositoryPath(dstFile.getAbsolutePath())
        newFile.setFileName(filename)
        newFile.setMimeType(mimeType)
        //newFile.setSize( byteCount + new Random().nextInt(20 * 1024))
        newFile.setSize( byteCount )
        newFile.setDateCreated(new Date())
        newFile.setLastUpdated(new Date())    
        newFile.setDownloadCount(0L)
        newFile.setDownloadRate1(50 * 1024)
        newFile.setDownloadRate2(500 * 1024)
        newFile.downloadKey = new Random().nextInt(1000000)
        newFile.lastDownload = new Date()
        newFile.setAvailable(true)
        newFile.setProcessed(false)
        newFile.setDamaged(false)
        newFile.setShoking(false)
        newFile.setLocked(false)
        
        //System.out.println("Test: " + newFile.encodeAsXML())
        System.out.println("Test: " + newFile.encodeAsJSON())
        
        newFile.save(failOnError:true,flush:true)
        
        return newFile 
        
        //render "{success:true}"

        //render "{success:false}"
        
    }

    public void decodeStream(String fileid, String filename , String givenDownloadKey, OutputStream sos)
    {
        
        
        //SecretKeySpec key= servletContext["key"] 
        //String basePath = servletContext["basePath"] 
        //long maxStorage = servletContext["maxStorage"]     
        //long usedStorage = servletContext["usedStorage"]
        //int cryptPass = servletContext["cryptPass"]        
       
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);        

        //String fileId = params["fileid"]
        //String filename = params["filename"]
        //String givenDownloadKey = params["dkey"]
        
        if(fileId == null || filename == null)
        {
            //flash.message = "Bad parameters."
            //render(view:"missing")
            return
        }

        long id
        int downloadKey
        try{
            id = Long.parseLong(fileId)
            downloadKey = Integer.parseInt(givenDownloadKey)
        }catch(NumberFormatException nfe01)
        {
            //flash.message = "Bad parameters."
            //render(view:"missing")
            return
        }
        
        AvailableFile cFile = AvailableFile.get(id)
        if(cFile == null )
        {
            //flash.message = "Record not found."
            //render(view:"missing")
            return
        }
        
        if(cFile.downloadKey != downloadKey)
        {
            //flash.message = "Bad download key. A download link can be use only one time."
            //render(view:"missing")
            return
        }        
        
        File targetFile = new File(cFile.getRepositoryPath())
        if(!targetFile.exists() || !targetFile.isFile() || !targetFile.canRead())
        {
            //flash.message = "Data not found."
            //render(view:"missing")
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
            //flash.message = "MIME don't match. AES decoding problem ?"
            //render(view:"missing")
            return
        }

        
        
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);                
        
        fis = new FileInputStream(targetFile)
                
        //OutputStream sos = response.getOutputStream()
        //response.setContentType(cFile.mimeType)
        
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
        
        
        //cFile.downloadCount = cFile.downloadCount + 1
        //cFile.downloadKey = new Random().nextInt(1000000)
        //cFile.save(flush:true)
        
    }

    public void decodeStream(AvailableFile cFile, OutputStream sos)
    {
        
        
        //SecretKeySpec key= servletContext["key"] 
        //String basePath = servletContext["basePath"] 
        //long maxStorage = servletContext["maxStorage"]     
        //long usedStorage = servletContext["usedStorage"]
        //int cryptPass = servletContext["cryptPass"]        
       
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);        

        //String fileId = params["fileid"]
        //String filename = params["filename"]
        //String givenDownloadKey = params["dkey"]
        

        if(cFile == null )
        {
            //flash.message = "Record not found."
            //render(view:"missing")
            return
        }
        
        
        File targetFile = new File(cFile.getRepositoryPath())
        if(!targetFile.exists() || !targetFile.isFile() || !targetFile.canRead())
        {
            //flash.message = "Data not found."
            //render(view:"missing")
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
            //flash.message = "MIME don't match. AES decoding problem ?"
            //render(view:"missing")
            return
        }

        
        
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);                
        
        fis = new FileInputStream(targetFile)
                
        //OutputStream sos = response.getOutputStream()
        //response.setContentType(cFile.mimeType)
        
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
        
        
        //cFile.downloadCount = cFile.downloadCount + 1
        //cFile.downloadKey = new Random().nextInt(1000000)
        //cFile.save(flush:true)
        
    }

    
}

