package nonageshop.dao;

import nonageshop.dto.Member;

public interface MemberDao {
    int confirmID(String userid);
    Member getMember(String id);
    int insertMember(Member member);
}
