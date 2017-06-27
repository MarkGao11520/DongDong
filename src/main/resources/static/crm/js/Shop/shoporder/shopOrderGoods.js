var controller = "shopOrderGoodsController";  //Controller名
var currentPages = 0;  //当前分页组
var resultPages;  //总页数
var resultRows;   //行数
var pageNum;      //当前页
var total;        //总数
var list;         //数据
var orderId;
$(function(){
	findShopOrderGoods(1,10);   //初始化查询
});

/**
 * 获取列表
 * @param page
 * @param rows
 */
function findShopOrderGoods(page,rows){   //获取广告位列表
	var goodsName = "";  //查询条件
	if($("#goodsName").val()!=""&&$("#goodsName").val()!=null){
        goodsName = $("#goodsName").val();
	}
	$.ajax({
		type:"get",
		url:"../"+controller+"/jsonShopOrderGoodsList",
		async:true,
		data:{
			"page":page,
			"rows":rows,
			"orderId":orderId,
			"goodsName":goodsName
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


/**
 * 将数据添加到表格
 * @param list
 */
function table(list) {   //
	var str = "";
	for(var i=0;i<list.length;i++){
        var obj = list[i];
        str += "<tr id=\"advp_"+obj.id+"\">" +
			"<td><input name=\'advPid\' value=\'"+obj.id+"\' type=\"checkbox\" /></td>" +
			"<td>" + obj.goodsName+ "</td>"+
            "<td>" + formatPic(obj.goodsImage) + "</td>"+
            "<td>" + obj.goodsPrice + "</td>"+
            "<td>" + obj.goodsNum + "</td>"+
            "<td>" + obj.goodsReturnnum + "</td>"+
            "<td>" + obj.goodsPayPrice + "</td>"+
            "<td>" + obj.buyerId + "</td>";
    }
    $("#tbody").html(str);
}


/**
 * 分页
 * @param pages
 * @param rows
 */
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
                str += "<li class=\"active\"><a href=\"javascript:findShopOrderGoods("+page+","+rows+")\">"+page+"</a></li>";
			}else {
                str += "<li><a href=\"javascript:findShopOrderGoods(" + page + "," + rows + ")\">" + page + "</a></li>";
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

/**
 * 翻页
 * @param temp
 */
function changePages(temp){
	if(temp==1){
		currentPages++;
		pages(resultPages,resultRows);
	}else if(temp==-1){
		currentPages--;
        pages(resultPages,resultRows);
	}
}


/**
 * 图片转换
 * @param pic
 * @returns {string}
 */
function formatPic(pic){
    return 	'<img  src="../'+pic+'" style="float: left; width: 50px;height: 50px;" class="img-circle" />';
}



