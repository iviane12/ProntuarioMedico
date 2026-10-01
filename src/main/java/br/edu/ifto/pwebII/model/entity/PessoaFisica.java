package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity
public abstract class PessoaFisica extends Pessoa {

    private String nome;
    private String cpf;


    public PessoaFisica() {}

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }


}