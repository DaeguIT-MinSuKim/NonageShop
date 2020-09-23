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

public class AdminProductUpdateHandler implements Command {
    private ProductService service = new ProductService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "adminProductList.do";
        
        HttpSession session = request.getSession();
        
        MultipartRequest multi = getMultipartRequest(request, session);
        
        Product product =  getProduct(multi);
        System.out.println("product > " + product);
        service.updateProduct(product);
        
        response.sendRedirect(url);

        return null;
    }

    private MultipartRequest getMultipartRequest(HttpServletRequest request, HttpSession session) throws IOException {
        int sizeLimit = 5 * 1024 * 1024;
        String savePath = "product_images";
        ServletContext context = session.getServletContext();
        String uploadFilePath = context.getRealPath(savePath);
        
        MultipartRequest multi = new MultipartRequest(
                request, // 1. 요청 객체
                uploadFilePath, // 2. 업로드될 파일이 저장될 파일 경로명
                sizeLimit, // 3. 업로드될 파일의 최대 크기(5Mb)
                "UTF-8", // 4. 인코딩 타입 지정
                new DefaultFileRenamePolicy() // 5. 덮어쓰기를 방지 위한 부분
        ); // 이 시점을 기해 파일은 이미 저장이 되었다
        return multi;
    }

    private Product getProduct(MultipartRequest multi) {
        String name = multi.getParameter("name");
        String kind = multi.getParameter("kind");
        
        String priceStr = multi.getParameter("price").replaceAll(",", "");
        int price = Integer.parseInt(priceStr);
        
        String salePriceStr = multi.getParameter("salePrice").replaceAll(",", "");
        int salePrice = Integer.parseInt(salePriceStr);
        
        String marginStr = multi.getParameter("margin").replaceAll(",", "");
        int margin = Integer.parseInt(marginStr);
        
        String content = multi.getParameter("content");
        String image = null;
        if(multi.getFilesystemName("image")==null){
            image =  multi.getParameter("nonmakeImg");
        }else{
            image =  multi.getFilesystemName("image");
        }
        
        Product product = new Product(name, kind, price, salePrice, margin, content, image);
        product.setNo(Integer.parseInt(multi.getParameter("pno")));
        
        return product;
    }

}
