package beans.admin.auth;

import entities.Admins;
import helpers.ApplicationHelper;
import helpers.PasswordHelper;
import helpers.PersistenceHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.AdminModel;

@ManagedBean
@ViewScoped
public class NewAdmin {

    @EJB
    private AdminModel adminModel;
    
    private String name;
    private String email;
    private String username;
    private String password;
    private boolean isSuper;
    private boolean isManager = true;
    
    public NewAdmin() {
    }
    
    public void create() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        password = PasswordHelper.hashPassword(password, null);
        Admins admin = new Admins();
        admin.setName(name);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPasswordDigest(password);
        admin.setIsSuper(isSuper);
        admin.setIsManager(isManager);
        admin.setCreateAt(PersistenceHelper.getCurrentTime());
        boolean result = adminModel.create(admin);
        if (result) {
            ApplicationHelper.addMessage("New Admin created!");
        } else {
            ApplicationHelper.addMessage("Failed to create new admin!");
        }
        ApplicationHelper.navigate("/admin/new.xhtml");
    }

    //SET GET
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
    
    public boolean isIsSuper() {
        return isSuper;
    }
    
    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }
    
    public boolean isIsManager() {
        return isManager;
    }
    
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
    
}
