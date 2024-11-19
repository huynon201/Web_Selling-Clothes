package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {

	
	public boolean insertBrand(Brand brand) {
        String sql = "INSERT INTO brand (name, des, country, website, contact_email, contact_phone, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, brand.getName());
            stmt.setString(2, brand.getDes());
            stmt.setString(3, brand.getCountry());
            stmt.setString(4, brand.getWebsite());
            stmt.setString(5, brand.getContact_email());
            stmt.setString(6, brand.getContact_phone());

            if (brand.getImage() != null) {
                stmt.setBytes(7, brand.getImage());
            } else {
                stmt.setNull(7, java.sql.Types.BLOB);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
		
		 
	    public List<Brand> getAllBrands() {
	        List<Brand> brands = new ArrayList<>();
	        String sql = "SELECT * FROM brand";
	        
	        try (Connection conn = ConnectionPool.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            
	            while (rs.next()) {
	                Brand brand = new Brand();
	                brand.setId(rs.getInt("id"));
	                brand.setName(rs.getString("name"));
	                brand.setImage(rs.getBytes("image"));
	                brand.setDes(rs.getString("des"));
	                brand.setCountry(rs.getString("country"));
	                brand.setWebsite(rs.getString("website"));
	                brand.setContact_email(rs.getString("contact_email"));
	                brand.setContact_phone(rs.getString("contact_phone"));
	                brands.add(brand);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return brands;
	    }
	    public byte[] getBrandImageById(int brandId) {
	        String sql = "SELECT image FROM brand WHERE id = ?";
	        
	        try (Connection conn = ConnectionPool.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, brandId);

	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return rs.getBytes("image");  
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;  
	    }
	    public Brand getBrandById(int brandId) {
	        String sql = "SELECT * FROM brand WHERE id = ?";
	        try (Connection conn = ConnectionPool.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, brandId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Brand brand = new Brand();
	                brand.setId(rs.getInt("id"));
	                brand.setName(rs.getString("name"));
	                brand.setDes(rs.getString("des"));
	                brand.setCountry(rs.getString("country"));
	                brand.setContact_email(rs.getString("contact_email"));
	                brand.setContact_phone(rs.getString("contact_phone"));
	                brand.setWebsite(rs.getString("website"));
	                brand.setImage(rs.getBytes("image"));
	                return brand;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;  
	    }
	    public boolean updateBrand(Brand brand) {
	        String sql = "UPDATE brand SET name=?, des=?, country=?, website=?, contact_email=?, contact_phone=?, image=? WHERE id=?";
	        try (Connection conn = ConnectionPool.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            
	            if (brand.getContact_phone().length() > 20) {
	                throw new IllegalArgumentException("Số điện thoại quá dài (tối đa 20 ký tự).");
	            }
	  
	            
	            stmt.setString(1, brand.getName());
	            stmt.setString(2, brand.getDes());
	            stmt.setString(3, brand.getCountry());
	            stmt.setString(4, brand.getWebsite());
	            stmt.setString(5, brand.getContact_email());
	            stmt.setString(6, brand.getContact_phone());

	            if (brand.getImage() != null) {
	                stmt.setBytes(7, brand.getImage());
	            } else {
	                stmt.setNull(7, Types.BLOB);
	            }

	            stmt.setInt(8, brand.getId());

	           
	            int rowsUpdated = stmt.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            System.err.println("Lỗi cập nhật brand: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean deleteBrand(int id) {
	        String query = "DELETE FROM brand WHERE id = ?";
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
