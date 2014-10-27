/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import entities.OrderProductDetails;
import entities.Orders;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    /**
     * Creates a new instance of OrderShowBean
     */
    public OrderShowBean() {
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

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
