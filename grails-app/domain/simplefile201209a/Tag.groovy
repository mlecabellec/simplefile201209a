package simplefile201209a

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Tag {

    static hasMany = [fileLinks : FileToTag]
    
    static searchable = true
    
    static constraints = {
        tag(nullable:false,size:0..64,unique:true)
        //dateCreated(nullable:false,editable:false)
        //lastUpdated(nullable:false,editable:false)        
    }
    
    static mapping = {
        //datasource "keep"
    }
    
    String tag
    //Date dateCreated
    //Date lastUpdated 
}
