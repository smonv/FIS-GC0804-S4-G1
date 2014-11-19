package beans.admin.product;

import entities.Manufacturers;
import entities.Nations;
import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ManufacturerModel;
import models.NationModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminIndexProduct {

    @EJB
    private ManufacturerModel manufacturerModel;
    @EJB
    private NationModel nationModel;

    @EJB
    private ProductModel productModel;

    //bean variables
    private Nations nation;
    private Manufacturers manufacturer;
    private List<Products> products; //list product for show
    private int pageSize = 10; //page size for pagination;
    private int currentPage = 1; //current page value for pagination
    private long totalProduct = 0;
    private long totalPage = 1;
    private int currentNid;
    private int currentMid;
    //view params
    private String page; //page value get from param;
    private String productCode; //product code get from param
    private String nid;
    private String mid;

    public AdminIndexProduct() {
    }

    public void init() { //call when prerender view
        currentPage = ApplicationHelper.isInteger(page) ? Integer.parseInt(page) : 1; //check if page is number, convert page to integer, else current page = 1;

        currentNid = ApplicationHelper.isInteger(nid) ? Integer.parseInt(nid) : 0;
        nation = nationModel.getById(currentNid);
        currentMid = ApplicationHelper.isInteger(mid)? Integer.parseInt(mid):0;
        manufacturer = manufacturerModel.getById(currentMid);
        //set category and manufactorer is null, min load = 0, max load = 2000
        products = productModel.getAllAdmin(currentPage - 1, pageSize, productCode, nation, manufacturer); //get all product for show in datatable;
        totalProduct = productModel.countAllAdmin(productCode, nation, manufacturer); //count all product, set like get all function;
        if (totalProduct % pageSize != 0) {
            totalPage = totalProduct / pageSize + 1;
        } else {
            totalPage = totalProduct / pageSize;
        }

    }

    public List<Nations> getAllNations() {
        return nationModel.getAll();
    }

    public List<Manufacturers> getAllManufacturers() {
        return nation != null ? manufacturerModel.getAll(nation) : manufacturerModel.getAll();
    }

    ///SET GET
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
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

    public long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

}
