package com.zhou.studentgrade.dao;

import com.zhou.studentgrade.entities.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:20 下午
 */
public interface SubjectDAO {

    int insertSubject(Subject subject);

    int updateSubject(Subject subject);

    List<Subject> selectSubjects(@Param("page") Integer page, @Param("limit") Integer limit);

    Subject selectSubjectById(@Param("subId") String subId);

    int deleteSubjectById(@Param("subId") String subId);
}
