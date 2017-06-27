function url1(code,cid) {
	if(code!=null){
		return "../UserManagent/JsonList?type=1&code="+code+"&cid="+cid;
	}else {
		return "../UserManagent/JsonList?type=1&cid="+cid;
	}
}

function url2(code,did,cid) {
	if(did!=null) {
		return '../UserManagent/JsonList?type=2&code=' + code + '&did=' + did + '&cid=' + cid;
	}else {
		return '../UserManagent/JsonList?type=2&code=' + code + '&cid=' + cid;
	}
}

function url3(cid,did){
	return '../PositionController/positionList?cid='+cid+'&did='+did;
}

function url4(cid){
	return '../PositionController/positionMain?type=3&cid='+cid;
}

function url5(cid,did,positionid) {
	return 'jsonpositionList?type=2&cid='+cid+'&did='+did+'&ppositionid='+positionid;
}

function url6(cid,did) {
	return 'jsonpositionList?type=2&cid='+cid+'&did='+did;
}

function url7(cid,did) {
	return 'addPositionInput?cid='+cid+'&did='+did;
}

function url8(cid,did,positionid) {
	return "addPositionInput?cid="+cid+"&did="+did+"&ppositionid="+positionid+""
}

function  obtainPositionBypositionName(){
	$("#datagrid").treegrid("reload",{
		"positionName" : $("#positionName").val(),
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



function update(){
	var row = $("#datagrid").datagrid("getSelected");
	positionid = row.positionid;
	window.location.href = "updatePositionInput?positionid="+positionid+"";
}

function auth(){
	var row = $("#datagrid").datagrid("getSelected");
	window.location.href = "../modController/modMain?type=1&positionid="+row.positionid+"&code=2";
}


function option(){
	var html = "";
	html +=   html += "<a href=\"javascript:add()\">";
	html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"添加\" /></a>  ";
	html += "<a href=\"javascript:update()\">";
	html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"编辑\" /></a>  ";
	html += "<a href=\"javascript:auth()\">";
	html += "<input type=\"button\"  class=\"btn btn-denger\"  value=\"授权\"></a>";
	return html;
}

function checkboxs(positionid){
	return "<input type=\"checkbox\" name=\"positionids\" id=\"positionids\" value=\""+positionid+"\" class=\"checked-focus\" />";
}
	function deleteall(cid,did){
		var str = '';
		for(var i=0;i < document.getElementsByName('positionids').length;i++)
		{
			if(document.getElementsByName('positionids')[i].checked){
//			  alert(document.getElementsByName('ids')[i].value);
				if(str=='') str += document.getElementsByName('positionids')[i].value;
				else str += ',' + document.getElementsByName('positionids')[i].value;
			}
		}
		//alert(str);
		if(str==''){
			alert("您没有选择任何数据");
		}else{
			$.ajax({
				url : 'deletePosition',
				type : 'post',
				cache : false,
				data : {
					"allpositionid" : str
				},
				success:function(result){
					alert(result);
					window.location.href="../PositionController/positionList?cid="+cid+"&did="+did;
				}
			});

		}
	}