package br.com.fatene.view.component;

import br.com.fatene.domain.model.Tarefa;
import br.com.fatene.view.controller.TarefaController;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kaio Henrique on 11/27/2019
 */
class InserirTarefa extends JFrame {

    private static final String DATA_FORMAT = "dd/MM/yyyy";

    private DefaultTableModel model;
    private JPanel pBackground;
    private JButton btnSalvar, btnLimpar;
    private JLabel lTitulo;
    private JLabel lDescricao;
    private JLabel lDtInicio;
    private JLabel lDtFim;
    private JLabel lStatus;
    private JTextField titulo;
    private JXDatePicker dtInicio, dtFim;
    private JTextArea descricao;
    private JCheckBox status;

    private TarefaController controller = new TarefaController();

    InserirTarefa(DefaultTableModel m) {
        super("Adicionar Tarefa");
        montarJanela();
        model = m;
    }

    private void montarJanela() {
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(60, 300, 80, 40);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(150, 300, 80, 40);

        lTitulo = new JLabel("Titulo*: ");
        lTitulo.setBounds(50, 40, 100, 20);

        lDescricao = new JLabel("Descrição: ");
        lDescricao.setBounds(50, 80, 100, 20);

        lDtInicio = new JLabel("Data Inicio: ");
        lDtInicio.setBounds(50, 180, 100, 20);

        lDtFim = new JLabel("Data Fim: ");
        lDtFim.setBounds(50, 220, 100, 20);

        lStatus = new JLabel("Status: ");
        lStatus.setBounds(50, 260, 100, 20);

        titulo = new JTextField();
        titulo.setBounds(150, 40, 200, 20);

        descricao = new JTextArea(5, 5);
        descricao.setLineWrap(true);
        descricao.setBounds(150, 80, 200, 80);

        dtInicio = new JXDatePicker();
        dtInicio.setFormats(DATA_FORMAT);
        dtInicio.setBounds(150, 180, 120, 20);

        dtFim = new JXDatePicker();
        dtFim.setFormats(DATA_FORMAT);
        dtFim.setBounds(150, 220, 120, 20);

        status = new JCheckBox();
        status.setBounds(150, 260, 100, 20);

        pBackground = new JPanel();
        pBackground.setLayout(null);
        pBackground.add(lTitulo);
        pBackground.add(titulo);
        pBackground.add(lDescricao);
        pBackground.add(descricao);
        pBackground.add(lDtInicio);
        pBackground.add(dtInicio);
        pBackground.add(lDtFim);
        pBackground.add(dtFim);
        pBackground.add(lStatus);
        pBackground.add(status);
        pBackground.add(btnSalvar);
        pBackground.add(btnLimpar);

        getContentPane().add(pBackground);
        setSize(new Dimension(800, 420));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        btnSalvar.addActionListener(new BtnSalvarTarefa());
        btnLimpar.addActionListener(new BtnLimparCampos());
    }

    private class BtnSalvarTarefa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (titulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos com (*).");
            } else {
                Tarefa t = new Tarefa();
                t.setTitulo(titulo.getText());
                t.setDescricao(descricao.getText());
                t.setDtInicio(convertDateToString(dtInicio.getDate()));
                t.setDtFim(convertDateToString(dtFim.getDate()));
                if (status.isSelected()) {
                    t.setStatus(true);
                } else {
                    t.setStatus(false);
                }

                controller.insertTask(t);
                ListarTarefas.search(model);

                setVisible(false);
            }
        }
    }

    private class BtnLimparCampos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            titulo.setText("");
            descricao.setText("");
            dtInicio.setDate(null);
            dtFim.setDate(null);
            status.setSelected(false);
        }
    }

    private String convertDateToString(Date date) {
        if (date != null) {
            DateFormat mask = new SimpleDateFormat(DATA_FORMAT);
            return mask.format(date);
        } else {
            return "";
        }
    }

}
