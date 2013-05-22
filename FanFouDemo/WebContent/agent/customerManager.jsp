<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/jsAndCSS.jsp"%>
<style>
body {
	padding-top: 10px;
	padding-bottom: 40px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<div class="container">
  
	<table class="table table-hover ">
              <thead>
                <tr >
                  <th colspan="4"  style="text-align: left;">
      					<form class="navbar-search pull-left">
  <input type="text" class="search-query" placeholder="客户名" style="height: 30;">
</form><button class="btn" style=";margin-top:5">查询</button>
                  </th>
                  <th colspan="2" style="text-align: right;">
                  
                  </th>
                </tr>
                <tr >
                  <th class="span1">#</th>
                  <th class="span2">
                  	客户名<i class=" icon-arrow-up"></i>
                  
                  </th>
                  <th class="span2">      创建时间</th>
                   <th class="span2">总消费金额</th>
                  <th class="span2">当前余额</th>
                   <th class="span3">
             <button class="btn" style="width: 110">新增用户</button>
                   </th>
                </tr>
              
              </thead>
              <tbody>
                <tr>
                  <td>
                  	1
                  </td>
                  <td align="left">
                  	zhangjinsheng
                  </td>
                  <td valign="middle">2013-01-01</td>
                  <td>￥1200</td>
                   <td>
        				￥90
                   </td>
                   <td>
        				<button class="btn " > 删除</button>
        				<button class="btn " > 充值</button>
                   </td>
                </tr>
               <tr>
                  <td>
                  	1
                  </td>
                  <td align="left">
                  	zhangjinsheng
                  </td>
                  <td valign="middle">2013-01-01</td>
                  <td>￥1200</td>
                   <td>
        				￥90
                   </td>
                   <td>
        				<button class="btn" > 删除</button>
        				<button class="btn" > 充值</button>
                   </td>
                </tr>
               
                
               
                
              </tbody>
            </table>
            <div class="pagination pagination-large  " align="center">
		<ul>
			<li><a href="#"><span>&laquo;</span></a></li>
			<li class="active"><a href="#">1</a></li>
			<li class="active"><a href="#">2</a></li>
			<li class="active"><a href="#">3</a></li>
			<li><a href="#"><span>&raquo;</span></a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
 $("tr").dblclick(function(){
	 alert(this.id)
 });
 $("button.btn").click(function(){
	 
	 alert("hello");
 });
	
</script>

<%@ include file="../common/bottom.jsp"%>