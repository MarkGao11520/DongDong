function  obtainAreaByAName(){
	$("#datagrid").treegrid("reload",{
		"aname" : $("#aname").val(),
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
	window.location.href = "addAreaInput?zonetype="+row.zonetype+"&aname="+row.aname+"&aid="+row.aid+"";
}

function update(){
	var row = $("#datagrid").datagrid("getSelected");
	aid = row.aid;
	window.location.href = "updateAreaInput?aid="+aid+"";
}

function option(){
	var html = "";
	html += "<a href=\"javascript:add()\">";
	html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"添加\"></a>";
	html += "<a href=\"javascript:update()\">";
	html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"编辑\"></a>";
	return html;
}

function checkboxs(aid){
	return "<input type=\"checkbox\" name=\"aids\" id=\"aid\" value=\""+aid+"\" class=\"checked-focus\" />";
}
	function deleteall() {
		var str = '';
		for (var i = 0; i < document.getElementsByName('aids').length; i++) {
			if (document.getElementsByName('aids')[i].checked) {
				//				  alert(document.getElementsByName('ids')[i].value);
				if (str == '')
					str += document.getElementsByName('aids')[i].value;
				else
					str += ',' + document.getElementsByName('aids')[i].value;
			}
		}
		if (str == '') {
			alert("您没有选择任何数据");
		} else {
			$.ajax({
				url : 'deleteArea',
				type : 'get',
				data : {
					"allarea" : str
				},
				success:function(result){
					alert(result);
					window.location.href = "../AreaManagent/areaList";
				}
			});
			//
		}
	}

function url1(aid){
	return 'jsonAreaList?type=2&aid='+aid;
}
function add1(){
    window.location.href = "addAreaInput?zonetype=1&code=1";

}