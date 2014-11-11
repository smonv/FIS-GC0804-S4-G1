package beans.admin.product;

import entities.Categories;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.CategoryModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminNewProduct {

    @EJB
    private ProductModel productModel;

    @EJB
    private CategoryModel categoryModel;

    //bean variable
    private List<Categories> categories;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal productContructionPrice;
    private int productContructionTime;
    private int productCategory;
    private String productCode;

    public AdminNewProduct() {
    }

    public void init() {
        categories = categoryModel.getAllCategory(); // get all category from database
    }

    public void create() {
        Products product = new Products();
        product.setCode(productCode); //set code for product
        product.setCategoryId(new Categories(productCategory)); //set category for product
        product.setName(productName); //set name for product
        product.setPrice(productPrice); //set price for product
        product.setConstructionPrice(productContructionPrice); //set contruction price for product
        product.setConstructionTime(productContructionTime); //set contruction time for product
        product.setCreateAt(PersistenceHelper.getCurrentTime()); //set create time for product

        int pid = productModel.create(product); //create new product, return product id in database
        if (pid > 0) {
            ApplicationHelper.addMessage("Product created! Edit to add more product information");
            ApplicationHelper.redirect("/admin/product/view.xhtml?pid=" + pid, true);
        } else {
            ApplicationHelper.addMessage("Failed to create new product!");
            ApplicationHelper.redirect("/admin/product/new.xhtml", true);
        }

    }

    public List<Categories> getAllCategories() {
        return categoryModel.getAllCategory();
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
