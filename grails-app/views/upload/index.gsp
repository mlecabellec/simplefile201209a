
<%@ page import="simplefile201209a.AvailableFile" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'availableFile.label', default: 'AvailableFile')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
  <script src="${resource(dir:'js/valums-file-uploader/client',file:'fileuploader.js')}" type="text/javascript"></script>
  <style>
    .qq-uploader { position:relative; width: 100%;}

    .qq-upload-button {
      display:block; /* or inline-block */
      width: 100%; padding: 7px 0; text-align:center;
      background:#880000; border-bottom:1px solid #ddd;color:#fff;
    }
    .qq-upload-button-hover {background:#cc0000;}
    .qq-upload-button-focus {outline:1px dotted black;}

    .qq-upload-drop-area {
      position:absolute; top:0; left:0; width:100%; height:100%; min-height: 70px; z-index:2;
      background:#FF9797; text-align:center;
    }
    .qq-upload-drop-area span {
      display:block; position:absolute; top: 50%; width:100%; margin-top:-8px; font-size:16px;
    }
    .qq-upload-drop-area-active {background:#FF7171;}

    .qq-upload-list {margin:15px 35px; padding:0; list-style:disc;}
    .qq-upload-list li { margin:0; padding:0; line-height:15px; font-size:12px;}
    .qq-upload-file, .qq-upload-spinner, .qq-upload-size, .qq-upload-cancel, .qq-upload-failed-text {
      margin-right: 7px;
    }

    .qq-upload-file {}
    .qq-upload-spinner {display:inline-block; background: url(""${resource(dir:'js/valums-file-uploader/client',file:'loading.gif')}"); width:15px; height:15px; vertical-align:text-bottom;}
    .qq-upload-size,.qq-upload-cancel {font-size:11px;}

    .qq-upload-failed-text {display:none;}
    .qq-upload-fail .qq-upload-failed-text {display:inline;}
  </style> 
</head>
<body>
  <a href="#list-availableFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="list-availableFile" class="content scaffold-list" role="main">
    <h1>Sample line</h1>
    <div style="margin: 5px 15px 5px 15px;">
      <div id="filedropzone" style="display:inline-block;padding: 5px 5px 5px 5px;border: lightgreen dotted 1px;min-height:200px;width:100%;">


      </div>
    </div>
    <g:javascript>
      function createUploader(){
      var uploader = new qq.FileUploader({
      element: document.getElementById('filedropzone'),
      action: '${resource(dir:'upload',file:'uploadfile_v1')}',
      debug: true,
      params:{dossier_id:''},
      template: '<div class="qq-uploader">' +
        '<div class="qq-upload-drop-area"><span>Lâchez !!!!!</span></div>' +
        '<div class="qq-upload-button">Glissez des fichiers ici pour les envoyer au serveur</div>' +
        '<ul class="qq-upload-list"></ul>' +
        '</div>'
      });
      }
      window.alert("CP1");
      createUploader();
      window.alert("CP2");
    </g:javascript>   
  </div>
</body>
</html>
