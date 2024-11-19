package Controller.Product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.Product;
import Model.Product.ProductDAO;

@WebServlet("/product/detail/*")
public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/")) {
            pathInfo = pathInfo.substring(1);  // Xóa dấu "/" đầu tiên
        }
        
        // Lấy ID sản phẩm từ URL
        String[] pathParts = pathInfo.split("/");
        int productId = Integer.parseInt(pathParts[0]);  // ID sản phẩm
        
        // Lấy thông tin sản phẩm
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
        
        if (product != null) {
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product/detail.jsp");
            dispatcher.forward(request, response);
        } else {
            // Nếu không tìm thấy sản phẩm, có thể chuyển hướng đến trang lỗi
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }
}
