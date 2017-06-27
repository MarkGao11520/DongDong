var controller = "shopGoodsClassController";  //Controller名
var currentPages = 0;  //当前分页组
var resultPages;  //总页数
var resultRows;   //行数
var pageNum;      //当前页
var total;        //总数
var list;         //数据
var temp = true;
$(function () {
    findShopGoodsClass(1,10);
});


/**
 * 树表初始化
 */
function treeTable(){
    var option = {
        theme: 'vsStyle',
        expandLevel: 1,
        beforeExpand: function ($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            if ($('.' + id, $treeTable).length) {
                return;
            }
            // 这里的html可以是ajax请求
            $.ajax({
                type:"get",
                url:"../"+controller+"/jsonShopGoodsClassListByPid",
                async:true,
                data:{
                    "gcParentId":id
                },
                success:function(result){
                    var html = appendTable(result,id);
                    $treeTable.addChilds(html);
                },
                error:function(error){
                    alert("访问服务器失败");
                    alert(error);
                }
            });
        },
        onSelect: function ($treeTable, id) {
        }
    };
    $('#treeTable1').treeTable(option);
}

/**
 * 获取分类列表
 * @param page
 * @param rows
 */
function findShopGoodsClass(page,rows){
    var gcName = "";  //查询条件
    if($("#gcName").val()!=""&&$("#gcName").val()!=null){
        gcName = $("#gcName").val();
    }
    $.ajax({
        type:"get",
        url:"../"+controller+"/jsonTopShopGoodsClassList",
        async:true,
        data:{
            "page":page,
            "rows":rows,
            "gcName":gcName
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
            findShopGoodsClass(pageNum,10);
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
 * 添加
 * @param pid
 */
function add(pid) {
    if(check()){
        $.ajax({
            type:"get",
            url:"../"+controller+"/addGoodsClass",
            async:true,
            data:{
                "gcName":$('#panal_gc').val(),
                "gcShow":$('#panal_show').val(),
                "gcParentId":pid,
                "gcTitle":$('#panal_title').val(),
                "gcKeywords":$('#panal_key').val(),
                "gcDescription":$('#panal_dec').val(),
                "expenScale":$('#panal_es').val()
            },
//		dataType:"JSONP",
            success:function(result){
                if(result==1){
                    alert("添加成功");
                    cloasGoodsClassPanal();
                    if(total%10!=0){
                        findShopGoodsClass(resultPages,10);
                    }else {
                        findShopGoodsClass(resultPages+1,10);
                    }
                }else if(result == -1){
                    alert("图片上传失败");
                }else{
                    alert("添加失败");
                    cloasGoodsClassPanal();
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
            url:"../"+controller+"/updateGoodsClass",
            async:true,
            data:{
                "id":id,
                "gcName":$('#panal_gc').val(),
                "gcShow":$('#panal_show').val(),
                "gcTitle":$('#panal_title').val(),
                "gcKeywords":$('#panal_key').val(),
                "gcDescription":$('#panal_dec').val(),
                "expenScale":$('#panal_es').val()
            },
//		dataType:"JSONP",
            success:function(result){
                if(result==1){
                    alert("编辑成功");
                    cloasGoodsClassPanal();
                    findShopGoodsClass(pageNum,10);
                }else{
                    alert("编辑失败");
                    cloasGoodsClassPanal();
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
 * 表格初始化
 * @param list
 */
function table(list) {
    var str = '<table id="treeTable1"  class="table table-striped table-hover table-bordered"><thead><tr><td>分类名称</td><td></td><td>图片</td><td>是否前台显示</td><td>名称</td><td>关键词</td><td>描述</td><td>费用比例</td></tr></thead><tbody id="tbody"></tbody></table>';
    $('#treeTable').html(str);
    $('#tbody').html(appendTable(list,-1));
    treeTable();

}

/**
 * 将数据添加到表格
 * @param list
 */
function appendTable(list,pid) {
    var str = "";
    for(var i=0;i<list.length;i++){
        var obj = list[i];
        // alert(obj.id);
        if(pid==-1){
            str += "<tr id=\""+obj.id+"\"  hasChild=\"true\"><td><span controller=\"true\">" + obj.gcName + "</span></td>" ;
        }else{
            str += "<tr id=\""+obj.id+"\" pId=\""+pid+"\" hasChild=\"true\"><td><span controller=\"true\">" + obj.gcName + "</span></td>" ;
        }
         str += "<td><input name=\'gcid\' value=\'"+obj.id+"\' type=\"checkbox\" /></td>" +
            "<td>" + formatPic(obj.gcPic,obj.id)+ "</td>"+
            "<td>" + formatGcShow(obj.gcShow) + "</td>"+
            "<td>" + obj.gcTitle + "</td>"+
            "<td>" + obj.gcKeywords+ "</td>"+
            "<td>" + obj.gcDescription + "</td>"+
            "<td>" + obj.expenScale + "</td>"+
            "<td><a href=\"javascript:addTopGoodsClass("+obj.id+")\"><input type=\"button\" class=\"btn btn-warning\" value=\"添加\"/></a>    <a href=\"javascript:updateGoodsClass("+obj.id+")\"><input type=\"button\" class=\"btn btn-danger\" value=\"编辑\"/></a></td></tr>";
    }
    return str;
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
                str += "<li class=\"active\"><a href=\"javascript:findShopGoodsClass("+page+","+rows+")\">"+page+"</a></li>";
            }else {
                str += "<li><a href=\"javascript:findShopGoodsClass(" + page + "," + rows + ")\">" + page + "</a></li>";
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
 * 添加前准备
 * @param pid
 */
function addTopGoodsClass(pid) {
    $('#goodsClassPanal_title').html("添加分类");
    $('#panal_sumbit').attr('onclick','add('+pid+')');
    $('#panal_show_div').html('<span >是否前台显示:</span>&nbsp;&nbsp;<input type="checkbox" checked id="panal_show" />');
    $('#panal_show').attr('value','true');
    openGoodsClassPanal();
}

/**
 * 编辑前准备
 * @param id
 */
function updateGoodsClass(id) {
    $('#goodsClassPanal_title').html("编辑分类");
    $('#panal_sumbit').attr('onclick','update('+id+')');
    for(var i=0;i<list.length;i++) {
        if (id == list[i].id) {
            var obj = list[i];
            if(!obj.gcShow){
                $('#panal_show_div').html('<span >是否前台显示:</span>&nbsp;&nbsp;<input type="checkbox"  id="panal_show" />');
                $('#panal_show').attr('value','false');
            }else{
                $('#panal_show_div').html('<span >是否前台显示:</span>&nbsp;&nbsp;<input type="checkbox" checked id="panal_show" />');
                $('#panal_show').attr('value','true');
            }
            $('#panal_gc').val(obj.gcName);
            $('#panal_title').val(obj.gcTitle);
            $('#panal_key').val(obj.gcKeywords);
            $('#panal_dec').val(obj.gcDescription);
            $('#panal_es').val(obj.expenScale);
        }
    }
    openGoodsClassPanal();
}

/**
 * 打开添加窗口
 */
function openGoodsClassPanal() {
    $("#panal_show").bootstrapSwitch({
        onText:"是",
        offText:"否",
        onColor:"success",
        offColor:"info",
        onSwitchChange:function(event,state){
            if(state==true){
                $('#panal_show').attr('value','true');
            }else{
                $('#panal_show').attr('value','false');
            }
        }
    });
    $('#goodsClassPanal').show();
}

/**
 * 关闭添加窗口
 */
function cloasGoodsClassPanal() {
    $('#goodsClassPanal').hide();
    clear();
}

/**
 * 打开图片上传窗口
 * @param id
 */
function openImageFormPanal(id) {
    $('#gc_id').val(id);
    for(var i=0;i<list.length;i++) {
        if (id == list[i].id) {
            var obj = list[i];
            $('#gc_pic').attr('src', "../" + obj.gcPic);
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
 * 添加/编辑前检查
 * @returns {boolean}
 */
function check() {
    if($('#panal_gc').val()==null||$('#panal_gc').val()==""){
        alert("分类名称不能为空");
        return false;
    }
    if($('#panal_title').val()==null||$('#panal_title').val()==""){
        alert("名称不能为空");
        return false;
    }
    if($('#panal_key').val()==null||$('#panal_key').val()==""){
        alert("关键词不能为空");
        return false;
    }
    if($('#panal_dec').val()==null||$('#panal_dec').val()==""){
        alert("描述不能为空");
        return false;
    }
    if($('#panal_es').val()==null||$('#panal_es').val()==""){
        alert("费用比例不能为空");
        return false;
    }else{
        if(isNaN($('#panal_es').val())){
            alert("费用比例必须是数字");
            return false;
        }
    }
    return true;
}


/**
 * 情况面板数据
 */
function clear() {
    $('#panal_gc').val("");
    $('#panal_title').val("");
    $('#panal_key').val("");
    $('#panal_dec').val("");
    $('#panal_es').val("");
}

/**
 * 修改图片显示
 */
function changeImage() {
    var url = window.URL.createObjectURL(document
        .getElementById("file").files[0]);
    $('#gc_pic').attr('src',url);
}

/**
 * 是否显示字段转换
 * @param gcShow
 * @returns {*}
 */
function formatGcShow(gcShow) {
    if(gcShow){
        return "是";
    }else {
        return "否";
    }
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