package com.zhou.studentgrade.controller;

import com.zhou.studentgrade.entities.CommonResult;
import com.zhou.studentgrade.entities.Score;
import com.zhou.studentgrade.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:34 下午
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @PostMapping("/createScore")
    public CommonResult<Integer> createScore(@RequestBody Score score) {
        int i = this.scoreService.insertScore(score);
        if (i > 0) {
            return new CommonResult<>(200, "新增成功", i);
        } else {
            return new CommonResult<>(444, "新增失败", i);
        }
    }

    @PostMapping("/updateScore")
    public CommonResult<Integer> updateScore(@RequestBody Score score) {
        int i = this.scoreService.updateScoreById(score);
        if (i > 0) {
            return new CommonResult<>(200, "更新成功", i);
        } else {
            return new CommonResult<>(444, "更新成功", i);
        }
    }

    @GetMapping("/listScore")
    public CommonResult<List<Score>> listScore(@RequestParam(value = "stuId", required = false) String stuId,
                                               @RequestParam(value = "subId", required = false) String subId,
                                               @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                               @RequestParam(value = "limit", defaultValue = "100", required = false) Integer limit) {
        List<Score> scores = new ArrayList<>();
        if (stuId == null && subId == null) {
            scores = this.scoreService.selectScores(page, limit);
        }else if (stuId != null && subId == null) {
            scores = this.scoreService.selectScoresByStuId(stuId, page, limit);
        } else if (stuId == null) {
            scores = this.scoreService.selectScoresBySubId(subId, page, limit);
        }
        return new CommonResult<>(200, "查询成功", scores);
    }

    @GetMapping("/queryScoreById/{scoreId}")
    public CommonResult<Score> queryScoreById(@PathVariable("scoreId") Integer scoreId) {
        Score score = this.scoreService.selectScoreById(scoreId);
        if (score != null) {
            return new CommonResult<>(200, "查询成功", score);
        }else {
            return new CommonResult<>(404, "查询不存在");
        }

    }

    @DeleteMapping("/deleteScoreById/{scoreId}")
    public CommonResult<Integer> deleteScoreById(@PathVariable("scoreId") Integer scoreId) {
        int i = this.scoreService.deleteScoreById(scoreId);
        if (i > 0) {
            return new CommonResult<>(200, "删除成功");
        }else {
            return new CommonResult<>(444, "删除失败");
        }
    }
}
