/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin.feedback;

import entities.Feedbacks;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
     private int pageSize = 1;
     private int page;
     private List<Feedbacks> feedbacks;
     private int totalfeedback;
     
    public AdminfeedbackShowBean() {
    }
    
    public boolean isHasNextPage() {
        return (page + 1) * this.getPageSize() + 1 <= feedbackModel.getAllForAdmin();
    }
    
    public List<Feedbacks> getFeedbacks(){
        loadFeedBackList();
        return feedbacks;
    }

    public void loadFeedBackList(){
        feedbacks=feedbackModel.getAllForAdmin(this.getStartIndex(), this.getPageSize());
        totalfeedback=feedbackModel.getAllForAdmin();
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

    public void setFeedbacks(List<Feedbacks> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    
    
}
