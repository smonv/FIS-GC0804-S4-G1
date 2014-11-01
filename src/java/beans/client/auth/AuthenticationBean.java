/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.auth;

import entities.Clients;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.ClientModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class AuthenticationBean {

    @EJB
    private ClientModel clientModel;

    String username;
    String password;
    boolean status;
    private String message;

    public AuthenticationBean() {
    }

    public void authenticate() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        if (session.get("msg") != null) {
            session.remove("msg");
        }
        Clients client = clientModel.authenticate(username, password);

        if (client != null) {
            client.setPasswordDigest("");
            ApplicationHelper.addMessage("Logged!");
            SessionHelper.getSessionMap().put("client", client);
            ApplicationHelper.redirect("/index.xhtml", true);
        } else {
            ApplicationHelper.addMessage("Wrong Username / Password");
            ApplicationHelper.redirect("/login.xhtml", true);
        }
    }

    public void logout() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        if (session.get("client") != null) {
            session.remove("client");
            ApplicationHelper.redirect("/index.xhtml", true);
        } else {
            ApplicationHelper.redirect("/403.xhtml", true);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        message = session.get("msg") != null ? session.get("msg").toString() : null;
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
