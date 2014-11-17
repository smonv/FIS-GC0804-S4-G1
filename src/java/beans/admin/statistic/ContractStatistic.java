package beans.admin.statistic;

import entities.Contracts;
import entities.Orders;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ContractModel;
import models.OrderModel;

@ManagedBean
@RequestScoped
public class ContractStatistic {

    @EJB
    private OrderModel orderModel;

    @EJB
    private ContractModel contractModel;

    //bean variables
    private List<Contracts> contracts;
    private int pageSize = 10;
    private int currentPage = 1;
    private long totalContracts = 0;
    private long totalPages = 0;
    private Date fromDate;
    private Date endDate;
    //view params
    private String page;
    private String number;
    private String from;
    private String end;

    public ContractStatistic() {
    }

    public void init() {
        currentPage = ApplicationHelper.isInteger(page) ? Integer.parseInt(page) : 1;
        Orders order = orderModel.getByNumber(number); //get order if number not null

        if (from != null) {
            fromDate = ApplicationHelper.parseDate(from, "dd-MM-yyyy"); //if from not null, parse to Date;
        }
        if (end != null) {
            endDate = ApplicationHelper.parseDate(end, "dd-MM-yyyy"); //if end not null, parse to Date;
        }
        contracts = contractModel.getAll(currentPage - 1, pageSize, order, fromDate, endDate);
        totalContracts = contractModel.countAll(order, fromDate, endDate);

        if (totalContracts % pageSize != 0) {
            totalPages = totalContracts / pageSize + 1;
        } else {
            totalPages = totalContracts / pageSize;
        }
    }

    public String formatDate(Date date) {
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }

    ///////SET GET
    public List<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contracts> contracts) {
        this.contracts = contracts;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalContracts() {
        return totalContracts;
    }

    public void setTotalContracts(long totalContracts) {
        this.totalContracts = totalContracts;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
