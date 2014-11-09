package beans.admin.order;

import entities.OrderProductDetails;
import entities.Orders;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import models.OrderModel;

@ManagedBean
@ViewScoped
public class AdminEditProductOrder {
    
    @EJB
    private OrderModel orderModel;

    //bean variable
    private Orders order;
    private List<OrderProductDetails> current_opds;
    private HtmlDataTable dataTable;
    private Map<String, Object> session;
    //view param
    private String number;
    
    public AdminEditProductOrder() {
    }
    
    public void init() {
        ///check order exists
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }

        //get session variable from facescontext
        session = SessionHelper.getSessionMap();
        ///nạp toàn bộ List<OrderProductDetails> vào session thể thay đổi
        if (session.get("aepo") == null) { //aepo = admin_edit_product_order
            session.put("aepo", order.getOrderProductDetailsList());
        }
    }
    
    public void update() {
        String productName = null;
        //get list order product in session
        List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("aepo");
        /// get updated order product in datatable
        OrderProductDetails opd = (OrderProductDetails) dataTable.getRowData();

        // find updated row in list order product
        for (OrderProductDetails o : opds) {
            //compare product id
            if (Objects.equals(o.getProductId().getPid(), opd.getProductId().getPid())) {
                ///set update value for order product
                o.setQuantity(opd.getQuantity());
                o.setFloors(opd.getFloors());
                o.setHeightOfFloor(opd.getHeightOfFloor());

                //get updated product name for message
                productName = o.getProductId().getName();
            }
        }

        //add message and redirect
        ApplicationHelper.addMessage(productName + " updated!");
        ApplicationHelper.redirect("/admin/order/edit_products.xhtml?number=" + number, true);
    }
    
    public void remove() {
        
    }

    ///SET GET
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public Orders getOrder() {
        return order;
    }
    
    public void setOrder(Orders order) {
        this.order = order;
    }
    
    public HtmlDataTable getDataTable() {
        return dataTable;
    }
    
    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public List<OrderProductDetails> getCurrent_opds() {
        return current_opds;
    }

    public void setCurrent_opds(List<OrderProductDetails> current_opds) {
        this.current_opds = current_opds;
    }
    
}
