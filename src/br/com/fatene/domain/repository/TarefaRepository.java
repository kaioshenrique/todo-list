package br.com.fatene.domain.repository;

import br.com.fatene.domain.model.Tarefa;

import java.util.List;

/**
 * @author Kaio Henrique on 11/25/2019
 */
public interface TarefaRepository {

    void insert(Tarefa tarefa);
    void update(Tarefa tarefa);
    void remove(int idTarefa);
    List<Tarefa> getTarefas();

}
