package Model.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Model.ConnectionPool;

public class ProductDAO {

    // Phương thức thêm sản phẩm vào cơ sở dữ liệu
	public boolean insertProduct(Product product) {
	    String sql = "INSERT INTO product (name, price, detailDesc, shortDesc, quantity, brand, target, image, createDate, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ConnectionPool.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, product.getName());
	        stmt.setDouble(2, product.getPrice());
	        stmt.setString(3, product.getDetailDesc());
	        stmt.setString(4, product.getShortDesc());
	        stmt.setInt(5, product.getQuantity());
	        stmt.setString(6, product.getBrand());
	        stmt.setString(7, product.getTarget());
	        
	        // Lưu ảnh dưới dạng mảng byte
	        if (product.getImage() != null) {
	            stmt.setBytes(8, product.getImage());
	        } else {
	            stmt.setNull(8, java.sql.Types.BLOB);
	        }
	        
	        stmt.setDate(9, product.getCreateDate());
	        stmt.setInt(10, product.getSold());

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();  // Để kiểm tra lỗi SQL
	        return false;
	    }
	}


    // Phương thức lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM clothes.product";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
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
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE id = ?";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);  // Thiết lập ID sản phẩm cần tìm

            ResultSet rs = stmt.executeQuery();  // Thực thi câu truy vấn
            
            if (rs.next()) {
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
                return product;  // Trả về đối tượng sản phẩm
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về null nếu không tìm thấy sản phẩm
    }
    public byte[] getProductImageById(int productId) {
        String sql = "SELECT image FROM product WHERE id = ?";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBytes("image");  // Trả về dữ liệu ảnh dạng byte[]
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Trả về null nếu không có ảnh
    }
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name=?, price=?, brand=?, target=?, shortDesc=?, detailDesc=?, image=? WHERE id=?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getBrand());
            stmt.setString(4, product.getTarget());
            stmt.setString(5, product.getShortDesc());
            stmt.setString(6, product.getDetailDesc());
            
            // Lưu ảnh nếu có, nếu không thì setNull
            if (product.getImage() != null) {
                stmt.setBytes(7, product.getImage());
            } else {
                stmt.setNull(7, Types.BLOB);
            }

            stmt.setInt(8, product.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return false;
        }
    }
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM product WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
