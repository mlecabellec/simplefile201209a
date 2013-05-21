
<%@ page import="simplefile201209a.AvailableFile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'availableFile.label', default: 'AvailableFile')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fileName" title="${message(code: 'availableFile.fileName.label', default: 'File Name')}" />
					
						<g:sortableColumn property="repositoryPath" title="${message(code: 'availableFile.repositoryPath.label', default: 'Repository Path')}" />
					
						<g:sortableColumn property="mimeType" title="${message(code: 'availableFile.mimeType.label', default: 'Mime Type')}" />
					
						<g:sortableColumn property="size" title="${message(code: 'availableFile.size.label', default: 'Size')}" />
					
						<g:sortableColumn property="downloadCount" title="${message(code: 'availableFile.downloadCount.label', default: 'Download Count')}" />
					
						<g:sortableColumn property="hitCount" title="${message(code: 'availableFile.hitCount.label', default: 'Hit Count')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${availableFileInstanceList}" status="i" var="availableFileInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${availableFileInstance.id}">${fieldValue(bean: availableFileInstance, field: "fileName")}</g:link></td>
					
						<td>${fieldValue(bean: availableFileInstance, field: "repositoryPath")}</td>
					
						<td>${fieldValue(bean: availableFileInstance, field: "mimeType")}</td>
					
						<td>${fieldValue(bean: availableFileInstance, field: "size")}</td>
					
						<td>${fieldValue(bean: availableFileInstance, field: "downloadCount")}</td>
					
						<td>${fieldValue(bean: availableFileInstance, field: "hitCount")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${availableFileInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
