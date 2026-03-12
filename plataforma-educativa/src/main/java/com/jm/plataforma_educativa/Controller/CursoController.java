package com.jm.plataforma_educativa.Controller;

import com.jm.plataforma_educativa.Entity.Curso;
import com.jm.plataforma_educativa.Entity.Estudiante;
import com.jm.plataforma_educativa.Entity.Profesor;
import com.jm.plataforma_educativa.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CursoController {

    @Autowired
    private CursoService courseService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> getAllCouses(){
        List<Curso> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCourseById(@PathVariable Long id){
        Curso toSearch = courseService.getById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @GetMapping("/studentList/{id}")
    public ResponseEntity<List<Estudiante>> getAllStudentsByIdCourse(@PathVariable Long id){
        List<Estudiante> studentList = courseService.getAllStudent(id);
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Curso> createCourse(@RequestBody Curso course){
        Curso toCreate = courseService.createCourse(course);
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Curso> updateCourse(@PathVariable Long id, @RequestBody Curso course){
        Curso toUpdate = courseService.updateCourse(id,course);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @PatchMapping("/update/{idCourse}/assignTeacher/{idTeacher}")
    public ResponseEntity<Curso> assignTeacher(@PathVariable Long idCourse, @PathVariable Long idTeacher){
        Curso toUpdate = courseService.assignProfessor(idCourse,idTeacher);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @PatchMapping("/update/{idCourse}/assignStudent/{idStudent}")
    public ResponseEntity<Curso> assignStudent(@PathVariable Long idCourse, @PathVariable Long idStudent){
        Curso toUpdate = courseService.assignStudent(idCourse,idStudent);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long id){
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course delete correctly", HttpStatus.NOT_FOUND);

    }
}
