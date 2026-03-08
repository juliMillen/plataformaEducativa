package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.Entity.Profesor;
import com.jm.plataforma_educativa.Repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private IProfesorRepository profesorRepository;

    public List<Profesor> getAllProfessors(){
        return profesorRepository.findAll();
    }

    public Profesor getById(Long id){
        return searchProfessor(id);
    }

    public Profesor createProfessor(Profesor professor){
        return profesorRepository.save(professor);
    }

    public Profesor updateProfessor(Long id, Profesor professor){
        Profesor toUpdate = searchProfessor(id);
        if(toUpdate != null){
            toUpdate.setSurname(professor.getSurname());
            toUpdate.setCourseList(professor.getCourseList());
            return profesorRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteById(Long id){
        profesorRepository.deleteById(id);
    }

    public Profesor searchProfessor(Long id){
        Profesor toSearch = profesorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        return toSearch;
    }
}
