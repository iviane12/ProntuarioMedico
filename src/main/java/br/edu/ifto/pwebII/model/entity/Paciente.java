package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import java.util.List;

// Avisa ao JPA que esta classe é uma entidade e deve virar uma tabela no banco de dados.
@Entity
public class Paciente extends  PessoaFisica {

    // Define que UM paciente pode estar associado a MUITAS consultas. O 'mappedBy' indica que o mapeamento principal foi feito no atributo 'paciente' da classe Consulta.
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

   public List<Consulta>getConsultas(){return consultas;}
   public void setConsultas(List<Consulta>consultas){this.consultas = consultas;}
}