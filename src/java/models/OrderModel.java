/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Clients;
import entities.ListStatus;
import entities.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderModel {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

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
