<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>树表控件</title>
        <link href="../treeTable/demo/style/demo.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        table,td,th {  border: 1px solid #8DB9DB; padding:5px; border-collapse: collapse; font-size:16px; }
        </style>

        <!--引用的文件 Begin-->
        <script type="text/javascript" src="../treeTable/script/jquery.js"></script>
        <script src="../treeTable/script/treeTable/jquery.treeTable.js" type="text/javascript"></script>
        <script type="text/javascript" src="../treeTable/script/treeTable/vsStyle/jquery.treeTable.css"></script>
        <!--引用的文件 End-->
        <script type="text/javascript">
        $(function(){
            var option = {
                theme:'vsStyle',
                expandLevel : 2,
                beforeExpand : function($treeTable, id) {
                	/* alert("456"); */
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) {   return; }
                    //这里的html可以是ajax请求
                    alert(id);
                    $.ajax({
            			url : 'areaListByAid',
            			type : 'get',
            			data : {
            				"aid" : id            			
            				},
            		success : function(result) {
            			$treeTable.addChilds(result);
            		}
            		});
                    
                    
                  /*   if(id == 1){
                     var html = '<tr id="8" pId="1"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
                             + '<tr id="9" pId="1"><td>5.2</td><td>动态的内容</td></tr>'; 
                    }
                    if(id == 2){
                    	 var html = '<tr id="10" pId="2"><td>7.1</td><td>可以是ajax请求来的内容</td></tr>'
                             + '<tr id="11" pId="2"><td>7.2</td><td>动态的内容</td></tr>'; 
                    } */
                    
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                    
                }

            };
            $('#treeTable1').treeTable(option);

            option.theme = 'default';
            $('#treeTable2').treeTable(option);
        });
        </script>
        <link id="tree_table_vsStyle" href="../treeTable/script/treeTable/vsStyle/jquery.treeTable.css" rel="stylesheet" type="text/css">
<link id="tree_table_default" href="../treeTable/script/treeTable/default/jquery.treeTable.css" rel="stylesheet" type="text/css">
<link id="SL_Style" rel="stylesheet" type="text/css" href="chrome://imtranslator/content/css/translator.css">
        
    </head>
    <body>
    <div id="page">
        <h1><span>treeTable <em>v 1.4.2</em></span></h1>
        <fieldset>
            
            <table id="treeTable2" style="width:100%">
                <tr>
                    <td style="width:200px;">标题</td>
                    <td>内容</td>
                </tr>
                <tr id="1">
                    <td><span controller="true">1</span></td>
                    <td>内容</td></tr>
                <tr id="2" pId="1">
                    <td><span controller="true">2</span></td>
                    <td>内容</td></tr>
                <tr id="3" pId="2">
                    <td>3</td>
                    <td>内容</td>
                </tr>
                <tr id="4" pId="2">
                    <td>4</td>
                    <td>内容</td>
                </tr>
               <!--  <tr id="5" pId="4">
                    <td>4.1</td>
                    <td>内容</td>
                </tr> -->
                <tr id="6" pId="1" hasChild="true">
                    <td>5</td>
                    <td>注意这个节点是动态加载的</td>
                </tr>
                <tr id="7" hasChild="true">
                    <td>8</td>
                    <td>内容</td>
                </tr>
            </table>
            
            <table id = 'treeTable1' class="table table-striped">
						<thead>
							<tr>
							<th>
									编号
								</th>
								<th>
								</th>
								
								<th>
									地区名称
								</th>
								<th>
									代码
								</th>
								<th>
									顺序
								</th>
								<th>
									地区分类
								</th>
								<th>
									父地区
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${areaMap.areaList}" var="var" varStatus="vs">
								<tr id="${var.aid }" hasChild="true">
								<td>
									${var.aid}
								</td>
								
								<td>
									<input type="checkbox" name="aids" id="aid" value="${var.aid }" class="checked-focus" />
								</td>
							
								<td>
									${var.aname}
								</td>
								<td>
									${var.code}
								</td>
								<td>
									${var.zonetype}
								</td>
								<td>
									${var.sortsum}
								</td>
								<td>
									${var.pid}
								</td>
								<td>
									<a href="updateAreaInput?aid=${var.aid}"><input type="button" class="btn btn-warning" value="编辑"></a>
								</td>
							</tr>
						</c:forEach>

						</tbody>
					</table>
            <hr/>
        </fieldset>
    </div>
    </body>
</html>
