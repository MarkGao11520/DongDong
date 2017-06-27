package com.zrkj.ecp.Utils;

/**
 * Created by gaowenfeng on 2017/2/16.
 */
public class Url {
    public static String URL = "http://localhost:9090/";

    public static String MYPLAT = "DongDong/";

    /** 获取版本号 **/
    public static final String VERSIONCODE_URL = URL +MYPLAT+"expressBillController/mobile/findVersion";
    /** 登录 **/
    public static final String LOGIN_URL = URL +MYPLAT+"UserManagent/mobile/loginCheck";
    /** 入库 **/
    public static final String PUTAWAY_URL = URL + MYPLAT+"expressBillController/mobile/putAway";
    /**快递公司列表**/
    public static final String FDEXLIST_URL=URL+MYPLAT+"expressFdexController/jsonExpressFdexList";
    /**会员列表**/
    public static final String MEMBERLIST_URL=URL+MYPLAT+"memberController/jsonMemberList";
    /** 获取快件信息 **/
    public static final String FINDEXPRESS_URL = URL + MYPLAT+"expressBillController/jsonExpressBillOne";
    /** 编辑快件信息 **/
    public static final String EDITEXPRESS_URL = URL + MYPLAT+"expressBillController/mobile/updateExpressBill";
    /** 异常件处理 **/
    public static final String RETENTIONEXPRESS_URL = URL + MYPLAT+"expressAbnormalController/mobile/addExpressAbnormal";
    /** 盘点处理
     * 1成功
     * -4失败**/
    public static final String CHECKEXPRESS_URL = URL + MYPLAT+"expressCheckRecordController/checkExpress";
    /** 派送件处理
     * 返回1，正常（600）；
     * 返回-1，该件还未被指派任务（状态未900）
     * 返回-3，状态异常（非600非900）
     * 返回-2，更改失败**/
    public static final String WAITSENDEXPRESS_URL = URL + MYPLAT+"expressBillController/mobile/waitsendEexpress";
    /** 派送件列表**/
    public static final String WAITSENDEXPRESSLIST_URL = URL + MYPLAT+"expressBillController/jsonToSendExpressBillList?code=1&status=700";
    /** 出库操作**/
    public static final String SIGNINEXPRESS_URL = URL + MYPLAT+"expressBillController/mobile/singinExpress";
    /** 移库处理 **/
    public static final String MOVEEXPRESS_URL = URL + MYPLAT+"expressBillController/mobile/moveExpress";
    /**异常原因列表**/
    public static final String RETENTIONLIST_URL=URL+MYPLAT+"expressReasoncController/jsonExpressReasonList";
    /**手机号模糊查询**/
    public static final String GETEQUIPMENTPHONELIST_URL=URL+MYPLAT+"memberController/getEquipmentList";
    /**根据手机号获取地址和姓名**/
    public static final String GETADDRESSANDNAMEBYPHONE_URL=URL+MYPLAT+"memberController/mobile/obationMemberNameAndAddressByPhone";
    /**根据billcode获取billid
     * 参数为billcode
     * 返回值为json形式的整数，若-10（小于0）则为订单不存在，大于0则为查询结果
     * **/
    public static final String OBTAINBILLIDBYBILLCODE = URL + MYPLAT+"expressBillController/mobile/obtainBillCodeByBillId";


}
