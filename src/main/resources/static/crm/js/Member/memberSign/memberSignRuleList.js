/**
 * Created by gaowenfeng on 2017/2/15.
 */
function update(){
    var row = $("#datagrid").datagrid("getSelected");
    srid = row.srid;
    window.location.href = "updateSignRuleInput?srid="+srid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(srid){
    return "<input type=\"checkbox\" name=\"srids\" id=\"srids\" value=\""+srid+"\" class=\"checked-focus\" />";
}
    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('srids').length;i++)
        {
            if(document.getElementsByName('srids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('srids')[i].value;
                else str += ',' + document.getElementsByName('srids')[i].value;
            }
        }
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'deleteSignRule',
                type : 'post',
                cache : false,
                data : {
                    "srIdstr" : str
                },
                success:function(result){
                    alert(result);
                    $("#datagrid").datagrid("reload");
                }
            });

        }
    }