package com.zrkj.ecp.domain.shoporder;

import java.math.BigDecimal;

public class ShopOrder {
    private Long id;

    private String orderSn;

    private Long storeId;

    private String storeName;

    private Long buyerId;

    private String buyerName;

    private String buyerEmail;

    private Long createTime;

    private Boolean orderType;

    private Long paymentId;

    private String paymentName;

    private String paymentCode;

    private String paymentBranch;

    private String paymentDirect;

    private Boolean paymentState;

    private String outSn;

    private String tradeSn;

    private Long paymentTime;

    private Long shippingTime;

    private int shippingExpressId;

    private String shippingCode;

    private String outPaymentCode;

    private Long finnshedTime;

    private String invoice;

    private BigDecimal goodsAmount;

    private BigDecimal discount;

    private BigDecimal orderAmount;

    private BigDecimal orderTotalPrice;

    private BigDecimal shippingFee;

    private String shippingName;

    private Boolean evaluationStatus;

    private Long evaluationTime;

    private Boolean evalsellerStatus;

    private Long evalsellerTime;

    private String orderMessage;

    private Integer orderState;

    private Integer orderPointscount;

    private Integer voucherId;

    private BigDecimal voucherPrice;

    private String voucherCode;

    private int  refundState;

    private int returnState;

    private BigDecimal refundAmount;

    private Integer returnNum;

    private Long groupId;

    private Integer groupCount;

    private Long xianshiId;

    private String xianshiExplain;

    private Long mansongId;

    private String mansongExplain;

    private Integer bundlingId;

    private String bundlingExplain;

    private String orderFrom;

    private Integer daddressId;

    private Long addressId;

    private Long payId;

    private String paySn;

    private Boolean balanceState;

    private Long balanceTime;

    private String shippingExpressCode;

    private BigDecimal predepositAmount;

    private String cancelCause;

    private Long couponId;

    private BigDecimal couponPrice;

    private BigDecimal promoPrice;

    private int lockState;

    private String create;

    private String finished;

    private String balance;

    private String payment;

    private String shipping;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getOrderType() {
        return orderType;
    }

    public void setOrderType(Boolean orderType) {
        this.orderType = orderType;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName == null ? null : paymentName.trim();
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode == null ? null : paymentCode.trim();
    }

    public String getPaymentBranch() {
        return paymentBranch;
    }

    public void setPaymentBranch(String paymentBranch) {
        this.paymentBranch = paymentBranch == null ? null : paymentBranch.trim();
    }

    public String getPaymentDirect() {
        return paymentDirect;
    }

    public void setPaymentDirect(String paymentDirect) {
        this.paymentDirect = paymentDirect == null ? null : paymentDirect.trim();
    }

    public Boolean getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Boolean paymentState) {
        this.paymentState = paymentState;
    }

    public String getOutSn() {
        return outSn;
    }

    public void setOutSn(String outSn) {
        this.outSn = outSn == null ? null : outSn.trim();
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn == null ? null : tradeSn.trim();
    }

    public Long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Long getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public int getShippingExpressId() {
        return shippingExpressId;
    }

    public void setShippingExpressId(int shippingExpressId) {
        this.shippingExpressId = shippingExpressId;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    public String getOutPaymentCode() {
        return outPaymentCode;
    }

    public void setOutPaymentCode(String outPaymentCode) {
        this.outPaymentCode = outPaymentCode == null ? null : outPaymentCode.trim();
    }

    public Long getFinnshedTime() {
        return finnshedTime;
    }

    public void setFinnshedTime(Long finnshedTime) {
        this.finnshedTime = finnshedTime;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    public Boolean getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(Boolean evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Long getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Long evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Boolean getEvalsellerStatus() {
        return evalsellerStatus;
    }

    public void setEvalsellerStatus(Boolean evalsellerStatus) {
        this.evalsellerStatus = evalsellerStatus;
    }

    public Long getEvalsellerTime() {
        return evalsellerTime;
    }

    public void setEvalsellerTime(Long evalsellerTime) {
        this.evalsellerTime = evalsellerTime;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage == null ? null : orderMessage.trim();
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderPointscount() {
        return orderPointscount;
    }

    public void setOrderPointscount(Integer orderPointscount) {
        this.orderPointscount = orderPointscount;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(BigDecimal voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode == null ? null : voucherCode.trim();
    }

    public int getRefundState() {
        return refundState;
    }

    public void setRefundState(int refundState) {
        this.refundState = refundState;
    }

    public int getReturnState() {
        return returnState;
    }

    public void setReturnState(int returnState) {
        this.returnState = returnState;
    }

    public int getLockState() {
        return lockState;
    }

    public void setLockState(int lockState) {
        this.lockState = lockState;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Long getXianshiId() {
        return xianshiId;
    }

    public void setXianshiId(Long xianshiId) {
        this.xianshiId = xianshiId;
    }

    public String getXianshiExplain() {
        return xianshiExplain;
    }

    public void setXianshiExplain(String xianshiExplain) {
        this.xianshiExplain = xianshiExplain == null ? null : xianshiExplain.trim();
    }

    public Long getMansongId() {
        return mansongId;
    }

    public void setMansongId(Long mansongId) {
        this.mansongId = mansongId;
    }

    public String getMansongExplain() {
        return mansongExplain;
    }

    public void setMansongExplain(String mansongExplain) {
        this.mansongExplain = mansongExplain == null ? null : mansongExplain.trim();
    }

    public Integer getBundlingId() {
        return bundlingId;
    }

    public void setBundlingId(Integer bundlingId) {
        this.bundlingId = bundlingId;
    }

    public String getBundlingExplain() {
        return bundlingExplain;
    }

    public void setBundlingExplain(String bundlingExplain) {
        this.bundlingExplain = bundlingExplain == null ? null : bundlingExplain.trim();
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom == null ? null : orderFrom.trim();
    }

    public Integer getDaddressId() {
        return daddressId;
    }

    public void setDaddressId(Integer daddressId) {
        this.daddressId = daddressId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn == null ? null : paySn.trim();
    }

    public Boolean getBalanceState() {
        return balanceState;
    }

    public void setBalanceState(Boolean balanceState) {
        this.balanceState = balanceState;
    }

    public Long getBalanceTime() {
        return balanceTime;
    }

    public void setBalanceTime(Long balanceTime) {
        this.balanceTime = balanceTime;
    }

    public String getShippingExpressCode() {
        return shippingExpressCode;
    }

    public void setShippingExpressCode(String shippingExpressCode) {
        this.shippingExpressCode = shippingExpressCode == null ? null : shippingExpressCode.trim();
    }

    public BigDecimal getPredepositAmount() {
        return predepositAmount;
    }

    public void setPredepositAmount(BigDecimal predepositAmount) {
        this.predepositAmount = predepositAmount;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause == null ? null : cancelCause.trim();
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

}