var controller = "shopOrderController";  //Controller名
var currentPages = 0;  //当前分页组
var resultPages;  //总页数
var resultRows;   //行数
var pageNum;      //当前页
var total;        //总数
var list;         //数据
$(function(){
	findShopOrder(1,10);   //初始化查询
});

/**
 * 获取列表
 * @param page
 * @param rows
 */
function findShopOrder(page,rows){   //获取广告位列表
	var orderSn = "";  //查询条件
	if($("#orderSn").val()!=""&&$("#orderSn").val()!=null){
        orderSn = $("#orderSn").val();
	}
	$.ajax({
		type:"get",
		url:"../"+controller+"/jsonShopOrderList",
		async:true,
		data:{
			"page":page,
			"rows":rows,
			"orderSn":orderSn
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
 * 添加
 */
function add() {  //添加广告位
    if(check()){
        $.ajax({
            type:"get",
            url:"../"+controller+"/addShopGoods",
            async:true,
            data:{
                "goodsName":$('#panel_gName').val(),
                "goodsSubtitle":$('#panel_gSubtitle').val(),
                "gcId":gcId,
                "goodsSerial":$('#panel_gSerial').val(),
                "goodsShow":$('#panel_gShow').val(),
                "goodsState":$('#panel_gState').val(),
                "goodsKeywords":$('#panel_gKeywords').val(),
                "goodsDescription":$('#panel_gDescription').val(),
                "pyPrice":$('#panel_gPyPrice').val(),
                "kdPrice":$('#panel_gKdPrice').val(),
                "esPrice":$('#panel_gEsPrice').val(),
                "goodsTransfeeCharge":$('#panel_TransfeeCharge').val(),
                "goodsMarketPrice":$('#panel_gMarketPrice').val(),
                "goodsCostPrice":$('#panel_gCostPrice').val()
            },
//		dataType:"JSONP",
            success:function(result){
                if(result==1){
                    alert("添加成功");
                    closeGoodsPanel();
                    if(total%10!=0){
                        findShopGoods(resultPages,10);
                    }else {
                        findShopGoods(resultPages+1,10);
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

/**
 * 编辑
 * @param id
 */
function update(id) {
    if(check()){
        $.ajax({
            type:"get",
            url:"../"+controller+"/updateShopGoods",
            async:true,
            data:{
                "id":id,
                "goodsName":$('#panel_gName').val(),
                "goodsSubtitle":$('#panel_gSubtitle').val(),
                "gcId":gcId,
                "goodsSerial":$('#panel_gSerial').val(),
                "goodsShow":$('#panel_gShow').val(),
                "goodsState":$('#panel_gState').val(),
                "goodsKeywords":$('#panel_gKeywords').val(),
                "pyPrice":$('#panel_gPyPrice').val(),
                "kdPrice":$('#panel_gKdPrice').val(),
                "esPrice":$('#panel_gEsPrice').val(),
                "goodsTransfeeCharge":$('#panel_TransfeeCharge').val(),
                "goodsMarketPrice":$('#panel_gMarketPrice').val(),
                "goodsCostPrice":$('#panel_gCostPrice').val()
            },
//		dataType:"JSONP",
            success:function(result){
                if(result==1){
                    alert("编辑成功");
                    closeGoodsPanel();
                    findShopGoods(pageNum,10);
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
			"<td>" + obj.orderSn+ "</td>"+
            "<td>" + obj.buyerName + "</td>"+
            "<td>" + obj.buyerEmail + "</td>"+
            "<td>" + obj.create + "</td>"+
            "<td>" + obj.paymentName + "</td>"+
            "<td>" + obj.finished + "</td>"+
            "<td>" + obj.goodsAmount + "</td>"+
            "<td>" + obj.shippingFee + "</td>"+
            "<td>" + obj.orderTotalPrice + "</td>"+
            "<td>" + formatOrderState(obj.orderState) + "</td>"+
            "<td>" + formatRefundState(obj.refundState) + "</td>"+
            "<td>" + formatReturnState(obj.returnState) + "</td>"+
            "<td>" + obj.refundAmount + "</td>"+
            "<td>" + obj.returnNum + "</td>"+
            "<td>" + obj.balanceState + "</td>"+
            "<td>" + obj.balance + "</td>"+
            "<td>" + obj.cancelCause + "</td>"+
            "<td>" + formatLockState(obj.lockState) + "</td>"+
            "<td><a href=\"javascript:showPayDetail("+obj.id+")\"><input type=\"button\" class=\"btn btn-primary\" value=\"支付详情\"/></a>" +
            "<a href=\"javascript:showShipDetail("+obj.id+")\"><input type=\"button\" class=\"btn btn-danger\" value=\"物流详情\"/></a>" +
            "<a href=\"../shopOrderGoodsController/shopOrderPage?orderId="+obj.id+"\"><input type=\"button\" class=\"btn btn-success\" value=\"商品详情\"/></a></td></tr>";
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
                str += "<li class=\"active\"><a href=\"javascript:findShopOrder("+page+","+rows+")\">"+page+"</a></li>";
			}else {
                str += "<li><a href=\"javascript:findShopOrder(" + page + "," + rows + ")\">" + page + "</a></li>";
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
 * 显示支付详情窗口
 */
function showPayDetail(id) {
    var str = "";
    str += '<div class="col-xs-3"></div>'+
        '<div class="col-xs-6" style="height: 100%;">'+
        '<div class="panel panel-primary" style="height: auto">'+
        '<div class="panel-heading" style="height: 35px">'+
        '<h3 id="goodsClassPanal_title" class="panel-title" style="float: left">支付详情</h3>'+
        '<span class="glyphicon glyphicon-remove" aria-hidden="true"  onclick="closeOrderPanel()" style="float: right;"></span>'+
        '</div>'+
        '<div class="panel-body" style="overflow: auto;height: 450px">'+
        '<div class="input-group" style="padding: 10px">'+
        '<span  class="input-group-addon">支付方式</span>'+
        '<input id="panel_payName" type="text" class="form-control" disabled="disabled" />'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">支付分支</span>'+
        '<input id="panel_payBranch"  type="text" class="form-control" disabled="disabled" />'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">支付类型</span>'+
        '<input id="panel_payDirect" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">付款状态</span>'+
        '<input id="panel_payState" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">订单编号</span>'+
        '<input id="panel_payOutSn" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">支付表编号</span>'+
        '<input id="panel_paySn" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">交易流水号</span>'+
        '<input id="panel_payTradeSn" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">交易时间</span>'+
        '<input id="panel_payTime" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '<div class="col-xs-3"></div>';
    $('#orderPanel').html(str);
    for(var i=0;i<list.length;i++) {
        if (id == list[i].id) {
            var obj = list[i];
            $('#panel_payName').val(obj.paymentName);
            $('#panel_payBranch').val(obj.paymentBranch);
            $('#panel_payDirect').val(obj.paymentDirect);
            $('#panel_payState').val(obj.paymentState);
            $('#panel_payOutSn').val(obj.outSn);
            $('#panel_paySn').val(obj.paySn);
            $('#panel_payTradeSn').val(obj.tradeSn);
            $('#panel_payTime').val(obj.payment);
            openOrderPanel();
            break;
        }
    }
}

/**
 * 显示配送详情窗口
 */
function showShipDetail(id) {
    var str = "";
    str += '<div class="col-xs-3"></div>'+
        '<div class="col-xs-6" style="height: 100%;">'+
        '<div class="panel panel-primary" style="height: auto">'+
        '<div class="panel-heading" style="height: 35px">'+
        '<h3 id="goodsClassPanal_title" class="panel-title" style="float: left">配送详情</h3>'+
        '<span class="glyphicon glyphicon-remove" aria-hidden="true"  onclick="closeOrderPanel()" style="float: right;"></span>'+
        '</div>'+
        '<div class="panel-body" style="overflow: auto;height: 450px">'+
        '<div class="input-group" style="padding: 10px">'+
        '<span  class="input-group-addon">配送时间</span>'+
        '<input id="panel_shipTime" type="text" class="form-control" disabled="disabled" />'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">配送公司id</span>'+
        '<input id="panel_shipExpressId"  type="text" class="form-control" disabled="disabled" />'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">配送公司编号</span>'+
        '<input id="panel_shipExpressCode" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">物流单号</span>'+
        '<input id="panel_shipCode" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">配送价格</span>'+
        '<input id="panel_shipFee" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">配送方式</span>'+
        '<input id="panel_shipName" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">发货备注</span>'+
        '<input id="panel_deliverExplain" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '<div class="input-group" style="padding: 10px">'+
        '<span class="input-group-addon">收货地址id</span>'+
        '<input id="panel_addressId" type="text" class="form-control" disabled="disabled"/>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '<div class="col-xs-3"></div>';
    $('#orderPanel').html(str);
    for(var i=0;i<list.length;i++) {
        if (id == list[i].id) {
            var obj = list[i];
            $('#panel_shipTime').val(obj.shipping);
            $('#panel_shipExpressId').val(obj.shippingExpressId);
            $('#panel_shipExpressCode').val(obj.shippingExpressCode);
            $('#panel_shipCode').val(obj.shippingCode);
            $('#panel_shipFee').val(obj.shippingFee);
            $('#panel_deliverExplain').val(obj.deliverExplain);
            $('#panel_shipName').val(obj.shippingName);
            $('#panel_addressId').val(obj.addressId);
            openOrderPanel();
            break;
        }
    }
}

/**
 * 显示编辑商品窗口
 */
function updateGoods(id) {
    for(var i=0;i<list.length;i++){
        if(id == list[i].id){
            var obj =list[i];
            var str= "";
            switch(obj.goodsShow){
                case 0:
                    str += "<option value=\'0\'>下架</option><option value=\'1\'>上架</option><option value=\'2\'>定时上架</option>";
                    break;
                case 1:
                    str += "<option value=\'1\'>上架</option><option value=\'0\'>下架</option><option value=\'2\'>定时上架</option>";
                    break;
                case 2:
                    str += "<option value=\'2\'>定时上架</option><option value=\'0\'>下架</option><option value=\'1\'>上架</option>";
                    break;
            }
            $('#panel_gShow').html(str);
            str = "";
            switch(obj.goodsState){
                case 0:
                    str += "<option value=\'0\'>开启</option><option value=\'1\'>违规下架</option>";
                    break;
                case 1:
                    str += "<option value=\'1\'>违规下架</option><option value=\'0\'>开启</option>";
                    break;
            }
            $('#panel_gState').html(str);
            str = "";
            switch(obj.goodsTransfeeCharge){
                case 0:
                    str += "<option value=\'0\'>买家承担</option><option value=\'1\'>卖家承担</option>";
                    break;
                case 1:
                    str += "<option value=\'1\'>卖家承担</option><option value=\'0\'>买家承担</option>";
                    break;
            }
            $('#panel_transfeecharge').html(str);


            $('#panel_gName').val(obj.goodsName);
            $('#panel_gSubtitle').val(obj.goodsSubtitle);
            $('#panel_gSerial').val(obj.goodsSerial);
            $('#panel_gDescription').text(obj.goodsDescription);
            $('#panel_gKeywords').val(obj.goodsKeywords);
            $('#panel_gPyPrice').val(obj.pyPrice);
            $('#panel_gKdPrice').val(obj.kdPrice);
            $('#panel_gEsPrice').val(obj.esPrice);
            $('#panel_gMarketPrice').val(obj.goodsMarketPrice);
            $('#panel_gCostPrice').val(obj.goodsCostPrice);
        }
    }
    $('#panel_sumbit').attr('onclick','update('+id+')');
    openGoodsPanel();

}



/**
 * 打开添加窗口
 */
function openOrderPanel() {
    $('#orderPanel').show();
}

/**
 * 关闭添加窗口
 */
function closeOrderPanel() {
    $('#orderPanel').hide();
    clear();
}




/**
 * 验证
 * @returns {boolean}
 */
function check() {   //验证
	if($('#panel_gName').val()==""){
		alert("商品名称不能为空");
		return false;
	}
	if($('#panel_gPyPrice').val()!=""&&isNaN($('#panel_gPyPrice').val())){
        alert("价格必须为数字");
        return false;
    }
    if($('#panel_gKdPrice').val()!=""&&isNaN($('#panel_gKdPrice').val())){
        alert("价格必须为数字");
        return false;
    }
    if($('#panel_gEsPrice').val()!=""&&isNaN($('#panel_gEsPrice').val())){
        alert("价格必须为数字");
        return false;
    }
    if($('#panel_gMarketPrice').val()!=""&&isNaN($('#panel_gMarketPrice').val())){
        alert("价格必须为数字");
        return false;
    }
    if($('#panel_gCostPrice').val()!=""&&isNaN($('#panel_gCostPrice').val())){
        alert("价格必须为数字");
        return false;
    }
    return true;
}


/**
 * 清空表格
 */
function clear() {
    $('#panel_gName').val("");
    $('#panel_gSubtitle').val("");
    $('#panel_gSerial').val("");
    $('#panel_gDescription').text("");
    $('#panel_gKeywords').val("");
    $('#panel_gPyPrice').val("");
    $('#panel_gKdPrice').val("");
    $('#panel_gEsPrice').val("");
    $('#panel_gMarketPrice').val("");
    $('#panel_gCostPrice').val("");
}

function formatOrderState(os) {
    switch(os){
        case 0:
            return "已取消";
        case 10:
            return "代付款";
        case 20:
            return "待发货";
        case 30:
            return "待收货";
        case 40:
            return "交易完成";
        case 50:
            return "已提交";
        case 60:
            return "已确认";
    }
}

function formatLockState(os) {
    if(os==0){
        return "正常";
    }else if(os>0){
        return "锁定";
    }
}

function formatReturnState(os) {
    switch(os){
        case 0:
            return "无退货";
        case 1:
            return "部分退货";
        case 2:
            return "全部退货";
    }
}

function formatRefundState(os) {
    switch(os){
        case 0:
            return "无退款";
        case 1:
            return "部分退款";
        case 2:
            return "全部退款";
    }
}




