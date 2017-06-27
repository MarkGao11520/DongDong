/**
 * Created by gaowenfeng on 2017/2/15.
 */
    function  obtainRuleBydetail(){
        $("#datagrid").datagrid("reload",{
            "ruleDetail" : $("#ruleDetail").val()
        });
    }

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    rid = row.rid;
    window.location.href = "updateRuleInput?rid="+rid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(rid){
    return "<input type=\"checkbox\" name=\"rids\" id=\"rids\" value=\""+rid+"\" class=\"checked-focus\" />";
}

    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('rids').length;i++)
        {
            if(document.getElementsByName('rids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('rids')[i].value;
                else str += ',' + document.getElementsByName('rids')[i].value;
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
