package beans.admin.project;

import entities.Orders;
import entities.Projects;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import models.OrderModel;
import models.ProjectModel;

@ManagedBean
@ViewScoped
public class AdminNewProject {

    @EJB
    private ProjectModel projectModel;

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

    public void create() {
        boolean valid = true;
        Date startAtDate = ApplicationHelper.parseDate(startAt, "dd/MM/yyyy");
        Date endAtDate = ApplicationHelper.parseDate(endAt, "dd/MM/yyyy");
        Date currentDate = PersistenceHelper.getCurrentTime();
        if (startAtDate.compareTo(currentDate) < 0) {
            ApplicationHelper.addMessage("Start date must be after current time!");
            valid = false;
        }
        if (endAtDate.compareTo(currentDate) < 0) {
            ApplicationHelper.addMessage("End date must be after current time!");
            valid = false;
        }
        if (startAtDate.compareTo(endAtDate) > 0) {
            ApplicationHelper.addMessage("Start date must be before end date!");
            valid = false;
        }
        if (!valid) {
            ApplicationHelper.redirect("/admin/project/new.xhtml?number=" + number, true);
        } else {
            Projects project = new Projects();
            project.setOrderId(order);
            project.setTitle(title);
            project.setContent(content);
            project.setStartAt(startAtDate);
            project.setEndAt(endAtDate);
            project.setCreateAt(PersistenceHelper.getCurrentTime());
            
            boolean result = projectModel.create(project);
            if (result) {
                ApplicationHelper.addMessage("Project created!");
                ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + number, true);
            } else {
                ApplicationHelper.addMessage("Failed to create new project!");
                ApplicationHelper.redirect("/admin/project/new.xhtml?number=" + number, true);
            }
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
