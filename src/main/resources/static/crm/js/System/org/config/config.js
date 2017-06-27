/**
 * Created by gaowenfeng on 2017/2/14.
 */
function  obtainConfig(){
    $("#datagrid").datagrid("reload",{
        "configname" : $("#configname").val(),
        "cid":$("#cid").val()
    });
}

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    cofid = row.cofid;
    window.location.href = "updateConfigInput?configid="+cofid+"";
}



function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(cofid){
    return "<input type=\"checkbox\" name=\"cofids\" id=\"cofids\" value=\""+cofid+"\" class=\"checked-focus\" />";
}
    function deleteall(cid){
        var str = '';
        for(var i=0;i < document.getElementsByName('cofids').length;i++)
        {
            if(document.getElementsByName('cofids')[i].checked){
                if(str=='') str += document.getElementsByName('cofids')[i].value;
                else str += ',' + document.getElementsByName('cofids')[i].value;
            }
        }
        //alert(str);
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            //alert("STR:"+str);
            $.ajax({
                url : 'delteConfig',
                type : 'post',
                cache : false,
                data : {
                    "allConfig" : str
                },
                success:function(result){
                    alert(result);
                    window.location.href="../configController/ConfigList?cid="+cid;
                }
            });
        }
    }