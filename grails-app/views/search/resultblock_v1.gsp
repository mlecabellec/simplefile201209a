<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div class="fileresultblock">
  <g:each var="file" in="${files}">
<g:include action="rendersinglefileresult" id="${file.id}" />
  </g:each>
</div>
<div class="commentresultblock">  
  <g:each var="comment" in="${comments}">
    <g:include action="rendersinglecommentresult" id="${comment.id}" />
  </g:each>
</div>
<div class="tagresultblock">  
  <g:each var="tag" in="${tags}">
    <g:include action="rendersingletagresult" id="${tag.id}" />
  </g:each>
</div>
