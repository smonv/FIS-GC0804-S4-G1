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
            return false;
        }
    }
    
    public Images createAndReturnImage(Images image){
        try {
            em.persist(image);
            em.flush();
            return image;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean remove(Images image){
        try {
            Images remove_image = em.getReference(Images.class, image.getImgId());
            em.remove(remove_image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
