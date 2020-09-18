package nonageshop.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Address;

public class AddressDaoImplTest {
    private static AddressDaoImpl dao = AddressDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testSelectAddressByDong() {
        System.out.println("testSelectAddressByDong()");
        
        ArrayList<Address> addresses = dao.selectAddressByDong("구암동");
        Assert.assertNotNull(addresses);
        addresses.stream().forEach(System.out::println);
    }

}
