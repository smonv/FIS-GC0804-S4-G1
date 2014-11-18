package beans.admin.statistic;

import entities.Admins;
import entities.ListStatus;
import entities.Orders;
import entities.Projects;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.AdminModel;
import models.ListStatusModel;
import models.OrderModel;
import models.ProjectModel;

@ManagedBean
@RequestScoped
public class ProjectStatistic {

    @EJB
    private OrderModel orderModel;
    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private AdminModel adminModel;

    @EJB
    private ProjectModel projectModel;

    //bean variables
    private String parseDatePattern = "dd-MM-yyyy";
    private List<Projects> projects;
    private int pageSize = 10;
    private int currentPage = 1;
    private long totalProjects = 0;
    private long totalPages = 0;
    private int currentAid = 0;
    private int currentStatus = 0;
    private Date startFromDate;
    private Date startToDate;
    private Date endFromDate;
    private Date endToDate;
    //view param
    private String page;
    private String aid;
    private String status;
    private String number;
    private String startFrom;
    private String startTo;
    private String endFrom;
    private String endTo;

    public ProjectStatistic() {
    }

    public void init() {
        //check page value, if page is number, convert else set current page = 1
        currentPage = ApplicationHelper.isInteger(page) ? Integer.parseInt(page) : 1;
        //check aid, if aid is number, convert to integer and get admin;
        currentAid = ApplicationHelper.isInteger(aid) ? Integer.parseInt(aid) : 0;
        Admins admin = adminModel.getById(currentAid);
        ///get order number if number not null;
        Orders order = number != null ? orderModel.getByNumber(number) : null;
        //check status, if status is number, convert to interger and get status;
        currentStatus = ApplicationHelper.isInteger(status) ? Integer.parseInt(status) : 0;
        ListStatus projectStatus = listStatusModel.getById(currentStatus);
        //check if value not null, parse it to date to filter
        startFromDate = startFrom != null ? ApplicationHelper.parseDate(startFrom, parseDatePattern) : null;
        startToDate = startTo != null ? ApplicationHelper.parseDate(startTo, parseDatePattern) : null;
        endFromDate = endFrom != null ? ApplicationHelper.parseDate(endFrom, parseDatePattern) : null;
        endToDate = endTo != null ? ApplicationHelper.parseDate(endTo, parseDatePattern) : null;
        ///////////////////// get value from database
        projects = projectModel.getAll(currentPage - 1, pageSize, admin, order, projectStatus,
                startFromDate,startToDate,endFromDate,endToDate);
        totalProjects = projectModel.countAll(admin, order, projectStatus,
                startFromDate,startToDate,endFromDate,endToDate);
        if (totalProjects % pageSize != 0) {
            totalPages = totalProjects / pageSize + 1;
        } else {
            totalPages = totalProjects / pageSize;
        }
    }

    public String formatDate(Date date) {
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }

    public List<Admins> getAllAdmins() {
        return adminModel.getAll();
    }

    public List<ListStatus> getAllStatus() {
        return listStatusModel.getAll();
    }

    ///////////SET GET
    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    public String getStartTo() {
        return startTo;
    }

    public void setStartTo(String startTo) {
        this.startTo = startTo;
    }

    public String getEndFrom() {
        return endFrom;
    }

    public void setEndFrom(String endFrom) {
        this.endFrom = endFrom;
    }

    public String getEndTo() {
        return endTo;
    }

    public void setEndTo(String endTo) {
        this.endTo = endTo;
    }

}
