function  obtainOrgByAName(){
	$("#datagrid").treegrid("reload",{
		"cname" : $("#cname").val(),
	});
}

function format(zonetype) {
	if(zonetype == 1){
		return "省";
	}else if(zonetype == 2){
		return "市";
	}else if(zonetype == 3){
		return "县";
	}
}

function add(){
	var row = $("#datagrid").datagrid("getSelected");
	window.location.href = "addCompanyInput?cid="+row.cid+"&name="+row.name+"&treeorder=2";
}

function update(){
	var row = $("#datagrid").datagrid("getSelected");
	cid = row.cid;
	window.location.href = "updateCompanyInput?cid="+cid+"";
}

function config(){
	var row = $("#datagrid").datagrid("getSelected");
	window.location.href = "../configController/ConfigList?cid="+row.cid+"";
}

function parame(){
	var row = $("#datagrid").datagrid("getSelected");
	cid = row.cid;
	window.location.href = "../parameController/ParameList?cid="+cid+"";
}

function option(treeorder){
	var html = "";
	if(treeorder<=1) {
		html += "<a href=\"javascript:add()\">";
		html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"添加\"></a>";
	}
	html += "<a href=\"javascript:update()\">";
	html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
	html += "<a href=\"javascript:config()\">";
	html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"系统设置\"></a>";
	html += "<a href=\"javascript:parame()\">";
	html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"参数设置\"></a>";
	return html;
}

function checkboxs(cid){
	return "<input type=\"checkbox\" name=\"cids\" id=\"cid\" value=\""+cid+"\" class=\"checked-focus\" />";
}

	function deleteall(){
		var str = '';
		for(var i=0;i < document.getElementsByName('cids').length;i++)
		{
			if(document.getElementsByName('cids')[i].checked){
//			  alert(document.getElementsByName('ids')[i].value);
				if(str=='') str += document.getElementsByName('cids')[i].value;
				else str += ',' + document.getElementsByName('cids')[i].value;
			}
		}
		//alert(str);
		if(str==''){
			alert("您没有选择任何数据");
		}else{
			$.ajax({
				url : 'deleteComp',
				type : 'post',
				data : {
					"allcomp" : str
				},
				success:function(result){
					alert(result);
					window.location.href="../CompanyManagent/companyList";
				}
			});

		}
	}



function url2(cid) {
	return "jsonOrgList?type=2&cid="+cid;
}

function url3(code,aid) {
	if(aid == null){
		return "JsonAreaList?code=" + code;
	}else {
		return "JsonAreaList?code=" + code + "&aid=" + aid;
	}
}

function url4(code,aid,pid) {
	if(aid!=null&&pid!=null) {
		return "JsonAreaList?code=" + code + "&aid=" + aid + "&pid=" + pid;
	}else if(aid!=null&&pid==null){
		return "JsonAreaList?code=" + code + "&aid=" + aid;
	}else if(aid==null&&pid!=null){
		return "JsonAreaList?code=" + code + "&pid=" + pid;
	}else{
		return "JsonAreaList?code=" + code
	}
}