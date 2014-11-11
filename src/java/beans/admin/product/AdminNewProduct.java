package beans.admin.product;

import entities.Categories;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.CategoryModel;

@ManagedBean
@RequestScoped
public class AdminNewProduct {

    @EJB
    private CategoryModel categoryModel;

    //bean variable
    private List<Categories> categories;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productContructionPrice;
    private int productContructionTime;
    private int productCategory;
    
    public AdminNewProduct() {
    }

    public void init() {
        categories = categoryModel.getAllCategory(); // get all category from database
    }
    
    public void create(){
        
    }

    
    //////SET GET
    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

}
