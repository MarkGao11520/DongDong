/**
 * Created by gaowenfeng on 2017/2/15.
 */
function  obtainMemberBCByUName(){
    $("#datagrid").datagrid("reload",{
        "userName" : $("#userName").val(),
    });
}

function format(status) {
    if(status == 0){
        return "异常";
    }else if(status == 1){
        return "正常";
    }
}

function formatdef(isdefault) {
    if(isdefault == 1){
        return "默认";
    }else if(isdefault == 0){
        return "非默认";
    }
}