package com.gomes8.To_Do_List.repositories;

import com.gomes8.To_Do_List.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findByNome(String nome);
}
