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

class UploadController {

    def index() { }
    
    
    def uploadfile_v1 = {

     
        //int result = ManipulationFichierService.writeDataIntoFichier(dossier,params.qqfile,request.getInputStream())
    
        int mimeDetectBufferSize = 2 * 1024 * 1024
        ByteArrayOutputStream mimeBaos = new ByteArrayOutputStream(mimeDetectBufferSize)
        byte[] mimeDetectBuffer 
        
        
        String filename = params.qqfile
       
        SecretKeySpec key= servletContext["key"] 
        String basePath = servletContext["basePath"] 
        long maxStorage = servletContext["maxStorage"]     
        long usedStorage = servletContext["usedStorage"]
        int cryptPass = servletContext["cryptPass"]
        
        
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
        
        InputStream ris = request.getInputStream()
        
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
        
        AvailableFile.withTransaction(){ status ->
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
            
        }
        

        
        render "{success:true}"

        //render "{success:false}"

  
    }    
}

/*
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
SecretKey tmp = factory.generateSecret(spec);
SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, secret);
AlgorithmParameters params = cipher.getParameters();
byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
byte[] ciphertext = cipher.doFinal("Hello, World!".getBytes("UTF-8"));
 */

/*
Now send the ciphertext and the iv to the recipient.
The recipient generates a SecretKey in exactly the same way, using the same salt and password.
Then initialize the cipher with the key and the initialization vector.
 */

/*
Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
String plaintext = new String(cipher.doFinal(ciphertext), "UTF-8");
System.out.println(plaintext);
 */

/*
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainClass {
public static void main(String[] args) throws Exception {
Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());    
byte[] input = "www.java2s.com".getBytes();
byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };

SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

System.out.println(new String(input));

// encryption pass
cipher.init(Cipher.ENCRYPT_MODE, key);

byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
ctLength += cipher.doFinal(cipherText, ctLength);
System.out.println(new String(cipherText));
System.out.println(ctLength);

// decryption pass
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
ptLength += cipher.doFinal(plainText, ptLength);
System.out.println(new String(plainText));
System.out.println(ptLength);
}
}
 */