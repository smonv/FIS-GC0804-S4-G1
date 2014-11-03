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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
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

    //private int pid;
    private int quantity;
    private String location_name;
    private String location_address;
    private int paymentTypeId;
    Products currentProduct;
    Map<String, Object> session = SessionHelper.getSessionMap();
    private HtmlDataTable selected_products;
    private OrderProductDetails dataItem;
    private String stringQuantity;
    private List<Orders> orders;
    private String stringFloor;
    private String stringHeightOfFloor;
    private int floor;
    private long heightOfFloor;

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

    public void addProduct() {
        int pid = 0;
        String stringPid = ApplicationHelper.getRequestParameterMap().get("pid");
        if (!ApplicationHelper.isInteger(stringPid)) {
            ApplicationHelper.addMessage("Wrong Product ID!");
            ApplicationHelper.redirect("/404.xhtml", true);
        } else {
            pid = Integer.parseInt(stringPid);
        }

        session = SessionHelper.getSessionMap();
        if (session.get("order_product_details") == null) {
            List<OrderProductDetails> opds = new ArrayList<>();
            OrderProductDetails opd = new OrderProductDetails();
            opd.setProductId(new Products(pid));
            opd.setQuantity(quantity);
            opd.setFloors(floor);
            opd.setHeightOfFloor(heightOfFloor);
            opds.add(opd);
            session.put("order_product_details", opds);
        } else {
            boolean exists = false;
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("order_product_details");
            for (OrderProductDetails opd : opds) {
                if (opd.getProductId().getPid() == pid) {
                    //nếu đã có trong list nhưng khác số tầng hoặc chiều cao mỗi tầng
                    //thì thêm lại vào list như 1 sản phẩm mới
                    if (!Objects.equals(opd.getFloors(), floor) || !Objects.equals(opd.getHeightOfFloor(), heightOfFloor)) {
                        OrderProductDetails newOpd = new OrderProductDetails();
                        newOpd.setProductId(opd.getProductId());
                        newOpd.setFloors(floor);
                        newOpd.setHeightOfFloor(heightOfFloor);
                        newOpd.setQuantity(quantity);
                        opds.add(newOpd);
                    } else { //nếu không khác thì cộng thêm vào sản phẩm đã có
                        int new_quantity = opd.getQuantity() + quantity;
                        if (new_quantity > 10) {
                            ApplicationHelper.addMessage("Max quantity is 10, you had " + opd.getQuantity() + " in cart!");
                            ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
                            return;
                        } else {
                            opd.setQuantity(opd.getQuantity() + quantity);
                        }

                    }

                    exists = true;
                    break;
                }
            }

            if (!exists) {
                OrderProductDetails opd = new OrderProductDetails();
                opd.setProductId(new Products(pid));
                opd.setQuantity(quantity);
                opd.setFloors(floor);
                opd.setHeightOfFloor(heightOfFloor);
                opds.add(opd);
            }

            session.put("order_product_details", opds);
        }

        ApplicationHelper.addMessage("Product added!");
        ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
    }

    public void updateSelectdProductQuantity() {
        OrderProductDetails opd = (OrderProductDetails) selected_products.getRowData();
        if (opd.getHeightOfFloor() <= 0) {
            ApplicationHelper.addMessage("Height of Floor greator than 0");
            ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
            return;
        }
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        for (OrderProductDetails o : opds) {
            if (Objects.equals(o.getProductId().getPid(), opd.getProductId().getPid())) {
                o.setQuantity(opd.getQuantity());
                o.setFloors(opd.getFloors());
                o.setHeightOfFloor(opd.getHeightOfFloor());
            }
        }
        ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
    }

    public void removeSelectedProduct() {
        int index = -1;
        OrderProductDetails opd = (OrderProductDetails) selected_products.getRowData();
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        if (opds.size() == 1) {
            ApplicationHelper.addMessage("Order need one or more products!");
            ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
            return;
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
        ApplicationHelper.redirect("/client/order/selected_products.xhtml", true);
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
                if (Objects.equals(opd.getProductId().getPid(), p.getPid())) {
                    totalPrice += opd.getQuantity() * p.getPrice();
                }
            }
        }

        return totalPrice;
    }

    public void newOrder() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        Clients client = (Clients) session.get("client");
        List<OrderProductDetails> opds = SessionHelper.getSessionOrderProductDetails();
        if (opds.isEmpty()) {
            ApplicationHelper.addMessage("Please select product to make order");
            ApplicationHelper.redirect("/client/order/new.xhtml", true);
        }

        Orders order = new Orders();
        order.setClientId(client);
        order.setNumber(ApplicationHelper.secureRandomString(8));
        order.setPaymentType(new PaymentTypes(paymentTypeId));
        order.setLocationName(location_name);
        order.setLocationAddress(location_address);
        order.setOrderStatus(new ListStatus(1));
        order.setCreateAt(PersistenceHelper.getCurrentTime());
        order = orderModel.createOrder(order);

        boolean result = false;
        if (order != null) {
            result = orderProductDetailModel.createListOrderProductDetails(opds, order);
        }

        if (result) {
            order.setOrderProductDetailsList(opds);
            session.remove("order_product_details");
            ApplicationHelper.addMessage("Order created!");
            ApplicationHelper.redirect("/client/order/new_order_details.xhtml?oid=" + order.getOid(), true);
        } else {
            orderModel.removeOrder(order);
            ApplicationHelper.addMessage("Failed to create new order!");
            ApplicationHelper.redirect("/client/order/new.xhtml", true);
        }
    }

    public List<Orders> getListOrders() {//phan show list 

        return orderModel.getListOrder(2);

    }

    //////////////////////////////
//    public int getPid() {
//        return pid;
//    }
//    
//    public void setPid(int pid) {
//        this.pid = pid;
//    }
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
        if (orders == null) {
            orders = orderModel.getListOrder(8); // get list order
        }
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getStringFloor() {
        return stringFloor;
    }

    public void setStringFloor(String stringFloor) {
        this.stringFloor = stringFloor;
    }

    public String getStringHeightOfFloor() {
        return stringHeightOfFloor;
    }

    public void setStringHeightOfFloor(String stringHeightOfFloor) {
        this.stringHeightOfFloor = stringHeightOfFloor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getHeightOfFloor() {
        return heightOfFloor;
    }

    public void setHeightOfFloor(long heightOfFloor) {
        this.heightOfFloor = heightOfFloor;
    }

}
