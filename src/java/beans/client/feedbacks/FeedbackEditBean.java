package beans.client.feedbacks;

import entities.FeedbackLevel;
import entities.Feedbacks;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.FeedbackModel;

@ManagedBean
@RequestScoped
public class FeedbackEditBean {

    @EJB
    private FeedbackModel feedbackModel;

    private String fid;
    private Feedbacks current_feedback;
    private String name;
    private String email;
    private String description;
    private int levelId;
    private String problem;
    private String improvement;

    public FeedbackEditBean() {
    }

    public void init() {
        if (!ApplicationHelper.isInteger(fid)) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
        int fbid = Integer.parseInt(fid);
        Feedbacks feedback = feedbackModel.getById(fbid);
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (feedback != null) {
                current_feedback = feedback;
                //gán lại giá trị để show ra view edit
                name = current_feedback.getName();
                email = current_feedback.getEmail();
                description = current_feedback.getFeedbackDescription();
                levelId = current_feedback.getFeedbackLevel().getFlid();
                problem = current_feedback.getProblem();
                improvement = current_feedback.getImprovement();

            } else {
                ApplicationHelper.redirect("/404.xhtml", true);
            }
        }
    }

    public void update() {
        Feedbacks update_feedbacks = feedbackModel.getById(Integer.parseInt(fid));
        update_feedbacks.setName(name);
        update_feedbacks.setEmail(email);
        update_feedbacks.setFeedbackDescription(description);
        update_feedbacks.setFeedbackLevel(new FeedbackLevel(levelId));
        update_feedbacks.setProblem(problem);
        update_feedbacks.setImprovement(improvement);
        update_feedbacks.setUpdateAt(PersistenceHelper.getCurrentTime());

        boolean result = feedbackModel.update(update_feedbacks);
        if (result) {
            ApplicationHelper.addMessage("Feedback updated!");
            ApplicationHelper.redirect("/client/feedback/index.xhtml", true);
        } else {
            ApplicationHelper.addMessage("Failed to update feedback!");
            ApplicationHelper.redirect("/client/feedback/edit.xhtml?fid=" + update_feedbacks.getFid(), true);
        }
    }

    ////SET GET
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

}
