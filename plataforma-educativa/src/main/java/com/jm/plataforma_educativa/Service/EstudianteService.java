package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.Entity.Curso;
import com.jm.plataforma_educativa.Entity.Estudiante;
import com.jm.plataforma_educativa.Repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    @Autowired
    private IEstudianteRepository estudianteRepository;

    public List<Estudiante> getAllStudents(){
        return estudianteRepository.findAll();
    }

    public Estudiante getStudentById(Long id){
        return searchStudent(id);
    }

    public Estudiante createStudent(Estudiante student){
        return estudianteRepository.save(student);
    }

    public Set<Curso> getAllCourses(Long id){
        Estudiante student = searchStudent(id);
        return student.getCursos();
    }

    public Estudiante addCourse(Long id, Curso course){
        Estudiante student = searchStudent(id);
        student.getCursos().add(course);
        return estudianteRepository.save(student);
    }

    public Estudiante updateStudent(Long id, Estudiante student){
        Estudiante oldStudent = searchStudent(id);
        if(student != null){
            oldStudent.setSurname(student.getSurname());
            oldStudent.setName(student.getName());
            oldStudent.setCursos(student.getCursos());
            return estudianteRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id){
        if(id == null){
            throw new RuntimeException("ID invalid");
        }
        estudianteRepository.deleteById(id);
    }

    public Estudiante removeCourse(Long id, Curso course){
        Estudiante student = searchStudent(id);
        student.getCursos().remove(course);
        return estudianteRepository.save(student);
    }


    public Estudiante searchStudent(Long id){
        if(id <= 0 || id == null){
            throw new RuntimeException("ID invalid");
        }
        return estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not exists"));
    }
}
