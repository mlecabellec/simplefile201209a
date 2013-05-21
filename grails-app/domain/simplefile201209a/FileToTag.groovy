package simplefile201209a

class FileToTag {

    static belongsTo = [file : AvailableFile , tag : Tag ]
    
    static constraints = {

        //dateCreated(nullable:false,editable:false)
        //lastUpdated(nullable:false,editable:false)         
    }
    
    //Date dateCreated
    //Date lastUpdated
}
