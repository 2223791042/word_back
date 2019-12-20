package mr.s.word.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Data
public class WordParam {

    @NotBlank(message = "单词英文不能为空!")
    private String english;

    @NotBlank(message = "英文发音不能为空!")
    private String pronunciation;

    @NotBlank(message = "单词解释不能为空!")
    private String chinese;
}
