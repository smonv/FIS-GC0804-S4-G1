/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client;

import entities.OrderProductDetails;
import entities.Orders;
import helpers.ApplicationHelper;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class OrderDetailsBean {

    @EJB
    private ProductModel productModel;
    @EJB
    private OrderProductDetailModel orderProductDetailModel;
    @EJB
    private OrderModel orderModel;
    private int oid;
    private Orders order;
    private List<OrderProductDetails> opds;

    /**
     * Creates a new instance of OrderDetailsBean
     */
    public OrderDetailsBean() {
    }

    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {

            if (oid == 0 && !orderModel.orderExists(oid)) {
                ApplicationHelper.redirect("/client/404.xhtml", false);
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

}
