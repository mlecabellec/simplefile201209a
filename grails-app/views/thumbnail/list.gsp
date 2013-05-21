
<%@ page import="simplefile201209a.Thumbnail" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thumbnail.label', default: 'Thumbnail')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-thumbnail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-thumbnail" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="mimeType" title="${message(code: 'thumbnail.mimeType.label', default: 'Mime Type')}" />
					
						<g:sortableColumn property="thumbData" title="${message(code: 'thumbnail.thumbData.label', default: 'Thumb Data')}" />
					
						<g:sortableColumn property="height" title="${message(code: 'thumbnail.height.label', default: 'Height')}" />
					
						<g:sortableColumn property="width" title="${message(code: 'thumbnail.width.label', default: 'Width')}" />
					
						<g:sortableColumn property="videoTimeOffset" title="${message(code: 'thumbnail.videoTimeOffset.label', default: 'Video Time Offset')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'thumbnail.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${thumbnailInstanceList}" status="i" var="thumbnailInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${thumbnailInstance.id}">${fieldValue(bean: thumbnailInstance, field: "mimeType")}</g:link></td>
					
						<td>${fieldValue(bean: thumbnailInstance, field: "thumbData")}</td>
					
						<td>${fieldValue(bean: thumbnailInstance, field: "height")}</td>
					
						<td>${fieldValue(bean: thumbnailInstance, field: "width")}</td>
					
						<td>${fieldValue(bean: thumbnailInstance, field: "videoTimeOffset")}</td>
					
						<td><g:formatDate date="${thumbnailInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${thumbnailInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
