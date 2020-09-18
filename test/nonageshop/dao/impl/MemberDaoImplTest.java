package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Member;

public class MemberDaoImplTest {
    private static MemberDaoImpl dao = MemberDaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testConfirmIDSuccess() {
        System.out.println("testConfirmIDSuccess()");
        String userId = "one";
        int res = dao.confirmID(userId);
        Assert.assertEquals(1, res);
    }
    
    @Test
    public void testConfirmIDFail() {
        System.out.println("testConfirmIDSuccess()");
        String userId = "one1";
        int res = dao.confirmID(userId);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testGetMemberFind() {
        System.out.println("testGetMemberFind()");
        Member member = dao.getMember("one");
        Assert.assertNotNull(member);
        System.out.println(member);
    }
    
    @Test
    public void testGetMemberNotFind() {
        System.out.println("testGetMemberNotFind()");
        Member member = dao.getMember("one1");
        Assert.assertNull(member);
        System.out.println(member);
    }

    @Test
    public void testInsertMember() {
        System.out.println("testInsertMember()");
        Member member = new Member("three", "1111", "세번째", "three@test.co.kr", "133-110", "서울시 성동구 성수동 1가 1번지 21호", "010-333-3333");
        int res = dao.insertMember(member);
        Assert.assertEquals(1, res);
    }

}
