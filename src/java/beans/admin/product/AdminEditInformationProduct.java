package beans.admin.product;

import entities.Nations;
import entities.ProductInformations;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import models.NationModel;
import models.ProductInformationModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminEditInformationProduct {

    @EJB
    private ProductInformationModel productInformationModel;

    @EJB
    private NationModel nationModel;

    @EJB
    private ProductModel productModel;

    //bean variable
    private Products product;
    private ProductInformations productInformation;

    private String productModelNo;
    private String productManufacturer;
    private int productProducedNation;
    private String productSize;
    private String productInformations;
    private int productLoad;
    private BigDecimal productSpeed;
    private String productFeature1;
    private String productFeature2;
    private String productFeature3;

    //view param
    private String pid;

    public AdminEditInformationProduct() {
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
        productInformation = product.getProductInformations();
        if (productInformation != null) {
            productModelNo = productInformation.getModelNo();
            productManufacturer = productInformation.getManufacturer();
            productProducedNation = productInformation.getProducedNation().getNid();
            productSize = productInformation.getSize();
            productInformations = productInformation.getInformations();
            productLoad = productInformation.getELoad();
            productSpeed = productInformation.getSpeed();
            productFeature1 = productInformation.getFeature1();
            productFeature2 = productInformation.getFeature2();
            productFeature3 = productInformation.getFeature3();
        }
    }

    public void update() {
        boolean result = false;
        int realPid = 0;
        String pid = ApplicationHelper.getRequestParameterMap().get("pid");
        if (!ApplicationHelper.isInteger(pid)) {
            ApplicationHelper.addMessage("Wrong product id!");
            return;
        }
        realPid = Integer.parseInt(pid);
        Products update_product = productModel.getById(realPid);
        ProductInformations update_product_info = new ProductInformations();

        update_product_info.setProductId(update_product);
        update_product_info.setModelNo(productModelNo);
        update_product_info.setManufacturer(productManufacturer);
        update_product_info.setProducedNation(new Nations(productProducedNation));
        update_product_info.setSize(productSize);
        update_product_info.setInformations(productInformations);
        update_product_info.setELoad(productLoad);
        update_product_info.setSpeed(productSpeed);
        update_product_info.setFeature1(productFeature1);
        update_product_info.setFeature2(productFeature2);
        update_product_info.setFeature3(productFeature3);

        if (update_product.getProductInformations() == null) {
            
            update_product_info.setCreateAt(PersistenceHelper.getCurrentTime());
            result = productInformationModel.create(update_product_info);
        } else {
            update_product_info.setPinfoid(update_product.getProductInformations().getPinfoid());
            update_product_info.setUpdateAt(PersistenceHelper.getCurrentTime());
            result = productInformationModel.update(update_product_info);
        }

        if (result) {
            ApplicationHelper.addMessage("Product Infomation Updated!");
            ApplicationHelper.redirect("/admin/product/view.xhtml?pid=" + pid, result);
        } else {
            ApplicationHelper.addMessage("Failed to update product informations !");
            ApplicationHelper.redirect("/admin/product/edit_information.xhtml?pid=" + pid, result);

        }
    }

    public List<Nations> getAllNation() {
        return nationModel.getAll();
    }

    ///SET GET
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductModelNo() {
        return productModelNo;
    }

    public void setProductModelNo(String productModelNo) {
        this.productModelNo = productModelNo;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getProductProducedNation() {
        return productProducedNation;
    }

    public void setProductProducedNation(int productProducedNation) {
        this.productProducedNation = productProducedNation;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductInformations() {
        return productInformations;
    }

    public void setProductInformations(String productInformations) {
        this.productInformations = productInformations;
    }

    public BigDecimal getProductSpeed() {
        return productSpeed;
    }

    public void setProductSpeed(BigDecimal productSpeed) {
        this.productSpeed = productSpeed;
    }

    public String getProductFeature1() {
        return productFeature1;
    }

    public void setProductFeature1(String productFeature1) {
        this.productFeature1 = productFeature1;
    }

    public String getProductFeature2() {
        return productFeature2;
    }

    public void setProductFeature2(String productFeature2) {
        this.productFeature2 = productFeature2;
    }

    public String getProductFeature3() {
        return productFeature3;
    }

    public void setProductFeature3(String productFeature3) {
        this.productFeature3 = productFeature3;
    }

    public int getProductLoad() {
        return productLoad;
    }

    public void setProductLoad(int productLoad) {
        this.productLoad = productLoad;
    }

}
