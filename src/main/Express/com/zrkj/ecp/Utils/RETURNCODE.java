package com.zrkj.ecp.Utils;

/**
 * Created by gaowenfeng on 2017/2/17.
 */
public class RETURNCODE {
    /**成功**/
    public static final String SUCCESS = "1";
    /**注册失败**/
    public static final String REGISTERFAIELD = "-3";
    /**用户已经存在**/
    public static final String USEREXISTS = "-2";
    /**用户名或者密码错误**/
    public static final String PASSWORDERROR = "-1";
    /**修改状态失败**/
    public static final String UPDATESTATUSFAIELD = "-2";
    /**添加失败**/
    public static final String ADDFAILED = "-4";
    /**修改失败**/
    public static final String UPDATEFAILED = "-5";
    /**实名认证失败**/
    public static final String REALNAMEFAILED = "-6";
    /**短信验证码错误**/
    public static final String MSGCODEERROR = "-7";
    /**短信验证码发送失败**/
    public static final String SENDMSGCODEFAILED = "-8";
    /**订单已经挫折**/
    public static final String BILLEXISTS = "-9";
    /**订单不存在**/
    public static final String BILLNOTEXISTS = "-10";
    /**已签收，不可重复签收**/
    public static final String BILLYIQIANSHOU = "-1";
    /**该件还未指派**/
    public static final String NOTAPPOINT = "-1";
    /**状态异常**/
    public static final String STATUSABNORMAL = "-3";




}
