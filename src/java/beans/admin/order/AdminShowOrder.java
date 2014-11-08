package beans.admin.order;

import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import models.OrderModel;
import models.ProductModel;

@ManagedBean(name = "adminShowOrder")
@RequestScoped
public class AdminShowOrder {

    @EJB
    private ProductModel productModel;

    @EJB
    private OrderModel orderModel;
    private HtmlDataTable orders_table;
    private List<Orders> orders;

    public AdminShowOrder() {
    }

    public List<Orders> getOrders() {
        orders = orderModel.getAll();
        return orders;
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
    //////////////////////////////

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public HtmlDataTable getOrders_table() {
        return orders_table;
    }

    public void setOrders_table(HtmlDataTable orders_table) {
        this.orders_table = orders_table;
    }

}
