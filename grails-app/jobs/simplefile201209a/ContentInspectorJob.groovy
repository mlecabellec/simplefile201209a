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

import org.apache.tika.parser.*
import org.apache.tika.metadata.*

import org.xml.sax.helpers.*

/**
 *
 * @author vortigern
 */
class ContentInspectorJob {
    //cron name: 'myTrigger', cronExpression: "0 0 6 * * ?"
    //
    //cronExpression: "s m h D M W Y"
    //                 | | | | | | `- Year [optional]
    //                 | | | | | `- Day of Week, 1-7 or SUN-SAT, ?
    //                 | | | | `- Month, 1-12 or JAN-DEC
    //                 | | | `- Day of Month, 1-31, ?
    //                 | | `- Hour, 0-23
    //                 | `- Minute, 0-59
    //                 `- Second, 0-59    
    
    def concurrent = false
    
    def sessionRequired = true
    
    static triggers = {
        simple name: 'ContentInspectorJob', startDelay: 30*1000, repeatInterval: 20*1000  ,repeatCount: -1
        //cron name:'ContentInspectorJob', startDelay:30*000, cronExpression: '* * * * * ?'
    }

    def execute(){
 
        System.println("ContentInspectorJob Started...") 
        
        //System.println("Test: " + applicationContext["test"]) 
        
        //SecretKeySpec key= servletContext["key"] 
        //String basePath = servletContext["basePath"] 
        //long maxStorage = servletContext["maxStorage"]     
        //long usedStorage = servletContext["usedStorage"]
        //int cryptPass = servletContext["cryptPass"]
        
        ContextUtil ctxUtil = ContextUtil.getInstance() 
        
       
        
        
        CipherUtil cipherUtil = new CipherUtil(     ctxUtil.key,
            ctxUtil.basePath,
            ctxUtil.maxStorage,
            ctxUtil.usedStorage,
            ctxUtil.cryptPass)
        
        
        AvailableFile.withTransaction(){ status ->
       
            //List <AvailableFile> queuedFiles = AvailableFile 
            List <AvailableFile> queuedFiles =  AvailableFile.findAllByProcessed(false, [max:10])
        
            for(AvailableFile cFile: queuedFiles)
            {
            
                cFile.lock()
            
                File tmpFile = File.createTempFile("tmp"  + new Random().nextInt(5000000), null)
        
                FileOutputStream fos = new FileOutputStream(tmpFile) 

                cipherUtil.decodeStream(cFile,fos)
            
                System.out.format("tmpFile = %s", tmpFile.getAbsolutePath())
            
                AutoDetectParser adp = new AutoDetectParser() 
            
                Metadata metadata = new Metadata() 
                ParseContext parseContext = new ParseContext()
                DefaultHandler defaultHandler = new DefaultHandler()
            
                adp.parse(  new FileInputStream(tmpFile),
                    defaultHandler,
                    metadata,
                    parseContext)
            
                //System.out.println("cFile: " + cFile.encodeAsJSON())
                //cFile.processed = true 
                //cFile.save()

                System.out.println("metadata = " + metadata)
            
                for(Map.Entry<String,String> cMetadata : metadata.metadata)
                {
                        cFile.addToFileProperties(name:cMetadata.key,value:cMetadata.value).save()

                }

                System.out.println("cFile: " + cFile.encodeAsJSON())
                cFile.processed = true 
                cFile.save()
                    
                
            }            
        }
        
 
        
    }
    
}