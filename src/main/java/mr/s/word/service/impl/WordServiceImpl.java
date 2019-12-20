package mr.s.word.service.impl;

import mr.s.word.entity.WordEntity;
import mr.s.word.param.WordParam;
import mr.s.word.repository.WordRepository;
import mr.s.word.service.WordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    public Page<WordEntity> getWordList(Integer page, Integer size, String word) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "english"));
        boolean isEmpty = word == null || "".equals(word.trim());
        if (isEmpty) {
            return wordRepository.findAll(pageRequest);
        }
        WordEntity wordEntity = new WordEntity();
        wordEntity.setEnglish(word.trim());
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("english", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<WordEntity> example = Example.of(wordEntity, matcher);
        return wordRepository.findAll(example, pageRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWord(WordParam wordParam) {
        WordEntity wordEntity = new WordEntity();
        BeanUtils.copyProperties(wordParam, wordEntity);
        wordRepository.save(wordEntity);
    }


    @Override
    public WordEntity getWordService(Integer id) {
        Optional<WordEntity> optional = wordRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delWord(Integer id) {
        wordRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editWord(WordEntity wordEntity) {
        wordRepository.save(wordEntity);
    }

    @Override
    public WordEntity getWord(String english) {
        WordEntity wordEntity = new WordEntity();
        wordEntity.setEnglish(english);
        Optional<WordEntity> optional = wordRepository.findOne(Example.of(wordEntity));
        return optional.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatch(List<Integer> idList) {
        wordRepository.deleteWordEntitiesByIdIn(idList);
    }
}
