package models;

import entities.Admins;
import helpers.PasswordHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdminModel {

    @PersistenceContext
    EntityManager em;

    public List<Admins> getAll() {
        return em.createNamedQuery("Admins.findAll").getResultList();
    }

    public Admins getById(int aid) {
        List<Admins> admins = em.createNamedQuery("Admins.findByAid").setParameter("aid", aid).getResultList();
        return !admins.isEmpty() ? admins.get(0) : null;
    }

    public boolean create(Admins admin) {
        try {
            em.persist(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Admins authenticate(String username, String password) {
        try {
            Admins admin = em.createNamedQuery("Admins.findByUsername", Admins.class).setParameter("username", username).getSingleResult();
            String salt = admin.getPasswordDigest().split("_")[1];
            boolean result = admin.getPasswordDigest().equals(PasswordHelper.hashPassword(password, salt));
            return result ? admin : null;
        } catch (Exception e) {
            return null;
        }
    }
}
