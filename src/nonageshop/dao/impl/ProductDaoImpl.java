package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.ProductDao;
import nonageshop.dto.Product;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();
    private Connection con;
    
    private ProductDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }
    
    public void setCon(Connection con) {
        this.con = con;
    }
    
    @Override
    public ArrayList<Product> listNewProduct() {
        String sql = "SELECT NO, NAME, SALEPRICE, IMAGE FROM NEW_PRO_VIEW";
        try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Product> list = new ArrayList<>();
                do {
                    list.add(getNewBestProduct(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private Product getNewBestProduct(ResultSet rs) throws SQLException {
        int no = rs.getInt("NO");
        String name = rs.getString("NAME");
        int salePrice = rs.getInt("SALEPRICE");
        String image = rs.getString("IMAGE");
        return new Product(no, name, salePrice, image);
    }

    @Override
    public ArrayList<Product> listBestProduct() {
        String sql = "SELECT NO, NAME, SALEPRICE, IMAGE FROM BEST_PRO_VIEW";
        try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Product> list = new ArrayList<>();
                do {
                    list.add(getNewBestProduct(rs));
                } while (rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public Product getProduct(int no) {
        String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE "
                + "FROM PRODUCT "
                + "WHERE NO=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, no);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private Product getProduct(ResultSet rs) throws SQLException{
        // KIND, PRICE, MARGIN, CONTENT,  
        // DEL_YN, BEST_YN, REG_DATE
        Product pdt = getNewBestProduct(rs);//NO, NAME, SALEPRICE, IMAGE
        pdt.setKind(rs.getString("KIND"));
        pdt.setPrice(rs.getInt("PRICE"));
        pdt.setMargin(rs.getInt("MARGIN"));
        pdt.setContent(rs.getString("CONTENT"));
        pdt.setDelYn(rs.getString("DEL_YN"));
        pdt.setBestYn(rs.getString("BEST_YN"));
        pdt.setRegDate(rs.getDate("REG_DATE"));
        return pdt;
    }

    @Override
    public ArrayList<Product> listKindProduct(String kind) {
        String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE " + 
                     "  FROM PRODUCT " + 
                     " WHERE kind=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, kind);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    ArrayList<Product> list = new ArrayList<>();
                    do {
                        list.add(getProduct(rs));
                    } while (rs.next());
                    return list;
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

}
