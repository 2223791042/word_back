package mr.s.word.service;

import mr.s.word.entity.WordEntity;
import mr.s.word.param.WordParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
public interface WordService {
    /**
     * 分页查询
     * @param page 页号
     * @param size 页面大小
     * @param word 英文单词
     * @return 查询结果
     */
    Page<WordEntity> getWordList(Integer page, Integer size, String word);

    /**
     * 保存单词
     * @param wordParam 单词信息
     */
    void saveWord(WordParam wordParam);

    /**
     * 根据主键获取单词实体
     * @param id 单词 id
     * @return 对应单词
     */
    WordEntity getWordService(Integer id);

    /**
     * 根据 id 删除对应单词实体
     * @param id 删除单词 id
     */
    void delWord(Integer id);

    /**
     * 修改对应单词实体
     * @param wordEntity 修改单词实体
     */
    void editWord(WordEntity wordEntity);

    /**
     * 根据单词英文查询单词
     * @param english 单词英文
     * @return 对应单词
     */
    WordEntity getWord(String english);

    /**
     * 批量删除
     * @param idList id 集合
     */
    void delBatch(List<Integer> idList);
}
