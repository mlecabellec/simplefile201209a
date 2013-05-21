package simplefile201209a

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class AvailableFile {

    
    static hasMany = [comments: FileComment,thumbnails:Thumbnail ,tagLinks : FileToTag, fileProperties : FileProperty]
    
    static searchable = true
    
    static constraints = {
        fileName(nullable:false,size:0..1024,editable:false)
        repositoryPath(nullable:false,size:0..2048,display:false)
        mimeType(nullable:true,size:0..128,editable:false)
        //key(nullable:true,size:0..256,editable:false,display:false)
        size(nullable:false,min:0L,editable:false)
        
        downloadCount(nullable:false,min:0L,editable:false)
        hitCount(nullable:false,min:0L,editable:false)
        
        downloadRate1(nullable:false,min:1,editable:false)
        downloadRate2(nullable:false,min:1,editable:false) 
        
        downloadKey(nullable:false,editable:false,display:false) 

        damaged(nullable:false)
        processed(nullable:false)
        locked(nullable:false)
        available(nullable:false)
        shoking(nullable:false)
        
        lastDownload(nullable:false,editable:false) 
        
        dateCreated(nullable:false,editable:false)
        lastUpdated(nullable:false,editable:false)       
    }
    
    String fileName 
    String repositoryPath
    String mimeType
    //String key
    long size
    
    long downloadCount 
    long hitCount
    
    int downloadRate1
    int downloadRate2
    
    int downloadKey 
    
    boolean damaged
    boolean processed
    boolean locked
    boolean available
    boolean shoking
    
    Date lastDownload
    
    Date dateCreated
    Date lastUpdated
    

    
}
