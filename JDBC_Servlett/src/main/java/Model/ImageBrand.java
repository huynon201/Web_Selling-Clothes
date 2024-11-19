package Model;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/brand/image")
public class ImageBrand extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID của brand từ tham số
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing brand ID");
            return;
        }

        int brandId;
        try {
            brandId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid brand ID format");
            return;
        }

        // Lấy ảnh từ database
        BrandDAO brandDAO = new BrandDAO();
        byte[] imageBytes = brandDAO.getBrandImageById(brandId);

        if (imageBytes == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found for brand ID: " + brandId);
            return;
        }

        // Gửi ảnh tới client
        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);
        try (OutputStream os = response.getOutputStream()) {
            os.write(imageBytes);
        }
    }
}
