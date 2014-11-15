package beans.admin.project;

import entities.Contracts;
import entities.ListStatus;
import entities.Projects;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.ContractModel;
import models.ListStatusModel;
import models.ProjectModel;

@ManagedBean
@ViewScoped
public class AdminNewProject {

    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private ProjectModel projectModel;
    @EJB
    private ContractModel contractModel;

    //bean variable
    private Contracts contract;
    private int currentCid;
    private String title;
    private String content;
    private String startAt;
    private String endAt;
    private int projectStatus;
    //view param
    private String cid;

    public AdminNewProject() {
    }

    public void init() {
        currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0;
        contract = contractModel.getById(currentCid);
        if (contract == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
    }

    public List<ListStatus> getListStatus() {
        List<ListStatus> status = listStatusModel.getAll();
        return status;
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
            ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + cid, true);
        }
        //////////////
        currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0;
        contract = contractModel.getById(currentCid);
        if (contract == null) {
            ApplicationHelper.addMessage("Can't find exists contract!");
            ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + cid, valid);
        }
        /////////

        Projects project = new Projects();
        project.setContractId(contract);
        project.setTitle(title);
        project.setContent(content);
        project.setProjectStatus(new ListStatus(projectStatus));
        project.setStartAt(startAtDate);
        project.setEndAt(endAtDate);
        project.setCreateAt(PersistenceHelper.getCurrentTime());

        boolean result = projectModel.create(project);
        if (result) {
            ApplicationHelper.addMessage("Project created!");
            ApplicationHelper.redirect("/admin/contract/view.xhtml?cid=" + contract.getCid(), true);
           
        } else {
            ApplicationHelper.addMessage("Failed to create new project!");
            ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + contract.getCid(), true);
            
        }
    }

    /// SET GET
    public Contracts getContract() {
        return contract;
    }

    public void setContract(Contracts contract) {
        this.contract = contract;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

}
