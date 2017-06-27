/**
 * Created by gaowenfeng on 2017/2/14.
 */
function detail(){
    var row = $("#datagrid").datagrid("getSelected");
    alert(row.content);
}
function option(){
    return "<a href='javascript:detail()'><input type=\"button\" class=\"btn btn-warning\"  value=\"查看详情\"></a>";
}
function  obtainLogByYear(){
    $("#datagrid").datagrid("reload",{
        "date" : $("#date1").datebox('getValue'),
        "uid" : $("#uid").val()
    });
}