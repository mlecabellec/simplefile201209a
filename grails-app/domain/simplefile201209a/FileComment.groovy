package simplefile201209a

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class FileComment {

    static searchable = true
    
    static belongsTo = [file:AvailableFile]  
    
    static constraints = {
    comment(nullable:false,size:0..180)
    good(nullable:false)
    bad(nullable:false)
    requestRemoval(nullable:false)
    shocking(nullable:false)
    
    dateCreated(nullable:false,editable:false)
    lastUpdated(nullable:false,editable:false)          
    }
    
    String comment
    boolean good
    boolean bad
    boolean requestRemoval
    boolean shocking
    
    
    Date dateCreated
    Date lastUpdated    
}
