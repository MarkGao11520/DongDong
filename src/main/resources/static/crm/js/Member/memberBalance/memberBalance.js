/**
 * Created by gaowenfeng on 2017/2/15.
 */
function acticity(){
    var row = $("#datagrid").datagrid("getSelected");
    baid = row.baid;
    window.location.href = "../memberBalanceActicityController/memberBalanceActicityList?baid="+baid+"";
}

function log(){
    var row = $("#datagrid").datagrid("getSelected");
    mid = row.mid;
    window.location.href = "../memberBalanceLogController/memberBalanceLogList?mid="+mid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:acticity()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"余额变动\"></a>     ";
    html += "<a href=\"javascript:log()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"余额日志\"></a>";
    return html;
}

function format(status) {
    if(status == 0){
        return "异常";
    }else if(status == 1){
        return "正常";
    }
}

function formatdef(isdefault) {
    if(isdefault == 1){
        return "默认";
    }else if(isdefault == 0){
        return "非默认";
    }
}