package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Member;

public interface MemberDao {
    int confirmID(String userId);
    Member getMember(String id);
    int insertMember(Member member);
    
    //관리자모드 사용
    ArrayList<Member> listMember(String memberName);
}
