package com.zhou.studentgrade.service.impl;

import com.zhou.studentgrade.dao.StudentDAO;
import com.zhou.studentgrade.entities.Student;
import com.zhou.studentgrade.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2020/3/10 4:32 下午
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDAO studentDAO;

    @Override
    public int insertStudent(Student student) {
        return this.studentDAO.insertStudent(student);
    }

    @Override
    public int updateStudentById(Student student) {
        return this.studentDAO.updateStudentById(student);
    }

    @Override
    public List<Student> selectStudents(Integer page, Integer limit) {
        if (page == null || page == 0) {
            return this.studentDAO.selectStudents(null, null);
        }
        return this.studentDAO.selectStudents(limit * (page - 1), limit);
    }

    @Override
    public Student selectStudentById(String stuId) {
        return this.studentDAO.selectStudentById(stuId);
    }

    @Override
    public int deleteStudentById(String stuId) {
        return this.studentDAO.deleteStudentById(stuId);
    }
}
