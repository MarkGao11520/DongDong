var controller = "shopAdvPositionController";  //Controller名
var currentPages = 0;  //当前分页组
var resultPages;  //总页数
var resultRows;   //行数
var pageNum;      //当前页
var total;        //总数
var list;         //数据
$(function(){
	findShopAdvPosition(1,10);   //初始化查询
});

function findShopAdvPosition(page,rows){   //获取广告位列表
	var apName = "";  //查询条件
	if($("#apName").val()!=""&&$("#apName").val()!=null){
        apName = $("#apName").val();
	}
	$.ajax({
		type:"get",
		url:"../"+controller+"/jsonShopAdvPositionList",
		async:true,
		data:{
			"page":page,
			"rows":rows,
			"apName":apName
		},
//		dataType:"JSONP",
		success:function(result){
			table(result.list);
            resultPages = result.pages;
            resultRows = result.pageSize;
            pageNum = result.pageNum;
            total = result.total;
            list = result.list;
			pages(resultPages,resultRows);
		},
		error:function(error){
			alert("访问服务器失败");
			alert(error);
		}
	});
}

function table(list) {   //将数据添加到表格
	var str = "";
	for(var i=0;i<list.length;i++){
        var obj = list[i];
        str += "<tr id=\"advp_"+obj.id+"\"><td>" + obj.id + "</td>" +
			"<td><input name=\'advPid\' value=\'"+obj.id+"\' type=\"checkbox\" /></td>" +
			"<td>" + obj.apName+ "</td>"+
            "<td>" + obj.apIntro + "</td>"+
            "<td>" + formatClass(obj.apClass) + "</td>"+
            "<td>" + formatDisplay(obj.apDisplay)+ "</td>"+
            "<td>" + formatIsUse(obj.isUse) + "</td>"+
            "<td>" + obj.apWidth + "</td>"+
            "<td>" + obj.apHeight + "</td>"+
            "<td>" + obj.apKey + "</td>"+
            "<td><a href=\"javascript:updateShopAdvPosition("+obj.id+")\"><input type=\"button\" class=\"btn btn-danger\" value=\"编辑\"/></a></td></tr>";
    }
    $("#tbody").html(str);
}

function formatClass(apClass) {      //类型数字转文字
	switch(apClass){
		case 0:
			return "图片";
		case 1:
			return "文字";
		case 2:
			return "幻灯片";
		case 3:
			return "Flash";
	}
}

function formatDisplay(apDisplay) {  //展示方式数字转文字
    switch(apDisplay){
        case 0:
            return "幻灯片";
        case 1:
            return "多广告展示";
        case 2:
            return "单广告展示";
    }
}

function formatIsUse(isUse) {   //是否启用数字转文字
    switch(isUse){
        case 0:
            return "不启用";
        case 1:
            return "启用";
    }
}

function pages(pages,rows) {   //分页
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
                str += "<li class=\"active\"><a href=\"javascript:findShopAdvPosition("+page+","+rows+")\">"+page+"</a></li>";
			}else {
                str += "<li><a href=\"javascript:findShopAdvPosition(" + page + "," + rows + ")\">" + page + "</a></li>";
            }
		}
	}
	if(currentPages<parseInt(resultPages/5)){
        str += "<li><a href=\"javascript:changePages(1)\">>></a></li>";
	}else {
        str += "<li><a href=\"#\">>></a></li>";
    }
	$('#page_ul').html(str);
}

function changePages(temp){   //翻页
	if(temp==1){
		currentPages++;
		pages(resultPages,resultRows);
	}else if(temp==-1){
		currentPages--;
        pages(resultPages,resultRows);
	}
}

function addShopAdvPosition() {   //显示添加广告位可编辑行
    var str = "";
        str += "<tr><td></td>" +
            "<td></td>" +
            "<td><input id=\'addColumApName\' type=\"text\"  style=\'height: 30px;width: 100px\'/></td>"+
            "<td><input id=\'addColumApIntro\' type=\"text\"  style=\'height: 30px;width: 100px\'/></td>"+
            "<td><select id=\'addColumClass\' style=\'height: 30px;width: 100px\'><option value=\'0\'>图片</option><option value=\'1\'>文字</option><option value=\'2\'>幻灯片</option><option value=\'3\'>Flash</option></select></td>"+
            "<td><select id=\'addColumDisplay\' style=\'height: 30px;width: 100px\'><option value=\'0\'>幻灯片</option><option value=\'1\'>多广告展示</option><option value=\'2\'>单广告展示</option></select></td>"+
            "<td><select id=\'addColumIsUse\' style=\'height: 30px;width: 100px\'><option value=\'1\'>启用</option><option value=\'0\'>不启用</option></select></td>"+
            "<td><input id=\'addColumApWidth\' type=\"number\"  style=\'height: 30px;width: 100px\'/></td>"+
            "<td><input id=\'addColumApHeight\' type=\"number\"  style=\'height: 30px;width: 100px\'/></td>"+
            "<td><input id=\'addColumApKey\' type=\"text\"  style=\'height: 30px;width: 100px\'/></td>"+
            "<td><a href=\"javascript:add()\"><input type=\"button\" class=\"btn btn-primary\" value=\"添加\"/></a></td></tr>";
    $("#tbody").append(str);
}

function updateShopAdvPosition(id) {

    for(var i=0;i<list.length;i++){
        if(id == list[i].id){
            var obj =list[i];
            var str = "";
            str += "<td></td>" +
                "<td></td>" +
                "<td><input id=\'updateColumApName\' value=\""+obj.apName+"\" type=\"text\"  style=\'height: 30px;width: 100px\'/></td>"+
                "<td><input id=\'updateColumApIntro\' value=\""+obj.apIntro+"\" type=\"text\"  style=\'height: 30px;width: 100px\'/></td>";
            switch(obj.apClass){
                case 0:
                    str += "<td><select id=\'updateColumClass\'  style=\'height: 30px;width: 100px\'><option value=\'0\'>图片</option><option value=\'1\'>文字</option><option value=\'2\'>幻灯片</option><option value=\'3\'>Flash</option></select></td>";
                    break;
                case 1:
                    str += "<td><select id=\'updateColumClass\'  style=\'height: 30px;width: 100px\'><option value=\'1\'>文字</option><option value=\'0\'>图片</option><option value=\'2\'>幻灯片</option><option value=\'3\'>Flash</option></select></td>";
                    break;
                case 2:
                    str += "<td><select id=\'updateColumClass\'  style=\'height: 30px;width: 100px\'><option value=\'2\'>幻灯片</option><option value=\'1\'>文字</option><option value=\'0\'>图片</option><option value=\'3\'>Flash</option></select></td>";
                    break;
                case 3:
                    str += "<td><select id=\'updateColumClass\'  style=\'height: 30px;width: 100px\'><option value=\'3\'>Flash</option><option value=\'1\'>文字</option><option value=\'0\'>图片</option><option value=\'2\'>幻灯片</option></select></td>";
                    break;
            }
            switch(obj.apDisplay){
                case 0:
                    str += "<td><select id=\'updateColumDisplay\' style=\'height: 30px;width: 100px\'><option value=\'0\'>幻灯片</option><option value=\'1\'>多广告展示</option><option value=\'2\'>单广告展示</option></select></td>"
                    break;
                case 1:
                    str += "<td><select id=\'updateColumDisplay\' style=\'height: 30px;width: 100px\'><option value=\'1\'>多广告展示</option><option value=\'0\'>幻灯片</option><option value=\'2\'>单广告展示</option></select></td>"
                    break;
                case 2:
                    str += "<td><select id=\'updateColumDisplay\' style=\'height: 30px;width: 100px\'><option value=\'2\'>单广告展示</option><option value=\'0\'>幻灯片</option><option value=\'1\'>多广告展示</option></select></td>"
                    break;
            }
            switch(obj.isUse){
                case 1:
                    str += "<td><select id=\'updateColumIsUse\' style=\'height: 30px;width: 100px\'><option value=\'1\'>启用</option><option value=\'0\'>不启用</option></select></td>"
                    break;
                case 0:
                    str += "<td><select id=\'updateColumIsUse\' style=\'height: 30px;width: 100px\'><option value=\'0\'>不启用</option><option value=\'1\'>启用</option></select></td>"
                    break;
            }
            str +="<td><input id=\'updateColumApWidth\' value=\""+obj.apWidth+"\" type=\"number\"  style=\'height: 30px;width: 100px\'/></td>"+
                "<td><input id=\'updateColumApHeight\' value=\""+obj.apHeight+"\" type=\"number\"  style=\'height: 30px;width: 100px\'/></td>"+
                "<td><input id=\'updateColumApKey\' value=\""+obj.apKey+"\" type=\"text\"  style=\'height: 30px;width: 100px\'/></td>"+
                "<td><a href=\"javascript:update("+id+")\"><input type=\"button\" class=\"btn btn-primary\" value=\"编辑\"/></a></td>";
            $('#advp_'+obj.id).html(str);
            return;
        }
    }

}

function add() {  //添加广告位
	if(check('add')){
        $.ajax({
            type:"get",
            url:"../"+controller+"/addAdvPositionPage",
            async:true,
            data:{
                "apName":$('#addColumApName').val(),
                "apIntro":$('#addColumApIntro').val(),
                "apClass":$('#addColumClass').val(),
                "apDisplay":$('#addColumDisplay').val(),
                "isUse":$('#addColumIsUse').val(),
                "apWidth":$('#addColumApWidth').val(),
                "apHeight":$('#addColumApHeight').val(),
                "apKey":$('#addColumApKey').val()
            },
//		dataType:"JSONP",
            success:function(result){
            	if(result==1){
                    alert("添加成功");
                    if(total%10!=0){
                        findShopAdvPosition(resultPages,10);
                    }else {
                        findShopAdvPosition(resultPages+1,10);
                    }
				}else{
                    alert("添加失败");
				}
            },
            error:function(error){
                alert("访问服务器失败");
                alert(error);
            }
        });
	}
}

function update(id) {  //添加广告位
    if(check('update')){
        $.ajax({
            type:"get",
            url:"../"+controller+"/updateAdvPositionPage",
            async:true,
            data:{
                "id":id,
                "apName":$('#updateColumApName').val(),
                "apIntro":$('#updateColumApIntro').val(),
                "apClass":$('#updateColumClass').val(),
                "apDisplay":$('#updateColumDisplay').val(),
                "isUse":$('#updateColumIsUse').val(),
                "apWidth":$('#updateColumApWidth').val(),
                "apHeight":$('#updateColumApHeight').val(),
                "apKey":$('#updateColumApKey').val()
            },
//		dataType:"JSONP",
            success:function(result){
                if(result==1){
                    alert("编辑成功");
                        findShopAdvPosition(pageNum,10);
                }else{
                    alert("编辑失败");
                }
            },
            error:function(error){
                alert("访问服务器失败");
                alert(error);
            }
        });
    }
}

function deleteall() {
    var str = '';
    for (var i = 0; i < document.getElementsByName('advPid').length; i++) {
        if (document.getElementsByName('advPid')[i].checked) {
//							  alert(document.getElementsByName('ids')[i].value);
            if (str == '') str += document.getElementsByName('advPid')[i].value;
            else str += ',' + document.getElementsByName('advPid')[i].value;
        }
    }
    if (str == '') {
        alert("您没有选择任何数据");
    } else {
        $.ajax({
            url: "../"+controller+"/deleteAdvPositionPage",
            type: 'post',
            cache: false,
            data: {
                "advids": str
            },
            success: function (result) {
                if(result==1){
                    alert("删除成功");
                }else{
                    alert("删除失败");
                }
            },
            error:function(error){
                alert("访问服务器失败");
                alert(error);
            }

        });

    }
}

function check(type) {   //验证
	if($('#'+type+'ColumApName').val()==""||$('#'+type+'ColumApName').val()==null){
		alert("广告位置名不能为空");
		return false;
	}
    if($('#'+type+'ColumApIntro').val()==""||$('#'+type+'ColumApIntro').val()==null){
        alert("广告位简介不能为空");
        return false;
    }
    if($('#'+type+'ColumApWidth').val()==""||$('#'+type+'ColumApWidth').val()==null){
        alert("宽度不能为空");
        return false;
    }
    if($('#'+type+'ColumApHeight').val()==""||$('#'+type+'ColumApHeight').val()==null){
        alert("高度不能为空");
        return false;
    }
    if($('#'+type+'ColumApKey').val()==""||$('#'+type+'ColumApName').val()==null){
        alert("区值键");
        return false;
    }
    return true;
}

