package models;

import entities.Categories;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryModel {

    @PersistenceContext
    EntityManager em;

    public List<Categories> getAllCategory() {
        List<Categories> categories = em.createNamedQuery("Categories.findAll").getResultList();
        return categories;
    }

    public Categories getById(int categoryId) {
        List<Categories> categories = em.createNamedQuery("Categories.findByCid").setParameter("cid", categoryId).getResultList();
        return categories.size() > 0 ? categories.get(0) : null;
    }

}
