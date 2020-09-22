package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Product;

public class ProductDaoImplAdminTest {
    private static ProductDaoImpl dao = ProductDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testTotalRecordProductName() {
        System.out.println("testTotalRecordProductName()");
        int res = dao.totalRecord("슬");
        Assert.assertNotEquals(0, res);
        System.out.println("res > " + res);
    }
    
    @Test
    public void testTotalRecord() {
        System.out.println("testTotalRecord()");
        int res = dao.totalRecord("");
        Assert.assertNotEquals(0, res);
        System.out.println("res > " + res);
    }
    
    @Test
    public void testPageNumber() {
        System.out.println("testPageNumber()");
        String str = dao.pageNumber(1, "");
        System.out.println(str);
        str = dao.pageNumber(3, "");
        System.out.println(str);
    }

    @Test
    public void testListProduct() {
    	System.out.println("testListProduct()");
    	int totalRecord = dao.totalRecord("");
    	
    	ArrayList<Product> list;
    	for(int i=1; i<totalRecord/5+2; i++) {
    		list = dao.listProduct(i, "");
        	Assert.assertNotNull(list);
        	list.stream().forEach(System.out::println);
        	System.out.println();
        	list.clear();
    	}
    	
    	list = dao.listProduct(1, "달");
    	System.out.println("list.size() > " + list.size());
     	list.stream().forEach(System.out::println);
    }

	/* @Test */
    public void testInsertProduct() {
    	System.out.println("testInsertProduct()");
    	Product product = new Product("삼선슬리퍼", "4", 4000, 5000, 1000, "삼선슬리퍼", "w11.jpg");
    	int res = dao.insertProduct(product);
    	Assert.assertEquals(1, res);
    }

    @Test
    public void testUpdateProduct() {
    	System.out.println("testUpdateProduct()");
    	Product product = new Product(12, "삼선슬리퍼");
    	product.setPrice(5000);
    	product.setSalePrice(6000);
    	product.setMargin(1000);
    	product.setContent("고딩 최애 상품");
    	product.setBestYn("y");
    	product.setDelYn("y");
    	product.setKind("4");
    	product.setImage("w11.jpg");
    	int res = dao.updateProduct(product);
    	Assert.assertEquals(1, res);
    }

}
