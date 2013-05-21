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
class FileTaggerJob {

    def concurrent = false
    
    def sessionRequired = true
    
    static triggers = {
        simple name: 'FileTaggerJob', startDelay: 30*1000, repeatInterval: 20*1000  ,repeatCount: -1
        //cron name:'ContentInspectorJob', startDelay:30*000, cronExpression: '* * * * * ?'
    }

    def execute(){
 
        System.println("FileTaggerJob Started...") 
        
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

        
        
    }
    
    
}

