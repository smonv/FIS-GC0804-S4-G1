package models;

import entities.Admins;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdminModel {

    @PersistenceContext
    EntityManager em;

    public List<Admins> getAll() {
        return em.createNamedQuery("Admins.findAll").getResultList();
    }

    public Admins getById(int aid) {
        List<Admins> admins = em.createNamedQuery("Admins.findByAid").setParameter("aid", aid).getResultList();
        return !admins.isEmpty() ? admins.get(0) : null;
    }
}
