/**
 * Created by gaowenfeng on 2017/2/15.
 */
function  obtainLevelByName(){
    $("#datagrid").datagrid("reload",{
        "leName" : $("#leName").val()
    });
}

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    leid = row.leid;
    window.location.href = "updateLevelInput?leid="+leid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(leid){
    return "<input type=\"checkbox\" name=\"leids\" id=\"leids\" value=\""+leid+"\" class=\"checked-focus\" />";
}
    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('leids').length;i++)
        {
            if(document.getElementsByName('leids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('leids')[i].value;
                else str += ',' + document.getElementsByName('leids')[i].value;
            }
        }
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'deleteLevel',
                type : 'post',
                cache : false,
                data : {
                    "leIdstr" : str
                },
                success:function(result){
                    alert(result);
                    $("#datagrid").datagrid("reload");
                }
            });

        }
    }