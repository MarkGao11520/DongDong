/**
 * Created by gaowenfeng on 2017/2/15.
 */

function xianshi(list,positionid,appid) {
    var i;
    for(i=0;i<list.length;i++){
        test(list[i].modid,list[i].modid,positionid,appid);
    }
}

function check(obj,i){
    var children = $('#mods'+i).find('input');
    if(obj.checked){
        $.each(children, function(k, v) {
            v.checked = 'checked';
        });
    }else{
        $.each(children, function(k, v) {
            v.checked = '';
        });
    }
}



function test(i,j,positionid,appid){
    $.ajax({
        url : '../modController/modHmtl',
        type : 'get',
        data : {
            "positionid":positionid,
            "appid" :appid ,
            "modid":i,
            "allpid":j
        },
        success : function(result) {
            $('#a'+i).attr("href","javascript:disabled()") ;
            $('#mods'+j).append(result);
        }
    });
}
function submit(positionid,appid){
    var str = '';
    var nocheckstr = '';
    for(var i=0;i <document.getElementsByName('modids').length;i++)
    {
        if(document.getElementsByName('modids')[i].checked){
            if(str=='') str += document.getElementsByName('modids')[i].value;
            else str += ',' + document.getElementsByName('modids')[i].value;
        }
    }
    for(var i=0;i < document.getElementsByName('checkedmodids').length;i++)
    {
        if(!document.getElementsByName('checkedmodids')[i].checked){
            if(nocheckstr=='') nocheckstr += document.getElementsByName('checkedmodids')[i].value;
            else nocheckstr += ',' + document.getElementsByName('checkedmodids')[i].value;
        }
    }


    if(str==''&&nocheckstr==''){
        alert("您没有做出任何修改!!!");
    }
    else{
        //
        $.ajax({
            url : '../modController/authorization1',
            type : 'post',
            data : {
                "modidstr" : str,
                "nocheckstr":nocheckstr,
                "appid":appid,
                "positionid":positionid
            },
            success:function(result){
                if(result=="success"){
                    alert("授权成功");
                    window.location.href="../modController/authorization?positionid="+positionid+"&appid="+appid;
                }else{
                    alert("授权失败");
                }

            }
        });

    }
}
