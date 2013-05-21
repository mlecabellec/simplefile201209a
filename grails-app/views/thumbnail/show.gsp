
<%@ page import="simplefile201209a.Thumbnail" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thumbnail.label', default: 'Thumbnail')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-thumbnail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-thumbnail" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list thumbnail">
			
				<g:if test="${thumbnailInstance?.mimeType}">
				<li class="fieldcontain">
					<span id="mimeType-label" class="property-label"><g:message code="thumbnail.mimeType.label" default="Mime Type" /></span>
					
						<span class="property-value" aria-labelledby="mimeType-label"><g:fieldValue bean="${thumbnailInstance}" field="mimeType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.thumbData}">
				<li class="fieldcontain">
					<span id="thumbData-label" class="property-label"><g:message code="thumbnail.thumbData.label" default="Thumb Data" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.height}">
				<li class="fieldcontain">
					<span id="height-label" class="property-label"><g:message code="thumbnail.height.label" default="Height" /></span>
					
						<span class="property-value" aria-labelledby="height-label"><g:fieldValue bean="${thumbnailInstance}" field="height"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.width}">
				<li class="fieldcontain">
					<span id="width-label" class="property-label"><g:message code="thumbnail.width.label" default="Width" /></span>
					
						<span class="property-value" aria-labelledby="width-label"><g:fieldValue bean="${thumbnailInstance}" field="width"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.videoTimeOffset}">
				<li class="fieldcontain">
					<span id="videoTimeOffset-label" class="property-label"><g:message code="thumbnail.videoTimeOffset.label" default="Video Time Offset" /></span>
					
						<span class="property-value" aria-labelledby="videoTimeOffset-label"><g:fieldValue bean="${thumbnailInstance}" field="videoTimeOffset"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="thumbnail.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${thumbnailInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="thumbnail.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${thumbnailInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${thumbnailInstance?.file}">
				<li class="fieldcontain">
					<span id="file-label" class="property-label"><g:message code="thumbnail.file.label" default="File" /></span>
					
						<span class="property-value" aria-labelledby="file-label"><g:link controller="availableFile" action="show" id="${thumbnailInstance?.file?.id}">${thumbnailInstance?.file?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${thumbnailInstance?.id}" />
					<g:link class="edit" action="edit" id="${thumbnailInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
