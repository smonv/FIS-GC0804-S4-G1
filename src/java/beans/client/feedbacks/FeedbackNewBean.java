package beans.client.feedbacks;

import entities.Clients;
import entities.FeedbackLevel;
import entities.Feedbacks;
import entities.ListStatus;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import helpers.SessionHelper;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.FeedbackLevelModel;
import models.FeedbackModel;

@ManagedBean
@RequestScoped
public class FeedbackNewBean {

    @EJB
    private FeedbackModel feedbackModel;
    @EJB
    private FeedbackLevelModel feedbackLevelModel;

    private String name;
    private String email;
    private String description;
    private int levelId;
    private String problem;
    private String improvement;

    private List<FeedbackLevel> feedbacklevels;

    public void createFeedback() {
        Map<String, Object> session = SessionHelper.getSessionMap();
        Clients client = (Clients) session.get("client");
        Feedbacks feedback = new Feedbacks();
        feedback.setClientId(client); //thay khi hoàn thành
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setFeedbackLevel(new FeedbackLevel(levelId));
        feedback.setFeedbackDescription(description);
        feedback.setProblem(problem);
        feedback.setImprovement(improvement);
        feedback.setFeedbackStatus(new ListStatus(1));
        feedback.setCreateAt(PersistenceHelper.getCurrentTime());

        boolean result = feedbackModel.create(feedback);
        if (result) {
            ApplicationHelper.addMessage("Feedback created!");
            ApplicationHelper.redirect("/client/feedback/index.xhtml", true);
        } else {
            ApplicationHelper.addMessage("Failed to create new feedback!");
            ApplicationHelper.redirect("/client/feedback/new.xhtml", true);
        }
    }

    /////////SET GET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public FeedbackNewBean() {
    }

    public List<FeedbackLevel> getFeedbacklevels() {
        feedbacklevels = feedbackLevelModel.getAll();
        return feedbacklevels;
    }

    public void setFeedbacklevels(List<FeedbackLevel> feedbacklevels) {
        this.feedbacklevels = feedbacklevels;
    }

}
