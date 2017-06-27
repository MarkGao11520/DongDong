package com.zrkj.ecp.Utils;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by gaowenfeng on 2017/5/8.
 */
public class WeiXinUrl {
    public static final String IP = "47.93.53.160";

    public static final String PORT = "80";

    public static final String MYPLAT = "DongDong/";

    public static final String URL = "http://"+IP+":"+PORT+"/"+MYPLAT;

    /** 插入新的微信用户
     * 参数：phone 手机号
     *      openid
     * 返回值：1代表成功，0代表失败
     * **/
    public static final String ADDWEIXINUSER_URL =URL+"memberController/mobile/addWeiXinUser";
    /** 根据openid更改手机号
     * 参数：phone 手机号
     *      openid
     * 返回值：1代表成功，0代表失败
     * **/
    public static final String UPDATEPHONEBYOPENID_URL =URL+"memberController/mobile/updatePhoneByOpenId";




}
