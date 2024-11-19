package Controller.Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.ProductDAO;

@WebServlet("/product/delete/*")
public class ProductDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // "/{id}"
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        String idStr = pathInfo.substring(1); // Lấy ID từ URL
        try {
            int productId = Integer.parseInt(idStr);

            ProductDAO productDAO = new ProductDAO();
            boolean isDeleted = productDAO.deleteProduct(productId);

            if (isDeleted) {
                response.sendRedirect(request.getContextPath() + "/product/show"); // Quay về danh sách sản phẩm
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Xóa sản phẩm thất bại.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm phải là một số hợp lệ.");
        }
    }
}
