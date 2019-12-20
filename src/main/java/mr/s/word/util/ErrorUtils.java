package mr.s.word.util;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
public class ErrorUtils {
    private ErrorUtils() {
    }

    public static String handleErrors(BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage
            ).collect(Collectors.joining(","));
        }
        return null;
    }
}
