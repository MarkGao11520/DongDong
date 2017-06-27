/**
 * Created by gaowenfeng on 2017/2/15.
 */

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    temid = row.temid;
    window.location.href = "updateSmsTemplateInput?temid="+temid+"";
}


function disable(isdefault) {
    if(isdefault==1){
        return "是";
    }else if(isdefault==0){
        return "否";
    }
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(temid){
    return "<input type=\"checkbox\" name=\"temids\" id=\"temids\" value=\""+temid+"\" class=\"checked-focus\" />";
}
    function deleteall(){
        var str = '';
        for(var i=0;i < document.getElementsByName('temids').length;i++)
        {
            if(document.getElementsByName('temids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('temids')[i].value;
                else str += ',' + document.getElementsByName('temids')[i].value;
            }
        }
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'deleteDept',
                type : 'post',
                cache : false,
                data : {
                    "allDept" : str
                },
                success:function(result){
                    alert(result);
                    // window.location.href="DeptManagent/DeptList?cid=${cid }";
                }
            });

        }
    }