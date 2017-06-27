var controller = "shopAddressController";
var currentPages = 0;
var resultPages;
var resultRows;
var pageNum;
$(function(){
	
	findShodAddress(1,10);
});

function findShodAddress(page,rows){
	var trueName = "";
	if($("#trueName").val()!=""&&$("#trueName").val()!=null){
		trueName = $("#trueName").val();
	}
	$.ajax({
		type:"get",
		url:"../"+controller+"/jsonShopAddressList",
		async:true,
		data:{
			"page":page,
			"rows":rows,
			"trueName":trueName
		},
//		dataType:"JSONP",
		success:function(result){
			table(result.list);
            resultPages = result.pages;
            resultRows = result.pageSize;
            pageNum = result.pageNum;
			pages(resultPages,resultRows);
		},
		error:function(error){
			alert("访问服务器失败");
			alert(error);
		}
	});
}

function table(list) {
	var str = "";
	for(var i=0;i<list.length;i++){
        var obj = list[i];
        str += "<tr><td>" + obj.memberId + "</td>" +
			"<td><input type=\"checkbox\" /></td>" +
			"<td>" + obj.trueName+ "</td>"+
            "<td>" + obj.address + "</td>"+
            "<td>" + obj.areaInfo + "</td>"+
            "<td>" + obj.mobPhone + "</td>"+
            "<td>" + obj.telPhone + "</td>"+
            "<td><a href=\"javascript:deleteall()\"><input type=\"button\" class=\"btn btn-danger\" value=\"编辑\"/></a></td></tr>";
    }
    $("#tbody").html(str);
}

function pages(pages,rows) {
	var str = "";
	if(currentPages>0){
        str += "<li><a href=\"javascript:changePages(-1)\"><<</a></li>";
	}else {
        str += "<li><a href=\"#\"><<</a></li>";
    }
	for(var i=1;i<6;i++){
		var page = i+(5*currentPages);
		if(page<=pages){
			if(page==pageNum){
                str += "<li class=\"active\"><a href=\"javascript:findShodAddress("+page+","+rows+")\">"+page+"</a></li>";
			}else {
                str += "<li><a href=\"javascript:findShodAddress(" + page + "," + rows + ")\">" + page + "</a></li>";
            }
		}
	}
	if(currentPages<parseInt(pages/5)){
        str += "<li><a href=\"javascript:changePages(1)\">>></a></li>";
	}else {
        str += "<li><a href=\"#\">>></a></li>";
    }
	$('#page_ul').html(str);
}

function changePages(temp){
	if(temp==1){
		currentPages++;
		pages(resultPages,resultRows);
	}else if(temp==-1){
		currentPages--;
        pages(resultPages,resultRows);
	}
}

