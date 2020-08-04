package com.dossen.model;

/**
 * 响应代码枚举
 */

public enum ResponseEnum {
    /**
     * 处理成功
     */
    SUCCESS(0, "成功"),

    /**
     * 处理失败
     */
    FAILED(999999, "失败"),

    /**
     * 用户账号或密码错误
     */
    LOGINNAME_OR_PASSWORD_ERROR(200011, "用户账号或密码错误"),

    /**
     * 业务系统用户不存在
     */
    BIZSYSTEM_USER_NO_EXISTS(200012, "业务系统用户不存在"),

    /**
     * 接口签名验证不通过
     */
    SIGN_VERIFY_FAILED(200013, "签名验证不通过"),

    /**
     * 无效的接口类型
     */
    API_TYPE_NOT_EXISTS(200014, "无效的接口类型"),

    /**
     * 用户已绑定
     */
    USER_BIND_ALREADY(200015, "用户已绑定"),

    /**
     * OneIDToken过期
     */
    ONEID_TOKEN_INVALID(900007, "OneIDToken失效");

    /**
     * 返回编码
     */
    private Integer returnCode;

    /**
     * 返回消息
     */
    private String returnMsg;

    /**
     * 构造器
     * @param returnCode
     * @param returnMsg
     */
    ResponseEnum(Integer returnCode,String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    /**
     * 返回编码
     */
    public Integer getReturnCode() {
        return returnCode;
    }

    /**
     * 返回编码
     */
    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 返回消息
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * 返回消息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}