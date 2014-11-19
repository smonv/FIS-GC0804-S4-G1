package beans.admin.project;

import entities.ListStatus;
import entities.Projects;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ListStatusModel;
import models.ProjectModel;

@ManagedBean
@RequestScoped
public class AdminIndexProject {

    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private ProjectModel projectModel;

    //bean variables
    private List<Projects> projects;

    //view params
    public AdminIndexProject() {
    }

    public void init() {
        ListStatus status = new ListStatus(3);
        projects = projectModel.getAll(true, status);
    }

    public List<ListStatus> getListStatus() {
        return listStatusModel.getAll();
    }

    public String formatDate(Date date) {
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }

    //SET GET
    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

}
