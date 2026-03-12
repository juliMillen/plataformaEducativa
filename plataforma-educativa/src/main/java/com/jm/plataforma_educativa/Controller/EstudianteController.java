package com.jm.plataforma_educativa.Controller;

import com.jm.plataforma_educativa.Entity.Curso;
import com.jm.plataforma_educativa.Entity.Estudiante;
import com.jm.plataforma_educativa.Service.EstudianteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class EstudianteController {

    @Autowired
    private EstudianteService studentService;

    @GetMapping("")
    public ResponseEntity<List<Estudiante>> getAllStudents(){
        List<Estudiante> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getStudentById(@PathVariable Long id){
        Estudiante toSearch = studentService.getStudentById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Estudiante> createStudent(@RequestBody Estudiante student){
        Estudiante toCreate = studentService.createStudent(student);
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @PatchMapping("/add/course/{id}")
    public ResponseEntity<Estudiante> addCourse(@PathVariable Long id, @RequestBody Curso course){
        Estudiante student = studentService.addCourse(id,course);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Estudiante> updateStudent(@PathVariable Long id, @RequestBody Estudiante student){
        Estudiante toUpdate = studentService.updateStudent(id,student);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student delete succesfully", HttpStatus.NOT_FOUND);
    }
}
