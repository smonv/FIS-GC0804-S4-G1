package beans.admin.order;

import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.SessionHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import models.OrderModel;
import models.OrderProductDetailModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminEditProductOrder {

    @EJB
    private OrderProductDetailModel orderProductDetailModel;

    @EJB
    private ProductModel productModel;

    @EJB
    private OrderModel orderModel;

    //bean variable
    private Orders order;
    private List<OrderProductDetails> current_opds;
    private HtmlDataTable dataTable;
    private Map<String, Object> session;

    ///add more variable
    private int floor;
    private int quantity;
    private BigDecimal heightOfFloor;
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
        if (order.getContracts() != null) {
            ApplicationHelper.addMessage("Order has contract, can't edit now!");
            ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + order.getNumber(), true);
        }
        //get session variable from facescontext
        session = SessionHelper.getSessionMap();

        session.put("aeon", number); //aeon: admin_edit_order_number;

        ///nạp toàn bộ List<OrderProductDetails> vào session thể thay đổi
        if (session.get("aepo") == null) { //aepo = admin_edit_product_order
            session.put("aepo", order.getOrderProductDetailsList());
        }

        //set list order product for variable
        current_opds = (List<OrderProductDetails>) session.get("aepo");
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
        session.put("aepo", opds);

        FacesContext.getCurrentInstance().responseComplete();
        //add message and redirect
        ApplicationHelper.addMessage(productName + " updated!");
        ApplicationHelper.redirect("/admin/order/edit_products.xhtml?number=" + number, true);
    }

    public void remove() {
        int index = -1;
        //get list order product in session
        List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("aepo");
        /// get updated order product in datatable
        OrderProductDetails opd = (OrderProductDetails) dataTable.getRowData();

        if (opds.size() == 1) {
            ApplicationHelper.addMessage("At least one product in cart!");
            ApplicationHelper.redirect("/admin/order/edit_products.xhtml?number=" + number, true);
            return;
        }
        // find remove row in list order product
        for (OrderProductDetails o : opds) {
            //compare product id
            if (Objects.equals(o.getProductId().getPid(), opd.getProductId().getPid())) {
                index = opds.indexOf(o);
            }
        }

        if (index > -1) {
            opds.remove(index);
        }
        ApplicationHelper.addMessage("Product removed!");
        ApplicationHelper.redirect("/admin/order/edit_products.xhtml?number=" + number, true);
    }

    ////add new product to order product list
    public void add() {
        session = SessionHelper.getSessionMap();
        Products product = null;
        //check real product id
        String string_pid = ApplicationHelper.getRequestParameterMap().get("pid");
        int pid = 0;
        if (ApplicationHelper.isInteger(string_pid)) {
            pid = Integer.parseInt(string_pid);
            product = productModel.getById(pid);
        } else {
            ApplicationHelper.addMessage("Wrong Product ID!");
            ApplicationHelper.redirect("/404.xhtml", true);
        }
        List<String> list_msg = new ArrayList<>();
        session = SessionHelper.getSessionMap();
        ///get current edit order number
        String order_number = session.get("aeon").toString();
        if (session.get("aepo") != null && order_number != null) {
            Orders current_order = orderModel.getByNumber(order_number);//get edit order

            //get updated list order product
            List<OrderProductDetails> opds = (List<OrderProductDetails>) session.get("aepo");

            boolean exists = false;
            for (OrderProductDetails opd : opds) {
                if (opd.getProductId().getPid() == pid) {
                    opd.setQuantity(quantity);
                    opd.setFloors(floor);
                    opd.setHeightOfFloor(heightOfFloor);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                OrderProductDetails opd = new OrderProductDetails();
                opd.setOrderId(current_order);
                opd.setProductId(product);
                opd.setQuantity(quantity);
                opd.setFloors(floor);
                opd.setHeightOfFloor(heightOfFloor);
                opds.add(opd);
            }
            session.put("aepo", opds);
            ApplicationHelper.addMessage("Product added!");

            ApplicationHelper.redirect("/admin/order/edit_products.xhtml?number=" + order_number, true);

        } else {
            ApplicationHelper.addMessage("You are not in update mode!");
            ApplicationHelper.redirect("/product/show.xhtml?pid=" + pid + "&mode=admin_edit", true);
        }

    }

///save new update to database
    public void save() {
        session = SessionHelper.getSessionMap(); //get session

        Orders current_order = orderModel.getByNumber(number); // get edit order

        List<OrderProductDetails> change = new ArrayList<>();
        List<OrderProductDetails> remove = new ArrayList<>();
        List<OrderProductDetails> add = new ArrayList<>();

        //find change
        List<OrderProductDetails> current_opds = (List<OrderProductDetails>) session.get("aepo");

        List<OrderProductDetails> order_opds = current_order.getOrderProductDetailsList();
        for (OrderProductDetails co : current_opds) {
            boolean exists = false; // vòng ngoài có, vong trong k có
            for (OrderProductDetails oo : order_opds) {
                if (Objects.equals(oo.getOpdid(), co.getOpdid())) {
                    oo.setQuantity(co.getQuantity());
                    oo.setFloors(co.getFloors());
                    oo.setHeightOfFloor(co.getHeightOfFloor());
                    change.add(oo);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                add.add(co);
            }
        }

        for (OrderProductDetails oo : order_opds) {
            boolean exists = false;
            for (OrderProductDetails co : current_opds) {
                if (Objects.equals(co.getOpdid(), oo.getOpdid())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                remove.add(oo);
            }
        }

        boolean result = true;
        if (!orderProductDetailModel.createList(add)) {
            ApplicationHelper.addMessage("Failed to add new product to order!");
            result = false;
        }
        if (!orderProductDetailModel.updateList(change)) {
            ApplicationHelper.addMessage("Failed to update exists product in order!");
            result = false;
        }
        if (!orderProductDetailModel.removeList(remove)) {
            ApplicationHelper.addMessage("Failed to remove product in order!");
            result = false;
        }
        if (result) {
            ApplicationHelper.addMessage("Order products updated!");
        }

        session.remove("aepo");

        //update order product details for current order
        List<OrderProductDetails> updated_opds = orderProductDetailModel.getByOrderId(current_order);
        current_order.setOrderProductDetailsList(updated_opds);
        ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + number, true);
    }

    //////////
    public List<OrderProductDetails> getListOrderProducts() {
        session = SessionHelper.getSessionMap();
        return (List<OrderProductDetails>) session.get("aepo");
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getHeightOfFloor() {
        return heightOfFloor;
    }

    public void setHeightOfFloor(BigDecimal heightOfFloor) {
        this.heightOfFloor = heightOfFloor;
    }

}
