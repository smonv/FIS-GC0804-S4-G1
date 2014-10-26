/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.PaymentTypes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
@Stateless
public class PaymentTypeModel {
    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<PaymentTypes> getAll(){
        return em.createNamedQuery("PaymentTypes.findAll").getResultList();
    }
}
