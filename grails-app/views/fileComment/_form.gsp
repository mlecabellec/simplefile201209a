<%@ page import="simplefile201209a.FileComment" %>



<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="fileComment.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" maxlength="180" value="${fileCommentInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'good', 'error')} ">
	<label for="good">
		<g:message code="fileComment.good.label" default="Good" />
		
	</label>
	<g:checkBox name="good" value="${fileCommentInstance?.good}" />
</div>

<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'bad', 'error')} ">
	<label for="bad">
		<g:message code="fileComment.bad.label" default="Bad" />
		
	</label>
	<g:checkBox name="bad" value="${fileCommentInstance?.bad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'requestRemoval', 'error')} ">
	<label for="requestRemoval">
		<g:message code="fileComment.requestRemoval.label" default="Request Removal" />
		
	</label>
	<g:checkBox name="requestRemoval" value="${fileCommentInstance?.requestRemoval}" />
</div>

<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'shocking', 'error')} ">
	<label for="shocking">
		<g:message code="fileComment.shocking.label" default="Shocking" />
		
	</label>
	<g:checkBox name="shocking" value="${fileCommentInstance?.shocking}" />
</div>

<div class="fieldcontain ${hasErrors(bean: fileCommentInstance, field: 'file', 'error')} required">
	<label for="file">
		<g:message code="fileComment.file.label" default="File" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="file" name="file.id" from="${simplefile201209a.AvailableFile.list()}" optionKey="id" required="" value="${fileCommentInstance?.file?.id}" class="many-to-one"/>
</div>

