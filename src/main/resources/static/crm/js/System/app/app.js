/**
 * Created by gaowenfeng on 2017/2/7.
 */
// var token = $("meta[name = '_csrf']").attr("content");
// var header = $("meta[name = '_csrf_header']").attr("content");
// $(document).ajaxSend(function(e,xhr,options) {
//     xhr.setRequestHeader(header,token);
// })

function obtainBatchByYear() {
    $("#datagrid").datagrid("reload", {
        "appFullName": $("#appFullName").val()
    });
}

function update() {
    var row = $("#datagrid").datagrid("getSelected");
    appid = row.appid;
    window.location.href = "updateAppInput?appid=" + appid + "";
}

function option() {
    var html = "";
    html += "<a href=\"javascript:update()\">";
    html += "<input type=\"button\"   class=\"btn btn-warning\"  value=\"编辑\"></a>";
    return html;
}

function checkboxs(appid) {
    return "<input type=\"checkbox\" name=\"appids\" id=\"appids\" value=\"" + appid + "\" class=\"checked-focus\" />";
}

function deleteall() {
    var str = '';
    for (var i = 0; i < document.getElementsByName('appids').length; i++) {
        if (document.getElementsByName('appids')[i].checked) {
//							  alert(document.getElementsByName('ids')[i].value);
            if (str == '') str += document.getElementsByName('appids')[i].value;
            else str += ',' + document.getElementsByName('appids')[i].value;
        }
    }
    if (str == '') {
        alert("您没有选择任何数据");
    } else {
        $.ajax({
            url: 'deleteApp',
            type: 'post',
            cache: false,
            data: {
                "allappid": str
            },
            success: function (result) {
                alert(result);
                window.location.href = "../appController/appList";
            }
        });

    }
}
