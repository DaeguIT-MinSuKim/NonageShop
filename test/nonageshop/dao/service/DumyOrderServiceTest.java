package nonageshop.dao.service;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.dto.Product;
import nonageshop.service.CartService;

public class DumyOrderServiceTest {

	private DumyOrderService service = new DumyOrderService();
	private static Member member;
	private static ArrayList<Cart> cartlist;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		CartService cartService = new CartService();
		
		member = new Member("two", null);
		
		Cart[] cartArr = {
				new Cart(0, member, new Product(9, null), 3, null),
				new Cart(0, member, new Product(10, null), 3, null),
				new Cart(0, member, new Product(11, null), 3, null)		
		};
		
		for(Cart c : cartArr) cartService.addCart(c);
		
		cartlist = cartService.listCartByMember(member);
	}
	
	@Test
	public void testOrderListByMember() {
		System.out.println("testOrderListByMember()");
		Orders orders = service.orderListByMember("one", 1, "1");
//		System.out.println(orders);
		System.out.printf("%d %s%n",orders.getNo(), orders.getMember().getId());
		orders.getDetails().stream().forEach(System.out::println);
	}

	@Test
	public void testMaxOrderNo() {
		System.out.println("testMaxOrderNo()");
		int res = service.maxOrderNo();
		Assert.assertNotEquals(0, res);
		System.out.println("max > " + res);
	}

	@Test
	public void testAddOrderAndDetail() {
		System.out.println("testAddOrderAndDetail()");
		
		ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
		for(Cart c : cartlist) {
			OrderDetail detail01 = new OrderDetail();
			detail01.setCart(c);
			details.add(detail01);
		}
		
		Orders orders = new Orders();
		orders.setDetails(details);
		orders.setMember(member);
		
		service.addOrderAndDetail(orders);
		System.out.println("max > " + service.maxOrderNo());
		Orders res = service.orderListByMember(member.getId(), service.maxOrderNo(), "1");
		System.out.println(res);
		System.out.printf("%d %s %tF%n",res.getNo(), res.getMember().getId(), res.getOrderDate());
		res.getDetails().stream().forEach(System.out::println);
	}

}
