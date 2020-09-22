package nonageshop.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.ds.JdbcUtil;
import nonageshop.dto.QnA;

public class QnADaoImplTest {
    private static QnADaoImpl dao = QnADaoImpl.getInstance();
    private static Connection con = JdbcUtil.getConnection();
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao.setCon(con);
    }

    @Test
    public void testListQna() {
        System.out.println("testListQna()");
        ArrayList<QnA> list = dao.listQna("one");
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testGetQnA() {
        System.out.println("testGetQnA()");
        QnA qna = dao.getQnA(1);
        Assert.assertNotNull(qna);
        System.out.println("qna > " + qna);
    }

    @Test
    public void testInsertqna() {
        System.out.println("testInsertqna()");
        QnA qna = new QnA("테스트테스트", "테스트테스트", "one");
        int res = dao.insertqna(qna);
        Assert.assertEquals(1, res);
        
    }

}
