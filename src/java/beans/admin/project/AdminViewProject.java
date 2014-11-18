package beans.admin.project;

import entities.Products;
import entities.Projects;
import helpers.ApplicationHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ProductModel;
import models.ProjectModel;

@ManagedBean
@RequestScoped
public class AdminViewProject {

    @EJB
    private ProjectModel projectModel;

    //bean variable
    private Projects project;
    //view param
    private String pid;

    public AdminViewProject() {
    }

    public void init() {
        if (!ApplicationHelper.isInteger(pid)) {
            ApplicationHelper.redirect("/404.xhtml", true);
            return;
        }
        int realPid = Integer.parseInt(pid);
        project = projectModel.getById(realPid);
        if (project == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
    }

    //SET GET
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }



}
