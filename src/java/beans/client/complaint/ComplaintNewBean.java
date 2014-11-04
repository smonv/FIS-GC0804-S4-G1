package beans.client.complaint;

import entities.Complaints;
import entities.ListStatus;
import entities.Orders;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import models.ComplaintModel;
import models.OrderModel;

@ManagedBean
@RequestScoped
public class ComplaintNewBean {

    @EJB
    private ComplaintModel complaintModel;

    @EJB
    private OrderModel orderModel;

    private String number;
    private Orders order;
    private String problem;

    public ComplaintNewBean() {
    }

    public void init() {
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
    }

    public void create() {
        String order_number = ApplicationHelper.getRequestParameterMap().get("order_number");
        Orders current_order = orderModel.getByNumber(order_number);

        Complaints complaint = new Complaints();
        complaint.setProblem(problem);
        complaint.setOrderId(current_order);
        complaint.setComplaintStatus(new ListStatus(1));
        complaint.setCreateAt(PersistenceHelper.getCurrentTime());

        boolean result = complaintModel.create(complaint);
        if (result) {
            ApplicationHelper.addMessage("Complaint created!");
            ApplicationHelper.redirect("/client/complaint/index.xhtml", true);
        } else {
            ApplicationHelper.addMessage("Failed to create new complaint!");
            ApplicationHelper.redirect("/client/complaint/new.xhtml?number=" + order_number, true);
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

}
