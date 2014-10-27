/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Clients;
import entities.Orders;
import helpers.PasswordHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
@Stateless
public class ClientModel {

    @PersistenceContext
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean authenticate(String username, String password) {
        try {
            Clients client = em.createNamedQuery("Clients.findByUsername", Clients.class).setParameter("username", username).getSingleResult();
            String salt = client.getPasswordDigest().split("_")[1];
            return client.getPasswordDigest().equals(PasswordHelper.hashPassword(password, salt));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean register(Clients client) {
        try {
            em.persist(client);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
}
