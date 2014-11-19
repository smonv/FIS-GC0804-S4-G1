package beans.admin.product;

import entities.Products;
import helpers.ApplicationHelper;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminViewProduct {

    @EJB
    private ProductModel productModel;

    //bean variable
    private Products product;
    //view param
    private String pid;

    public AdminViewProduct() {
    }

    public void init() {
        if (!ApplicationHelper.isInteger(pid)) {
            ApplicationHelper.redirect("/404.xhtml", true);
            return;
        }
        int realPid = Integer.parseInt(pid);
        product = productModel.getById(realPid);
        if (product == null) {
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

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

}
