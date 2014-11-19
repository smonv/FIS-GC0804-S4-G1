/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin.user;

import entities.Admins;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.AdminModel;

/**
 *
 * @author BUIVUHUECHI
 */
@ManagedBean
@RequestScoped
public class AdminShowIndex {
    @EJB
    private AdminModel adminModel;

    //bean variables
    private List<Admins> users;
    private int pageSize = 10; //page size for pagination;
    private int currentPage = 1; //current page value for pagination
    private long totalUser = 0;
    private long totalPage;
    
     //view params
    private String page;
    private String name;
    
    public AdminShowIndex() {
    }
    
    public void init(){
        currentPage = ApplicationHelper.isInteger(page) ? Integer.parseInt(page) : 1;
        users=adminModel.getAllForAdmin(currentPage -1, pageSize, name);
        totalUser=adminModel.countAll(name);
         if (totalUser % pageSize != 0) {
            totalPage = totalUser / pageSize + 1;
        } else {
            totalPage = totalUser / pageSize;
        }
    }
    
    public String formatDate(Date date) {
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }
    
    ///SET GET

    public List<Admins> getUsers() {
        return users;
    }

    public void setUsers(List<Admins> users) {
        this.users = users;
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

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
