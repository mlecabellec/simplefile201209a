
<%@ page import="simplefile201209a.AvailableFile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'availableFile.label', default: 'AvailableFile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-availableFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-availableFile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list availableFile">
			
				<g:if test="${availableFileInstance?.fileName}">
				<li class="fieldcontain">
					<span id="fileName-label" class="property-label"><g:message code="availableFile.fileName.label" default="File Name" /></span>
					
						<span class="property-value" aria-labelledby="fileName-label"><g:fieldValue bean="${availableFileInstance}" field="fileName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.repositoryPath}">
				<li class="fieldcontain">
					<span id="repositoryPath-label" class="property-label"><g:message code="availableFile.repositoryPath.label" default="Repository Path" /></span>
					
						<span class="property-value" aria-labelledby="repositoryPath-label"><g:fieldValue bean="${availableFileInstance}" field="repositoryPath"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.mimeType}">
				<li class="fieldcontain">
					<span id="mimeType-label" class="property-label"><g:message code="availableFile.mimeType.label" default="Mime Type" /></span>
					
						<span class="property-value" aria-labelledby="mimeType-label"><g:fieldValue bean="${availableFileInstance}" field="mimeType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="availableFile.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${availableFileInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.downloadCount}">
				<li class="fieldcontain">
					<span id="downloadCount-label" class="property-label"><g:message code="availableFile.downloadCount.label" default="Download Count" /></span>
					
						<span class="property-value" aria-labelledby="downloadCount-label"><g:fieldValue bean="${availableFileInstance}" field="downloadCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.hitCount}">
				<li class="fieldcontain">
					<span id="hitCount-label" class="property-label"><g:message code="availableFile.hitCount.label" default="Hit Count" /></span>
					
						<span class="property-value" aria-labelledby="hitCount-label"><g:fieldValue bean="${availableFileInstance}" field="hitCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.downloadRate1}">
				<li class="fieldcontain">
					<span id="downloadRate1-label" class="property-label"><g:message code="availableFile.downloadRate1.label" default="Download Rate1" /></span>
					
						<span class="property-value" aria-labelledby="downloadRate1-label"><g:fieldValue bean="${availableFileInstance}" field="downloadRate1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.downloadRate2}">
				<li class="fieldcontain">
					<span id="downloadRate2-label" class="property-label"><g:message code="availableFile.downloadRate2.label" default="Download Rate2" /></span>
					
						<span class="property-value" aria-labelledby="downloadRate2-label"><g:fieldValue bean="${availableFileInstance}" field="downloadRate2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.downloadKey}">
				<li class="fieldcontain">
					<span id="downloadKey-label" class="property-label"><g:message code="availableFile.downloadKey.label" default="Download Key" /></span>
					
						<span class="property-value" aria-labelledby="downloadKey-label"><g:fieldValue bean="${availableFileInstance}" field="downloadKey"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.damaged}">
				<li class="fieldcontain">
					<span id="damaged-label" class="property-label"><g:message code="availableFile.damaged.label" default="Damaged" /></span>
					
						<span class="property-value" aria-labelledby="damaged-label"><g:formatBoolean boolean="${availableFileInstance?.damaged}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.processed}">
				<li class="fieldcontain">
					<span id="processed-label" class="property-label"><g:message code="availableFile.processed.label" default="Processed" /></span>
					
						<span class="property-value" aria-labelledby="processed-label"><g:formatBoolean boolean="${availableFileInstance?.processed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.locked}">
				<li class="fieldcontain">
					<span id="locked-label" class="property-label"><g:message code="availableFile.locked.label" default="Locked" /></span>
					
						<span class="property-value" aria-labelledby="locked-label"><g:formatBoolean boolean="${availableFileInstance?.locked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.available}">
				<li class="fieldcontain">
					<span id="available-label" class="property-label"><g:message code="availableFile.available.label" default="Available" /></span>
					
						<span class="property-value" aria-labelledby="available-label"><g:formatBoolean boolean="${availableFileInstance?.available}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.shoking}">
				<li class="fieldcontain">
					<span id="shoking-label" class="property-label"><g:message code="availableFile.shoking.label" default="Shoking" /></span>
					
						<span class="property-value" aria-labelledby="shoking-label"><g:formatBoolean boolean="${availableFileInstance?.shoking}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.lastDownload}">
				<li class="fieldcontain">
					<span id="lastDownload-label" class="property-label"><g:message code="availableFile.lastDownload.label" default="Last Download" /></span>
					
						<span class="property-value" aria-labelledby="lastDownload-label"><g:formatDate date="${availableFileInstance?.lastDownload}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="availableFile.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${availableFileInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="availableFile.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${availableFileInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="availableFile.comments.label" default="Comments" /></span>
					
						<g:each in="${availableFileInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="fileComment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="availableFile.tags.label" default="Tags" /></span>
					
						<g:each in="${availableFileInstance.tags}" var="t">
						<span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${availableFileInstance?.thumbnails}">
				<li class="fieldcontain">
					<span id="thumbnails-label" class="property-label"><g:message code="availableFile.thumbnails.label" default="Thumbnails" /></span>
					
						<g:each in="${availableFileInstance.thumbnails}" var="t">
						<span class="property-value" aria-labelledby="thumbnails-label"><g:link controller="thumbnail" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${availableFileInstance?.id}" />
					<g:link class="edit" action="edit" id="${availableFileInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
