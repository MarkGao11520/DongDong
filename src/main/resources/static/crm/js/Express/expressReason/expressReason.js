/**
 * Created by gaowenfeng on 2017/2/15.
 */
function update(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "updateExpressReasonInput?reasonid="+row.reasonid;
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}