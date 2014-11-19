package models;

import entities.Admins;
import entities.Contracts;
import entities.ListStatus;
import entities.Orders;
import entities.Projects;
import java.util.ArrayList;
import java.util.Date;
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
public class ProjectModel {

    @PersistenceContext
    EntityManager em;

    public List<Projects> getAll() {
        return em.createNamedQuery("Projects.findAll").getResultList();
    }

    public List<Projects> getAll(boolean isPublic, ListStatus status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projects> query = cb.createQuery(Projects.class); //create new query
        Root<Projects> p = query.from(Projects.class); //add from table project for query
        query.select(p);
        query.where(cb.equal(p.get("isPublic"), isPublic));
        query.where(cb.equal(p.get("projectStatus"), status));
        query.orderBy(cb.desc(p.get("createAt")));
        return em.createQuery(query).setMaxResults(10).getResultList();
    }

    public List<Projects> getAll(int page, int pageSize,
            Admins admin, Orders order, ListStatus status,
            Date startFrom, Date startTo, Date endFrom, Date endTo) {
        int startIndex = page * pageSize;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projects> query = cb.createQuery(Projects.class); //create new query
        Root<Projects> p = query.from(Projects.class); //add from table project for query
        Join<Projects, Contracts> c = p.join("contractId", JoinType.LEFT); //join with table contract by contractId
        //Join<Contracts, Orders> o = c.join("orderId", JoinType.LEFT); //join with table order by orderId in contrac table
        query.select(p); //add select * to query;

        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (admin != null) {
            predicates.add(cb.equal(p.get("adminId"), admin));
        }
        if (order != null) {
            predicates.add(cb.equal(c.get("orderId"), order));
        }
        if (status != null) {
            predicates.add(cb.equal(p.get("projectStatus"), status));
        }
        if (startFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(p.<Date>get("startAt"), startFrom));
        }
        if (startTo != null) {
            predicates.add(cb.lessThanOrEqualTo(p.<Date>get("startAt"), startTo));
        }
        if (endFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(p.<Date>get("endAt"), endFrom));
        }
        if (endTo != null) {
            predicates.add(cb.lessThanOrEqualTo(p.<Date>get("endAt"), endTo));
        }

        query.where(predicates.toArray(new Predicate[]{})); //build where conditions;
        query.orderBy(cb.desc(p.get("createAt"))); //order by create time desc

        return em.createQuery(query).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
    }

    public long countAll(Admins admin, Orders order, ListStatus status,
            Date startFrom, Date startTo, Date endFrom, Date endTo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class); //create new query
        Root<Projects> p = query.from(Projects.class); //add from table project for query

        Join<Projects, Contracts> c = p.join("contractId", JoinType.LEFT); //join with table contract by contractId
        query.select(cb.count(p.get("pid"))); //add select count(pid) to query;
        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (admin != null) {
            predicates.add(cb.equal(p.get("adminId"), admin));
        }
        if (order != null) {
            predicates.add(cb.equal(c.get("orderId"), order));
        }
        if (status != null) {
            predicates.add(cb.equal(p.get("projectStatus"), status));
        }
        if (startFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(p.<Date>get("startAt"), startFrom));
        }
        if (startTo != null) {
            predicates.add(cb.lessThanOrEqualTo(p.<Date>get("startAt"), startTo));
        }
        if (endFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(p.<Date>get("endAt"), endFrom));
        }
        if (endTo != null) {
            predicates.add(cb.lessThanOrEqualTo(p.<Date>get("endAt"), endTo));
        }
        query.where(predicates.toArray(new Predicate[]{})); //build where conditions;
        return (long) em.createQuery(query).getSingleResult();
    }

    public boolean create(Projects project) {
        boolean result = false;
        try {
            em.persist(project);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Projects getById(int pid) {
        List<Projects> products = em.createNamedQuery("Projects.findByPid").setParameter("pid", pid).getResultList();
        if (products.size() > 0) {
            return products.get(0);
        }
        return null;
    }

    public boolean update(Projects project) {
        try {
            if (em.find(Projects.class, project.getPid()) != null) {
                em.merge(project);
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
