package beans.admin.auth;

import entities.Admins;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.AdminModel;

@ManagedBean
@RequestScoped
public class AuthenticateAdmin {

    @EJB
    private AdminModel adminModel;

    private String username;
    private String password;
    private String message;

    public AuthenticateAdmin() {
    }

    public void authenticate() {

        Map<String, Object> session = SessionHelper.getSessionMap();
        if (session.get("msg") != null) {
            session.remove("msg");
        }

        Admins admin = adminModel.authenticate(username, password);
        if (admin == null) {
            ApplicationHelper.addMessage("Wrong Username/Password!");
            ApplicationHelper.navigate("/login-admin.xhtml");
        } else {
            admin.setPasswordDigest("");
            SessionHelper.getSessionMap().put("admin", admin);
            ApplicationHelper.redirect("/admin/index.xhtml", true);
        }
    }

    public void logout() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        if (session.get("admin") != null) {
            session.remove("admin");
            ApplicationHelper.redirect("/login-admin.xhtml", true);
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

    public String getMessage() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        message = session.get("msg") != null ? session.get("msg").toString() : null;
        return message;
    }

}
