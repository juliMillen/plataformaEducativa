package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.Entity.Curso;
import com.jm.plataforma_educativa.Entity.Estudiante;
import com.jm.plataforma_educativa.Entity.Profesor;
import com.jm.plataforma_educativa.Repository.ICursoRepository;
import com.jm.plataforma_educativa.Repository.IEstudianteRepository;
import com.jm.plataforma_educativa.Repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IProfesorRepository profesorRepository;

    @Autowired
    private IEstudianteRepository estudianteRepository;

    public List<Curso> getAllCourses(){
        return cursoRepository.findAll();
    }

    public List<Estudiante> getAllStudent(Long id){
        Curso course = searchCourseById(id);
        return course.getStudentList();
    }

    public Curso getById(Long id){
        return searchCourseById(id);
    }

    public Curso createCourse(Curso course){
        return cursoRepository.save(course);
    }

    public Curso assignProfessor(Long idCourse, Long idProfessor){
        Curso course = searchCourseById(idCourse);
        Profesor teacher = profesorRepository.findById(idProfessor).orElseThrow(() -> new RuntimeException("Teacher not exists"));

        course.setTeacher(teacher);
        return cursoRepository.save(course);
    }

    public Curso assignStudent(Long idCourse, Long idStudent){
        Curso course = searchCourseById(idCourse);

        Estudiante student = estudianteRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("Student not exists"));

        course.getStudentList().add(student);

        return cursoRepository.save(course);
    }

    public Curso updateCourse(Long id, Curso course){
        Curso toUpdate = searchCourseById(id);
        if(toUpdate != null){
            toUpdate.setName(course.getName());
            toUpdate.setTeacher(course.getTeacher());
            toUpdate.setStudentList(course.getStudentList());
            return cursoRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteCourse(Long id){
        cursoRepository.deleteById(id);
    }

    public Curso searchCourseById(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("id invalid");
        }
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not exists"));
    }
}
