package nonageshop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class AdminQnADetailHandler implements Command {
    private QnAService service = new QnAService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "admin/qna/qnaDetail.jsp";
        String no = request.getParameter("no").trim();
        
        QnA qna = service.getQnA(Integer.parseInt(no));
        request.setAttribute("qna", qna);

        return url;
    }

}
