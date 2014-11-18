package models;

import entities.Categories;
import entities.Manufacturers;
import entities.Nations;
import entities.ProductInformations;
import entities.Products;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class ProductModel {

    @PersistenceContext
    EntityManager em;

    public List<Products> getAll() {
        return em.createNamedQuery("Products.findAll").getResultList();
    }

    public List<Products> getAll(int page, int pageSize,
            Categories category, int minLoad, int maxLoad,
            Manufacturers manufacturer, Nations nation) {
        int startIndex = page * pageSize; //caculate start row select

        CriteriaBuilder cb = em.getCriteriaBuilder(); // create criteria builder
        CriteriaQuery<Products> q = cb.createQuery(Products.class); // create criteria query
        Root<Products> p = q.from(Products.class); // create FROM products
        Join<Products, ProductInformations> pi = p.join("productInformations", JoinType.LEFT); //join with product_infomations table
        Join<ProductInformations, Manufacturers> ma = pi.join("manufacturerId", JoinType.LEFT);

        q.select(p); // create SELECT *

        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (category != null) {
            predicates.add(cb.equal(p.get("categoryId"), category)); // create where categoryId 
        }
        if (minLoad > 0) {
            predicates.add(cb.gt(pi.get("eLoad"), minLoad)); //compare eload greator than min load
        }
        if (maxLoad < 2000) {
            predicates.add(cb.lt(pi.get("eLoad"), maxLoad)); //compare eload less than max load
        }

        if (manufacturer != null) {
            predicates.add(cb.equal(pi.get("manufacturerId"), manufacturer)); //create where manufacturer equal with input manufacturer
        }

        if (nation != null) {
            predicates.add(cb.equal(ma.get("nationId"), nation)); //create where nation equal with input nation
        }

        q.where(predicates.toArray(new Predicate[]{})); //apply where query

        //TypedQuery tq = em.createQuery(q); //tranform to query
        List<Products> products = em.createQuery(q).setFirstResult(startIndex).setMaxResults(pageSize).getResultList(); //get data

        return products;
    }

    public long countAll(Categories category, int minLoad, int maxLoad, Manufacturers manufacturer, Nations nation) {
        CriteriaBuilder cb = em.getCriteriaBuilder(); // create criteria builder
        CriteriaQuery<Long> q = cb.createQuery(Long.class); // create criteria query
        Root<Products> p = q.from(Products.class); // create FROM products
        Join<Products, ProductInformations> pi = p.join("productInformations", JoinType.LEFT); //join with product_infomations table
        Join<ProductInformations, Manufacturers> ma = pi.join("manufacturerId", JoinType.LEFT);
        q.select(cb.count(p.get("pid"))); // create SELECT *

        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (category != null) {
            predicates.add(cb.equal(p.get("categoryId"), category)); // create where categoryId 
        }
        if (minLoad > 0) {
            predicates.add(cb.gt(pi.get("eLoad"), minLoad)); //compare eload greator than min load
        }
        if (maxLoad < 2000) {
            predicates.add(cb.lt(pi.get("eLoad"), maxLoad)); //compare eload less than max load
        }

        if (manufacturer != null) {
            predicates.add(cb.equal(pi.get("manufacturerId"), manufacturer)); //create where produced nation equal with input nation
        }

        if (nation != null) {
            predicates.add(cb.equal(ma.get("nationId"), nation)); //create where nation equal with input nation
        }

        q.where(predicates.toArray(new Predicate[]{})); //apply where query
        return (long) em.createQuery(q).getSingleResult();

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

    public int create(Products product) {
        try {
            em.persist(product);
            em.flush();
            return product.getPid();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    ////get all for admin bean
    public List<Products> getAllAdmin(int page, int pageSize, String code,
            Nations nation, Manufacturers manufacturer) {
        int startIndex = page * pageSize;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Products> query = cb.createQuery(Products.class);
        Root<Products> p = query.from(Products.class); /// from products table
        Join<Products, ProductInformations> pi = p.join("productInformations", JoinType.LEFT); //join with product_infomations table
        Join<ProductInformations, Manufacturers> ma = pi.join("manufacturerId", JoinType.LEFT);
        query.select(p); //select * 

        List<Predicate> predicates = new ArrayList<>();
        if (code != null) {
            predicates.add(cb.like(p.get("code"), "%" + code + "%"));
        }
        if (nation != null) {
            predicates.add(cb.equal(ma.get("nationId"), nation));
        }
        if (manufacturer != null) {
            predicates.add(cb.equal(pi.get("manufacturerId"), manufacturer));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(cb.desc(p.get("createAt")));
        return em.createQuery(query).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
    }

    public long countAllAdmin(String code,
            Nations nation, Manufacturers manufacturer) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Products> p = query.from(Products.class); /// from products table
        Join<Products, ProductInformations> pi = p.join("productInformations", JoinType.LEFT); //join with product_infomations table
        Join<ProductInformations, Manufacturers> ma = pi.join("manufacturerId", JoinType.LEFT);
        query.select(cb.count(p.get("pid")));
        List<Predicate> predicates = new ArrayList<>();
        if (code != null) {
            predicates.add(cb.like(p.get("code"), "%" + code + "%"));
        }
        if (nation != null) {
            predicates.add(cb.equal(ma.get("nationId"), nation));
        }
        if (manufacturer != null) {
            predicates.add(cb.equal(pi.get("manufacturerId"), manufacturer));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(query).getSingleResult();
    }

}
