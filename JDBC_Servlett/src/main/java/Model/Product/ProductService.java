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
public class ProductService {
	
    public List<Product> getProductsByTarget(String target) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE target = ?";
        
        try (Connection conn = ConnectionPool.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, target);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDetailDesc(rs.getString("detailDesc"));
                product.setShortDesc(rs.getString("shortDesc"));
                product.setQuantity(rs.getInt("quantity"));
                product.setBrand(rs.getString("brand"));
                product.setTarget(rs.getString("target"));
                product.setImage(rs.getBytes("image"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setSold(rs.getInt("sold"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
