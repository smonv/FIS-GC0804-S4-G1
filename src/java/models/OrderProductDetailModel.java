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

    public boolean createListOrderProductDetails(List<OrderProductDetails> opds, int order_id) {
        try {
            for (OrderProductDetails opd : opds) {
                opd.setOrderId(new Orders(order_id));
                em.persist(opd);
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
}
