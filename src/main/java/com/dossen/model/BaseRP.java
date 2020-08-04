package com.dossen.model;


/**
 * OneID平台响应实体
 */

public class BaseRP<T> {

    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T item;

    /**
     * 默认构造器
     */
    public BaseRP() {
    }

    /**
     * 构造器
     *
     * @param code
     * @param returnMsg
     * @param item
     */
    public BaseRP(Integer code,String returnMsg,T item) {
        this.code = code;
        this.message = returnMsg;
        this.item = item;
    }

    /**
     * 执行成功
     *
     * @return
     */
    public static <T> BaseRP<T> responseSuccess() {
        return new BaseRP<>( ResponseEnum.SUCCESS.getReturnCode(), ResponseEnum.SUCCESS.getReturnMsg(), null);
    }

    /**
     * 执行失败
     *
     * @param item
     * @return
     */
    public static <T> BaseRP<T> responseError(T item) {
        return new BaseRP<>( ResponseEnum.FAILED.getReturnCode(), ResponseEnum.FAILED.getReturnMsg(), item);
    }

    /**
     * 返回编码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 返回编码
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 响应消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 响应消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 响应数据
     */
    public T getItem() {
        return item;
    }

    /**
     * 响应数据
     */
    public void setItem(T item) {
        this.item = item;
    }
}
