/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Clients;
import helpers.PersistenceHelper;
import helpers.PasswordHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ClientModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class RegisterBean {

    String name;
    String username;
    String email;
    String password;
    String contact_details;
    String address;
    String company_name;

    public RegisterBean() {
    }

    public String register() {
        Clients client = new Clients();
        //before validate
        client.setName(name);
        client.setUsername(username);
        client.setEmail(email);
        client.setPasswordDigest(password);
        client.setContactDetails(contact_details);
        client.setClientAddress(address);
        client.setCompanyName(company_name);
        //after validate
        try {
            client.setPasswordDigest(PasswordHelper.hashPassword(password, null));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.setCreateAt(PersistenceHelper.getCurrentTime());
        //save client to db
        ClientModel cm = new ClientModel();
        boolean result = cm.register(client);

        return "register.xhtml";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact_details() {
        return contact_details;
    }

    public void setContact_details(String contact_details) {
        this.contact_details = contact_details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

}
