package models;

import entities.Manufacturers;
import entities.Nations;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ManufacturerModel {

    @PersistenceContext
    EntityManager em;

    public List<Manufacturers> getAll() {
        return em.createNamedQuery("Manufacturers.findAll").getResultList();
    }
    
    public List<Manufacturers> getAll(Nations nation) {
        return em.createNamedQuery("Manufacturers.findByNation").setParameter("nation", nation).getResultList();
    }
    

    public Manufacturers getById(int mid) {
        List<Manufacturers> manufacturers = em.createNamedQuery("Manufacturers.findByMid").setParameter("mid", mid).getResultList();
        return manufacturers.size() > 0 ? manufacturers.get(0) : null;
    }

}
