package com.zhou.studentgrade.dao;

import com.zhou.studentgrade.entities.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:19 下午
 */
public interface StudentDAO {

    int insertStudent(Student student);

    int updateStudentById(Student student);

    List<Student> selectStudents(@Param("page") Integer page, @Param("limit") Integer limit);

    Student selectStudentById(@Param("stuId") String stuId);

    int deleteStudentById(@Param("stuId") String stuId);
}
