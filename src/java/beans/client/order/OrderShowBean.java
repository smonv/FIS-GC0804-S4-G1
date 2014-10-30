/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.OrderModel;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class OrderShowBean {

    @EJB
    private ProductModel productModel;

    @EJB
    private OrderModel orderModel;

    private List<Orders> orders;

    private int oid;

    private int statusid;

    private Orders order;

    private Products product;

    private String number;

    private String search;

    public OrderShowBean() {
    }

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (!orderModel.orderExists(number)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            order = orderModel.getByNumber(number);
        }
    }

    public int getTotalOrderProduct(Orders order) {
        List<OrderProductDetails> opds = order.getOrderProductDetailsList();
        return opds.size();
    }

    public long getTotalOrderCost(Orders order) {
        long totalCost = 0;
        List<OrderProductDetails> opds = order.getOrderProductDetailsList();
        for (OrderProductDetails opd : opds) {
            totalCost += productModel.getById(opd.getProductId().getPid()).getPrice() * opd.getQuantity();
        }
        return totalCost;
    }

    public List<Orders> getOrders() {
            orders = orderModel.getListOrder(1);//test=id truc tiep
            return orders;
        
    }

    

    public String showInfo(){
        //  search="dwqdqwd1";
        orders = orderModel.getListOrderbylocaladdress(search, 1);
        if(search.equals("")&&statusid ==0){
            orders =null;
            return "lists.xhtml";
        }
        if (statusid > 0) {
            orders = orderModel.getListOrderByStatus(1, statusid);
            statusid=0;
            return "lists.xhtml";
        }
        if (orders.size() > 0) {
            statusid=0;
            search="";
            return "lists.xhtml";
        } else {
            orders = orderModel.getListOrderbylocalname(search, 1);
            statusid=0;
            search="";
            return "lists.xhtml";
        }

    }

    public Products getCurrentProduct(int pid) {
        product = productModel.getById(pid);
        return product;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

}
