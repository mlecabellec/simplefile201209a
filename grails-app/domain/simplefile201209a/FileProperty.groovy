package simplefile201209a

class FileProperty {

    static belongsTo = [file : AvailableFile]
    
    static searchable = true
    
    static constraints = {

        name(nullable:false,size:0..190)
        value(nullable:false,size:0..190)
        dateCreated(nullable:false,editable:false)
        lastUpdated(nullable:false,editable:false)         
    }
    
    String name
    String value
    
    Date dateCreated
    Date lastUpdated
}
