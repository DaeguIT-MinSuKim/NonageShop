package nonageshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.OrderDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dao.impl.OrderDaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;

public class OrderService {
    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private CartDao cartDao = CartDaoImpl.getInstance();
    
    public ArrayList<Orders> listOrders(String memberName){
        return orderDao.listOrders(memberName);
    }
    
    public int updateOrderResult(int orderNo) {
        return updateOrderResult(orderNo);
    }
    
    public int addOrderAndDetail(Orders orders) {
        String ordersSql = "INSERT INTO ORDERS(ID) VALUES(?)";
        String detailSql = "INSERT INTO ORDER_DETAIL (ONO, PNO, QUANTITY) VALUES(?, ?, ?)";
        Connection con = null;
        PreparedStatement orderPstmt = null;
        PreparedStatement detailPstmt = null;
        int ordersMaxNo = 0;
        try {
            con = JndiDS.getConnection();
            con.setAutoCommit(false);
            
            orderPstmt = con.prepareStatement(ordersSql);
            orderPstmt.setString(1, orders.getMember().getId());
            orderPstmt.executeUpdate();
            
            detailPstmt = con.prepareStatement(detailSql);
            ordersMaxNo = orderDao.selectMaxOrdersNo();
            
            for(OrderDetail od : orders.getDetails()) {
                detailPstmt.setInt(1, ordersMaxNo);
                detailPstmt.setInt(2, od.getCart().getProduct().getNo());
                detailPstmt.setInt(3, od.getCart().getQuantity());
                detailPstmt.executeUpdate();
                
                cartDao.updateCartResult(od.getCart());
            }
            
            con.commit();
        } catch (SQLException e) {
            rollbackUtil(con, e);
        } finally {
            closeUtil(con, orderPstmt, detailPstmt);
        }
		return ordersMaxNo;

    }

    private void rollbackUtil(Connection con, SQLException e) {
        try {
            System.out.println("roll back");
            con.rollback();
            throw new RuntimeException(e);
        } catch (SQLException ex) {
        }
    }

    private void closeUtil(Connection con, PreparedStatement dPstmt, PreparedStatement tPstmt) {
        try {
            if (dPstmt != null) {
                dPstmt.close();
            }
            if (tPstmt != null) {
                tPstmt.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
        }
    }
}
