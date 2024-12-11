package com.gomes8.To_Do_List.services;

import com.gomes8.To_Do_List.exceptions.ResourceNotFoundException;
import com.gomes8.To_Do_List.model.Tarefa;
import com.gomes8.To_Do_List.model.enums.Status;
import com.gomes8.To_Do_List.repositories.TarefasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {

    @Autowired
    private TarefasRepository repository;

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarTarefaPorID(Long id) {
        return repository.findById(id);
    }

    public Optional<Tarefa> buscaTarefaPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Tarefa> buscarPorStatus(Status status) {
        return repository.findByStatus(status);
    }

    public Tarefa update(Long id, Tarefa obj) {
        try {
            Tarefa entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Tarefa entity, Tarefa obj) {
        entity.setNome(obj.getNome());
        entity.setDescricao(obj.getDescricao());
        entity.setPrioridade(obj.getPrioridade());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Tarefa markAsCompleted(Long id) {
        Optional<Tarefa> optionalTarefa = repository.findById(id);

        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setStatus(Status.CONCLUIDA);
            return repository.save(tarefa);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
