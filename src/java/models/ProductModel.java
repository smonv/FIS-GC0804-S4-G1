/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Categories;
import entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    public List<Products> getAllProduct(int startIndex, int pageSize) {
        List<Products> products = em.createNamedQuery("Products.findAll").setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
        return products;
    }

    public List<Products> getAll(int startIndex, int pageSize, int category_id) {
        Categories category = new Categories(category_id);
        List<Products> products = em.createNamedQuery("Products.findAllByCategoryId").setParameter("categoryId", category).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
        return products;
    }
    
    public int getAllTotalProducts(){
        List<Products> products = em.createNamedQuery("Products.findAll").getResultList();
        return products.size();
    }
    
    public int getAllTotalProducts(int category_id){
        Categories category = new Categories(category_id);
        List<Products> products = em.createNamedQuery("Products.findAllByCategoryId").setParameter("categoryId", category).getResultList();
        return products.size();
    }
    

    public List<Products> getTop12() {
        List<Products> products = em.createNamedQuery("Products.findAll").setFirstResult(0).setMaxResults(12).getResultList();
        return products;
    }

    public List<Products> getProductsByCategory(int id) {
        List<Products> products = em.createNamedQuery("Products.findByCategoy").setParameter("category_id", id).getResultList();
        return products;
    }

    public List<Products> getProductsByCategorySort(int id) {
        List<Products> products = em.createNamedQuery("Products.findByCategoy").setParameter("category_id", id).getResultList();
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

    public boolean productExists(int pid) {
        long result = (long) em.createNamedQuery("Products.exists").setParameter("pid", pid).getSingleResult();
        return result > 0;
    }

    public long getProductPriceById(int pid) {
        try {
            long price = (long) em.createNamedQuery("Products.getProductPrice").setParameter("pid", pid).getSingleResult();
            return price;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
