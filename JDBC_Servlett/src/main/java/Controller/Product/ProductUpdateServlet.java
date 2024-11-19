package Controller.Product;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Product.Product;
import Model.Product.ProductDAO;

@WebServlet("/product/update")
@MultipartConfig
public class ProductUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  request.setCharacterEncoding("UTF-8"); // Đọc dữ liệu tiếng Việt từ request
    	    response.setCharacterEncoding("UTF-8"); // Trả dữ liệu tiếng Việt về client
    	    response.setContentType("text/html; charset=UTF-8");
        String productIdStr = request.getParameter("id");

        if (productIdStr == null || productIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/product/update.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm phải là một số hợp lệ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   request.setCharacterEncoding("UTF-8"); // Đọc dữ liệu tiếng Việt từ request
    	    response.setCharacterEncoding("UTF-8"); // Trả dữ liệu tiếng Việt về client
    	    response.setContentType("text/html; charset=UTF-8");
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm phải là một số hợp lệ.");
            return;
        }

        // Lấy dữ liệu từ các trường của form
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String target = request.getParameter("target");
        String detailDesc = request.getParameter("detailDesc");
        String shortDesc = request.getParameter("shortDesc");
        double price;

        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Giá sản phẩm phải là một số hợp lệ.");
            request.getRequestDispatcher("/product/update.jsp").forward(request, response);
            return;
        }

        // Xử lý file ảnh
        Part imagePart = request.getPart("image");
        byte[] imageBytes = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream inputStream = imagePart.getInputStream()) {
                imageBytes = inputStream.readAllBytes();
            }
        }

        // Tạo đối tượng Product và gán dữ liệu
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setTarget(target);
        product.setDetailDesc(detailDesc);
        product.setShortDesc(shortDesc);

        // Nếu có ảnh mới, cập nhật ảnh
        if (imageBytes != null) {
            product.setImage(imageBytes);
        }

        // Cập nhật sản phẩm vào cơ sở dữ liệu
        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.updateProduct(product);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/product/show");
        } else {
            request.setAttribute("errorMessage", "Cập nhật sản phẩm thất bại");
            request.setAttribute("product", product);
            request.getRequestDispatcher("/product/update.jsp").forward(request, response);
        }
    }


}
