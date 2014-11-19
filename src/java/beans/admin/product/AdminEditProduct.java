package beans.admin.product;

import entities.Categories;
import entities.Products;
import helpers.ApplicationHelper;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.CategoryModel;
import models.ProductModel;

@ManagedBean
@ViewScoped
public class AdminEditProduct {
    
    @EJB
    private CategoryModel categoryModel;
    @EJB
    private ProductModel productModel;

    ///bean variables
    private int currentPid;
    private Products product;
    private String productCode;
    private String productName;
    private int categoryId;
    private BigDecimal productPrice;
    private BigDecimal productContructionPrice;
    private int productContructionTime;

    //view params
    public String pid;
    
    public AdminEditProduct() {
    }
    
    public void init() {
        currentPid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
        product = productModel.getById(currentPid);
        if (!FacesContext.getCurrentInstance().isPostback() && product != null) {
            productCode = product.getCode();
            productName = product.getName();
            categoryId = product.getCategoryId().getCid();
            productPrice = product.getPrice();
            productContructionPrice = product.getConstructionPrice();
            productContructionTime = product.getConstructionTime();
        }
    }
    
    public void update() {
        currentPid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
        product = productModel.getById(currentPid);
        
        product.setCategoryId(new Categories(categoryId));
        product.setCode(productCode);
        product.setName(productName);
        product.setPrice(productPrice);
        product.setConstructionPrice(productContructionPrice);
        product.setConstructionTime(productContructionTime);
        
        boolean result = productModel.update(product);
        if (result) {
            ApplicationHelper.addMessage("Product Updated!");
            ApplicationHelper.redirect("/admin/product/view.xhtml?pid=" + product.getPid(), result);
        } else {
            ApplicationHelper.addMessage("Failed to update product!");
            ApplicationHelper.navigate("/admin/product/view.xhtml?pid=" + product.getPid());
        }
        
    }
    
    public List<Categories> getAllCategories() {
        return categoryModel.getAllCategory();
    }

    ///SET GET
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
    
    public ProductModel getProductModel() {
        return productModel;
    }
    
    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    
    public BigDecimal getProductContructionPrice() {
        return productContructionPrice;
    }
    
    public void setProductContructionPrice(BigDecimal productContructionPrice) {
        this.productContructionPrice = productContructionPrice;
    }
    
    public int getProductContructionTime() {
        return productContructionTime;
    }
    
    public void setProductContructionTime(int productContructionTime) {
        this.productContructionTime = productContructionTime;
    }
    
}
