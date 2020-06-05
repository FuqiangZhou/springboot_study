package com.zhou.studentgrade.dao;

import com.zhou.studentgrade.entities.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:19 下午
 */
public interface ScoreDAO {

    int insertScore(Score score);

    int updateScoreById(Score score);

    List<Score> selectScores(@Param("page") Integer page, @Param("limit") Integer limit);

    List<Score> selectScoresByStuId(@Param("stuId") String stuId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<Score> selectScoresBySubId(@Param("subId") String subId, @Param("page") Integer page, @Param("limit") Integer limit);

    Score selectScoreById(@Param("scoreId") Integer scoreId);

    Score selectScore(@Param("stuId") String stuId, @Param("subId") String subId);

    int deleteScoreById(@Param("scoreId") Integer scoreId);
}
