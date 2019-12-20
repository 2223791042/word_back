package mr.s.word.controller;

import mr.s.word.entity.WordEntity;
import mr.s.word.param.WordParam;
import mr.s.word.service.WordService;
import mr.s.word.util.ErrorUtils;
import mr.s.word.util.ResultUtils;
import mr.s.word.vo.ResultVO;
import mr.s.word.vo.TableListVO;
import mr.s.word.vo.WordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.S
 * @date 2019/12/18
 */
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/getWordList")
    public ResultVO getWordList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "size", defaultValue = "5") Integer size,
                                @RequestParam(name = "word") String word) {
        Page<WordEntity> wordPage = wordService.getWordList(page, size, word);
        List<WordVO> wordList = wordPage.get().map(wordEntity -> {
            WordVO wordVO = new WordVO();
            BeanUtils.copyProperties(wordEntity, wordVO);
            return wordVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(new TableListVO<>(wordPage.getNumber(), wordPage.getTotalElements(), wordList));
    }

    @PostMapping("/addWord")
    public ResultVO addWord(@Validated @RequestBody WordParam wordParam,
                            BindingResult result) {
        String msg = ErrorUtils.handleErrors(result);
        if (msg != null) {
            return ResultUtils.fail(msg);
        }
        WordEntity wordEntity = wordService.getWord(wordParam.getEnglish());
        if (wordEntity != null) {
            return ResultUtils.fail("添加失败，单词已存在!");
        }
        try{
            wordService.saveWord(wordParam);
            return ResultUtils.success();
        }catch (Exception e) {
            return ResultUtils.fail("添加失败!");
        }
    }

    @DeleteMapping("/delWord/{id}")
    public ResultVO delWord(@PathVariable("id") Integer id) {
        WordEntity wordEntity = wordService.getWordService(id);
        if (wordEntity == null) {
            return ResultUtils.fail("删除 id 不存在!");
        }
        try {
            wordService.delWord(id);
        }catch (Exception e) {
            return ResultUtils.fail("删除失败!");
        }
        return ResultUtils.success();
    }

    @PutMapping("/editWord/{id}")
    public ResultVO editWord(@PathVariable("id") Integer id,
                             @Validated @RequestBody WordParam wordParam,
                             BindingResult result) {
        String msg = ErrorUtils.handleErrors(result);
        if (msg != null) {
            return ResultUtils.fail(msg);
        }
        WordEntity wordEntity = wordService.getWordService(id);
        if (wordEntity == null) {
            return ResultUtils.fail("修改 id 不存在!");
        }
        WordEntity saveWord = wordService.getWord(wordParam.getEnglish());
        boolean canEdit = saveWord == null || saveWord.getId().equals(wordEntity.getId());
        if (!canEdit) {
            return ResultUtils.fail("修改失败，当前单词已存在!");
        }
        BeanUtils.copyProperties(wordParam, wordEntity);
        try {
            wordService.editWord(wordEntity);
        }catch (Exception e) {
            return ResultUtils.fail("修改失败!");
        }
        return ResultUtils.success();
    }

    @DeleteMapping("/delBatch/{ids}")
    public ResultVO delBatch(@PathVariable("ids") String ids) {
        boolean isEmpty = ids == null || "".equals(ids.trim());
        if (isEmpty) {
            return ResultUtils.fail("批量删除，必须要有 id !");
        }
        String[] idArr = ids.split(",");
        List<Integer> idList = new ArrayList<>(20);
        try{
            for (String id : idArr) {
                idList.add(Integer.parseInt(id));
            }
        }catch (Exception e) {
            return ResultUtils.fail("批量删除，id 存在不合法情况!");
        }
        try {
            wordService.delBatch(idList);
        }catch (Exception e) {
            return ResultUtils.fail("批量删除失败!");
        }
        return ResultUtils.success();
    }
}
