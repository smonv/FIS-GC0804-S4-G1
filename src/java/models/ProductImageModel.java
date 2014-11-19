package models;

import entities.ProductImages;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductImageModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(ProductImages productImage) {
        try {
            em.persist(productImage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean remove(ProductImages productImages){
        try {
            ProductImages remove_pi = em.getReference(ProductImages.class, productImages.getProductImgId());
            em.remove(remove_pi);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
