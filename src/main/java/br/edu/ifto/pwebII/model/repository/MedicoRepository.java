package br.edu.ifto.pwebII.model.repository;

import br.edu.ifto.pwebII.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MedicoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Medico medico) {
        em.persist(medico);
    }

    public Medico buscar(Long id) {
        return em.find(Medico.class, id);
    }

    public List<Medico> listar() {
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }

    public void update(Medico medico) {
        em.merge(medico);
    }

    public void remove(Long id) {
        Medico m = em.find(Medico.class, id);
        em.remove(m);
    }
}