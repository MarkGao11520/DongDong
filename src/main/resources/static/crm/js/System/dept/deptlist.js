/**
 * Created by gaowenfeng on 2017/2/14.
 */
function  obtainDeptByDname(){
    $("#datagrid").datagrid("reload",{
        "dname" : $("#dname").val(),
        "cid":$("#cid").val()
    });
}

function url1(cid) {
    return "jsonDeptList?tr=1&cid="+cid;
}

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    did = row.did;
    window.location.href = "updateDeptInput?did="+did+"";
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
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\" /></a>";
    return html;
}

function checkboxs(did){
    // alert("did:"+did);
    return "<input type=\"checkbox\" name=\"dids\" id=\"dids\" value=\""+did+"\" class=\"checked-focus\" />";
}
    function deleteall(cid){
        var str = '';
        for(var i=0;i < document.getElementsByName('dids').length;i++)
        {
            // alert("yes");
            if(document.getElementsByName('dids')[i].checked){
                if(str=='') str += document.getElementsByName('dids')[i].value;
                else str += ',' + document.getElementsByName('dids')[i].value;
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
                    window.location.href="../DeptManagent/DeptList?cid="+cid;
                }
            });

        }
    }




