package nonageshop.controller.handler.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.Orders;
import nonageshop.service.OrderService;

public class OrderAllHandler implements Command {
    private OrderService orderService = new OrderService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "mypage/mypage.jsp";

        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            url = "loginform.do";
        } else {
            
            ArrayList<Integer> oseqList = orderService.selectSeqOrderIng(loginUser, "%");
            System.out.println("oseqList > " + oseqList);
            ArrayList<Orders> ordersList = new ArrayList<Orders>();
            for (int orderNo : oseqList) {
                Orders orders = orderService.orderListByMember(loginUser.getId(), orderNo, "%");
                System.out.println("orders > " + orders);
                ordersList.add(orders);
            }
            request.setAttribute("title", "총 주문 내역");
            request.setAttribute("ordersList", ordersList);
        }
        return url;
    }

}
