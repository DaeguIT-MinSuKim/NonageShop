package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Orders;
import nonageshop.service.OrderService;

public class AdminOrderListActionHandler implements Command {
    private OrderService service = new OrderService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "admin/order/orderList.jsp";
        String key = "";
        if (request.getParameter("key") != null) {
            key = request.getParameter("key");
        }
        ArrayList<Orders> ordersList = service.listOrders(key);
        request.setAttribute("ordersList", ordersList);

        return url;
    }

}
