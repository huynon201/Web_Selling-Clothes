package Controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Brand;
import Model.BrandDAO;

@WebServlet("/brand/update")
@MultipartConfig
public class BrandUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  request.setCharacterEncoding("UTF-8"); 
    	    response.setCharacterEncoding("UTF-8");
    	    response.setContentType("text/html; charset=UTF-8");
        String brandIdStr = request.getParameter("id");

        if (brandIdStr == null || brandIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        try {
            int brandId = Integer.parseInt(brandIdStr);
           BrandDAO brandDAO = new BrandDAO();
            Brand brand = brandDAO.getBrandById(brandId);

            if (brand != null) {
                request.setAttribute("brand", brand);
                request.getRequestDispatcher("/brand/update.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Hãng không tồn tại.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID hãng phải là một số hợp lệ.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   request.setCharacterEncoding("UTF-8");
    	    response.setCharacterEncoding("UTF-8"); 
    	    response.setContentType("text/html; charset=UTF-8");
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID hãng không hợp lệ.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID hãng phải là một số hợp lệ.");
            return;
        }

        
        String name = request.getParameter("name");
        String des = request.getParameter("des");
        String website = request.getParameter("website");
        String contact_email = request.getParameter("contact_email");
        String contact_phone = request.getParameter("contact_phone");
        String country = request.getParameter("country");
        

       
        Part imagePart = request.getPart("image");
        byte[] imageBytes = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream inputStream = imagePart.getInputStream()) {
                imageBytes = inputStream.readAllBytes();
            }
        }

       
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setDes(des);
        brand.setCountry(country);
        brand.setContact_email(contact_email);
        brand.setContact_phone(contact_phone);
        brand.setWebsite(website);

   
        if (imageBytes != null) {
            brand.setImage(imageBytes);
        }

       
        BrandDAO brandDAO = new BrandDAO();
        boolean success = brandDAO.updateBrand(brand);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/brand/show");
        } else {
            request.setAttribute("errorMessage", "Cập nhật hãng thất bại");
            request.setAttribute("brand", brand);
            request.getRequestDispatcher("/brand/update.jsp").forward(request, response);
        }
    }


}
