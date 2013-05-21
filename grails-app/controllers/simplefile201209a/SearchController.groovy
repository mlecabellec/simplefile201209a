package simplefile201209a

class SearchController {

    def index() { }
    
    def search_v1() {
        
        //System.out.println("params = " + params)
        
        if(params["value"] == null || params.value.length() < 3)
        {
            render "..."
            return
        }
        
        String q = params.value 
        
        System.out.println("q = " + q) 
        
        List<AvailableFile> foundFiles = AvailableFile.search(q,[max:30]).results
        ArrayList<AvailableFile> filteredFiles = new ArrayList<AvailableFile>()
        List<FileComment> foundComments = FileComment.search(q,[max:30]).results
        List<Tag> foundTags = Tag.search(q,[max:30]).results
        
        System.out.println("foundFiles.size() = " + foundFiles.size()) 
        System.out.println("foundComments.size() = " + foundComments.size()) 
        System.out.println("foundTags.size() = " + foundTags.size()) 
        
        int resultCount = (foundFiles.size() + foundComments.size()+ foundTags.size())
        
        if(resultCount == 0)
        {
            q = "*" + q + "*"
            
            System.out.println("q = " + q) 
            
            foundFiles = AvailableFile.search(q,[max:30]).results
            foundComments = FileComment.search(q,[max:30]).results
            foundTags = Tag.search(q,[max:30]).results  
            resultCount = (foundFiles.size() + foundComments.size()+ foundTags.size())
            
            System.out.println("foundFiles.size() = " + foundFiles.size()) 
            System.out.println("foundComments.size() = " + foundComments.size()) 
            System.out.println("foundTags.size() = " + foundTags.size()) 
        }
        
        for(AvailableFile cFile : foundFiles)
        {
            
            System.out.println("cFile: " + cFile.encodeAsJSON())
            
            if( !cFile.damaged
                //&& cFile.processed
                && !cFile.locked
                && cFile.available
                && !cFile.shoking)
            {
                filteredFiles.add(cFile)
            }
        }
        
        render(view:"resultblock_v1",model:[files:filteredFiles,comments:foundComments,tags:foundTags])
    }
    
    def rendersinglefileresult() {
        AvailableFile file = AvailableFile.get(params.id)
        
        if(file != null)
        {
            //file.hitCount = file.hitCount + 1
            //file.save(flush:false)
            render(view:"filesingleresult_v1",model:[file:file])
            
        }else
        {
            flash.message ="File not found !!!"
            render(view:"missing")
        }        
        

    }
    
    def rendersinglecommentresult() {
        FileComment comment = FileComment.get(params.id)
        
        if(comment != null)
        {
            render(view:"commentsingleresult_v1",model:[comment:comment])
            
        }else
        {
            flash.message ="Comment not found !!!"
            render(view:"missing")
        }            
    }

    def rendersingletagresult() {
        Tag tag = Tag.get(params.id)
        
        if(tag != null)
        {
            render(view:"tagsingleresult_v1",model:[tag:tag])
            
        }else
        {
            flash.message ="Tag not found !!!"
            render(view:"missing")
        }        
    }    
    
    def getfiledetails(){
        AvailableFile file = AvailableFile.get(params.id) 
        
        if(file != null)
        {
            render(view:"filedetails",model:[file:file])
            
        }else
        {
            flash.message ="File not found !!!"
            render(view:"missing")
        }
        
    }
    
}
