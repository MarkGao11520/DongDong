/**
 * Created by gaowenfeng on 2017/2/15.
 */

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    sendid = row.sendid;
    window.location.href = "updateExpressSendFeeInput?sendid="+sendid+"";
}


function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(sendid){
    return "<input type=\"checkbox\" name=\"sendids\" id=\"sendids\" value=\""+sendid+"\" class=\"checked-focus\" />";
}
    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('sendids').length;i++)
        {
            if(document.getElementsByName('sendids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('sendids')[i].value;
                else str += ',' + document.getElementsByName('sendids')[i].value;
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
