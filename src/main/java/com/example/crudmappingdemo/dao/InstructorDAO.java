package com.example.crudmappingdemo.dao;


import com.example.crudmappingdemo.entity.Course;
import com.example.crudmappingdemo.entity.Instructor;
import com.example.crudmappingdemo.entity.InstructorDetail;
import com.example.crudmappingdemo.entity.Student;

import java.util.List;

public interface InstructorDAO {
    void save(Instructor instructor);

    void deleteInstructorById(int id);

    Instructor findInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void save(Course course);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCourseByStudentId(int id);

    void update(Student student);
}
