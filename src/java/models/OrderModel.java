/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Clients;
import entities.Orders;
import helpers.PersistenceHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cu Beo
 */
@Stateless
public class OrderModel {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public int createOrder(Orders order) {
        try {
            em.persist(order);
            em.flush();
            return order.getOid();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public Orders getById(int oid) {
        try {
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

    public boolean removeOrder(int order_id) {
        try {
            em.remove(order_id);
            return true;
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

    public List<Orders> getOrderByLocalName(String LocalName, int id) {
        List<Orders> orders = em.createNamedQuery("Orders.findByLocationName")
                .setParameter("locationName", LocalName)
                .setParameter("cid", new Clients(id))
                .getResultList();
        return orders;
    }

    public List<Orders> getOrderByLocalAddress(String Localaddress, int id) {
        List<Orders> orders = em.createNamedQuery("Orders.findByLocationAddress")
                .setParameter("locationAddress", Localaddress)
                .setParameter("cid", new Clients(id))
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
