package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.Product;
import Model.Product.ProductDAO;

import java.io.IOException;

@WebServlet("/shopDetail")
public class ShopDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdParam = request.getParameter("id"); 

        try {
            
            int productId = Integer.parseInt(productIdParam);

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

            if (product == null) {
                System.out.println("Product not found with ID: " + productId);
                response.sendRedirect("homepage");
                return;
            }

       
            request.setAttribute("product", product);
            request.getRequestDispatcher("shopDetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Invalid product ID format: " + productIdParam);
            response.sendRedirect("homepage");
        }
    }

}
