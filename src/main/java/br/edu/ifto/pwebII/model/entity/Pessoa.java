package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity

//JOINED: O JPA vai criar uma tabela separada para Pessoa, e ligar as filhas a ela através do ID.
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa  implements java.io.Serializable {

    // Define que o atributo logo abaixo 'id' será a chave primária da tabela no banco de dados
    @Id

    //Configura o banco de dados para gerar os números do ID de forma automática e sequencial '1, 2, 3, 4...'
    //Do tipo auto-incremento
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telefone;

    public Pessoa(){}

    // Getters e Setters


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
