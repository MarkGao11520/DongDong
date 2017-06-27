/**
 * Created by gaowenfeng on 2017/2/14.
 */
function  obtainParamByParamName(){
    $("#datagrid").treegrid("reload",{
        "paraName" : $("#paraName").val(),
        "cid":$("#cid").val()
    });
}



function update(){
    var row = $("#datagrid").datagrid("getSelected");
    paraid = row.paraid;
    window.location.href = "updateParameInput?paraid="+paraid+"";
}



function option(){
    var html = "";
    html += "<a href=\"javascript:add()\">";
    html += "<input type=\"button\"  class=\"btn btn-primary\"  value=\"添加\"></a>";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"  class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}
s
function checkboxs(paraid){
    return "<input type=\"checkbox\" name=\"paraids\" id=\"paraids\" value=\""+paraid+"\" class=\"checked-focus\" />  "+paraid;
}
    function deleteall(cid){
        var str = '';
        for(var i=0;i < document.getElementsByName('paraids').length;i++)
        {
            if(document.getElementsByName('paraids')[i].checked){
                if(str=='') str += document.getElementsByName('paraids')[i].value;
                else str += ',' + document.getElementsByName('paraids')[i].value;
            }
        }
        //alert(str);
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            //alert("STR:"+str);
            $.ajax({
                url : 'delteParame',
                type : 'post',
                cache : false,
                data : {
                    "allParame" : str
                },
                success:function(result){
                    alert(result);
                    window.location.href="../parameController/ParameList?cid="+cid;
                }
            });
        }
    }

function url1(cid,paraid){
    if(paraid!=null) {
        return 'jsonParamList?type=2&cid=' + cid + '&paraid=' + paraid;
    }else{
        return 'jsonParamList?type=2&cid=' + cid;
    }
}

function url4(cid,paraid) {
    return "addParameInput?cid="+cid+"&pparaid="+paraid;
}
