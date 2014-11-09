/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin.feedback;

import entities.Feedbacks;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import models.FeedbackModel;

/**
 *
 * @author BUIVUHUECHI
 */
@ManagedBean
@RequestScoped
public class AdminfeedbackShowBean {

    @EJB
    private FeedbackModel feedbackModel;

    /**
     * Creates a new instance of FeedBackShowBean
     */
    private int pageSize = 2;
    private int page;
    private List<Feedbacks> feedbacks;
    private HtmlDataTable feedbackTable;
    private int totalfeedback;
    private String fid;
    private Feedbacks current_feedback;

    public AdminfeedbackShowBean() {
    }
    public void init() {
        if (!ApplicationHelper.isInteger(fid)) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
        int fbid = Integer.parseInt(fid);
        Feedbacks feedback = feedbackModel.getById(fbid);
        if (feedback != null) {
            current_feedback = feedback;
        } else {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
    }

    public boolean isHasNextPage() {
        return (page + 1) * this.getPageSize() + 1 <= feedbackModel.getAllForAdmin();
    }

    public List<Feedbacks> getFeedbacks() {
        loadFeedBackList();
        return feedbacks;
    }

    public void loadFeedBackList() {
        feedbacks = feedbackModel.getAllForAdmin(this.getStartIndex(), this.getPageSize());
        totalfeedback = feedbackModel.getAllForAdmin();
    }
    
    public String formateDate(Date date) {
        String formated_date = ApplicationHelper.formatDate(date, "dd-MM-yyyy HH:mm:ss");
        return formated_date;
    }

    public int getStartIndex() {
        return page * pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalfeedback() {
        return totalfeedback;
    }

    public void setTotalfeedback(int totalfeedback) {
        this.totalfeedback = totalfeedback;
    }

    public HtmlDataTable getFeedbackTable() {
        return feedbackTable;
    }

    public void setFeedbackTable(HtmlDataTable feedbackTable) {
        this.feedbackTable = feedbackTable;
    }
    
    

    public void setFeedbacks(List<Feedbacks> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Feedbacks getCurrent_feedback() {
        return current_feedback;
    }

    public void setCurrent_feedback(Feedbacks current_feedback) {
        this.current_feedback = current_feedback;
    }
    
    

}
