package br.edu.ifto.pwebII.model.repository;

import br.edu.ifto.pwebII.model.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ConsultaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Consulta consulta) {
        em.persist(consulta);
    }

    public Consulta buscar(Long id) {
        return em.find(Consulta.class, id);
    }

    public List<Consulta> listar() {
        Query query = em.createQuery("from Consulta");
        return query.getResultList();
    }

    public void update(Consulta consulta) {
        em.merge(consulta);
    }

    public void remove(Long id) {
        Consulta c = em.find(Consulta.class, id);
        em.remove(c);
    }
}