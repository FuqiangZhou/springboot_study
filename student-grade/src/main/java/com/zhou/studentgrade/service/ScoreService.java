package com.zhou.studentgrade.service;

import com.zhou.studentgrade.entities.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:31 下午
 */
public interface ScoreService {

    int insertScore(Score score);

    int updateScoreById(Score score);

    List<Score> selectScores(Integer page, Integer limit);

    List<Score> selectScoresByStuId(String stuId, Integer page,Integer limit);

    List<Score> selectScoresBySubId(String subId, Integer page, Integer limit);

    Score selectScoreById(Integer scoreId);

    Score selectScore(String stuId, String subId);

    int deleteScoreById(Integer scoreId);
}
