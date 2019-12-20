package mr.s.word.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Data
public class WordVO {
    private Integer id;

    private String english;

    private String pronunciation;

    private String chinese;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
