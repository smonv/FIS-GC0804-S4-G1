package beans.admin.order;

import entities.ListStatus;
import entities.Orders;
import entities.PaymentTypes;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.ListStatusModel;
import models.OrderModel;
import models.PaymentTypeModel;

@ManagedBean
@ViewScoped
public class AdminEditOrder {
    
    @EJB
    private ListStatusModel listStatusModel;
    @EJB
    private PaymentTypeModel paymentTypeModel;
    
    @EJB
    private OrderModel orderModel;

    //bean variable
    private Orders order;
    private List<ListStatus> status;
    private List<PaymentTypes> payments;
    //view param
    private String number;
    private String locationName;
    private String locationAddress;
    private int orderStatus;
    private int paymentType;
    
    public AdminEditOrder() {
    }

    //call before page render
    public void init() {
        ///check order exists
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
        if (!FacesContext.getCurrentInstance().isPostback()) {
            locationName = order.getLocationName();
            locationAddress = order.getLocationAddress();
            orderStatus = order.getOrderStatus().getLsid();
            paymentType = order.getPaymentType().getPtid();
        }
        /// get value for render view
        status = listStatusModel.getAll();
        payments = paymentTypeModel.getAll();
    }
    
    public void update() {
        order.setLocationName(locationName);
        order.setLocationAddress(locationAddress);
        order.setOrderStatus(new ListStatus(orderStatus));
        order.setPaymentType(new PaymentTypes(paymentType));
        
        boolean result = orderModel.update(order);
        if (result) {
            ApplicationHelper.addMessage("Order updated!");
            ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + number, true);
        } else {
            ApplicationHelper.addMessage("Failed to update order!");
            ApplicationHelper.redirect("/admin/order/edit.xhtml?number=" + number, true);
        }
    }

    ////////SET GET
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
    
    public int getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public int getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
    
    public List<ListStatus> getStatus() {
        return status;
    }
    
    public void setStatus(List<ListStatus> status) {
        this.status = status;
    }
    
    public List<PaymentTypes> getPayments() {
        return payments;
    }
    
    public void setPayments(List<PaymentTypes> payments) {
        this.payments = payments;
    }
    
}
