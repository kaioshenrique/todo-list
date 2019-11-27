package br.com.fatene.view.controller;

import br.com.fatene.datasource.TarefaDataRepository;
import br.com.fatene.domain.model.Tarefa;

import java.util.List;

/**
 * @author Kaio Henrique on 11/26/2019
 */
public class TarefaController {

    private TarefaDataRepository repo = new TarefaDataRepository();

    public List<Tarefa> getTasks() {
        return repo.getTarefas();
    }

    public Tarefa getTaskById(int id) {
        return repo.getTarefasById(id);
    }

    public void insertTask(Tarefa tarefa) {
        repo.insert(tarefa);
    }

    public void updateTask(Tarefa tarefa) {
        repo.update(tarefa);
    }

    public void removeTask(int idTarefa) {
        repo.remove(idTarefa);
    }


}
