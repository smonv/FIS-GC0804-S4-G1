/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import entities.Clients;
import entities.ListStatus;
import entities.OrderProductDetails;
import entities.Orders;
import entities.PaymentTypes;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import helpers.SessionHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.OrderModel;
import models.OrderProductDetailModel;
import models.PaymentTypeModel;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class OrderCreateBean implements Serializable {

    @EJB
    private OrderProductDetailModel orderProductDetailModel;

    @EJB
    private OrderModel orderModel;

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
    private OrderProductDetails dataItem;
    private String stringQuantity;
    private List<Orders> orders;
    /**
     * Creates a new instance of OrderBean
     */
    public OrderCreateBean() {
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

    public void addProduct(int pid) throws IOException {
        boolean valid = false;
        ExternalContext ec = ApplicationHelper.getExternalContext();
        if (ApplicationHelper.isInteger(stringQuantity)) {
            quantity = Integer.parseInt(stringQuantity);
            if (0 < quantity && quantity <= 10) {
                valid = true;
            }
        }

        if (!valid) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantity between 1 and 10"));
            ec.getFlash().setKeepMessages(true);
            ec.redirect(ec.getRequestContextPath() + "/client/product/show.xhtml?pid=" + pid);
        }

        session = SessionHelper.getSessionMap();
        if (session.get("order_product_details") == null) {
            List<OrderProductDetails> opds = new ArrayList<>();
            OrderProductDetails opd = new OrderProductDetails();
            opd.setProductId(new Products(pid));
            opd.setQuantity(quantity);
            opds.add(opd);
            session.put("order_product_details", opds);
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

            session.put("order_product_details", opds);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added!"));
        ec.redirect(ec.getRequestContextPath() + "/client/order/selected_products.xhtml");
    }

    public String updateSelectdProductQuantity() {
        OrderProductDetails opd = (OrderProductDetails) selected_products.getRowData();
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        for (OrderProductDetails o : opds) {
            if (o.getProductId().getPid() == opd.getProductId().getPid()) {
                o.setQuantity(opd.getQuantity());
            }
        }
        return "selected_products.xhtml";
    }

    public String removeSelectedProduct() {
        int index = -1;
        OrderProductDetails opd = (OrderProductDetails) selected_products.getRowData();
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        for (OrderProductDetails o : opds) {
            if (o.getProductId().getPid() == opd.getProductId().getPid()) {
                index = opds.indexOf(o);
            }
        }

        if (index >= 0) {
            opds.remove(index);
        }
        return "selected_products.xhtml";
    }

    public int getTotalSelectedProducts() {
        int total = 0;
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        total = opds.size();
        return total;
    }

    public long getTotalSelectedProductsPrice() {
        long totalPrice = 0;
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        List<Products> products = productModel.getAll();
        for (OrderProductDetails opd : opds) {
            for (Products p : products) {
                if (opd.getProductId().getPid() == p.getPid()) {
                    totalPrice += opd.getQuantity() * p.getPrice();
                }
            }
        }

        return totalPrice;
    }

    public void newOrder() throws IOException {
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        if (opds.isEmpty()) {
            ApplicationHelper.addMessage("Please select product to make order");
            ApplicationHelper.redirect("/client/order/new.xhtml", true);
        }

        Orders order = new Orders();
        order.setCid(new Clients(8));
        order.setNumber(ApplicationHelper.secureRandomString(16));
        order.setPaymentType(new PaymentTypes(paymentTypeId));
        order.setLocationName(location_name);
        order.setLocationAddress(location_address);
        order.setOrderStatus(new ListStatus(1));
        order.setCreateAt(PersistenceHelper.getCurrentTime());
        int oid = orderModel.createOrder(order);

        boolean result = false;
        if (oid > 0) {
            result = orderProductDetailModel.createListOrderProductDetails(opds, oid);
        }

        if (result) {
            ApplicationHelper.addMessage("Order created!");
            ApplicationHelper.redirect("/client/order/new_order_details.xhtml?oid=" + oid, true);
        } else {
            orderModel.removeOrder(oid);
            ApplicationHelper.addMessage("Failed to create new order!");
            ApplicationHelper.redirect("/client/order/new.xhtml", true);
        }
    }

    public List<Orders> getListOrders() {//phan show list 

        return orderModel.getListOrder(8);

    }

    //////////////////////////////

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

    public OrderProductDetails getDataItem() {
        return dataItem;
    }

    public void setDataItem(OrderProductDetails dataItem) {
        this.dataItem = dataItem;
    }

    public String getStringQuantity() {
        return stringQuantity;
    }

    public void setStringQuantity(String stringQuantity) {
        this.stringQuantity = stringQuantity;
    }

    public List<Orders> getOrders() {
        if(orders == null){
            orders = orderModel.getListOrder(8); // get list order
        }
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    
    

}
