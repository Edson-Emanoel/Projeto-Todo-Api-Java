package com.treinamentos.javaspring.repostiory;

import org.springframework.data.repository.CrudRepository;

import com.treinamentos.javaspring.model.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Long> {}