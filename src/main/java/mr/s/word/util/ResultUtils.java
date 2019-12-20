package mr.s.word.util;

import lombok.Data;
import mr.s.word.enums.ResultEnum;
import mr.s.word.vo.ResultVO;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Data
public class ResultUtils {
    private ResultUtils() {
    }

    private static ResultVO setResultVO(Integer code, String msg, Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(String msg, Object data) {
       return setResultVO(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static ResultVO success(Object data) {
        return success(ResultEnum.SUCCESS.getMsg(), data);
    }

    public static ResultVO fail(String msg) {
        return setResultVO(ResultEnum.FAIL.getCode(), msg, null);
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO fail() {
        return fail(ResultEnum.FAIL.getMsg());
    }

    public static ResultVO notFound(String msg) {
        return setResultVO(ResultEnum.NOT_FOUND.getCode(), msg, null);
    }
}
