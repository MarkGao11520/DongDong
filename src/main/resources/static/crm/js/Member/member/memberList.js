/**
 * Created by gaowenfeng on 2017/2/15.
 */
function  obtainMemberByUName(){
    $("#datagrid").datagrid("reload",{
        "uname" : $("#uname").val(),
    });
}

function format(status) {
    if(status == 0){
        return "未实名";
    }else if(status == 1){
        return "未审核";
    }else if(status == 2){
        return "已审核";
    }
}

function realName(){
    var row = $("#datagrid").datagrid("getSelected");
    //alert(row.uname);
    window.location.href = "memberRealNameDetail?mid="+row.mid;
}

function detail(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "memberDetail?mid="+row.mid+"";
}

function integral(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "../memberIntegralController/memberIntegralList?mid="+row.mid+"";
}

function signRecord(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "../memberSignController/memberSignRecordList?mid="+row.mid+"";
}

function bc(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "../memberBcController/memberBcList?mid="+row.mid+"";
}

function balance(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "../memberBalacneController/memberBalanceList?mid="+row.mid+"";
}

function address1(){
    var row = $("#datagrid").datagrid("getSelected");
    window.location.href = "../memberAddressController/memberList?mid="+row.mid+"";
}

function option(){
    var html = "";
    html += "<a href=\"javascript:realName()\">";
    html += "<input type=\"button\" class=\"btn btn-primary\"  value=\"实名详情\"></a>";
    html += "<a href=\"javascript:detail()\">";
    html += "<input type=\"button\" class=\"btn btn-warning\"  value=\"信息详情\"></a>";
    html += "<a href=\"javascript:integral()\">";
    html += "<input type=\"button\" class=\"btn btn-primary\"  value=\"会员积分\"></a>";
    html += "<a href=\"javascript:signRecord()\">";
    html += "<input type=\"button\" class=\"btn btn-warning\"  value=\"签到记录\"></a>";
    html += "<a href=\"javascript:bc()\">";
    html += "<input type=\"button\" class=\"btn btn-primary\"  value=\"银行卡\"></a>";
    html += "<a href=\"javascript:balance()\">";
    html += "<input type=\"button\" class=\"btn btn-warning\"  value=\"余额\"></a>";
    html += "<a href=\"javascript:address1()\">";
    html += "<input type=\"button\" class=\"btn btn-primary\"  value=\"地址\"></a>";
    return html;
}

function checkboxs(mid){
    return "<input type=\"checkbox\" name=\"mids\" id=\"mids\" value=\""+mid+"\" class=\"checked-focus\" />";
}

    function throughReview(){
        var str = '';
        for(var i=0;i < document.getElementsByName('mids').length;i++)
        {
            if(document.getElementsByName('mids')[i].checked){
//			  alert(document.getElementsByName('ids')[i].value);
                if(str=='') str += document.getElementsByName('mids')[i].value;
                else str += ',' + document.getElementsByName('mids')[i].value;
            }
        }
        //alert(str);
        if(str==''){
            alert("您没有选择任何数据");
        }else{
            $.ajax({
                url : 'throughReviewAll',
                type : 'post',
                data : {
                    "midstr" : str
                },
                success:function(result){
                    if(result=="true"){alert("成功");}else{alert("失败");}

                    $("#datagrid").datagrid("reload");
                }
            });

        }
    }