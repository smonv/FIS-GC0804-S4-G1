/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
    
    String username;
    String password;
    boolean status;
    
    public AuthenticationBean() {
    }
    
    public String authenticate() {
        ClientModel cm = new ClientModel();
        boolean result = cm.authenticate(username, password);
        FacesMessage msg = null;
        if (result) {
            msg = new FacesMessage("Login Successfully!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            setStatus(true);
            return "index.xhtml";
        } else {
            msg = new FacesMessage("Wrong username/password!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            setStatus(false);
            return "login.xhtml";
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
    
}
