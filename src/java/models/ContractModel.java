package models;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import entities.Contracts;
import entities.Orders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class ContractModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(Contracts contract) {
        try {
            em.persist(contract);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contracts getById(int cid) {
        List<Contracts> contracts = em.createNamedQuery("Contracts.findByCid").setParameter("cid", cid).getResultList();
        return contracts.size() > 0 ? contracts.get(0) : null;
    }

    public List<Contracts> getAll() {
        return em.createNamedQuery("Contracts.findAll").getResultList();
    }

    public List<Contracts> getAll(int page, int pageSize, Orders order, Date fromDate, Date endDate) {
        int startIndex = page * pageSize;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contracts> q = cb.createQuery(Contracts.class);
        Root<Contracts> contract = q.from(Contracts.class);
        q.select(contract);

        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (order != null) {
            predicates.add(cb.equal(contract.get("orderId"), order));
        }
        if (fromDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(contract.<Date>get("createAt"), fromDate));
        }

        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(contract.<Date>get("createAt"), endDate));
        }
        q.where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(q).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
    }

    public long countAll(Orders order, Date fromDate, Date endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<Contracts> contract = q.from(Contracts.class);
        q.select(cb.count(contract.get("cid")));
        
        List<Predicate> predicates = new ArrayList<>(); // create list of where
        if (order != null) {
            predicates.add(cb.equal(contract.get("orderId"), order));
        }
        if (fromDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(contract.<Date>get("createAt"), fromDate));
        }

        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(contract.<Date>get("createAt"), endDate));
        }
        q.where(predicates.toArray(new Predicate[]{}));
        
        return (long) em.createQuery(q).getSingleResult();
    }

}
