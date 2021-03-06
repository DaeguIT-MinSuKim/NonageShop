package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class AdminQnaListHandler implements Command {
    private QnAService service = new QnAService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "admin/qna/qnaList.jsp";
        
        ArrayList<QnA> qnaList = service.listAllQnA();
        request.setAttribute("qnaList", qnaList);
        return url;
    }

}
