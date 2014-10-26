/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client;

import entities.OrderProductDetails;
import entities.PaymentTypes;
import entities.Products;
import helpers.SessionHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.NoResultException;
import models.PaymentTypeModel;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class OrderBean implements Serializable {

    @EJB
    private PaymentTypeModel paymentTypeModel;

    @EJB
    private ProductModel productModel;

    private int pid;
    private int quantity;
    private String location_name;
    private String location_address;
    private int paymentTypeId;
    Products currentProduct;
    Map<String, Object> session;
    private HtmlDataTable selected_products;
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }

    public List<Products> getListProductSelectBox() {
        List<Products> products = productModel.getAllForSelectBox();
        return products;
    }

    public List<PaymentTypes> getListPaymentType() {
        return paymentTypeModel.getAll();
    }

    public Products getProduct(int id) {
        Products p = productModel.getById(id);
        return p;
    }

    public List<OrderProductDetails> getSelectedProducts() {
        session = SessionHelper.getSessionMap();
        if (session.get("order_product_details") == null) {
            List<OrderProductDetails> opds = new ArrayList<>();
            session.put("order_product_details", opds);
            OrderProductDetails opd = new OrderProductDetails();
            opd.setProductId(new Products(1));
            opd.setQuantity(6);
            opds.add(opd);
            return opds;
        } else {
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("order_product_details");
            return opds;
        }
    }

    public void addProduct(AjaxBehaviorEvent event) {
        System.out.println("here");
        session = SessionHelper.getSessionMap();
        if (session.get("order_product_details") == null) {
            List<OrderProductDetails> opds = new ArrayList<>();
            OrderProductDetails opd = new OrderProductDetails();
            opd.setProductId(new Products(pid));
            opd.setQuantity(quantity);
            opds.add(opd);
            session.put("order_product_details", opds);
            System.out.println(opds.size());
        } else {
            boolean exists = false;
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("order_product_details");
            for (OrderProductDetails opd : opds) {
                if (opd.getProductId().getPid() == pid) {
                    opd.setQuantity(opd.getQuantity() + quantity);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                OrderProductDetails opd = new OrderProductDetails();
                opd.setProductId(new Products(pid));
                opd.setQuantity(quantity);
                opds.add(opd);
            }
            System.out.println(opds.size());
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added!"));
    }

    public int checkSession() {
        int size = 0;
        Map<String, Object> session = SessionHelper.getSessionMap();
        if (session.get("order_product.details") != null) {
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("order_product_details");
            size = opds.size();
        }

        return size;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Products getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Products currentProduct) {
        this.currentProduct = currentProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public HtmlDataTable getSelected_products() {
        return selected_products;
    }

    public void setSelected_products(HtmlDataTable selected_products) {
        this.selected_products = selected_products;
    }
    
}
