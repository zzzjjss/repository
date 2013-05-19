<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="navTop.jsp"%>

<div class="container">
	<ul class="thumbnails">
		<li class="span4 ">
			<div class="thumbnail">
				<a href="#"> <img src="../img/a.jpg">
				</a>
				<div class="caption">
					<h3>$10</h3>
					<dl>
						<dt>简介：</dt>
						<dd>好的顶顶dd</dd>
					</dl>
					<p>
						<a href="#" class="btn btn-primary">订购</a>
					</p>
				</div>
			</div>
		</li>
	</ul>
</div>
<div class="navbar navbar-inverse navbar-fixed-bottom">
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

$("#index").attr("class","active");

</script>

<%@ include file="bottom.jsp"%>