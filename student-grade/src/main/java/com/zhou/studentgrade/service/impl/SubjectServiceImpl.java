package com.zhou.studentgrade.service.impl;

import com.zhou.studentgrade.dao.SubjectDAO;
import com.zhou.studentgrade.entities.Subject;
import com.zhou.studentgrade.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:33 下午
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectDAO subjectDAO;

    @Override
    public int insertSubject(Subject subject) {
        return this.subjectDAO.insertSubject(subject);
    }

    @Override
    public int updateSubject(Subject subject) {
        return this.subjectDAO.updateSubject(subject);
    }

    @Override
    public List<Subject> selectSubjects(Integer page, Integer limit) {
        if (page == null || page == 0) {
            return this.subjectDAO.selectSubjects(null, null);
        }
        return this.subjectDAO.selectSubjects(limit * (page - 1), limit);
    }

    @Override
    public Subject selectSubjectById(String subId) {
        return this.subjectDAO.selectSubjectById(subId);
    }

    @Override
    public int deleteSubjectById(String subId) {
        return this.subjectDAO.deleteSubjectById(subId);
    }
}
