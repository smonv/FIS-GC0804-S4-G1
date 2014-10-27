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
    
    public List<Orders> getListOrder(String username){
        try {
        Clients cl= em.createNamedQuery("Clients.findByUsername", Clients.class).setParameter("username", username).getSingleResult();
        List<Orders> orders=cl.getOrdersList();
        return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
