package beans.admin.order;

import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.OrderModel;

@ManagedBean
@RequestScoped
public class AdminViewOrder {

    @EJB
    private OrderModel orderModel;

    //bean variable
    public int current_oid;
    private Orders order;
    //view param
    public String number;

    public AdminViewOrder() {
    }

    public void init() {
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/404.xhtml", true);
        }
    }

    public BigDecimal getTotalPrice(int quantity, BigDecimal price) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
        return totalPrice;
    }

    public BigDecimal getContructionPrice(int floor, BigDecimal heightOfFloor, BigDecimal contructionPrice) {
        BigDecimal totalContructionPrice = BigDecimal.ZERO;
        BigDecimal realHeight = heightOfFloor.multiply(new BigDecimal(floor));
        totalContructionPrice = totalContructionPrice.add(contructionPrice.multiply(realHeight));
        return totalContructionPrice.setScale(0, RoundingMode.HALF_UP);
    }

    public BigDecimal getContructionTime(int floor, BigDecimal heightOfFloor, int contructionTime) {
        BigDecimal totalContructionTime = BigDecimal.ZERO;
        BigDecimal realHeight = heightOfFloor.multiply(new BigDecimal(floor));
        totalContructionTime = totalContructionTime.add(realHeight.multiply(new BigDecimal(contructionTime)));
        return totalContructionTime.setScale(0, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalCost(OrderProductDetails opd, Products product) {
        BigDecimal totalCost = BigDecimal.ZERO;
        totalCost = totalCost.add(getTotalPrice(opd.getQuantity(), product.getPrice()));
        totalCost = totalCost.add(getContructionPrice(opd.getFloors(), opd.getHeightOfFloor(), product.getConstructionPrice()));
        return totalCost;
    }

    ////////////////SET GET
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
