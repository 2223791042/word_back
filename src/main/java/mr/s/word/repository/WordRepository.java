package mr.s.word.repository;

import mr.s.word.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
public interface WordRepository extends JpaRepository<WordEntity, Integer>{
    /**
     * 批量删除
     * @param ids id 集合
     */
    void deleteWordEntitiesByIdIn(List<Integer> ids);
}
