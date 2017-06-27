/**
 * Created by gaowenfeng on 2017/2/7.
 */
// var token = $("meta[name = '_csrf']").attr("content");
// var header = $("meta[name = '_csrf_header']").attr("content");
// $(document).ajaxSend(function(e,xhr,options) {
//     xhr.setRequestHeader(header,token);
// })

function obtainBatchByYear() {
    $("#datagrid").datagrid("reload", {
        "appFullName": $("#appFullName").val()
    });
}

function update() {
    var row = $("#datagrid").datagrid("getSelected");
    orderid = row.orderid;
    window.location.href = "updateExpressOrderInput?orderid=" + orderid + "";
}

function option() {
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}




