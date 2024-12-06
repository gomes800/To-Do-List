package com.gomes8.To_Do_List.service;

import com.gomes8.To_Do_List.exceptions.ResourceNotFoundException;
import com.gomes8.To_Do_List.model.Tarefa;
import com.gomes8.To_Do_List.model.enums.Prioridade;
import com.gomes8.To_Do_List.repositories.TarefasRepository;
import com.gomes8.To_Do_List.services.TarefasService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @InjectMocks
    private TarefasService tarefasService;

    @Mock
    private TarefasRepository tarefasRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarTodasAsTarefas() {
        List<Tarefa> tarefasMock = Arrays.asList(
                new Tarefa(1L, "Tarefa 1", "Teste 1", false, Prioridade.ALTA),
                new Tarefa(2L, "Tarefa 2", "Teste 2",true, Prioridade.MEDIA )
        );

        when(tarefasRepository.findAll()).thenReturn(tarefasMock);

        List<Tarefa> tarefas = tarefasService.listarTarefas();

        assertEquals(1L, tarefas.get(0).getId(), "O ID está incorreto.");
        assertEquals("Tarefa 2", tarefas.get(1).getNome(), "O Título está incorreto.");
    }

    @Test
    public void deveAdicionarNovaTarefa() {
        Tarefa novaTarefa = new Tarefa(null, "Tarefa 3", "Teste 3", false, Prioridade.BAIXA);
        Tarefa tarefaSalva = new Tarefa(3L, "Tarefa 3", "Teste 3", false, Prioridade.BAIXA);

        when(tarefasRepository.save(novaTarefa)).thenReturn(tarefaSalva);

        Tarefa resultado = tarefasService.adicionarTarefa(novaTarefa);

        assertEquals("Tarefa 3", resultado.getNome(), "O título da tarefa adicionada está incorreto.");
        assertEquals(3L, resultado.getId(), "O ID da tarefa adicionada está incorreto.");
    }

    @Test
    public void deveAtualizarUmaTarefa() {
        Long idTarefa = 1L;

        Tarefa tarefaExistente = new Tarefa(idTarefa, "Tarefa antiga", "Descrição antiga", false, Prioridade.ALTA);

        Tarefa tarefaAtualizada = new Tarefa(5L, "Tarefa atualizada", "Descrição atualizada", false, Prioridade.BAIXA);

        when(tarefasRepository.getReferenceById(idTarefa)).thenReturn(tarefaExistente);

        when(tarefasRepository.save(tarefaExistente)).thenReturn(tarefaExistente);

        Tarefa resultado = tarefasService.update(idTarefa, tarefaAtualizada);

        assertEquals("Tarefa atualizada", resultado.getNome());
        assertEquals("Descrição atualizada", resultado.getDescricao());
        assertEquals(Prioridade.BAIXA, resultado.getPrioridade());
        assertFalse(resultado.isRealizado());

        verify(tarefasRepository, times(1)).getReferenceById(idTarefa);
        verify(tarefasRepository, times(1)).save(tarefaExistente);
    }

    @Test
    public void deveLancarExcecaoQuandoTarefaNaoForEncontrada() {
        Long idInvalido = 999L;

        when(tarefasRepository.getReferenceById(idInvalido)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> tarefasService.update(idInvalido, new Tarefa()));
    }

    @Test
    public void deletarTarefaPorId() {
        Long idTarefa = 1L;

        doNothing().when(tarefasRepository).deleteById(idTarefa);

        tarefasService.delete(idTarefa);

        verify(tarefasRepository, times(1)).deleteById(idTarefa);
    }


}