/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.auth;

import entities.Clients;
import helpers.SessionHelper;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SolomonT
 */
@ManagedBean
@SessionScoped
public class SessionBean {

    private Clients currentClient;

    public SessionBean() {
    }

    public boolean isClientLogged() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        return session.get("client") != null;
    }

    public Clients getCurrentClient() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        currentClient = session.get("client") != null ? (Clients) session.get("client") : null;
        return currentClient;
    }

    public void setCurrentClient(Clients currentClient) {
        this.currentClient = currentClient;
    }
}
