/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.OrderProductDetails;
import entities.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
@Stateless
public class OrderProductDetailModel {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean createListOrderProductDetails(List<OrderProductDetails> opds, Orders order) {
        try {
            for (OrderProductDetails opd : opds) {
                opd.setOrderId(order);
                em.persist(opd);
                //em.flush();
                opd = null;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<OrderProductDetails> getByOrderId(Orders order) {
        try {
            List<OrderProductDetails> opds = em.createNamedQuery("OrderProductDetails.findByOrderId").setParameter("orderId", order).getResultList();
            return opds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderProductDetails getSingeByOrderId(Orders order) {
        try {
            OrderProductDetails detail = em.createNamedQuery("OrderProductDetails.findByOpdid", OrderProductDetails.class).setParameter("opdid", order.getOid()).getSingleResult();
            return detail;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean update(OrderProductDetails opd) {
        try {
            if (em.find(OrderProductDetails.class, opd.getOpdid()) != null) {
                em.merge(opd);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateList(List<OrderProductDetails> opds) {
        try {
            for (OrderProductDetails opd : opds) {
                if (em.find(OrderProductDetails.class, opd.getOpdid()) != null) {
                    em.merge(opd);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(OrderProductDetails opd) {
        try {
            OrderProductDetails o = em.find(OrderProductDetails.class, opd.getOpdid());
            em.remove(o);
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeList(List<OrderProductDetails> opds) {
        try {
            for (OrderProductDetails opd : opds) {
                OrderProductDetails o = em.find(OrderProductDetails.class, opd.getOpdid());
                em.remove(o);
            }
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createList(List<OrderProductDetails> opds) {
        try {
            for (OrderProductDetails opd : opds) {
                em.persist(opd);
            }
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
