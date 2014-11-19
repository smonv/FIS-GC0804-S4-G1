/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin.clients;

import entities.Clients;
import entities.Orders;
import helpers.ApplicationHelper;
import helpers.PaginationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import models.ClientModel;
import models.OrderModel;

/**
 *
 * @author BUIVUHUECHI
 */
@ManagedBean
@SessionScoped
public class ClientShowBean {

    @EJB
    private OrderModel orderModel;

    @EJB
    private ClientModel clientModel;

    private List<Clients> clients;

    private Clients acc;

    private PaginationHelper pagination;

    private int cid;

    private DataModel items = null;

    private String search;

    /**
     * Creates a new instance of ClientShowBean
     */
    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (!clientModel.clientExists(cid)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            acc = clientModel.getByCid(cid);
        }
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {

                    return clientModel.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(clientModel.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

            };
        }

        return pagination;
    }

    public int getTotalOrder(Clients client) {
        List<Orders> listOrder = client.getOrdersList();
        return listOrder.size();
    }

    public String showInfo() {

        if (!search.equals("")) {
            recreateModel();
            pagination = new PaginationHelper(5) {

                @Override
                public int getItemsCount() {
                    return clientModel.getListClientByName(search).size();
                }

                ;
            
            @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(clientModel.getClientsByName(search, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        if (clientModel.getListClientByName(search).isEmpty()) {
            recreateModel();
            pagination = new PaginationHelper(5) {

                @Override
                public int getItemsCount() {
                    return clientModel.getListClientByMail(search).size();
                }

                ;
            
            @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(clientModel.getListClientByMail(search));
                }
            };
        }
        if (clientModel.getListClientByName(search).isEmpty() && clientModel.getListClientByMail(search).size() == 0) {
            recreateModel();
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {

                    return clientModel.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(clientModel.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

            };
        }
        return "list.xhtml";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "list.xhtml";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "list.xhtml";
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
