/**
 * Created by gaowenfeng on 2017/2/15.
 */
function update(){
    var row = $("#datagrid").datagrid("getSelected");
    fdid = row.fdid;
    window.location.href = "updateExpressFdexInput?fdid="+fdid+"";
}

function SendFee(){
    var row = $("#datagrid").datagrid("getSelected");
    fdid = row.fdid;
    window.location.href = "../expressSendFeeController/expressSendFeeList?fdid="+fdid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    html += "<a href=\"javascript:SendFee()\">";
    html += "<input type=\"button\"  onclick=\"javascript:SendFee()\" class=\"btn btn-danger\"  value=\"派送费用\"></a>";
    return html;
}

function checkboxs(fdid){
    return "<input type=\"checkbox\" name=\"fdids\" id=\"fdids\" value=\""+fdid+"\" class=\"checked-focus\" />";
}
    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('fdids').length;i++)
        {
            if(document.getElementsByName('fdids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('fdids')[i].value;
                else str += ',' + document.getElementsByName('fdids')[i].value;
            }
        }
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'deleteRule',
                type : 'post',
                cache : false,
                data : {
                    "rIdstr" : str
                },
                success:function(result){
                    alert(result);
                    $("#datagrid").datagrid("reload");
                }
            });

        }
    }
