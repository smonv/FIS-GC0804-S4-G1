package beans.admin.project;

import entities.Orders;
import helpers.ApplicationHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.OrderModel;

@ManagedBean
@RequestScoped
public class AdminNewProject {
    
    @EJB
    private OrderModel orderModel;
    //bean variable
    private Orders order;
    //view param
    private String number;

    //project variable
    private String title;
    private String content;
    private String startAt;
    private String endAt;
    
    public AdminNewProject() {
    }
    
    public void init() {
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
    }

    ///SET GET
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getStartAt() {
        return startAt;
    }
    
    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }
    
    public String getEndAt() {
        return endAt;
    }
    
    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
    
    public Orders getOrder() {
        return order;
    }
    
    public void setOrder(Orders order) {
        this.order = order;
    }
    
}
