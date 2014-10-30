/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.order;

import entities.OrderProductDetails;
import entities.Orders;
import entities.PaymentTypes;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import helpers.SessionHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
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
public class OrderEditBean {
    
    @EJB
    private OrderProductDetailModel orderProductDetailModel;
    
    @EJB
    private PaymentTypeModel paymentTypeModel;
    @EJB
    private ProductModel productModel;
    @EJB
    private OrderModel orderModel;
    private Orders order;
    private String number;
    private String locationName;
    private String locationAddress;
    private int paymentId;
    private Map<String, Object> session = SessionHelper.getSessionMap();
    private Products currentProduct;
    private HtmlDataTable edit_products;
    private int quantity;
    
    public OrderEditBean() {
    }
    
    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (!orderModel.orderExists(number)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }

            //get order
            order = orderModel.getByNumber(number);
            locationName = order.getLocationName();
            locationAddress = order.getLocationAddress();
            paymentId = order.getPaymentType().getPtid();
            boolean reload = false;
            if (session.get("edit_products") == null) {
                reload = true;
                
            } else {
                List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("edit_products");
                if (opds.size() > 0) {
                    if (!Objects.equals(opds.get(0).getOrderId().getOid(), order.getOid())) {
                        reload = true;
                    }
                } else {
                    reload = true;
                }
            }
            if (reload) {
                session.put("edit_products", order.getOrderProductDetailsList());
            }
        }
    }
    
    public void updateInfo() {
        if (!locationName.isEmpty() && !locationAddress.isEmpty()) {
            order = orderModel.getByNumber(number);
            order.setLocationName(locationName);
            order.setLocationAddress(locationAddress);
            order.setPaymentType(new PaymentTypes(paymentId));
            order.setUpdateAt(PersistenceHelper.getCurrentTime());
            
            boolean result = orderModel.update(order);
            if (result) {
                ApplicationHelper.addMessage("Order updated!");
            } else {
                ApplicationHelper.addMessage("Failed to update order!");
            }
            ApplicationHelper.redirect("/client/order/details.xhtml?number=" + number, true);
        } else {
            ApplicationHelper.addMessage("Please fill all blank field!");
            ApplicationHelper.redirect("/client/order/edit_info.xhtml?number=" + number, true);
        }
    }
    
    public List<OrderProductDetails> getListOrderProducts() {
        return (List<OrderProductDetails>) session.get("edit_products");
    }
    
    public List<PaymentTypes> getListPaymentTypes() {
        return paymentTypeModel.getAll();
    }
    
    public Products getCurrentProduct(int pid) {
        if (currentProduct == null) {
            currentProduct = productModel.getById(pid);
        } else if (currentProduct.getPid() != pid) {
            currentProduct = productModel.getById(pid);
        }
        
        return currentProduct;
    }
    
    public int getTotalSelectedProducts() {
        int total = 0;
        List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("edit_products");
        total = opds.size();
        return total;
    }
    
    public long getTotalSelectedProductsPrice() {
        long totalPrice = 0;
        List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("edit_products");
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
    
    public void updateSelectedProduct() {
        
        OrderProductDetails opd = (OrderProductDetails) edit_products.getRowData();
        if (opd.getQuantity() > 0 && opd.getQuantity() < 10) {
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("edit_products");
            for (OrderProductDetails o : opds) {
                if (Objects.equals(o.getProductId().getPid(), opd.getProductId().getPid())) {
                    o.setQuantity(opd.getQuantity());
                }
            }
        } else {
            ApplicationHelper.addMessage("Quantity min 1 and max 10");
        }
        
        ApplicationHelper.redirect("/client/order/edit_products.xhtml?number=" + number, true);
    }
    
    public void removeSelectedProduct() {
        int index = -1;
        OrderProductDetails opd = (OrderProductDetails) edit_products.getRowData();
        List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("edit_products");
        if (opds.size() == 1) {
            ApplicationHelper.addMessage("Order need one or more products!");
            ApplicationHelper.redirect("/client/order/edit_products.xhtml?number=" + number, true);
        }
        
        for (OrderProductDetails o : opds) {
            if (Objects.equals(o.getProductId().getPid(), opd.getProductId().getPid())) {
                index = opds.indexOf(o);
            }
        }
        
        if (index >= 0) {
            opds.remove(index);
            ApplicationHelper.addMessage("Product removed!");
        }
        ApplicationHelper.redirect("/client/order/edit_products.xhtml?number=" + number, true);
    }
    
    public void updateProducts() {
        Orders current_order = orderModel.getByNumber(number);
        List<OrderProductDetails> change = new ArrayList<>();
        List<OrderProductDetails> remove = new ArrayList<>();
        List<OrderProductDetails> add = new ArrayList<>();
        
        List<OrderProductDetails> current_opds = (List<OrderProductDetails>) session.get("edit_products");
        List<OrderProductDetails> order_opds = current_order.getOrderProductDetailsList();
        for (OrderProductDetails co : current_opds) {
            boolean exists = false; // vòng ngoài có, vong trong k có
            for (OrderProductDetails oo : order_opds) {
                if (Objects.equals(oo.getOpdid(), co.getOpdid())) {
                    oo.setQuantity(co.getQuantity());
                    change.add(oo);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                add.add(co);
            }
        }
        
        for (OrderProductDetails oo : order_opds) {
            boolean exists = false;
            for (OrderProductDetails co : current_opds) {
                if (Objects.equals(co.getOpdid(), oo.getOpdid())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                remove.add(oo);
            }
        }
        
        if (orderProductDetailModel.createList(add) && orderProductDetailModel.updateList(change) && orderProductDetailModel.removeList(remove)) {
            ApplicationHelper.addMessage("Order products updated!");
            session.remove("edit_products");
            List<OrderProductDetails> updated_opds = orderProductDetailModel.getByOrderId(current_order);
            current_order.setOrderProductDetailsList(updated_opds);
        } else {
            ApplicationHelper.addMessage("Order products update fail!");
        }
        ApplicationHelper.redirect("/client/order/details.xhtml?number=" + number, true);
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public String getLocationAddress() {
        return locationAddress;
    }
    
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
    
    public int getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public HtmlDataTable getEdit_products() {
        return edit_products;
    }
    
    public void setEdit_products(HtmlDataTable edit_products) {
        this.edit_products = edit_products;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
