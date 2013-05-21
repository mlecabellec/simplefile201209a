package simplefile201209a

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Thumbnail {

    static belongsTo = [file:AvailableFile] 
    
    static searchable = false    
    
    static constraints = {
        
        mimeType(nullable:false,blank:false,size:0..50,inList:[ "image/png",
                                    "image/svg",
                                    "image/jpeg"])        
        thumbData(nullable:false,maxSize:80*1024)
        height(nullable:false,min:2,max:512)
        width(nullable:false,min:2,max:512)
        videoTimeOffset(nullable:false,min:0)
        
        dateCreated(nullable:false,editable:false)
        lastUpdated(nullable:false,editable:false)        
    }
    
    static mapping = {
        //datasource "keep"
        thumbData type: "materialized_blob"
        //id generator:"hilo", params:[table:"hi_value",column:"next_value",max_lo:100]
        cache false        
    }       
    
    String mimeType
    byte[] thumbData
    int height
    int width
    int videoTimeOffset
    
    
    Date dateCreated
    Date lastUpdated
}
