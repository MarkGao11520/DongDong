/**
 * Created by gaowenfeng on 2017/2/15.
 */

function detail(){
    var row = $("#datagrid").datagrid("getSelected");
    if (row != null) {
        $("#cargo").textbox("setValue", row.cargo);
        $("#billdateString").textbox("setValue", row.billdateString);
        $("#freight").textbox("setValue", row.freight);
        $("#claimvalue").textbox("setValue", row.claimvalue);
        $("#notes").textbox("setValue", row.notes);
        $("#revusername").textbox("setValue", row.revusername);
        $("#sendusername").textbox("setValue", row.sendusername);
        $("#sendmembername").textbox("setValue", row.sendname);
        $("#revmembername").textbox("setValue", row.revname);
        $("#sgincardnum").textbox("setValue", row.sgincardnum);
        $("#serialno").textbox("setValue", row.serialno);
        $("#positionno").textbox("setValue", row.positionno);
        $("#fdname").textbox("setValue", row.fdname);
        $("#cname").textbox("setValue", row.cname);
        $('#u_dlg').dialog('open').dialog('setTitle', '详情');}else{
        $.messager.show({
            title : "消息提示",
            msg : "请选择该行数据后查询!",
            showType : "show"
        });
    }
}

function tracing() {
    var row = $("#datagrid").datagrid("getSelected");
    billcode1 = row.billcode;
    window.location.href = "../expressTracingController/expressTracingList?billcode="+billcode1+"";
}

function update() {
    var row = $("#datagrid").datagrid("getSelected");
    billid = row.billid;
    window.location.href = "../expressBillController/updateExpressBillInput?billid="+billid+"";
}


function option(){
    var html = "";
    html += "<a href=\"javascript:detail()\">";
    html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"详情\"></a>";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"编辑\"></a>";
    html += "<a href=\"javascript:tracing()\">";
    html += "<input type=\"button\"  class=\"btn btn-danger\"  value=\"跟踪记录\"></a>";
    return html	}


function option1(){
    var html = "";
    html += "<a href=\"javascript:detail()\">";
    html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"详情\"></a>";
    html += "<a href=\"javascript:tracing()\">";
    html += "<input type=\"button\"  class=\"btn btn-danger\"  value=\"跟踪记录\"></a>";
    return html	}

function format(isfragile) {
    if(isfragile == 0){
        return "非易碎";
    }else if(isfragile == 1){
        return "易碎";
    }
}

function formatpayment(payment) {
    switch(payment)
    {
        case -10:
            return "到付";
            break;
        case 10:
            return "现金";
            break;
        case 20:
            return "支付宝";
            break;
        case 30:
            return "微信";
            break;
        case 40:
            return "银联";
            break;
        default:
            "其他"
    }
}

function checkboxs(billid){
    return "<input type=\"checkbox\" name=\"billids\" id=\"billids\" value=\""+billid+"\" class=\"checked-focus\" />";
}

function appoint(uid){
    var str = '';
    for(var i=0;i < document.getElementsByName('billids').length;i++)
    {
        if(document.getElementsByName('billids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
            if(str=='') str += document.getElementsByName('billids')[i].value;
            else str += ',' + document.getElementsByName('billids')[i].value;
        }
    }
    if(str==''){
        alert("您没有选择任何数据");
    }else{
        $.ajax({
            url : 'appointExpress',
            type : 'post',
            cache : false,
            data : {
                "billIdStr" : str,
                "uid" : uid
            },
            success:function(result){
                if(result == 1){
                    alert("指派成功");
                }else{
                    alert("指派失败");
                }
                $("#datagrid").datagrid("reload");
            }
        });

    }
}

function formatdef(stauts){
    if(stauts == 100){
        return "<span style=\"color: red\">待收件</span>";
    }else if(stauts == 200){
        return "<span style=\"color: blue\">已收件</span>";
    }else if(stauts == 300){
        return "<span style=\"color: yellow\">快递员收件</span>";
    }else if(stauts == 400){
        return "<span style=\"color: green\">退回件</span>";
    }else if(stauts == 500){
        return "<span style=\"color: red\">待签收</span>";
    }else if(stauts == 600){
        return "<span style=\"color: blue\">派送中</span>";
    }else if(stauts == 700){
        return "<span style=\"color: yellow\">已签收</span>";
    }else if(stauts == 800){
        return "<span style=\"color: green\">异常件</span>";
    }else if(stauts == 900){
        return "<span style=\"color: pink\">待派送</span>";
    }
}

function findByStatus(status) {
    var code;
    if(status==null){
        code = 0;
    }else {
        code =1;
    }
    $('#datagrid').datagrid("reload",{
        "status":status,
        "code":code
    })
}

function findByStatus1(status,uid) {
    var code;
    if(status==null){
        code = 0;
    }else {
        code =1;
    }
    $('#datagrid').datagrid("reload",{
        "uid":uid,
        "status":status,
        "code":code
    })
}

function search1() {
    $('#datagrid').datagrid("reload",{
        "code":0,
        "startDate":($('#startDate').datebox('getValue')=="")? null :$('#startDate').datebox('getValue') ,
        "endDate":($('#endDate').datebox('getValue')=="")? null : $('#endDate').datebox('getValue'),
        "billCode":$('#billCode').val(),
        "revname":$('#revname').val(),
        "revphone":$('#revphone').val(),
        "fdedid":$('#fdexid').combobox('getValue')
    })
}