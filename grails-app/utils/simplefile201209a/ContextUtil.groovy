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
class ContextUtil {
	
    protected final SecretKeySpec key
    protected final String basePath
    protected final long maxStorage 
    protected final long usedStorage 
    protected final int cryptPass  
    
    static protected ContextUtil singleton
        
    protected ContextUtil(
        SecretKeySpec pKey,
        String pBasePath,
        long pMaxStorage ,
        long pUsedStorage ,
        int pCryptPass )
    {
        key = pKey
        basePath = pBasePath
        maxStorage = pMaxStorage
        usedStorage = pUsedStorage
        cryptPass = pCryptPass             
    }
    
    public static ContextUtil getInstance(
        SecretKeySpec pKey,
        String pBasePath,
        long pMaxStorage ,
        long pUsedStorage ,
        int pCryptPass )
    {
        if(singleton == null)
        {
            singleton = new ContextUtil(
                pKey,
                pBasePath,
                pMaxStorage ,
                pUsedStorage ,
                pCryptPass )
        }
        
        return singleton
    }
    
    public static ContextUtil getInstance()
    {
        return singleton 
    }
    
}

