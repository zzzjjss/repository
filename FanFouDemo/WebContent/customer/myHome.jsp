<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navTop.jsp"%>

<div class="container">

    <!-- Docs nav
    ================================================== -->
    <div class="row-fluid" >
      <div class="span3 bs-docs-sidebar">
        <ul class="nav nav-list bs-docs-sidenav affix">
          <li class="active"><a href="myOrder.jsp" target="content"><i class="icon-chevron-right"></i> 饮食安排</a></li>
          <li><a href="#buttonGroups"><i class="icon-chevron-right"></i> 已完成订单</a></li>
          <li><a href="#buttonDropdowns"><i class="icon-chevron-right"></i> 我的账户</a></li>
          
        </ul>
      </div>
      
   <iframe class="span9 "
           
           id="content" style="border-width: 0;height: 800px" src="myOrder.jsp" >
   </iframe>
      
    </div>

  </div>

<script type="text/javascript">

$("#myHome").attr("class","active");

</script>
<%@ include file="bottom.jsp"%>