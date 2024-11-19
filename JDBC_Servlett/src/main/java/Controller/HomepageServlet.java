package Controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.Product;
import Model.Product.ProductDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();


        if (products == null || products.isEmpty()) {
            System.out.println("No products found in the database.");
        } else {
            System.out.println("Products found: " + products.size());
        }

 
        request.setAttribute("products", products);

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}

