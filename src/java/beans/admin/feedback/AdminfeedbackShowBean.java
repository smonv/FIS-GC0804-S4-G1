package beans.admin.feedback;

import entities.FeedbackLevel;
import entities.Feedbacks;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import models.FeedbackLevelModel;
import models.FeedbackModel;

@ManagedBean
@RequestScoped
public class AdminfeedbackShowBean {
    @EJB
    private FeedbackLevelModel feedbackLevelModel;

    @EJB
    private FeedbackModel feedbackModel;

    private int pageSize = 10;
    private int current_page = 1;
    
    private List<Feedbacks> feedbacks;
    private HtmlDataTable feedbackTable;
    private long totalfeedback;
    private String page;
    private String fid;
    private String flid;
    private String search;
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

    
    public int getTotalPages() {
        int pages = 0;
        long totalPages = totalfeedback / pageSize;
        pages = totalPages > (int) totalPages ? ((int) totalPages) + 1 : (int) totalPages;
        pages = pages == 0 ? 1 : pages;
        return pages;
    }
    
    public List<Feedbacks> getFeedbacks() {
        loadFeedBackList();
        return feedbacks;
    }

    public void loadFeedBackList() {
        current_page = ApplicationHelper.getCurrentPage(page);
        if(flid == null && search == null){
        feedbacks = feedbackModel.getAllForAdmin(current_page -1, this.getPageSize());
        totalfeedback = feedbackModel.getAllForAdmin();
        }
        else if(flid != null && search == null){
            if (!ApplicationHelper.isInteger(flid)) {
                ApplicationHelper.redirect("/404.xhtml", true);
                return;
            }
            int sflid=Integer.parseInt(flid);
            FeedbackLevel fl=feedbackLevelModel.getById(sflid);
            if (fl == null) {
                ApplicationHelper.redirect("/404.xhtml", true);
            }
            feedbacks=feedbackModel.getByLevel(fl, current_page -1,this.getPageSize());
            totalfeedback=feedbackModel.getByLevel(fl);
        }else{       
                feedbacks=feedbackModel.getByName(search, current_page -1,this.getPageSize());
                totalfeedback=feedbackModel.getByName(search);   
        }
    }
    
    public String formateDate(Date date) {
        String formated_date = ApplicationHelper.formatDate(date, "dd-MM-yyyy HH:mm:ss");
        return formated_date;
    }
    
    public List<FeedbackLevel> getFeedbackLevel(){
        return feedbackLevelModel.getAll();
    }

   

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalfeedback() {
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

    public String getFlid() {
        return flid;
    }

    public void setFlid(String flid) {
        this.flid = flid;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    

  
}
