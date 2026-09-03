package br.edu.ifto.pwebII.model.repository;

import br.edu.ifto.pwebII.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Repository indica ao Spring que esta classe é responsável por acessar o banco de dados.
@Repository
@Transactional
public class PacienteRepository {

    // O @PersistenceContext injeta automaticamente o EntityManager, que é o
    // recurso responsável por realizar as operações de sincronismo com o
    // banco de dados (inserir, remover, atualizar ou consultar - CRUD)
    @PersistenceContext
    private EntityManager em;

    public void save(Paciente paciente) {
        em.persist(paciente); // Substitui o INSERT
    }

    public Paciente buscar(Long id) {
        return em.find(Paciente.class, id); // Substitui o SELECT * WHERE ID = ?
    }

    public List<Paciente> listar() {
        Query query = em.createQuery("from Paciente"); // Usamos HQL (Hibernate Query Language) aqui
        return query.getResultList();
    }

    public void update(Paciente paciente) {
        em.merge(paciente); // Substitui o UPDATE
    }

    public void remove(Long id) {
        Paciente p = em.find(Paciente.class, id); // Primeiro acha o paciente
        em.remove(p); // Depois exclui (Substitui o DELETE)
    }
}