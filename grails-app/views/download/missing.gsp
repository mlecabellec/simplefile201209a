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


    <div  class="custombox001" id="messagezone" style="min-height: 110px; ">
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if> 			<g:hasErrors bean="${availableFileInstance}">
        <ul class="errors" role="alert">
          <g:eachError bean="${availableFileInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
          </g:eachError>
        </ul>
      </g:hasErrors>

    </div>     



  </body>
</html>
