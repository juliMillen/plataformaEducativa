package com.jm.plataforma_educativa.Controller;

import com.jm.plataforma_educativa.Entity.Profesor;
import com.jm.plataforma_educativa.Service.ProfesorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfesorController {

    @Autowired
    private ProfesorService teacherService;


    @GetMapping("")
    public ResponseEntity<List<Profesor>> getAllTeachers(){
        List<Profesor> teacherList = teacherService.getAllProfessors();
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getTeacherById(@PathVariable Long id){
        Profesor toSearch = teacherService.getById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Profesor> createTeacher(@RequestBody Profesor teacher){
        Profesor toCreate = teacherService.createProfessor(teacher);
        return new ResponseEntity<>(toCreate, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Profesor> updateTeacher(@PathVariable Long id, @RequestBody Profesor teacher){
        Profesor toUpdate = teacherService.updateProfessor(id,teacher);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Long id){
        teacherService.deleteById(id);
        return new ResponseEntity<>("Teacher delete correctly", HttpStatus.NOT_FOUND);
    }
}
