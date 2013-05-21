<%@ page import="simplefile201209a.AvailableFile" %>



<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'fileName', 'error')} ">
	<label for="fileName">
		<g:message code="availableFile.fileName.label" default="File Name" />
		
	</label>
	<g:textArea name="fileName" cols="40" rows="5" maxlength="1024" value="${availableFileInstance?.fileName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'mimeType', 'error')} ">
	<label for="mimeType">
		<g:message code="availableFile.mimeType.label" default="Mime Type" />
		
	</label>
	<g:textField name="mimeType" maxlength="128" readonly="readonly" value="${availableFileInstance?.mimeType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="availableFile.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" type="number" min="0" value="${availableFileInstance.size}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'downloadCount', 'error')} required">
	<label for="downloadCount">
		<g:message code="availableFile.downloadCount.label" default="Download Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="downloadCount" type="number" min="0" value="${availableFileInstance.downloadCount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'hitCount', 'error')} required">
	<label for="hitCount">
		<g:message code="availableFile.hitCount.label" default="Hit Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="hitCount" type="number" min="0" value="${availableFileInstance.hitCount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'downloadRate1', 'error')} required">
	<label for="downloadRate1">
		<g:message code="availableFile.downloadRate1.label" default="Download Rate1" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="downloadRate1" type="number" min="1" value="${availableFileInstance.downloadRate1}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'downloadRate2', 'error')} required">
	<label for="downloadRate2">
		<g:message code="availableFile.downloadRate2.label" default="Download Rate2" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="downloadRate2" type="number" min="1" value="${availableFileInstance.downloadRate2}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'damaged', 'error')} ">
	<label for="damaged">
		<g:message code="availableFile.damaged.label" default="Damaged" />
		
	</label>
	<g:checkBox name="damaged" value="${availableFileInstance?.damaged}" />
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'processed', 'error')} ">
	<label for="processed">
		<g:message code="availableFile.processed.label" default="Processed" />
		
	</label>
	<g:checkBox name="processed" value="${availableFileInstance?.processed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'locked', 'error')} ">
	<label for="locked">
		<g:message code="availableFile.locked.label" default="Locked" />
		
	</label>
	<g:checkBox name="locked" value="${availableFileInstance?.locked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'available', 'error')} ">
	<label for="available">
		<g:message code="availableFile.available.label" default="Available" />
		
	</label>
	<g:checkBox name="available" value="${availableFileInstance?.available}" />
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'shoking', 'error')} ">
	<label for="shoking">
		<g:message code="availableFile.shoking.label" default="Shoking" />
		
	</label>
	<g:checkBox name="shoking" value="${availableFileInstance?.shoking}" />
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'lastDownload', 'error')} required">
	<label for="lastDownload">
		<g:message code="availableFile.lastDownload.label" default="Last Download" />
		<span class="required-indicator">*</span>
	</label>
	${availableFileInstance?.lastDownload?.toString()}
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="availableFile.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${availableFileInstance?.comments?}" var="c">
    <li><g:link controller="fileComment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fileComment" action="create" params="['availableFile.id': availableFileInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fileComment.label', default: 'FileComment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="availableFile.tags.label" default="Tags" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: availableFileInstance, field: 'thumbnails', 'error')} ">
	<label for="thumbnails">
		<g:message code="availableFile.thumbnails.label" default="Thumbnails" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${availableFileInstance?.thumbnails?}" var="t">
    <li><g:link controller="thumbnail" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="thumbnail" action="create" params="['availableFile.id': availableFileInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'thumbnail.label', default: 'Thumbnail')])}</g:link>
</li>
</ul>

</div>

