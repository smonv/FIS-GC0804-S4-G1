/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import entities.Clients;
import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public BigDecimal getTotalOrderCost(Orders order) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderProductDetails> opds = order.getOrderProductDetailsList();

        List<Products> products = productModel.getAll();
        for (OrderProductDetails opd : opds) {
            for (Products p : products) {
                if (Objects.equals(opd.getProductId().getPid(), p.getPid())) {

                    //add total product price
                    totalPrice = totalPrice.add(p.getPrice().multiply(new BigDecimal(opd.getQuantity())));
                    //add total contruction price
                    BigDecimal totalHeight = opd.getHeightOfFloor().multiply(new BigDecimal(opd.getFloors()));
                    totalPrice = totalPrice.add(p.getConstructionPrice().multiply(totalHeight).setScale(0, RoundingMode.HALF_UP));
                }
            }
        }

        return totalPrice;
    }

    public List<Orders> getOrders() {
        //Clients current_client = SessionHelper.getCurrentClient();
        //orders = orderModel.getListOrder(current_client.getCid());

        orders = orderModel.getListOrder(1);
        return orders;
    }

    public String showInfo() {
        //  search="dwqdqwd1";
        Clients current_client = SessionHelper.getCurrentClient();
        orders = orderModel.getListOrderbylocaladdress(search, current_client.getCid());
        if (search.equals("") && statusid == 0) {
            orders = null;
            return "lists.xhtml";
        }
        if (statusid > 0) {
            orders = orderModel.getListOrderByStatus(current_client.getCid(), statusid);
            statusid = 0;
            return "lists.xhtml";
        }
        if (orders.size() > 0) {
            statusid = 0;
            search = "";
            return "lists.xhtml";
        } else {
            orders = orderModel.getListOrderbylocalname(search, current_client.getCid());
            if (orders.isEmpty()) {
                orders = orderModel.getListOrderByNumber(search, current_client.getCid());
            }
            statusid = 0;
            search = "";
            return "lists.xhtml";
        }

    }

    public Products getCurrentProduct(int pid) {
        product = productModel.getById(pid);
        return product;
    }

    public String formateDate(Date date) {
        String formated_date = ApplicationHelper.formatDate(date, "dd-MM-yyyy HH:mm:ss");
        return formated_date;
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
