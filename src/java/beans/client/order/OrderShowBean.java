/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import java.io.IOException;
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

    private Orders order;

    private Products product;

    /**
     * Creates a new instance of OrderShowBean
     */
    public OrderShowBean() {
    }

    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (oid == 0 && !orderModel.orderExists(oid)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            getOrder();
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
        if (orders == null) {
            orders = orderModel.getListOrder(8);
        }
        return orders;
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
        if (order == null) {
            order = orderModel.getById(oid);
        }
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

}
