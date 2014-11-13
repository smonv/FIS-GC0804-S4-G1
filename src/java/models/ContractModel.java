package models;

import entities.Contracts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContractModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(Contracts contract) {
        try {
            em.persist(contract);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contracts getById(int cid) {
        List<Contracts> contracts = em.createNamedQuery("Contracts.findByCid").setParameter("cid", cid).getResultList();
        return contracts.size() > 0 ? contracts.get(0) : null;
    }

}
