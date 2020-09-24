package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;

public class AdminMemberListHandler implements Command {
    private MemberService service = new MemberService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "admin/member/memberList.jsp";
        String memberName = "";
        if (request.getParameter("key") != null) {
            memberName = request.getParameter("key");
        }
        ArrayList<Member> memberList = service.getMemberList(memberName);
        request.setAttribute("memberList", memberList);
        return url;
    }

}
