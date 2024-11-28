package com.gomes8.To_Do_List.repositories;

import com.gomes8.To_Do_List.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {

    Optional<Tarefas> findByNome( String nome);
}
