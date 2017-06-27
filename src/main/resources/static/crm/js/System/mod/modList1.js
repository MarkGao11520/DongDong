/**
 * Created by gaowenfeng on 2017/2/14.
 */
function  obtainModByModName(){
    $("#datagrid").treegrid("reload",{
        "modname" : $("#modname").val(),
        "appid" : $("#appid").val()
    });
}

function add(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "addModInput?appid="+row.appid+"&modid="+row.modid+"";
}

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    modid = row.modid;
    window.location.href = "updateModInput?modid="+modid+"";
}

function disable(disabled) {
    if(disabled==1){
        return "是";
    }else if(disabled==0){
        return "否";
    }
}



function option(){
    var html = "";
    html += "<a href=\"javascript:add()\">";
    html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"添加\" /></a>";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"编辑\" /></a>";
    return html;
}

function checkboxs(modid){
    return "<input type=\"checkbox\" name=\"modids\" id=\"modid\" value=\""+modid+"\" class=\"checked-focus\" />";
}