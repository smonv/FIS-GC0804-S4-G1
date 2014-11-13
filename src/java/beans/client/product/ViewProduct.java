package beans.client.product;

import entities.Products;
import helpers.ApplicationHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import models.ProductModel;

@ManagedBean
@ViewScoped
public class ViewProduct {
    
    @EJB
    private ProductModel productModel;

    //bean variable
    private Products product;
    private int currentCid = 0;
    //view params
    private String pid;
    private String mode;
    
    public ViewProduct() {
    }
    
    public void init() {
        currentCid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
        product = productModel.getById(currentCid);
        if (product == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
        checkMode();
    }
    
    public void checkMode() {
        if (mode != null) {
            if (!mode.equals("update") && !mode.equals("admin_edit")) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
        }
    }
    
    public boolean isAdminMode(){
        if(mode != null){
            return mode.equals("admin_edit");
        }
        return false;
    }

    ////SET GET 
    public String getPid() {
        return pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    public String getMode() {
        return mode;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    
}
