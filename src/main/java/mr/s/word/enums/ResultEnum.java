package mr.s.word.enums;

import lombok.Getter;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Getter
public enum ResultEnum {
    /**
     * 404 未找到
     * 200 成功
     * 100 失败
     */
    SUCCESS(200, "成功"),
    FAIL(100, "失败"),
    NOT_FOUND(404, "未找到");
    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
