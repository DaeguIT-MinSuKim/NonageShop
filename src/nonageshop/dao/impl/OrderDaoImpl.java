package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.OrderDao;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.dto.Product;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl instance = new OrderDaoImpl();
    private Connection con;
    
    private OrderDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @Override
    public Orders listOrderByMember(String memberId, int orderNo, String result) {
        String sql = "SELECT ONO, MID, MNAME, PHONE, ZIP_NUM, ADDRESS, DNO, ORDER_DATE, RESULT, "
                    +       "PNO, PNAME, QUANTITY, SALEPRICE "
                    +  "FROM ORDER_VIEW "
                    + "WHERE MID=? AND RESULT LIKE ? AND ONO=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, memberId);
            pstmt.setString(2, result);
            pstmt.setInt(3, orderNo);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    Orders orders = new Orders();
                    orders.setNo(rs.getInt("ONO"));
                    Member member = new Member(rs.getString("MID"), rs.getString("MNAME"));
                    member.setPhone(rs.getString("PHONE"));
                    member.setZipNum(rs.getString("ZIP_NUM"));
                    member.setAddress(rs.getString("ADDRESS"));
                    orders.setMember(member);
                    
                   ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
                    do {
                        OrderDetail detail = new OrderDetail();
                        detail.setNo(rs.getInt("DNO"));
                        detail.setOrderDate(rs.getDate("ORDER_DATE"));
                        detail.setResult(rs.getString("RESULT"));
                        
                        Product product = new Product(rs.getInt("PNO"), rs.getString("PNAME"));
                        product.setSalePrice(rs.getInt("SALEPRICE"));
                        
                        Cart cart = new Cart();
                        cart.setProduct(product);
                        cart.setQuantity(rs.getInt("QUANTITY"));
                        
                        detail.setCart(cart);
                      
                        details.add(detail);
                    }while(rs.next());
                    
                    orders.setDetails(details);
                    return orders;
                }
                
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Integer> selectSeqOrderIng(Member member) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectMaxOrdersNo() {
        String sql = "select max(no) from orders";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
    }

}
