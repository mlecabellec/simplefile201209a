
<%@ page import="simplefile201209a.FileComment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fileComment.label', default: 'FileComment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fileComment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fileComment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="comment" title="${message(code: 'fileComment.comment.label', default: 'Comment')}" />
					
						<g:sortableColumn property="good" title="${message(code: 'fileComment.good.label', default: 'Good')}" />
					
						<g:sortableColumn property="bad" title="${message(code: 'fileComment.bad.label', default: 'Bad')}" />
					
						<g:sortableColumn property="requestRemoval" title="${message(code: 'fileComment.requestRemoval.label', default: 'Request Removal')}" />
					
						<g:sortableColumn property="shocking" title="${message(code: 'fileComment.shocking.label', default: 'Shocking')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'fileComment.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fileCommentInstanceList}" status="i" var="fileCommentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fileCommentInstance.id}">${fieldValue(bean: fileCommentInstance, field: "comment")}</g:link></td>
					
						<td><g:formatBoolean boolean="${fileCommentInstance.good}" /></td>
					
						<td><g:formatBoolean boolean="${fileCommentInstance.bad}" /></td>
					
						<td><g:formatBoolean boolean="${fileCommentInstance.requestRemoval}" /></td>
					
						<td><g:formatBoolean boolean="${fileCommentInstance.shocking}" /></td>
					
						<td><g:formatDate date="${fileCommentInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fileCommentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
