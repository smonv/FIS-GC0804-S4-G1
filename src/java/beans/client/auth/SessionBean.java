package beans.client.auth;

import entities.Clients;
import helpers.SessionHelper;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
