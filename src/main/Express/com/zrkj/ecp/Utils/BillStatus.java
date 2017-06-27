package com.zrkj.ecp.Utils;

/**
 * Created by gaowenfeng on 2017/2/16.
 */
public class BillStatus {
    /**
     * 寄件状态
     */
    /**待收件**/
    public static final Integer TOTHERECEIVER = 100;
    /**已收件**/
    public static final Integer RECEIVERA = 200;
    /**快递员收件**/
    public static final Integer COURIERRECEIPT = 300;
    /**退回件**/
    public static final Integer RETURNA = 400;
    /**待签收**/
    public static final Integer GENERATIONOFSIGNAFTERRECEIVINGSTH = 500;
    /**派送中**/
    public static final Integer INTHEDELIVERY = 600;
    /**已签收**/
    public static final Integer HAVETOSIGNFOR = 700;
    /**异常件**/
    public static final Integer ABNORMAL = 800;
    /**待派送**/
    public static final Integer INTHEDEREADY = 900;


}