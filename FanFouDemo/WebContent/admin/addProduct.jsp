<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="PM_addProduct.action" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <td>name:</td>  
                <td><input type="text" name="name" ></td>  
            </tr>  
             <tr>  
                <td>price:</td>  
                <td><input type="text" name="price" ></td>  
            </tr>  
             <tr>  
                <td>description:</td>  
                <td><input type="text" name="description" ></td>  
            </tr>  
            <tr>  
                <td>上传文件:</td>  
                <td><input type="file" name="uploadImg"></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value="上传"></td>  
                <td><input type="reset"></td>  
            </tr>  
        </table>  
      </form>  
</body>
</html>