package beans.client.profile;

import entities.Clients;
import helpers.ApplicationHelper;
import helpers.PasswordHelper;
import helpers.SessionHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.ClientModel;

@ManagedBean
@ViewScoped
public class ClientChangePassword {

    @EJB
    private ClientModel clientModel;

    private Clients client;
    private String currentPassword;
    private String newPassword;
    private Map<String, Object> session;

    public ClientChangePassword() {
    }

    public void init() {

    }

    public void changePassword() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        session = SessionHelper.getSessionMap();
        client = (Clients) session.get("client");
        Clients update_client = clientModel.authenticate(client.getUsername(), currentPassword);
        if (update_client == null) {
            ApplicationHelper.addMessage("Wrong current password!");
            ApplicationHelper.redirect("/client/profile.xhtml", true);
            return;
        } else {
            update_client.setPasswordDigest(PasswordHelper.hashPassword(newPassword, null));
            boolean result = clientModel.changePassword(update_client);
            if (result) {
                ApplicationHelper.addMessage("Password changed!");
            } else {
                ApplicationHelper.addMessage("Failed to change password!");
            }
            ApplicationHelper.redirect("/client/profile.xhtml", true);
        }
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
