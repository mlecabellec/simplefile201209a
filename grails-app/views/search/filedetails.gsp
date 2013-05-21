<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="custom001">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
    <title><g:message code="application.title" default="La grange"/></title>
    <script src="${resource(dir:'js/valums-file-uploader/client',file:'fileuploader.js')}" type="text/javascript"></script>

  </head>
  <body class="body">
    <h1 style="text-align:center"><g:message code="application.title" default="La grange"/></h1>


    <div  class="custombox001 downloadbox" id="filedetails" style="min-height: 110px; ">
      <h2>File informations</h2>

      <div class="download">
        <g:link class="download" controller="download" action="downloadfile_v1" id="${file.fileName}"
                params="[fileid: file.id, dkey: file.downloadKey, filename: file.fileName]">
          <g:img class="download" dir="images" file="fileexport.png"/><br/>
          download link<br/>
          (single use)
        </g:link>
      </div>

      <div class="attribute"><label class="attribute"> Name: </label><div class="attributevalue">${file.fileName}</div></div>
      <div class="attribute"><label class="attribute"> MIME: </label><div class="attributevalue">${file.mimeType}</div></div>
      <div class="attribute"><label class="attribute"> Size: </label><div class="attributevalue">${file.size} byte(s)</div></div>
      <div class="attribute"><label class="attribute"> Hit count: </label><div class="attributevalue">${file.hitCount}</div></div>
      <div class="attribute"><label class="attribute"> Download count: </label><div class="attributevalue">${file.downloadCount}</div></div>




    </div>     
    <div  class="custombox001" id="commentzone" style="min-height: 110px; ">
      <h2>Comments</h2>
    </div>     
    <div  class="custombox001" id="tagzone" style="min-height: 110px; ">
      <h2>Tags</h2>
    </div>     



  </body>
</html>
