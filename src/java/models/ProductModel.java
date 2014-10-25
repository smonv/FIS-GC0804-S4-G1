/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Products;
import helpers.PersistenceHelper;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Cu Beo
 */
public class ProductModel implements Serializable {

    EntityManager em;

    public ProductModel() {
    }

    public List<Products> fetchAll() {
        em = PersistenceHelper.getEntityManager();
        List<Products> products = null;
        try {
            products = (List<Products>) em.createNamedQuery("Products.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Products> fetchAllSelectBox() {
        em = PersistenceHelper.getEntityManager();
        List<Products> products = null;
        try {
            products = (List<Products>) em.createNamedQuery("Products.getForSelectBox").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public Products fetchOnce(int pid) {
        em = PersistenceHelper.getEntityManager();
        try {
            List<Products> p = (List<Products>) em.createNamedQuery("Products.findByPid").setParameter("pid", pid).getResultList();
            if (p.size() > 0) {
                return p.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
