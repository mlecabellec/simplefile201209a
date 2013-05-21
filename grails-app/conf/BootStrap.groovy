import simplefile201209a.*
import java.security.*
import java.security.Provider.Service
import javax.crypto.SecretKeyFactory
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.PBEKeySpec
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class BootStrap {

    def init = { servletContext ->
        
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider())
        
        for(Provider cProvider: Security.getProviders() )
        {
            System.out.format("Crypto provider found => name %s version %s ",cProvider.getName(),cProvider.getInfo())
            for(Service cService : cProvider.getServices())
            {
             System.out.format(" - Crypto service found => algo %s type %s ",cService.getAlgorithm(),cService.getType())   
            }
        }
        
        List<String> keyFragments = [
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n",
            "o","p","q","r","s","t","u","v","w","x","y","O","1","2",
            "3","4","5","6","7","8","9"] 
        
        //String keyString = "ThisIsASampleKeyWhichShouldBeLongEnought" 
        String keyString = "" 

        
        Random random = new Random()
        for(int ctf = 0 ; ctf < 40 ; ctf++ )
        {
            keyString += keyFragments.get(random.nextInt(keyFragments.size()))
        }
        
        System.out.println("keyString = " + keyString) 
        
        
        
        //TODO: Retrieve the key from an external protected server
        servletContext["keyString"] = keyString 
        servletContext["basePath"] = "/tmp/simplefile201209a" 
        servletContext["maxStorage"] = 100L * 1024L * 1024L * 1024L 
        servletContext["cryptPass"] = 1

        
        long bootstrap_t10a = System.currentTimeMillis()
        System.out.println("Generating the key...")
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(keyString.toCharArray(),keyString.getBytes(),65535,256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec key = new SecretKeySpec(tmp.getEncoded(), "AES");        
        servletContext["key"] = key
        long bootstrap_t10b = System.currentTimeMillis()
        System.out.format("Key generated in %s ms ",bootstrap_t10b-bootstrap_t10a)
        
        System.out.format("max storage: %s MB ", servletContext["maxStorage"] /  (1024L * 1024L))
        
        long usedStorage = 0L
        
        System.out.println("Computing used storage space...")
        
        AvailableFile.list().each(){
            usedStorage += it.size  
        }
        
        System.out.format("Used storage: %s MB ", usedStorage /  (1024L * 1024L))
        
        servletContext["usedStorage"] = usedStorage
        
        
        //SecretKeySpec key= servletContext["key"] 
        //String basePath = servletContext["basePath"] 
        //long maxStorage = servletContext["maxStorage"]     
        //long usedStorage = servletContext["usedStorage"]
        //int cryptPass = servletContext["cryptPass"]
        
        System.out.println("servletContext[\"key\"] = " + servletContext["key"])
        System.out.println("servletContext[\"basePath\"] = " + servletContext["basePath"])
        System.out.println("servletContext[\"maxStorage\"] = " + servletContext["maxStorage"])
        System.out.println("servletContext[\"usedStorage\"] = " + servletContext["usedStorage"])
        System.out.println("servletContext[\"cryptPass\"] = " + servletContext["cryptPass"])
        
        ContextUtil.getInstance(    servletContext["key"],
                                    servletContext["basePath"],
                                    servletContext["maxStorage"],
                                    servletContext["usedStorage"],
                                    servletContext["cryptPass"])
        
        
    }
    
    
    def destroy = {
    }
}
