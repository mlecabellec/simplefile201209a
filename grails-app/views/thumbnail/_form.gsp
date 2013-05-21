<%@ page import="simplefile201209a.Thumbnail" %>



<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'mimeType', 'error')} required">
	<label for="mimeType">
		<g:message code="thumbnail.mimeType.label" default="Mime Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="mimeType" from="${thumbnailInstance.constraints.mimeType.inList}" required="" value="${thumbnailInstance?.mimeType}" valueMessagePrefix="thumbnail.mimeType"/>
</div>

<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'thumbData', 'error')} required">
	<label for="thumbData">
		<g:message code="thumbnail.thumbData.label" default="Thumb Data" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="thumbData" name="thumbData" />
</div>

<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'height', 'error')} required">
	<label for="height">
		<g:message code="thumbnail.height.label" default="Height" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="height" type="number" min="2" max="512" value="${thumbnailInstance.height}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'width', 'error')} required">
	<label for="width">
		<g:message code="thumbnail.width.label" default="Width" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="width" type="number" min="2" max="512" value="${thumbnailInstance.width}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'videoTimeOffset', 'error')} required">
	<label for="videoTimeOffset">
		<g:message code="thumbnail.videoTimeOffset.label" default="Video Time Offset" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="videoTimeOffset" type="number" min="0" value="${thumbnailInstance.videoTimeOffset}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: thumbnailInstance, field: 'file', 'error')} required">
	<label for="file">
		<g:message code="thumbnail.file.label" default="File" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="file" name="file.id" from="${simplefile201209a.AvailableFile.list()}" optionKey="id" required="" value="${thumbnailInstance?.file?.id}" class="many-to-one"/>
</div>

