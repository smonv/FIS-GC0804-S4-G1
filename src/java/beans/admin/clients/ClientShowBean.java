/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin.clients;

import entities.Clients;
import entities.Orders;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.ClientModel;
import models.OrderModel;

/**
 *
 * @author BUIVUHUECHI
 */
@ManagedBean
@RequestScoped
public class ClientShowBean {
    @EJB
    private OrderModel orderModel;

    @EJB
    private ClientModel clientModel;

    private List<Clients> clients;

    private Clients acc;

    private int cid;

    /**
     * Creates a new instance of ClientShowBean
     */

    public void init(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (!clientModel.clientExists(cid)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            acc = clientModel.getByCid(cid);
        }
    }
    
    public int getTotalOrder(Clients client){
        List<Orders> listOrder=client.getOrdersList();
        return listOrder.size();
    }

    public List<Clients> getClients() {
        if (clients == null) {
            clients = clientModel.getListClient();
        }
        return clients;

    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public Clients getAcc() {
        return acc;
    }

    public void setAcc(Clients acc) {
        this.acc = acc;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

}
