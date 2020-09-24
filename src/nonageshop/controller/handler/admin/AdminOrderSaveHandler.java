package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.OrderService;

public class AdminOrderSaveHandler implements Command {
	private OrderService service = new OrderService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "adminOrderList.do";

		String[] resultArr = request.getParameterValues("result");
		System.out.println("resultArr > " + Arrays.toString(resultArr));
		for (String orderNo : resultArr) {
			int res = service.updateOrderResult(Integer.parseInt(orderNo));
			System.out.println("orderNo > " + orderNo + " res > " + res);
		}
		return url;

	}

}
