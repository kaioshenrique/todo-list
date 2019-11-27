package br.com.fatene.view.component;

import br.com.fatene.domain.model.Tarefa;
import br.com.fatene.view.controller.TarefaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Kaio Henrique on 11/26/2019
 */
public class ListarTarefas extends JFrame {

    private JPanel pBackground;
    private JPanel pButtons;
    private JTable tabela;
    private JScrollPane pScroll;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;
    private DefaultTableModel model = new DefaultTableModel();

    private static TarefaController controller = new TarefaController();

    public ListarTarefas() {
        super("Tarefas");
        createTable();
        createWindow();
    }

    private void createTable() {
        tabela = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("TITULO");
        model.addColumn("DESCRICAO");
        model.addColumn("DATA INICIO");
        model.addColumn("DATA FIM");
        model.addColumn("STATUS");

        tabela.getColumnModel().getColumn(0).setPreferredWidth(25);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(80);

        search(model);
    }

    static void search(DefaultTableModel model) {
        List<Tarefa> tarefas = controller.getTasks();
        model.setNumRows(0);

        for (Tarefa t : tarefas) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTitulo(),
                    t.getDescricao(),
                    t.dataInicio(),
                    t.dataFim(),
                    t.status()
            });
        }
    }

    private void createWindow() {
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        pButtons = new JPanel();
        pScroll = new JScrollPane(tabela);
        pBackground = new JPanel();

        pBackground.setLayout(new BorderLayout());
        pBackground.add(BorderLayout.CENTER, pScroll);

        pButtons.add(btnAdicionar);
        pButtons.add(btnEditar);
        pButtons.add(btnRemover);
        pBackground.add(BorderLayout.SOUTH, pButtons);

        getContentPane().add(pBackground);
        setSize(new Dimension(800, 420));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        btnAdicionar.addActionListener(new InsertListener());
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InserirTarefa t = new InserirTarefa(model);
            t.setVisible(true);
        }
    }

}
