package com.gomes8.To_Do_List.controllers;

import com.gomes8.To_Do_List.model.Tarefa;
import com.gomes8.To_Do_List.services.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefasService service;

    @GetMapping
    public List<Tarefa> listatTarefas() {
        return service.listarTarefas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorID(@PathVariable Long id) {
        Optional<Tarefa> tarefas = service.buscarTarefaPorID(id);
        return tarefas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Tarefa> buscarTarefaPorNome(@PathVariable String nome) {
        Optional<Tarefa> tarefas = service.buscaTarefaPorNome(nome);
        return tarefas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = service.adicionarTarefa(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = service.update(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> marcarComoconcluida(@PathVariable Long id) {
        Tarefa tarefaConcluida = service.markAsCompleted(id);
        return ResponseEntity.ok(tarefaConcluida);
    }

}
