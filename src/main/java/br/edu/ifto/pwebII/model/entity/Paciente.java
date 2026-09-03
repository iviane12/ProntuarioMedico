package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity
public class Paciente implements Serializable {

    // Define que o atributo logo abaixo 'id' será a chave primária da tabela no banco de dados
    @Id

    //Configura o banco de dados para gerar os números do ID de forma automática e sequencial '1, 2, 3, 4...'
    //Do tipo auto-incremento
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;

    // Define que UM paciente pode estar associado a MUITAS consultas.
    // O 'mappedBy' indica que o mapeamento principal foi feito no atributo 'paciente' da classe Consulta.
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    public Paciente() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}