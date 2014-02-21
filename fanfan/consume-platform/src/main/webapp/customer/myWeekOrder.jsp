<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ include file="../index/jsAndCSS.jsp"%>
<style>
body {
	padding-top: 10px;
	padding-bottom: 40px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<div class="container">

	<ul class="thumbnails ">
		<li class="span4 ">
			<div class="thumbnail" style="background-color: #EEEEEE;"  id="day1">
				<a > <img src="../img/a.jpg">
				
				</a>
				<h3 align="center">星期天</h3>
				<p align="center">
				<c:forEach items="${SUNDAY}" var="tradeDetail">
					<span>${tradeDetail.productid}</span>
				</c:forEach>
				<button  type="button" class="btn disabled">完成配送</button>
				</p>
				
			</div>
		</li>
		<li class="span4 " >
			<div class="thumbnail" style="background-color: #EEEEEE;" id="day2">
				<a > <img src="../img/a.jpg">
				</a>
				<h3 align="center">星期一</h3>
				<p align="center">
				<button  type="button" class="btn disabled">完成配送</button>
				</p>
				
			</div>
		</li>
		<li class="span4 " >
			<div class="thumbnail" style="background-color: #EEEEEE;" id="day3">
				<a > <img src="../img/noTrade.jpg">
				</a>
				<h3 align="center">星期二</h3>
				<p align="center">
				<button  type="button" class="btn disabled">没有订餐</button>
				</p>
				
			</div>
		</li>
		<li class="span4 " >
			<div class="thumbnail" style="background: lime;" id="day4">
				<a > <img src="../img/a.jpg">
				</a>
				<h3 align="center">星期三 
				</h3>
				<p align="center">
				<button  type="button" class="btn btn-primary">取消订餐</button>
				</p>
			</div>
		</li>
		<li class="span4 " >
			<div class="thumbnail" style="background:lime;" id="day5">
				<a > <img src="../img/trade.jpg">
				</a>
				<h3 align="center">星期四</h3>
				<p align="center">
				<button  type="button" class="btn btn-primary">订餐</button>
				</p>
			</div>
		</li>
		<li class="span4 " >
			<div class="thumbnail" style="background:lime;"  id="day6">
				<a > <img src="../img/trade.jpg">
				</a>
				<h3 align="center">星期五</h3>
				<p align="center">
				<button  type="button" class="btn btn-primary">订餐</button>
				</p>
			</div>
		</li>
			<li class="span4 " >
			<div class="thumbnail" style="background:lime;" id="day7">
				<a > <img src="../img/trade.jpg">
				</a>
				<h3 align="center">星期六</h3>
				<p align="center">
				<button  type="button" class="btn btn-primary">订餐</button>
				</p>
			</div>
		</li>
		
	</ul>
</div>

<script type="text/javascript">
	var today=${today};
	for(var i=1;i<today;i++){
		$("#day"+i).attr("style","background-color: #EEEEEE");
	}
</script>

<%@ include file="../index/bottom.jsp"%>