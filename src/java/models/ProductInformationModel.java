package models;

import entities.ProductInformations;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductInformationModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(ProductInformations productInformation) {
        try {
            em.persist(productInformation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ProductInformations productInformation) {
        try {
            if (em.find(ProductInformations.class, productInformation.getPinfoid()) != null) {
                em.merge(productInformation);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
