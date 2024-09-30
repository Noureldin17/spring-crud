package com.example.crudmappingdemo.dao;


import com.example.crudmappingdemo.entity.Course;
import com.example.crudmappingdemo.entity.Instructor;
import com.example.crudmappingdemo.entity.InstructorDetail;
import com.example.crudmappingdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorDAOImpl implements InstructorDAO{

    private EntityManager entityManager;

    @Autowired
    public InstructorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor =  entityManager.find(Instructor.class,id);
        entityManager.remove(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
       return entityManager.find(Instructor.class,id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
        instructorDetail.getInstructor().setInstructorDetail(null);
//        instructorDetail.getInstructor().setFirstName("Marwan");
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data",id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i join fetch i.courses join fetch i.instructorDetail where i.id = :data", Instructor.class
        );
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c join fetch c.students where c.id = :data", Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s join fetch s.courses where s.id = :data", Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
