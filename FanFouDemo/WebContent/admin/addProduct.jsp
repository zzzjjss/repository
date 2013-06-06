<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="jsAndCSS.jsp"%>
</head>
<body>



	<div class="container well row">
		
		
			<form class="form-horizontal ">
				<div class="control-group">
						<label class="control-label" for="uploadFile">图片</label>
						<div class="controls">
							<input type="file" name="uploadImg" id="uploadFile">
						</div>
				</div>
					
					<div class="control-group">
						<label class="control-label" for="inputEmail">Email</label>
						<div class="controls">
							<input type="text" id="inputEmail" placeholder="Email">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">Password</label>
						<div class="controls">
							<input type="password" id="inputPassword" placeholder="Password">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">

							<button type="submit" class="btn">确定</button>
						</div>
					</div>
				</form>
			
		

	</div>
</body>
</html>
