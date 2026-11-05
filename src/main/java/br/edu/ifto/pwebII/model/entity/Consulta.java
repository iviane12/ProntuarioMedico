package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity
public class Consulta implements Serializable {

    // @Id declara que este atributo será o identificador Chave Primária da tabela.
    @Id
    // @GeneratedValue com a estratégia IDENTITY diz que o próprio banco vai gerar esse número Auto-incremento.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data e hora são obrigatórias.")
    @FutureOrPresent(message = "A data da consulta não pode estar no passado.")
    private LocalDateTime data;

    @NotNull(message = "O valor é obrigatório.")
    @Positive(message = "O valor deve ser maior que zero.")
    private Double valor;

    private String observacao;


    @NotNull(message = "Selecione um paciente.")
    // @ManyToOne indica o relacionamento Muitos para Um. Várias consultas pertencem a um único Paciente.
    // A classe Consulta é a "dona" do relacionamento lado forte. É ela quem guarda o ID do paciente.
    @ManyToOne
    // @JoinColumn é usada para definir o nome exato da coluna que será a Chave Estrangeira no banco.
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;


    @NotNull(message = "Selecione um médico.")
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;


    public Consulta() {}

    // Getters e Setters (Essenciais para o Spring MVC ler e gravar os dados nos formulários HTML)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}