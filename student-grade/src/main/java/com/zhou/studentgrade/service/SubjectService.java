package com.zhou.studentgrade.service;

import com.zhou.studentgrade.entities.Subject;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:31 下午
 */
public interface SubjectService {

    int insertSubject(Subject subject);

    int updateSubject(Subject subject);

    List<Subject> selectSubjects(Integer page, Integer limit);

    Subject selectSubjectById(String subId);

    int deleteSubjectById(String subId);
}
