package beans.admin.profile;

import entities.Admins;
import helpers.ApplicationHelper;
import helpers.PasswordHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.AdminModel;

@ManagedBean
@ViewScoped
public class AdminEditProfile {
    
    @EJB
    private AdminModel adminModel;

    //bean variable
    private Admins admin;
    private int adminId = 0;
    private String currentPassword;
    private String newPassword;
    //view param
    private String id;
    
    public AdminEditProfile() {
    }
    
    public void init() {
        adminId = ApplicationHelper.isInteger(id) ? Integer.parseInt(id) : 0;
        if (adminId != 0) {
            admin = adminModel.getById(adminId);
        } else {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
    }
    
    public void changePassword() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        String salt = admin.getPasswordDigest().split("_")[1];
        String hashedPassword = PasswordHelper.hashPassword(currentPassword, salt);
        if (admin.getPasswordDigest().equals(hashedPassword)) {
            String newHashedPassword = PasswordHelper.hashPassword(newPassword, null);
            admin.setPasswordDigest(newHashedPassword);
            boolean result = adminModel.update(admin);
            if (result) {
                ApplicationHelper.addMessage("Password changed!");
            } else {
                ApplicationHelper.addMessage("Failed to change password!");
            }
        } else {
            ApplicationHelper.addMessage("Wrong current password!");
        }
        ApplicationHelper.redirect("/admin/profile/edit.xhtml?id=" + admin.getAid(), true);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Admins getAdmin() {
        return admin;
    }
    
    public void setAdmin(Admins admin) {
        this.admin = admin;
    }
    
    public String getCurrentPassword() {
        return currentPassword;
    }
    
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
}
