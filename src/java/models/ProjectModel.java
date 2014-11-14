package models;

import entities.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjectModel {

    @PersistenceContext
    EntityManager em;

    public List<Projects> getAll() {
        return em.createNamedQuery("Projects.findAll").getResultList();
    }

    public boolean create(Projects project) {
        boolean result = false;
        try {
            em.persist(project);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
