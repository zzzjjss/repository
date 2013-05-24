<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navTop.jsp"%>

<div class="container">

    <div class="row-fluid" >
      <div class="span3 bs-docs-sidebar">
        <ul class="nav nav-list bs-docs-sidenav affix">
          <li id="productManage" class="active" ><a href="productManage.jsp" target="content"><i class="icon-chevron-right"></i> 菜谱管理</a></li>
          <li id="currentProductManage"><a href="currentProductManage.jsp" target="content"><i class="icon-chevron-right"></i> 本期菜谱管理</a></li>
          <li id="agentManage"><a href="agentManage.jsp" target="content"><i class="icon-chevron-right"></i>代理点管理 </a></li>
          
        </ul>
      </div>
      
   <iframe class="span9 "
           
           id="content" style="border-width: 0;" src="productManage.jsp" onload="selfAdjustHeight(this)">
   </iframe>
      
    </div>

  </div>

<script type="text/javascript">
function selfAdjustHeight(win){

	if (win.contentDocument && win.contentDocument.body.offsetHeight) {
		win.height = win.contentDocument.body.offsetHeight+100;
	}else if(win.Document && win.Document.body.scrollHeight){
		win.height = win.Document.body.scrollHeight;
	} 
}

$("#index").attr("class","active");

$("li").click(function(){
	$("li.active").removeAttr("class");
	$("#index").attr("class","active");
	this.setAttribute("class","active");
	
} );
</script>
<%@ include file="bottom.jsp"%>