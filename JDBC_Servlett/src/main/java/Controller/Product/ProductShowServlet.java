package Controller.Product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.Product;
import Model.Product.ProductDAO;

// Servlet cho việc hiển thị danh sách sản phẩm
@WebServlet("/product/show")
public class ProductShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách sản phẩm từ ProductDAO
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();

        // In số lượng sản phẩm ra console để kiểm tra
        System.out.println("Số lượng sản phẩm: " + products.size());

        // Chuyển danh sách sản phẩm vào JSP để hiển thị
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product/show.jsp").forward(request, response);
    }
}
