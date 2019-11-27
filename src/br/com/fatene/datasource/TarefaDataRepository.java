package br.com.fatene.datasource;

import br.com.fatene.domain.model.Tarefa;
import br.com.fatene.domain.repository.TarefaRepository;
import br.com.fatene.util.ConnectionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaio Henrique on 11/25/2019
 */
public class TarefaDataRepository implements TarefaRepository {

    private static final String INSERT = "INSERT INTO TB_TASKS (titulo, descricao, dt_inicio, dt_fim, status) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE TB_TASKS SET titulo=?, descricao=?, dt_inicio=?, dt_fim=?, status=? WHERE id=?";
    private static final String DELETE = "DELETE FROM TB_TASKS WHERE id =?";
    private static final String LIST = "SELECT * FROM TB_TASKS";
    private static final String TASKBYID = "SELECT * FROM TB_TASKS WHERE id=?";

    private static final String EMPTY_TEXT = "Nenhum dado informado.";

    private Connection conn;
    private PreparedStatement stmt = null;

    public TarefaDataRepository() {
    }

    @Override
    public void insert(Tarefa tarefa) {
        if (tarefa != null) {
            try {
                executeSQL(INSERT);

                stmt.setString(1, tarefa.getTitulo());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setString(3, tarefa.getDtInicio());
                stmt.setString(4, tarefa.getDtFim());
                stmt.setBoolean(5, tarefa.isStatus());

                stmt.execute();
                JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso.");
                ConnectionDB.closeConnection(conn, stmt);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar tarefa: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, EMPTY_TEXT);
        }
    }

    @Override
    public void update(Tarefa tarefa) {
        if (tarefa != null) {
            try {
                executeSQL(UPDATE);

                stmt.setString(1, tarefa.getTitulo());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setString(3, tarefa.getDtInicio());
                stmt.setString(4, tarefa.getDtFim());
                stmt.setBoolean(5, tarefa.isStatus());
                stmt.setInt(6, tarefa.getId());

                stmt.execute();
                JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso.");
                ConnectionDB.closeConnection(conn, stmt);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, EMPTY_TEXT);
        }
    }

    @Override
    public void remove(int idTarefa) {
        try {
            executeSQL(DELETE);

            stmt.setInt(1, idTarefa);

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Tarefa deletada com sucesso.");
            ConnectionDB.closeConnection(conn, stmt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar tarefa: " + e.getMessage());
        }
    }

    @Override
    public List<Tarefa> getTarefas() {
        ResultSet rs;
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        try {
            executeSQL(LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tarefa task = new Tarefa();

                task.setId(rs.getInt("id"));
                task.setTitulo(rs.getString("titulo"));
                task.setDescricao(rs.getString("descricao"));
                task.setDtInicio(rs.getString("dt_inicio"));
                task.setDtFim(rs.getString("dt_fim"));
                task.setStatus(rs.getBoolean("status"));

                tarefas.add(task);
            }
            ConnectionDB.closeConnection(conn, stmt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível listar suas tarefas.");
        }

        return tarefas;
    }

    @Override
    public Tarefa getTarefasById(int idTarefa) {
        ResultSet rs;
        Tarefa task = new Tarefa();

        try {
            executeSQL(TASKBYID);
            stmt.setInt(1, idTarefa);
            rs = stmt.executeQuery();
            while (rs.next()) {
                task.setId(rs.getInt("id"));
                task.setTitulo(rs.getString("titulo"));
                task.setDescricao(rs.getString("descricao"));
                task.setDtInicio(rs.getString("dt_inicio"));
                task.setDtFim(rs.getString("dt_fim"));
                task.setStatus(rs.getBoolean("status"));
            }
            ConnectionDB.closeConnection(conn, stmt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar sua tarefa.");
        }
        return task;
    }

    private void executeSQL(String sql) throws SQLException, ClassNotFoundException {
        conn = ConnectionDB.getConnection();
        stmt = conn.prepareStatement(sql);
    }
}
