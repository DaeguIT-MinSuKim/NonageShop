package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Orders;

public class OrderDaoImplTest {
    private static OrderDaoImpl dao = OrderDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }
    @Test
    public void testListOrderByMember() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectSeqOrderIng() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectMaxOrdersNo() {
        fail("Not yet implemented");
    }

    @Test
    public void testListOrders() {
        System.out.println("testListOrders()");
        ArrayList<Orders> ordersLists = dao.listOrders("김나리");
        Assert.assertNotNull(ordersLists);
        ordersLists.stream().forEach(System.out::println);
    }

    @Test
    public void testUpdateOrderResult() {
        System.out.println("testUpdateOrderResult()");
        int res = dao.updateOrderResult(7);
        Assert.assertEquals(1, res);
    }

}
