package nonageshop.service;

import java.sql.Connection;

import nonageshop.dao.impl.MemberDaoImpl;
import nonageshop.ds.JndiDS;

public class MemberService {
    private MemberDaoImpl dao = MemberDaoImpl.getInstance();
    private Connection con = JndiDS.getConnection();

    public MemberService() {
        dao.setCon(con);
    }
    
    public int confirmId(String userId) {
        return dao.confirmID(userId);
    }
}
