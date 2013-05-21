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


    <div  class="custombox001" id="highlightzone" style="min-height: 110px; ">
    </div>     
    <div  class="custombox001" id="searchzone" style="min-height: 110px; ">
      <div id="searchBox" class="searchBox">
        <g:message code="searchBox.label" default="Search: " />
        <g:remoteField class="searchField" update="searchResult"
                       name="searchField" value="" controller="search" action="search_v1" />

      </div>
      <div id="searchResult">
      </div>         
    </div>
    <div  class="custombox001" id="filedropzone" style="min-height: 110px; ">
    </div>
  <g:javascript>
    function createUploader(){
    var uploader = new qq.FileUploader({
    element: document.getElementById('filedropzone'),
    action: '${resource(dir:'upload',file:'uploadfile_v1')}',
    debug: true,
    params:{dossier_id:''},
    template: '<div class="qq-uploader">' +
      '<div class="qq-upload-drop-area"><span>Drop !!!!!</span></div>' +
      '<div class="qq-upload-button">Drop your files here !!!</div>' +
      '<ul class="qq-upload-list"></ul>' +
      '</div>'
    });
    }
    //window.alert("CP1");
    createUploader();
    //window.alert("CP2");
  </g:javascript> 
</body>
</html>
