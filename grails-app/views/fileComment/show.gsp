
<%@ page import="simplefile201209a.FileComment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fileComment.label', default: 'FileComment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fileComment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fileComment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fileComment">
			
				<g:if test="${fileCommentInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="fileComment.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${fileCommentInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.good}">
				<li class="fieldcontain">
					<span id="good-label" class="property-label"><g:message code="fileComment.good.label" default="Good" /></span>
					
						<span class="property-value" aria-labelledby="good-label"><g:formatBoolean boolean="${fileCommentInstance?.good}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.bad}">
				<li class="fieldcontain">
					<span id="bad-label" class="property-label"><g:message code="fileComment.bad.label" default="Bad" /></span>
					
						<span class="property-value" aria-labelledby="bad-label"><g:formatBoolean boolean="${fileCommentInstance?.bad}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.requestRemoval}">
				<li class="fieldcontain">
					<span id="requestRemoval-label" class="property-label"><g:message code="fileComment.requestRemoval.label" default="Request Removal" /></span>
					
						<span class="property-value" aria-labelledby="requestRemoval-label"><g:formatBoolean boolean="${fileCommentInstance?.requestRemoval}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.shocking}">
				<li class="fieldcontain">
					<span id="shocking-label" class="property-label"><g:message code="fileComment.shocking.label" default="Shocking" /></span>
					
						<span class="property-value" aria-labelledby="shocking-label"><g:formatBoolean boolean="${fileCommentInstance?.shocking}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="fileComment.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${fileCommentInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="fileComment.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${fileCommentInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fileCommentInstance?.file}">
				<li class="fieldcontain">
					<span id="file-label" class="property-label"><g:message code="fileComment.file.label" default="File" /></span>
					
						<span class="property-value" aria-labelledby="file-label"><g:link controller="availableFile" action="show" id="${fileCommentInstance?.file?.id}">${fileCommentInstance?.file?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fileCommentInstance?.id}" />
					<g:link class="edit" action="edit" id="${fileCommentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
