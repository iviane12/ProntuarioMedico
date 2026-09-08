package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import java.util.List;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity

// Diz que a chave primária da tabela Paciente vai se ligar à tabela PessoaFisica
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")

public class Medico extends PessoaJuridica {

    private String crm;

    // Define que UM medico pode estar associado a MUITAS consultas. O 'mappedBy' indica que o mapeamento principal foi feito no atributo 'paciente' da classe Consulta.
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    public Medico() {}

    // Getters e Setters
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}