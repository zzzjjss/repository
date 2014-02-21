<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../index/jsAndCSS.jsp"%>
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
                <tr>
                  <th class="span2">#</th>
                  <th class="span4">菜名</th>
                   <th class="span2">价格</th>
                  <th class="span2">配送日期</th>
                   <th class="span2">评价</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>
                  	<a href="#">
                  		<img alt="" src="../img/a.mini.jpg">
                  	</a>
                  </td>
                  <td align="left">
                  	大虾鸡翅饭
                  </td>
                  <td valign="middle">10元</td>
                  <td>2013-01-01</td>
                   <td>
        
                   </td>
                </tr>
                <tr >
                  <td >
                  	<a href="#">
                  		<img alt="" src="../img/a.mini.jpg">
                  	</a>
                  </td>
                  <td align="left">
                  	大虾鸡翅饭
                  </td>
                  <td valign="middle">10元</td>
                  <td>2013-01-01</td>
                   <td>
        
                   </td>
                </tr>
                 <tr >
                  <td>
                  	<a href="#">
                  		<img alt="" src="../img/a.mini.jpg">
                  	</a>
                  </td>
                  <td align="left">
                  	大虾鸡翅饭
                  </td>
                  <td valign="middle">10元</td>
                  <td>2013-01-01</td>
                   <td>
        
                   </td>
                </tr>
                 <tr>
                  <td>
                  	<a href="#">
                  		<img alt="" src="../img/a.mini.jpg">
                  	</a>
                  </td>
                  <td align="left">
                  	大虾鸡翅饭
                  </td>
                  <td valign="middle">10元</td>
                  <td>2013-01-01</td>
                   <td>
        
                   </td>
                </tr>
                 <tr>
                  <td>
                  	<a href="#">
                  		<img alt="" src="../img/a.mini.jpg">
                  	</a>
                  </td>
                  <td align="left">
                  	大虾鸡翅饭
                  </td>
                  <td valign="middle">10元</td>
                  <td>2013-01-01</td>
                   <td>
        
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
 $("tr").click(function(){
	 alert(this.id)
 });
	
</script>

<%@ include file="../index/bottom.jsp"%>