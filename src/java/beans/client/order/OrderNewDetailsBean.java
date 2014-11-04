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
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            order = orderModel.getById(oid);
        }
    }

    public BigDecimal getTotalOrderCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        if (order != null) {
            opds = orderProductDetailModel.getByOrderId(order);
            if (opds != null) {
                List<Products> products = productModel.getAll();
                for (OrderProductDetails opd : opds) {
                    for (Products p : products) {

                        totalCost = totalCost.add(p.getPrice().multiply(new BigDecimal(opd.getQuantity())));

                        ////add contruction price
                        BigDecimal totalHeight = opd.getHeightOfFloor().multiply(new BigDecimal(opd.getFloors()));
                        totalCost = totalCost.add(p.getConstructionPrice().multiply(totalHeight));
                    }
                }
            }
        }

        return totalCost.setScale(2, RoundingMode.HALF_UP);
    }

    public String showdetail() {//phan show list order
        try {
            Orders o = (Orders) ordertable.getRowData();
            detail = orderProductDetailModel.getSingeByOrderId(o);
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
