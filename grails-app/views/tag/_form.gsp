<%@ page import="simplefile201209a.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'tag', 'error')} ">
	<label for="tag">
		<g:message code="tag.tag.label" default="Tag" />
		
	</label>
	<g:textField name="tag" maxlength="64" value="${tagInstance?.tag}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'file', 'error')} ">
	<label for="file">
		<g:message code="tag.file.label" default="File" />
		
	</label>
	<g:select name="file" from="${simplefile201209a.AvailableFile.list()}" multiple="multiple" optionKey="id" size="5" value="${tagInstance?.file*.id}" class="many-to-many"/>
</div>

