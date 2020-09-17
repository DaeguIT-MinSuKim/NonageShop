package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.ds.JndiDS;
import nonageshop.dto.Product;

public class ProductDaoImplTest {
    private static ProductDaoImpl dao = ProductDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testListNewProduct() {
        System.out.println("testListNewProduct()");
        ArrayList<Product> newPdtList = dao.listNewProduct();
        Assert.assertNotNull(newPdtList);
        newPdtList.stream().forEach(System.out::println);
    }

    @Test
    public void testListBestProduct() {
        System.out.println("testListBestProduct()");
        ArrayList<Product> bestPdtList = dao.listBestProduct();
        Assert.assertNotNull(bestPdtList);
        bestPdtList.stream().forEach(System.out::println);
    }

    @Test
    public void testGetProduct() {
        System.out.println("testGetProduct()");
        Product getProduct = dao.getProduct(1);
        Assert.assertNotNull(getProduct);
        System.out.println(getProduct);
    }

    @Test
    public void testListKindProduct() {
        System.out.println("testListKindProduct()");
        String[] kindArr = {"1", "2", "3", "4", "5"};
        for(String kind : kindArr) {
            ArrayList<Product> kindList = dao.listKindProduct(kind);
            Assert.assertNotNull(kindList);
            System.out.println(kind);
            kindList.stream().forEach(System.out::println);
        }
    }

}
