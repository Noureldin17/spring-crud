package com.example.crudmappingdemo;

import com.example.crudmappingdemo.dao.InstructorDAO;
import com.example.crudmappingdemo.entity.Course;
import com.example.crudmappingdemo.entity.Instructor;
import com.example.crudmappingdemo.entity.InstructorDetail;
import com.example.crudmappingdemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudMappingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudMappingDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO){
        return runner -> {
//            createInstructor(instructorDAO);
//            findInstru ctorById(instructorDAO);
//            deleteInstructorById(instructorDAO);
//            findInstructorDetail(instructorDAO);
//            deleteInstructorDetail(instructorDAO);
//            createInstructorWithCourses(instructorDAO);
//            findInstructorWithCourses(instructorDAO);
//            createCourseWithStudents(instructorDAO);
//            getCourseWithStudents(instructorDAO);
//            getStudentWithCourses(instructorDAO);
//            addCourseToStudent(instructorDAO);
        };
    }

    private void addCourseToStudent(InstructorDAO instructorDAO) {
        int id = 2;
        Student student = instructorDAO.findStudentAndCourseByStudentId(id);
        Course course1 = new Course("Rubik's Cube - How to speed cube");
        Course course2 = new Course("Atari 2600 - Game development");
        student.addCourse(course1);
        student.addCourse(course2);
        System.out.println("Student : " + student);
        System.out.println("Courses : " + student.getCourses());

        instructorDAO.update(student);
    }

    private void getStudentWithCourses(InstructorDAO instructorDAO) {
        int id = 2;
        System.out.println("Fetching Student...");
        Student student = instructorDAO.findStudentAndCourseByStudentId(id);
        System.out.println("Student : " + student);
        System.out.println("Courses : " + student.getCourses());
    }

    private void getCourseWithStudents(InstructorDAO instructorDAO) {
        int id = 10;
        System.out.println("Fetching Course...");
        Course theCourse =  instructorDAO.findCourseAndStudentsByCourseId(id);
        System.out.println("Course : " + theCourse);
        System.out.println("Students : " + theCourse.getStudents());

    }

    private void createCourseWithStudents(InstructorDAO instructorDAO) {
        Course tempCourse = new Course("Pacman - How to score one million points");

        Student tempStudent1 = new Student("Noureldin", "Mohamed","noureldin@luv2code.com");
        Student tempStudent2 = new Student("Marwan", "Mohamed","marwan@luv2code.com");
        Student tempStudent3 = new Student("Ahmed", "Mohamed","ahmed@luv2code.com");

        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);
        tempCourse.addStudent(tempStudent3);

        System.out.println("Saving Course And Students... ");
        System.out.println("Course : " + tempCourse);
        System.out.println("Students : " + tempCourse.getStudents());

        instructorDAO.save(tempCourse);

        System.out.println("Saved!!");

    }

    private void findInstructorWithCourses(InstructorDAO instructorDAO) {
        int id = 1;
        System.out.println("Finding instructor by id ...  id = " + id);
        Instructor instructor = instructorDAO.findInstructorByIdJoinFetch(id);
        System.out.println("Instructor : " + instructor );

//        List<Course> courses = instructorDAO.findCoursesByInstructorId(id);
//        instructor.setCourses(courses);
        System.out.println("Courses : " + instructor.getCourses());

    }

    private void createInstructorWithCourses(InstructorDAO instructorDAO) {
        Instructor instructor = new Instructor("Noureldin","Mohamed","noureldin@luv2code.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.luv2code.com/youtube","Luv 2 code");
        instructor.setInstructorDetail(instructorDetail);
        Course course1 = new Course("Air Guitar - The Ultimate Guide");
        Course course2 = new Course("React - The Complete Guide");
        Course course3 = new Course("Spring Boot - The Ultimate Guide");
        instructor.add(course1);
        instructor.add(course2);
        instructor.add(course3);
        System.out.println("Saving instructor...  " + instructor);
        System.out.println("Courses  " + instructor.getCourses());
        instructorDAO.save(instructor);
        System.out.println("Instructor Saved !!");
    }

    private void deleteInstructorDetail(InstructorDAO instructorDAO) {
        int id = 3;
        System.out.println("Deleting instructor detail ...  ");
        instructorDAO.deleteInstructorDetailById(id);
        System.out.println("Instructor detail Deleted !!  ");

    }

    private void findInstructorDetail(InstructorDAO instructorDAO) {
        int id = 2;
        System.out.println("Finding instructor detail ...  ");
        InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
        System.out.println("Instructor detail found - " + instructorDetail);
        System.out.println("Finding instructor...  ");
        System.out.println("Instructor found - " + instructorDetail.getInstructor());

    }

    private void deleteInstructorById(InstructorDAO instructorDAO) {
        int id = 1;
        System.out.println("Deleting Instructor with id - " + id);
        instructorDAO.deleteInstructorById(id);
        System.out.println("Instructor Deleted !!");
    }

    private void findInstructorById(InstructorDAO instructorDAO) {
        int id = 1;

        System.out.println("Finding instructor...  ");
        Instructor dbInstructor = instructorDAO.findInstructorById(id);
        System.out.println("Instructor - " + dbInstructor);
        System.out.println("Instructor Details - " + dbInstructor.getInstructorDetail());
    }

    private void createInstructor(InstructorDAO instructorDAO) {
        Instructor instructor = new Instructor("Noureldin","Mohamed","noureldin@luv2code.com");
        InstructorDetail instructorDetail = new InstructorDetail("https://www.luv2code.com/youtube","Luv 2 code");
        instructor.setInstructorDetail(instructorDetail);
        System.out.println("Saving instructor...  " + instructor);
        instructorDAO.save(instructor);
        System.out.println("Instructor Saved !!");
    }
}
