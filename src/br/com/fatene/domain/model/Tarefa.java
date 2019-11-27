package br.com.fatene.domain.model;

/**
 * @author Kaio Henrique on 11/25/2019
 */
public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String dtInicio;
    private String dtFim;
    private boolean status;

    public Tarefa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String status() {
        if (status) {
            return "Concluída";
        } else {
            return "Não concluída";
        }
    }

    public String dataInicio() {
        if (dtInicio.isEmpty()) {
            return "-";
        } else {
            return getDtInicio();
        }
    }

    public String dataFim() {
        if (dtFim.isEmpty()) {
            return "-";
        } else {
            return getDtFim();
        }
    }
}
