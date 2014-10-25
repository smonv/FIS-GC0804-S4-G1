/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
@Stateless
public class ProductModel {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Products> getAll() {
        List<Products> products = em.createNamedQuery("Products.findAll").getResultList();
        return products;
    }

    public List<Products> getAllForSelectBox() {
        List<Products> products = em.createNamedQuery("Products.getForSelectBox").getResultList();
        return products;
    }

    public Products getById(int pid) {
        List<Products> products = em.createNamedQuery("Products.findByPid").setParameter("pid", pid).getResultList();
        if (products.size() > 0) {
            return products.get(0);
        }
        return null;
    }

}
