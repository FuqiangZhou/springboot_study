package com.zhou.studentgrade.service.impl;

import com.zhou.studentgrade.dao.ScoreDAO;
import com.zhou.studentgrade.entities.Score;
import com.zhou.studentgrade.service.ScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:32 下午
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreDAO scoreDAO;

    @Override
    public int insertScore(Score score) {
        return this.scoreDAO.insertScore(score);
    }

    @Override
    public int updateScoreById(Score score) {
        return this.scoreDAO.updateScoreById(score);
    }

    @Override
    public List<Score> selectScores(Integer page, Integer limit) {
        if (page == null || page == 0) {
            return this.scoreDAO.selectScores(null, null);
        }
        return this.scoreDAO.selectScores(limit * (page - 1), limit);
    }

    @Override
    public List<Score> selectScoresByStuId(String stuId, Integer page, Integer limit) {
        if (page == null || page == 0) {
            return this.scoreDAO.selectScoresByStuId(stuId, null, null);
        }
        return this.scoreDAO.selectScoresByStuId(stuId, limit * (page - 1), limit);
    }

    @Override
    public List<Score> selectScoresBySubId(String subId, Integer page, Integer limit) {
        if (page == null || page == 0) {
            return this.scoreDAO.selectScoresByStuId(subId, null, null);
        }
        return this.scoreDAO.selectScoresByStuId(subId, limit * (page - 1), limit);
    }

    @Override
    public Score selectScoreById(Integer scoreId) {
        return this.scoreDAO.selectScoreById(scoreId);
    }

    @Override
    public Score selectScore(String stuId, String subId) {
        return this.scoreDAO.selectScore(stuId, subId);
    }

    @Override
    public int deleteScoreById(Integer scoreId) {
        return this.scoreDAO.deleteScoreById(scoreId);
    }
}
