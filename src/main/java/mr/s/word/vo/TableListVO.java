package mr.s.word.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Data
public class TableListVO<E> {
    private Integer currentPage;
    private Long total;
    private List<E> data;
    public TableListVO(Integer currentPage, Long total, List<E> data) {
        this.currentPage = currentPage;
        this.total = total;
        this.data = data;
    }
}
