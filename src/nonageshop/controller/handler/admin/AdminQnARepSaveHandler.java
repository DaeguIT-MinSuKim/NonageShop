package nonageshop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class AdminQnARepSaveHandler implements Command {
    private QnAService service = new QnAService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "adminQnAList.do";
        String no = request.getParameter("no").trim();
        String reply =request.getParameter("reply").trim();
        
        QnA qna = new QnA();
        qna.setNo(Integer.parseInt(no));
        qna.setRep(reply); 
        
        
        service.updateQnA(qna);
        
        response.sendRedirect(url);

        return null;
    }

}
