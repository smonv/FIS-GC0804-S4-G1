/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import entities.OrderProductDetails;
import entities.Orders;
import helpers.ApplicationHelper;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import models.OrderModel;
import models.OrderProductDetailModel;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class OrderNewDetailsBean {

    @EJB
    private ProductModel productModel;
    @EJB
    private OrderProductDetailModel orderProductDetailModel;
    @EJB
    private OrderModel orderModel;
    private int oid;
    private Orders order;
    private List<OrderProductDetails> opds;
    private OrderProductDetails detail;
    private HtmlDataTable ordertable;

    /**
     * Creates a new instance of OrderDetailsBean
     */
    public OrderNewDetailsBean() {
    }

    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {

            if (!orderModel.orderExists(oid)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            if (order == null) {
                order = orderModel.getById(oid);
            }
        }
    }

    public long getTotalOrderCost() {
        long totalCost = 0;
        if (order != null) {
            opds = orderProductDetailModel.getByOrderId(order);
            if (opds.size() > 0) {
                for (OrderProductDetails opd : opds) {
                    totalCost += productModel.getProductPriceById(opd.getProductId().getPid()) * opd.getQuantity();
                }
            }
        }

        return totalCost;
    }

    public String showdetail(){//phan show list order
        try {
        Orders o=(Orders)ordertable.getRowData();
        detail= orderProductDetailModel.getSingeByOrderId(o);
        return "showdetail";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return null;
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

    public OrderProductDetails getDetail() {
        return detail;
    }

    public void setDetail(OrderProductDetails detail) {
        this.detail = detail;
    }

    public HtmlDataTable getOrdertable() {
        return ordertable;
    }

    public void setOrdertable(HtmlDataTable ordertable) {
        this.ordertable = ordertable;
    }

}
