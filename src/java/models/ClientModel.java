/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Clients;
import helpers.PasswordHelper;
import helpers.PersistenceHelper;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
public class ClientModel implements Serializable {

    private EntityManager em;
    //private EntityTransaction et;

    public ClientModel() {
        em = PersistenceHelper.getEntityManager();
    }

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
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(client);
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            return false;
        }

    }
}
