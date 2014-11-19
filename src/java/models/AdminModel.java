package models;

import entities.Admins;
import helpers.PasswordHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public List<Admins> getAllForAdmin(int page, int pageSize, String name) {
        int startIndex = page * pageSize;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Admins> query = cb.createQuery(Admins.class);
        Root<Admins> p = query.from(Admins.class);
        query.select(p);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(cb.equal(p.get("name"), name));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(cb.desc(p.get("createAt")));
        return em.createQuery(query).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
    }

    public long countAll(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Admins> p = query.from(Admins.class);
        query.select(cb.count(p.get("aid")));
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(cb.equal(p.get("name"), name));
        }
        query.where(predicates.toArray(new Predicate[]{}));

        return em.createQuery(query).getSingleResult();
    }

    public boolean update(Admins admin) {
        try {
            if (em.find(Admins.class, admin.getAid()) != null) {
                em.merge(admin);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
