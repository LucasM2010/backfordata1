package com.example.demo.controller;

import com.example.demo.model.Curriculum; 
import com.example.demo.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @GetMapping
    public List<Curriculum> getAllCurriculums() { 
        return curriculumRepository.findAll();
    }

    @PostMapping
    public Curriculum createCurriculum(@RequestBody Curriculum curriculum) { 
        return curriculumRepository.save(curriculum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curriculum> updateCurriculum(@PathVariable Long id, @RequestBody Curriculum curriculumDetails) {
        return curriculumRepository.findById(id)
                .map(curriculum -> {
                    curriculum.setNome(curriculumDetails.getNome());
                    curriculum.setEmail(curriculumDetails.getEmail());
                    curriculum.setResumo(curriculumDetails.getResumo());
                    curriculum.setExperiencia(curriculumDetails.getExperiencia());
                    curriculum.setEducacao(curriculumDetails.getEducacao());
                    curriculum.setHabilidades(curriculumDetails.getHabilidades());
                    Curriculum updatedCurriculum = curriculumRepository.save(curriculum);
                    return ResponseEntity.ok(updatedCurriculum); 
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
