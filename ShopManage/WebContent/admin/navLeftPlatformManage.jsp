<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="jsAndCSS.jsp"%>

<div class="container">

    <div class="row-fluid" >
      <div class="span3 bs-docs-sidebar">
        <ul class="nav nav-list bs-docs-sidenav affix">
          <li id="agentManage" class="active"><a href="agentManage.jsp" target="content"><i class="icon-chevron-right"></i>代理点管理 </a></li>
          <li id="agentManage"><a href="deliverymanManage.jsp" target="content"><i class="icon-chevron-right"></i>配送员管理 </a></li>
          <li id="agentManage"><a href="moneyManage.jsp" target="content"><i class="icon-chevron-right"></i>账款管理 </a></li>
        </ul>
      </div>
      
    </div>

  </div>

<script type="text/javascript">



$("li").click(function(){
	$("li.active").removeAttr("class");
	this.setAttribute("class","active");
	
} );
</script>
