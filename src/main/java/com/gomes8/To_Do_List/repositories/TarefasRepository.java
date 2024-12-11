package com.gomes8.To_Do_List.repositories;

import com.gomes8.To_Do_List.model.Tarefa;
import com.gomes8.To_Do_List.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findByNome(String nome);

    List<Tarefa> findByStatus(Status status);
}
