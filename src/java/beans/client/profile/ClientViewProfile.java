package beans.client.profile;

import entities.Clients;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.ClientModel;

@ManagedBean
@ViewScoped
public class ClientViewProfile {

    @EJB
    private ClientModel clientModel;

    private Clients client;
    private Map<String, Object> session;
    private String name;
    private String email;
    private String address;
    private String contact_detail;
    private String company_name;

    public ClientViewProfile() {
    }

    public void init() {
        session = SessionHelper.getSessionMap();
        client = (Clients) session.get("client");
        if (!FacesContext.getCurrentInstance().isPostback()) {
            name = client.getName();
            email = client.getEmail();
            address = client.getClientAddress();
            contact_detail = client.getContactDetails();
            company_name = client.getCompanyName();
        }
    }

    public void update() {
        if (!email.equals(client.getEmail())) {
            boolean exists = clientModel.emailExists(email);
            if (!exists) {
                ApplicationHelper.addMessage("Email already exists! Please use another email!");
                ApplicationHelper.redirect("/client/profile.xhtml", true);
                return;
            }
        }
        session = SessionHelper.getSessionMap();
        client = (Clients) session.get("client");
        client.setName(name);
        client.setClientAddress(address);
        client.setContactDetails(contact_detail);
        client.setCompanyName(company_name);

        boolean result = clientModel.update(client);
        if (result) {
            ApplicationHelper.addMessage("Profile Updated!");
        } else {
            ApplicationHelper.addMessage("Failed to update profile!");
        }
        ApplicationHelper.redirect("/client/profile.xhtml", true);
        return;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_detail() {
        return contact_detail;
    }

    public void setContact_detail(String contact_detail) {
        this.contact_detail = contact_detail;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

}
