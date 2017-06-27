/**
 * Created by gaowenfeng on 2017/2/19.
 */
function formatdef(stauts) {
    if(stauts == 100){
        return "<span style=\"color: red\">待收件</span>";
    }else if(stauts == 200){
        return "<span style=\"color: blue\">已收件</span>";
    }else if(stauts == 300){
        return "<span style=\"color: yellow\">快递员收件</span>";
    }else if(stauts == 400){
        return "<span style=\"color: green\">退回件</span>";
    }else if(stauts == 500){
        return "<span style=\"color: red\">待签收</span>";
    }else if(stauts == 600){
        return "<span style=\"color: blue\">派送中</span>";
    }else if(stauts == 700){
        return "<span style=\"color: yellow\">已签收</span>";
    }else if(stauts == 800){
        return "<span style=\"color: green\">异常件</span>";
    }else if(stauts == 900){
        return "<span style=\"color: pink\">待派送</span>";
    }
}