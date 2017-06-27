/**
 * Created by gaowenfeng on 2017/2/6.
 */

function appendMod(appid) {
    $.ajax({
        url : 'adminController/jsonMod',
        type : 'get',
        data : {
            "appid":appid
        },
        success : function(result) {
            $('#sidebarul li').remove();
            var arr = result;
            var html = "";
            for(var i=0;i<arr.length;i++){
                if(arr[i].classlevel==1){
                    html += "<li><a data-toggle=\"collapse\" href=\"#navside-collapse-"+(i+1)+"-"+(i+1)+"\">";
                    html +=	"<img src=\"crm/img/settings.png\" class=\"icon-width\" /> <span ";
                    html += "class=\"nav-label\">"+arr[i].modname+"</span></a>";
                    html += "<ul class=\"nav navside-nav navside-second collapse in\"";
                    html += "id=\"navside-collapse-"+(i+1)+"-"+(i+1)+"\">";
//				alert(arr[i].modname);
                    for(var j=0;j<arr.length;j++){
                        if(arr[i].modid==arr[j].pid){
                            html += "<li><a href=\""+arr[j].url+"\"";
                            html +=	"target=\"main\" class=\"open-tab\"";
                            html +=	"tab-name=\"navside-collapse-"+appid+"-"+(i+1)+"-"+(i+1)+"-"+(j+1)+"\"> <img";
                            html +=	"src=\"crm/img/message.png\" class=\"icon-width\" /> <span\"";
                            html += "class=\"nav-label\">"+arr[j].modname+"</span></a></li>";
                            // alert("\"navside-collapse-"+appid+"-"+(i+1)+"-"+(i+1)+"-"+(j+1)+"\"");
                        }
                    }
                    html+= "</ul></li>";
                }
            }
            $('#sidebarul').append(html);
        }
    });
}

function updatePassword() {
    if(check()){
        alert("开始修改");
        $.ajax({
            url:"UserManagent/updatePassword",
            type:"post",
            data:{
                "oldP":$('#oldP').val(),
                "newP":$('#newP').val()
            },
            success:function (result) {
                alert(result)
                if(result==1){
                    alert("修改成功");
                    closeUpdateDiv();
                }else{
                    alert("修改失败");
                }
            },
            error:function (error) {
                alert("修改失败");
            }
        })
    }
}

function openUpdatePassDiv() {
    $('#updatePassDiv').show();
}

function closeUpdateDiv() {
    $('#updatePassDiv').hide();
}

function check() {
    if($('#oldP').val()==null||$('#oldP').val()==""){
        alert("原密码不能为空");
        return false;
    }
    if($('#newP').val()==null||$('#newP').val()==""){
        alert("新密码不能为空");
        return false;
    }
    if($('#newP').val()!=$('#newP1').val()){
        alert("两次密码输入不一致");
        return false;
    }

    return true;
}



