package models;

import entities.Images;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ImageModel {
    @PersistenceContext
    EntityManager em;
    
    public boolean create(Images image){
        try {
            em.persist(image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
