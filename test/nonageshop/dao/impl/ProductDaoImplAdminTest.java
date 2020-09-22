package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;

public class ProductDaoImplAdminTest {
    private static ProductDaoImpl dao = ProductDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testTotalRecord() {
        fail("Not yet implemented");
    }

    @Test
    public void testPageNumber() {
        fail("Not yet implemented");
    }

    @Test
    public void testListProduct() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertProduct() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateProduct() {
        fail("Not yet implemented");
    }

}
