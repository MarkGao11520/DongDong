/**
 * Created by gaowenfeng on 2017/2/15.
 */
function  obtainUserByUid(){
    $("#datagrid").datagrid("reload",{
        "realname" : $("#realname").val(),
        "aid":$("#aid").val()
    });
}

function update(){
    var row = $("#datagrid").datagrid("getSelected");
    uid = row.uid;
    window.location.href = "updateUserInput?uid="+uid+"";
}

function log(){
    var row = $("#datagrid").datagrid("getSelected");
    uid = row.uid;
    window.location.href = "LogList?uid="+uid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    html += "<a href=\"javascript:log()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"查看日志\"></a>";
    return html;
}

function checkboxs(uid){
    return "<input type=\"checkbox\" name=\"uids\" id=\"uids\" value=\""+uid+"\" class=\"checked-focus\" />";
}
    function deleteall(cid){
        var str = '';
        for(var i=0;i < document.getElementsByName('uids').length;i++)
        {
            if(document.getElementsByName('uids')[i].checked){
//							  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('uids')[i].value;
                else str += ',' + document.getElementsByName('uids')[i].value;
            }
        }
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'deleteUser',
                type : 'get',
                cache : false,
                data : {
                    "allUser" : str
                }
            });
            window.location.href="../UserManagent/UserList?cid="+cid;
        }
    }

function url1(code,cid) {
    if(cid!=null) {
        return 'JsonList?type=1&code=' + code + '&cid=' + cid;
    }else{
        return 'JsonList?type=1&code=' + code;
    }
}

function url2(cid){
    return 'JsonList?type=2&cid='+cid;
}

function url3(code,did,cid) {
    if(did!=null&&cid!=null) {
        return 'JsonList?type=2&code=' + code + '&did=' + did + '&cid=' + cid;
    }else if(cid!==null&&did==null){
        return 'JsonList?type=2&code=' + code +'&cid=' + cid;
    }else if(cid==null&&did!=null){
        return 'JsonList?type=2&code=' + code + '&did=' + did;
    }else{
        return 'JsonList?type=2&code=' + code;
    }
}

function url4(did) {
    return 'JsonList?type=3&did='+did;
}

function url5(code,cid,did,posid) {
    if(cid!=null&&did!=null&&posid!=null){
        return 'JsonList?type=3&code='+code+'&cid='+cid+'&did='+did+'&posid='+posid;
    }else if(cid!=null&&did!=null&&posid==null){
        return 'JsonList?type=3&code='+code+'&cid='+cid+'&did='+did;
    }else if(cid!=null&&did==null&posid==null){
        return 'JsonList?type=3&code='+code+'&cid='+cid;
    }else if(cid==null&&did==null&posid==null){
        return 'JsonList?type=3&code='+code;
    }

}