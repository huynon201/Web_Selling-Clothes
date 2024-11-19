package Controller.Product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Product.Product;
import Model.Product.ProductDAO;

@WebServlet("/product/create")
@MultipartConfig
public class ProductCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/product/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String detailDesc = request.getParameter("detailDesc");
        String shortDesc = request.getParameter("shortDesc");
        String brand = request.getParameter("brand");
        String target = request.getParameter("target");
        int quantity = 0;
        
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Quantity must be a valid number.");
            request.getRequestDispatcher("/product/create.jsp").forward(request, response);
            return;
        }

        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Price is required.");
            request.getRequestDispatcher("/product/create.jsp").forward(request, response);
            return;
        }

        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr.trim());
            if (price < 0) {
                request.setAttribute("errorMessage", "Price must be a positive number.");
                request.getRequestDispatcher("/product/create.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Price is not a valid number.");
            request.getRequestDispatcher("/product/create.jsp").forward(request, response);
            return;
        }

        // Xử lý ảnh (nếu có)
        Part filePart = request.getPart("image");
        byte[] image = saveImage(filePart); // Đọc ảnh thành mảng byte

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setDetailDesc(detailDesc);
        newProduct.setShortDesc(shortDesc);
        newProduct.setQuantity(quantity);
        newProduct.setBrand(brand);
        newProduct.setTarget(target);
        newProduct.setImage(image); // Thiết lập mảng byte cho thuộc tính image

        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        newProduct.setCreateDate(currentDate);
        newProduct.setSold(0);

        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.insertProduct(newProduct);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/product/show");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("errorMessage", "Error inserting product");
            request.getRequestDispatcher("/product/create.jsp").forward(request, response);
        }
    }

    // Phương thức lưu ảnh dưới dạng mảng byte
    private byte[] saveImage(Part filePart) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                return inputStream.readAllBytes(); // Đọc dữ liệu ảnh thành mảng byte
            }
        }
        return null; // Không có file ảnh được tải lên
    }
}
