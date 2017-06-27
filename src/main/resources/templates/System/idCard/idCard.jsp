<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>身份验证</title>
		<link rel="stylesheet" type="text/css" href="../crm/css/bootstrap.css" />
		<style type="text/css">
			body {
				margin: 30px;
			}
			*:focus{outline: none !important;}
		</style>
	</head>

	<body>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form action="${code.url}Dept" method="post">
				    <input name="did" type="text" hidden = "hidden"  value="${updateDept.did}"/>
					<div class="form-group">
						<label>真实姓名</label>
						<input name="deptname" type="text" class="form-control" />
					</div>
					<div class="form-group">
						<label>手机号</label>
						<input name="address" type="text" class="form-control"/>
					</div>
					<div class="form-group">
						<label>身份证号</label>
						<input name="telephone" type="text" class="form-control" />
					</div>
					
					<input type="submit" class="btn btn-primary" value="提交">
					<a href="<%=basePath%>${code.url }DeptInput?cid =${updateDept.cid}"><input type="button" class="btn btn-danger" value="取消"></a>
				</form>
			</div>
		</div>
	</body>

</html>