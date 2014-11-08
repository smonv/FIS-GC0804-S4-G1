/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.ListStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SolomonT
 */
@Stateless
public class ListStatusModel {

    @PersistenceContext
    EntityManager em;

    public List<ListStatus> getAll() {
        List<ListStatus> status = em.createNamedQuery("ListStatus.findAll").getResultList();
        return status;
    }

    public ListStatus getById(int status_id) {
        List<ListStatus> status = em.createNamedQuery("ListStatus.findByLsid").setParameter("lsid", status_id).getResultList();
        return status.size() > 0 ? status.get(0) : null;
    }
}
