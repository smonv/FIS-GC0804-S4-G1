package beans.admin.statistic;

import entities.ListStatus;
import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import models.ListStatusModel;
import models.OrderModel;
import models.ProductModel;

@ManagedBean(name = "orderStatistic")
@RequestScoped
public class OrderStatistic {

    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private ProductModel productModel;

    @EJB
    private OrderModel orderModel;

    //bean variable
    private HtmlDataTable orders_table;
    private List<Orders> orders;
    private int current_page = 1;
    private int pageSize = 10;
    private long totalOrders;

    //bean param
    private String page;
    private String number;
    private String status;

    public OrderStatistic() {
    }

    public void init() {
        current_page = ApplicationHelper.getCurrentPage(page);
        if (number == null && status == null) {
            orders = orderModel.getAll(current_page - 1, pageSize);
            totalOrders = orderModel.getTotalOrder();
        } else if (number != null && status == null) {
            orders = orderModel.getListByNumber(number, current_page - 1, pageSize);
            totalOrders = orders.size();
        } else if (status != null && number == null) {

            int sid = 0;
            if (!ApplicationHelper.isInteger(status)) {
                ApplicationHelper.redirect("/404.xhtml", true);
                return;
            }
            sid = Integer.parseInt(status);
            ListStatus status = listStatusModel.getById(sid);
            if (status == null) {
                ApplicationHelper.redirect("/404.xhtml", true);
            }
            orders = orderModel.getListByStatus(status, current_page - 1, pageSize);
            totalOrders = orderModel.getCountByStatus(status);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalOrderCost(List<OrderProductDetails> opds) {
        BigDecimal totalCost = BigDecimal.ZERO;
        List<Products> products = productModel.getAll();

        for (OrderProductDetails opd : opds) {
            for (Products p : products) {
                if (Objects.equals(opd.getProductId().getPid(), p.getPid())) {
                    //add total product price
                    totalCost = totalCost.add(p.getPrice().multiply(new BigDecimal(opd.getQuantity())));
                    //add total contruction price
                    BigDecimal totalHeight = opd.getHeightOfFloor().multiply(new BigDecimal(opd.getFloors()));
                    totalCost = totalCost.add(p.getConstructionPrice().multiply(totalHeight).setScale(0, RoundingMode.HALF_UP));
                }
            }
        }

        return totalCost;
    }

    public int getTotalPages() {
        int pages = 0;
        long totalPages = totalOrders / pageSize;
        pages = totalPages > (int) totalPages ? ((int) totalPages) + 1 : (int) totalPages;
        pages = pages == 0 ? 1 : pages;
        return pages;
    }
    
    public List<ListStatus> getAllStatus(){
        return listStatusModel.getAll();
    }
    
    public String formatDate(Date date){
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }

    //////////////////////////////
    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public HtmlDataTable getOrders_table() {
        return orders_table;
    }

    public void setOrders_table(HtmlDataTable orders_table) {
        this.orders_table = orders_table;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
