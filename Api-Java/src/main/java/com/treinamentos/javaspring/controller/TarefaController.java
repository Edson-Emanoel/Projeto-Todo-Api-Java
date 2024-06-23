package com.treinamentos.javaspring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.treinamentos.javaspring.model.Tarefa;
import com.treinamentos.javaspring.repostiory.TarefaRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @CrossOrigin("*")
    @PostMapping("/cadastrar")
    public Tarefa cadastrar(@RequestBody Tarefa obj) {
        return tarefaRepository.save(obj);
    }

    @CrossOrigin("*")
    @GetMapping("/todos")
    public Iterable<Tarefa> listar() {
        return tarefaRepository.findAll();
    }
    
    @CrossOrigin("*")
    @GetMapping("/aluno/{id}")
    public Optional<Tarefa> listarUm(@PathVariable Long id) {
        return tarefaRepository.findById(id);
    }
    
    @CrossOrigin("*")
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("*")
    @PutMapping("/alterar/{id}")
    public Tarefa atualizar(@RequestBody Tarefa obj) {
        if (obj.getId() != null) {
            return tarefaRepository.save(obj);    
        }   return obj;
    }
}