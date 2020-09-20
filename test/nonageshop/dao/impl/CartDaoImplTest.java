package nonageshop.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartDaoImplTest {
	private static CartDaoImpl dao = CartDaoImpl.getInstance();
	private static Connection con = JdbcUtil.getConnection();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@Test
	public void test1InsertCart() {
		System.out.println("test1InsertCart()");
		int res = dao.insertCart(new Cart(0, new Member("one", null), new Product(2, null), 2, new Date()));
		Assert.assertEquals(1, res);
	}

	@Test
	public void test2ListCart() {
		System.out.println("test2ListCart()");
		ArrayList<Cart> list = dao.listCart(new Member("one", null));
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test3DeleteCart() {
		System.out.println("test3DeleteCart()");
		int res = dao.deleteCart(43);
		Assert.assertEquals(1, res);
	}

}
