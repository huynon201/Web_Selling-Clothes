package Model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ConnectionPool;

@WebServlet("/product-list")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String target = request.getParameter("target"); // Lấy tham số target từ URL (Nam/Nữ)

        // Kiểm tra nếu tham số target không hợp lệ, chuyển hướng về trang chủ
        if (target == null || (!target.equals("Nam") && !target.equals("Nữ"))) {
            response.sendRedirect("homepage"); // Hoặc bạn có thể hiển thị lỗi
            return;
        }

        List<Product> productList = new ArrayList<>();

        // Sử dụng ConnectionPool để lấy kết nối
        try (Connection conn = ConnectionPool.getConnection()) {
            // Truy vấn sản phẩm theo target (Nam/Nữ)
            String sql = "SELECT * FROM product WHERE target = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, target); // Gán tham số target (Nam hoặc Nữ)
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Tạo đối tượng Product từ dữ liệu trả về
                        Product product = new Product();
                        product.setId(rs.getInt("id"));
                        product.setName(rs.getString("name"));
                        product.setPrice(rs.getDouble("price"));
                        product.setImage(rs.getBytes("image"));
                        product.setDetailDesc(rs.getString("detailDesc"));
                        product.setShortDesc(rs.getString("shortDesc"));
                        product.setQuantity(rs.getInt("quantity"));
                        product.setCreateDate(rs.getDate("createDate"));
                        product.setSold(rs.getInt("sold"));
                        product.setBrand(rs.getString("brand"));
                        product.setTarget(rs.getString("target"));
                        
                        // Thêm sản phẩm vào danh sách
                        productList.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi, có thể chuyển hướng đến trang lỗi hoặc thông báo
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }

        // Gửi danh sách sản phẩm đến trang JSP
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }
}
