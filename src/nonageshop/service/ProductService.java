package nonageshop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.ds.JndiDS;
import nonageshop.dto.Product;

public class ProductService {
    private ProductDaoImpl dao = ProductDaoImpl.getInstance();
    private Connection con = JndiDS.getConnection();

    public ProductService() {
        dao.setCon(con);
    }

    // 신상품 리스트 얻어오기
    public ArrayList<Product> newProducts() {
        return dao.listNewProduct();
    }

    // 베스트 상품 리스트 얻어오기
    public ArrayList<Product> bestProducts() {
        return dao.listBestProduct();
    }

    // 상품번호로 상품 정보 한개 얻어오기
    public Product getProduct(int no) {
        return dao.getProduct(no);
    }
    
    // 상품종류별 상품 리스트 얻어오기
    public ArrayList<Product> kindProducts(String kind) {
        return dao.listKindProduct(kind);
    }
}
