package nonageshop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class AdminProductWriteHandler implements Command {
    private ProductService service = new ProductService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "adminProductList.do";

        HttpSession session = request.getSession();

        int sizeLimit = 5 * 1024 * 1024;
        String savePath = "product_images";
        ServletContext context = session.getServletContext();
        String uploadFilePath = context.getRealPath(savePath);

        MultipartRequest multi = new MultipartRequest(request, // 1. 요청 객체
                uploadFilePath, // 2. 업로드될 파일이 저장될 파일 경로명
                sizeLimit, // 3. 업로드될 파일의 최대 크기(5Mb)
                "UTF-8", // 4. 인코딩 타입 지정
                new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
        ); // 이 시점을 기해 파일은 이미 저장이 되었다

        Product product = getProduct(multi);
        System.out.println("product > " + product);
        
        int res = service.insertProduct(product);

        if (res == 1) response.sendRedirect(url);

        return null;
    }

    private Product getProduct(MultipartRequest multi) {
        String name = multi.getParameter("name");
        String kind = multi.getParameter("kind");
        int price = Integer.parseInt(multi.getParameter("price"));
        int salePrice = Integer.parseInt(multi.getParameter("salePrice"));
        int margin = Integer.parseInt(multi.getParameter("margin"));
        String content = multi.getParameter("content");
        String image = multi.getFilesystemName("image");
     
        return new Product(name, kind, price, salePrice, margin, content, image);
    }

}
