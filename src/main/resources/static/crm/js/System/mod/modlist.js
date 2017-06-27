function url1(appid) {
    return "modlistByPid?type=1&appid=" + appid;
}

function url2(rmodid, appid) {
    return "modlistByPid?type=2&modid=" + rmodid + "&appid=" + appid;
}

function url3(positionid, appid) {
    return "../modController/authorization?positionid=" + positionid + "&appid=" + appid;
}


function deleteall(appid) {
    var str = '';
    for (var i = 0; i < document.getElementsByName('modids').length; i++) {
        if (document.getElementsByName('modids')[i].checked) {
            if (str == '') str += document.getElementsByName('modids')[i].value;
            else str += ',' + document.getElementsByName('modids')[i].value;
        }
    }
    //alert(str);
    if (str == '') {
        alert("您没有选择任何数据");
    } else {
        $.ajax({
            url: 'deleteMod',
            type: 'post',
            cache: false,
            data: {
                "modidstr": str
            },
            success: function (result) {
                alert(result);
                window.location.href = "../modController/modList?appid=" + appid;
            }
        });
    }
}