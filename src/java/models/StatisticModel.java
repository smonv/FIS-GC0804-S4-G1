package models;

import entities.Contracts;
import entities.Orders;
import entities.Projects;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class StatisticModel {

    @PersistenceContext
    EntityManager em;

    public long countOrder(Date start, Date end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Orders> o = query.from(Orders.class);
        query.select(cb.count(o.get("oid")));
        query.where(cb.greaterThanOrEqualTo(o.<Date>get("createAt"), start));
        query.where(cb.lessThanOrEqualTo(o.<Date>get("createAt"), end));
        return em.createQuery(query).getSingleResult();
    }

    public long countContract(Date start, Date end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Contracts> c = query.from(Contracts.class);
        query.select(cb.count(c.get("cid")));
        query.where(cb.greaterThanOrEqualTo(c.<Date>get("createAt"), start));
        query.where(cb.lessThanOrEqualTo(c.<Date>get("createAt"), end));
        return em.createQuery(query).getSingleResult();
    }

    public long countProject(Date start, Date end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Projects> p = query.from(Projects.class);
        query.select(cb.count(p.get("pid")));
        query.where(cb.greaterThanOrEqualTo(p.<Date>get("createAt"), start));
        query.where(cb.lessThanOrEqualTo(p.<Date>get("createAt"), end));
        return em.createQuery(query).getSingleResult();
    }
    
    public List<Projects> getProjectsStart(Date start, Date end){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projects> query = cb.createQuery(Projects.class);
        Root<Projects> p = query.from(Projects.class);
        query.select(p);
        query.where(cb.greaterThanOrEqualTo(p.<Date>get("startAt"), start));
        query.where(cb.lessThanOrEqualTo(p.<Date>get("startAt"), end));
        return em.createQuery(query).getResultList();
    }
    
    public List<Projects> getProjectsEnd(Date start, Date end){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projects> query = cb.createQuery(Projects.class);
        Root<Projects> p = query.from(Projects.class);
        query.select(p);
        query.where(cb.greaterThanOrEqualTo(p.<Date>get("endAt"), start));
        query.where(cb.lessThanOrEqualTo(p.<Date>get("endAt"), end));
        return em.createQuery(query).getResultList();
    }
}
