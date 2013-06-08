<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="jsAndCSS.jsp"%>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<style>
body {
	padding-top: 30px;
	padding-bottom: 40px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
</head>
<body>



	<div class="container well center">


		<div class="form-horizontal "   >
			<div class="control-group">
				<label class="control-label" for="uploadImg">图片</label>
				<div class="controls">
					<input type="file" name="uploadImg" id="uploadImg">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="productName">菜名</label>
				<div class="controls">
					<input type="text" id="productName" placeholder="菜名" name="name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="price">价格</label>
				<div class="controls">
					<input type="text" id="price" placeholder="0.00" name="price">
				</div>

			</div>
			<div class="control-group">
				<label class="control-label" for="description">描述</label>
				<div class="controls">
					<textarea  name="description" id="description" placeholder="描述......."></textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">

					<button class="btn" id="addProductBtn" onclick="addProduct()">添加</button>
				</div>
			</div>
	</div>
	<div class="container well">

		<table class="table table-hover ">
			<thead>
				<tr>
					<th class="span2">#</th>
					<th class="span4">菜名</th>
					<th class="span2">价格</th>
					<th class="span2">描述</th>

				</tr>
			</thead>
			<tbody id="addedProduct">

			</tbody>
		</table>

	</div>
	</div>
</body>
<script type="text/javascript">
	function addProduct(){
			$("#addProductBtn").addClass("disabled");		
			var name=$("#productName").val();
			var price=$("#price").val();
			var des=$("#description").val();
			$.ajaxFileUpload
			(
				{
					url:'PM_addProduct.action',
					secureuri:false,
					fileElementId:'uploadImg',
					data:{name:name,price:price,description:des},
					dataType: 'xml',
					
					complete:function()
					{
						$("#loading").hide();
						
					},				
					success: function (data, status)
					{
						if(typeof(data.error) != 'undefined')
						{	
								alert(data.error);
								
						}else{
							var resobj = $(data);
							if(resobj.find("error")==null){
								alert("添加出错，原因："+resobj.find("error").text);	
								return ;
							}else{
								//can optimize,the valuc can get from page
								var imgPath=resobj.find("imgPath").text();
								var name=resobj.find("name").text();
								var price resobj.find("price").text();
								var des=resobj.find("des").text();
								var result = $("#addedProduct");
								result.html("<tr><td><img src='"+imgPath+"'></td><td>"+name+"</td><td>"+price+"</td><td>"+des+"</td></tr>");
								 
								 	
							}
							$("#addProductBtn").removeClass("disabled");
							 //clean the form.
						}
					},
					error: function (data, status, e)
					{
						alert(e);
					}
				}
			)
			
			return false;

		
		
	}
	
</script>
</html>
