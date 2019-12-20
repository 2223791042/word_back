package mr.s.word.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Data
@Entity
@Table(name = "t_word")
@EntityListeners(AuditingEntityListener.class)
public class WordEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "english", nullable = false)
    private String english;

    @Column(name = "pronunciation", nullable = false)
    private String pronunciation;

    @Column(name = "chinese", nullable = false)
    private String chinese;

    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;
}
