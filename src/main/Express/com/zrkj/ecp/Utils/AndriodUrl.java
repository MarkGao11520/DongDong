package com.zrkj.ecp.Utils;

/**
 * Created by gaowenfeng on 2017/2/17.
 */
public class AndriodUrl {
    public static String URL = "http://localhost:9090/";

    public static String MYPLAT = "DongDong/";
    /**会员登录 **/
    public static final String MEMBERLOGIN = URL+MYPLAT+"memberController/mobile/memberLogin";
    /**会员注册 **/
    public static final String MEMBERREGISTER = URL + MYPLAT + "memberController/mobile/memberRegister";
    /**发送短信验证码**/
    public static final String SENDMSGCODE = URL + MYPLAT + "memberController/mobile/mobileCheckCode";
    /**会员个人设置**/
    public static final String UPDATEMEMBERPROPERTY = URL + MYPLAT + "memberController/mobile/updateMemberProperty";
    /**会员修改密码**/
    public static final String UPDATEPASSWORD = URL + MYPLAT + "memberController/mobile/updatePassword";
    /**验证手机验证码
     * msgcode 手机验证码
     * id 手机验证码接口返回的uuid字符串
     * 返回
     * 成功 1
     * 失败 -7
     * **/
    public static final String CHECKMOBILECODE = URL + MYPLAT + "memberController/mobile/checkMobileCode";
    /**会员实名验证**/
    public static final String MEMBERREALNAME = URL + MYPLAT + "memberController/mobile/realName";
    /**会员实名详情**/
    public static final String MEMBERREALNAMEDETAIL = URL + MYPLAT + "memberController/mobile/realNameDetail";
    /**会员详情**/
    public static final String MEMBERDETAIL = URL + MYPLAT + "memberController/mobile/jsonMemberDetail";
    /**添加银行卡**/
    public static final String ADDMEMBERBC = URL + MYPLAT + "memberBcController/mobile/addMemberBc";
    /**修改银行卡**/
    public static final String UPDATEMEBERBC = URL + MYPLAT + "memberController/mobile/updateMemberBc";
    /**根据bcid查找银行卡**/
    public static final String MEMBERBCONE = URL + MYPLAT + "memberController/mobile/jsonMemberBcOne";
    /**修改余额**/
    public static final String UPDATEBALANCE =URL +MYPLAT + "memberBalanceActicityController/mobile/addBalanceActicity";
    /**添加余额**/
    public static final String ADDBALANCE =URL +MYPLAT + "memberBalacneController/mobile/addBalance";
    /**根据余额id查找**/
    public static final String BALANCEONE =URL +MYPLAT + "memberBalacneController/mobile/jsonBalanceOne";
    /**根据会员id查找余额变动记录**/
    public static final String BALANCEACTIVE =URL +MYPLAT + "memberBalanceActicityController/jsonBalanceActicityList";
    /**根据收件人id获取快递信息**/
    public static final String BILLLISTBYREVID = URL + MYPLAT +"expressBillController/mobile/obtainExpressBillListByRevNameId";
    /**待派送**/
    public static final String DAIPAISONG = URL + MYPLAT +"expressBillController/mobile/peisongEexpress";
    /**寄件**/
    public static final String ADDEXPRESS = URL + MYPLAT +"expressBillController/mobile/sendExpress";
    /**获取学校列表**/
    public static final String SCHOOLLIST = URL + MYPLAT + "CompanyManagent/schoollist";
    /**获取站点**/
    public static final String BRANCHESLIST = URL + MYPLAT + "parameController/branchesList";
    /**添加地址**/
    public static final String ADDADDRESS = URL + MYPLAT + "memberAddressController/mobile/addMemberAddress";
    /**编辑地址**/
    public static final String UPDATEADDRESS = URL + MYPLAT + "memberAddressController/mobile/updateMemberAddress";
    /**删除地址**/
    public static final String DELETEADDRESS = URL + MYPLAT + "memberAddressController/mobile/delteMemberAddress";
    /**根据用户id获取地址列表**/
    public static final String ADDRESSBYMID = URL + MYPLAT + "memberAddressController/mobile/memberListByMid";
    /**唯一查找**/
    public static final String ADDRESSBYCMID = URL + MYPLAT + "memberAddressController/mobile/memberListBycmId";
    /**修改默认地址**/
    public static final String UPDATEDEFAULTADDRESS = URL + MYPLAT + "memberAddressController/mobile/updateDefaultAddress";
    /**根据站点id或者站点名称
     * 参数为paramid
     * **/
    public static final String OBTAINBRANCHENAMEBYID = URL + MYPLAT + "parameController/obtainConfigNameByConfigId";
    /**
     * 获取用户收件列表
     * 参数为  revmemberid
     *  status  为需要查询的状态  500-900
     */
    public static final String OBTAINBILLBYREVMEMBERID = URL + MYPLAT + "expressBillController/jsonToSendExpressBillList?code=1";
    /**
     * 获取用户寄件列表
     * 参数为sendmemberid
     * status  为需要查询的状态 100-400
     */
    public static final String OBTAINBILLBYSENDMEMBERID = URL + MYPLAT + "expressBillController/jsonSendExpressBillList?code=1";
    /**
     * 上传头像
     * 参数：photo 照片文件 file类型
     * mid 会员id Integer类型
     * 返回值
     * 1：上传成功
     * -5：上传失败
     */
    public static final String UPLOADPHOTO = URL + MYPLAT + "memberController/uploadPhoto";

    /**
     * 生成订单
     *
     * 参数
     * cid 学校id
     * type 类别 1：派送收费，2寄件收费（上门取件） 3寄件收费（站点寄件）
     * ordercontent 收/付款主题（对应subject字段）
     * description 描述
     * billids 快递单号集合（逗号隔开）
     * mname 会员名称
     * mid 会员id
     * createDate 生辰时间
     *
     * 返回值
     * {"orderCode":"生成的订单号"}
     */
    public static  final String ADDORDER =  URL + MYPLAT + "expressOrderController/addOrderByType";

    /**
     * 订单签约
     * money 金额
     * paydateString 交易时间（时间戳 String 类型）
     * ordercode 订单编号
     *
     * 返回值
     * 签约成功 {"check"："true"，"orderInfo"："签名结果。。。","orderCode":"生成的订单号...."}
     * 签约失败 {"check"："false"，"orderInfo"："","orderCode":""}
     *
     */
    public static final String SIGNORDERINFO = URL + MYPLAT + "expressOrderController/signOrder";

    /**
     * 根据订单号获取支付结果
     * 参数 orderCode 订单号
     *
     * 返回值
     * -99 :订单支付成功但修改订单状态失败
     * 其他代号参照com.zrkj.ecp.Utils.PayResult
     *
     */
    public static final String RESULTBYORDERCODE = URL + MYPLAT + "expressOrderController/obtainResultByOrderCode";

    /**
     * 根据mid获取未支付订单列表
     * 参数
     * mid 会员id
     *
     * 返回值
     * expressorder组成的list
     */
    public static final String OBTAINNOTPAYORDERBYMID = URL + MYPLAT + "expressOrderController/obtainOrderListByMid";




}
