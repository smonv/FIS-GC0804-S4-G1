package models;

import entities.Clients;
import entities.ListStatus;
import entities.Orders;
import helpers.ApplicationHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class OrderModel {

    @PersistenceContext
    EntityManager em;

    public Orders checkOrderId(String oid) {
        boolean valid = false;
        if (ApplicationHelper.isInteger(oid)) {
            int real_oid = Integer.parseInt(oid);
            List<Orders> orders = em.createNamedQuery("Orders.findByOid").setParameter("oid", real_oid).getResultList();
            return orders.size() > 0 ? orders.get(0) : null;
        }
        return null;
    }

    public List<Orders> getAll() {
        List<Orders> orders = em.createNamedQuery("Orders.findAll").getResultList();
        return orders;
    }

    public List<Orders> getAll(int page, int pageSize, String number, ListStatus status, Date from, Date to) {
        int startIndex = page * pageSize;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> query = cb.createQuery(Orders.class);
        Root<Orders> o = query.from(Orders.class);
        query.select(o);
        List<Predicate> predicates = new ArrayList<>();
        if (number != null) {
            predicates.add(cb.like(o.get("number"), "%" + number + "%"));
        }
        if (status != null) {
            predicates.add(cb.equal(o.get("orderStatus"), status));
        }
        if (from != null) {
            predicates.add(cb.greaterThanOrEqualTo(o.<Date>get("createAt"), from));
        }
        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(o.<Date>get("createAt"), to));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(query).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
    }

    public long countAll(String number, ListStatus status, Date from, Date to) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Orders> o = query.from(Orders.class);
        query.select(cb.count(o.get("oid")));
        List<Predicate> predicates = new ArrayList<>();
        if (number != null) {
            predicates.add(cb.like(o.get("number"), "%" + number + "%"));
        }
        if (status != null) {
            predicates.add(cb.equal(o.get("orderStatus"), status));
        }
        if (from != null) {
            predicates.add(cb.greaterThanOrEqualTo(o.<Date>get("createAt"), from));
        }
        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(o.<Date>get("createAt"), to));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(cb.desc(o.get("createAt")));
        return em.createQuery(query).getSingleResult();
    }

    public long getTotalOrder() {
        long totalOrder = (long) em.createNamedQuery("Orders.totalOrder").getSingleResult();
        return totalOrder;
    }

    public Orders createOrder(Orders order) {
        try {
            em.persist(order);
            em.flush();
            em.refresh(order);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Orders getById(int oid) {
        try {
            em.clear();
            List<Orders> orders = em.createNamedQuery("Orders.findByOid").setParameter("oid", oid).getResultList();
            if (orders.size() > 0) {
                return orders.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Orders getByNumber(String number) {
        List<Orders> orders = em.createNamedQuery("Orders.findByNumber").setParameter("number", number).getResultList();
        return orders.size() > 0 ? orders.get(0) : null;
    }

    public List<Orders> getListByNumber(String number, int page, int pageSize) {
        int startIndex = page * pageSize;
        List<Orders> orders = em.createNamedQuery("Orders.findByNumber")
                .setParameter("number", number)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
        return orders;
    }

    public List<Orders> getListByStatus(ListStatus status, int page, int pageSize) {
        int startIndex = page * pageSize;
        List<Orders> orders = em.createNamedQuery("Orders.findByStatus")
                .setParameter("orderStatus", status)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();

        return orders;
    }

    public long getCountByStatus(ListStatus status) {
        long totalOrder = (long) em.createNamedQuery("Orders.countByStatus").setParameter("orderStatus", status).getSingleResult();
        return totalOrder;
    }

    public boolean removeOrder(Orders order) {
        try {
            if (em.find(Orders.class, order.getOid()) != null) {
                em.remove(order);
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean orderExists(int order_id) {
        long result = (long) em.createNamedQuery("Orders.orderExists").setParameter("oid", order_id).getSingleResult();
        return result > 0;
    }

    public boolean orderExists(String number) {
        long result = (long) em.createNamedQuery("Orders.orderNumberExists").setParameter("number", number).getSingleResult();
        return result > 0;
    }

    public List<Orders> getListOrder(int clientId) {
        try {
            List<Orders> orders = em.createNamedQuery("Orders.findByClientId").setParameter("cid", new Clients(clientId)).getResultList();
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Orders> getListOrderbylocalname(String Name, int Id) {
        List<Orders> orders = em.createNamedQuery("Orders.findByLocationName").setParameter("locationName", Name).setParameter("cid", new Clients(Id)).getResultList();
        return orders;
    }

    public List<Orders> getListOrderbylocaladdress(String Address, int Id) {
        List<Orders> orders = em.createNamedQuery("Orders.findByLocationAddress")
                .setParameter("locationAddress", Address)
                .setParameter("cid", new Clients(Id))
                .getResultList();
        return orders;
    }

    public List<Orders> getListOrderByStatus(int clientId, int statusId) {
        List<Orders> orders = em.createNamedQuery("Orders.findByClientIdAndStatus")
                .setParameter("cid", new Clients(clientId))
                .setParameter("orderStatus", new ListStatus(statusId))
                .getResultList();
        return orders;
    }

    public List<Orders> getListOrderByNumber(String number, int clientId) {
        List<Orders> orders = em.createNamedQuery("Orders.searchByNumber")
                .setParameter("number", "%" + number + "%")
                .setParameter("cid", new Clients(clientId))
                .getResultList();
        return orders;
    }

    public boolean update(Orders order) {
        try {
            if (em.find(Orders.class, order.getOid()) != null) {
                em.merge(order);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
