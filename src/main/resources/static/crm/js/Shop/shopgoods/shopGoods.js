var controller = "shopGoodsController";  //Controller名
var currentPages = 0;  //当前分页组
var resultPages;  //总页数
var resultRows;   //行数
var pageNum;      //当前页
var total;        //总数
var list;         //数据
var gcId;
$(function(){
	findShopGoods(1,10);   //初始化查询
});

/**
 * 获取列表
 * @param page
 * @param rows
 */
function findShopGoods(page,rows){   //获取广告位列表
	var goodsName = "";  //查询条件
	if($("#goodsName").val()!=""&&$("#goodsName").val()!=null){
        goodsName = $("#goodsName").val();
	}
	$.ajax({
		type:"get",
		url:"../"+controller+"/jsonShopGoodsList",
		async:true,
		data:{
			"page":page,
			"rows":rows,
			"gcId":gcId,
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
 * 删除
 */
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

/**
 * 图片上传
 */
function imageForm_sumbit() {
    $.messager.progress(); // 显示进度条
    $('#imageForm').form('submit', {
        url : "../"+controller+"/uploadImage",
        onSubmit : function() {
            var isValid = $('#file').val();
            if (isValid=="") {
                alert("请选择头像上传");
                return false; // 返回false终止表单提交
                $.messager.progress('close'); // 如果表单是无效的则隐藏进度条
            }
        },
        success : function(result) {
            if (result == 1)
                alert("上传成功");
            cloasImageFormPanal();
            findShopGoods(pageNum,10);
            if (result == -1)
                alert("文件上传失败，请重新上传");
            $.messager.progress('close'); // 如果提交成功则隐藏进度条
        },
        error : function() {
            alert("消息提示", "请求失败");
            $.messager.progress('close');
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
            "<td>" + formatPic(obj.goodsImage,obj.id) + "</td>"+
            "<td>" + obj.goodsClick + "</td>"+
            "<td>" + obj.goodsCommend + "</td>"+
            "<td>" + obj.create + "</td>"+
            "<td>" + obj.update + "</td>"+
            "<td>" + obj.commentnum + "</td>"+
            "<td>" + obj.salenum + "</td>"+
            "<td>" + obj.goodsCollect + "</td>"+
            "<td>" + obj.goodsMarketPrice + "</td>"+
            "<td>" + obj.goodsCostPrice + "</td>"+
            "<td><a href=\"javascript:updateGoods("+obj.id+")\"><input type=\"button\" class=\"btn btn-danger\" value=\"编辑/详情\"/></a></td></tr>";
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
                str += "<li class=\"active\"><a href=\"javascript:findShopGoods("+page+","+rows+")\">"+page+"</a></li>";
			}else {
                str += "<li><a href=\"javascript:findShopGoods(" + page + "," + rows + ")\">" + page + "</a></li>";
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
 * 显示添加商品窗口
 */
function addShopGoods() {   //
    if(gcId!=""&&gcId!=null){
        $('#panel_sumbit').attr('onclick','add()');
        openGoodsPanel();
    }else{
        alert("请选择商品分类");
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
function openGoodsPanel() {
    $('#goodsPanel').show();
}

/**
 * 关闭添加窗口
 */
function closeGoodsPanel() {
    $('#goodsPanel').hide();
    clear();
}

/**
 * 打开图片上传窗口
 * @param id
 */
function openImageFormPanal(id) {
    $('#g_id').val(id);
    for(var i=0;i<list.length;i++) {
        if (id == list[i].id) {
            var obj = list[i];
            $('#g_Image').attr('src', "../" + obj.goodsImage);
        }
    }
    $('#imageFormPanal').show();
}

/**
 * 关闭图片上传窗口
 */
function cloasImageFormPanal() {
    $('#imageFormPanal').hide();
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

/**
 * 图片转换
 * @param pic
 * @param id
 * @returns {string}
 */
function formatPic(pic,id){
    return 	'<img  src="../'+pic+'" onclick="openImageFormPanal('+id+')" style="float: left; width: 50px;height: 50px;" class="img-circle" />';
}

/**
 * 修改图片显示
 */
function changeImage() {
    var url = window.URL.createObjectURL(document
        .getElementById("file").files[0]);
    $('#g_Image').attr('src',url);
}

