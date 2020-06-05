package com.zhou.studentgrade.service;

import com.zhou.studentgrade.entities.Student;

import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:30 下午
 */
public interface StudentService {

    int insertStudent(Student student);

    int updateStudentById(Student student);

    List<Student> selectStudents(Integer page, Integer limit);

    Student selectStudentById(String stuId);

    int deleteStudentById(String stuId);
}
