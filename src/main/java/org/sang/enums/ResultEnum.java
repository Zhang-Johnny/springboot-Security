package org.sang.enums;

/**
 * Created by zhangjun
 * 2018-09-15 14:23
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(200, "成功"),
    SEND_FAIL(205,"发送失败"),
    NOT_FIND(100, "未查询到值"),
    ADD_SUCCESS(201, "添加成功"),
    ADD_ERROR(202, "添加失败"),
    UPDATE_SUCCESS(301, "修改成功"),
    UPDATE_ERROR(302, "修改失败"),
    DELETE_SUCCESS(401,"删除成功"),
    DELETE_ERROR(402,"删除失败"),
    LACK_PARAMETER(500,"缺少参数"),
    CHECK_SUCCESS(203,"验证成功"),
    CHECK_ERROR(204,"验证失败"),
    NOTHING(206,"试用期未结束");


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
