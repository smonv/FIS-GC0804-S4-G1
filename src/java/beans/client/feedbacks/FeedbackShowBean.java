package beans.client.feedbacks;

import entities.Clients;
import entities.Feedbacks;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import models.FeedbackModel;

@ManagedBean
@RequestScoped
public class FeedbackShowBean {

    @EJB
    private FeedbackModel feedbackModel;
    private HtmlDataTable feedbackTable;
    private List<Feedbacks> feedbacks;
    private String fid;
    private Feedbacks current_feedback;

    public FeedbackShowBean() {
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

    public void removeFeedback() {
        Feedbacks feedback = (Feedbacks) feedbackTable.getRowData();
        boolean result = feedbackModel.remove(feedback);
        if (result) {
            ApplicationHelper.addMessage("Feedback removed!");
        } else {
            ApplicationHelper.addMessage("Failed to remove feedback!");
        }

        ApplicationHelper.redirect("/client/feedback/index.xhtml", true);
    }

    public String formateDate(Date date) {
        String formated_date = ApplicationHelper.formatDate(date, "dd-MM-yyyy HH:mm:ss");
        return formated_date;
    }

    public List<Feedbacks> getFeedbacks() {
        Clients current_client = SessionHelper.getCurrentClient();
        feedbacks = feedbackModel.getByClientId(current_client);
        return feedbacks;
    }

    public void setFeedbacks(List<Feedbacks> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public HtmlDataTable getFeedbackTable() {
        return feedbackTable;
    }

    public void setFeedbackTable(HtmlDataTable feedbackTable) {
        this.feedbackTable = feedbackTable;
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
