package Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Product.ProductDAO;

@WebServlet("/product/image")
public class ImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id"); // Lấy tham số 'id'
        
        if (idParam == null || idParam.trim().isEmpty()) {
            // Trả về lỗi 400 nếu tham số 'id' bị thiếu hoặc rỗng
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ hoặc bị thiếu.");
            return;
        }
        
        try {
            int productId = Integer.parseInt(idParam); // Chuyển đổi 'id' sang số nguyên
            ProductDAO productDAO = new ProductDAO();

            // Lấy dữ liệu ảnh từ cơ sở dữ liệu
            byte[] imageData = productDAO.getProductImageById(productId);

            if (imageData != null) {
                // Đặt kiểu nội dung là image/jpeg (hoặc image/png)
                response.setContentType("image/jpeg");
                response.setContentLength(imageData.length);

                // Ghi dữ liệu ảnh vào output stream
                response.getOutputStream().write(imageData);
            } else {
                // Trả về 404 nếu không tìm thấy ảnh
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy ảnh cho sản phẩm ID: " + productId);
            }
        } catch (NumberFormatException e) {
            // Trả về lỗi 400 nếu tham số 'id' không phải là số hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ: " + idParam);
        }
    }
}
