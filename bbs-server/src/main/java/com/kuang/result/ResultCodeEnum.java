package com.kuang.result;

import lombok.Getter;

/**
 * 枚举结果代码
 *
 * @author yanni
 * @date 2021/11/22
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(true, 20000, "成功"),
    /**
     * 未知的原因
     */
    UNKNOWN_REASON(false, 20001, "未知错误");

    /**
     * 成功
     */
    private Boolean success;
    /**
     * 代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}