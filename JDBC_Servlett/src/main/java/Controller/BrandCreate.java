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

@WebServlet("/brand/create")
@MultipartConfig
public class BrandCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/brand/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        String name = request.getParameter("name");
        String countryStr = request.getParameter("country");
        String des = request.getParameter("des");
        String website = request.getParameter("website");
        String contact_email = request.getParameter("contact_email");
        String contact_phone = request.getParameter("contact_phone");

        
        if (name == null || countryStr == null || name.trim().isEmpty() || countryStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tên và thành phố là bắt buộc.");
            request.getRequestDispatcher("/brand/create.jsp").forward(request, response);
            return;
        }

        
        Part filePart = request.getPart("image");
        byte[] image = null;
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                image = inputStream.readAllBytes();
            }
        }

        
        Brand newBrand = new Brand();
        newBrand.setName(name);
        newBrand.setCountry(countryStr);
        newBrand.setDes(des);
        newBrand.setWebsite(website);
        newBrand.setContact_email(contact_email);
        newBrand.setContact_phone(contact_phone);
        newBrand.setImage(image);

        BrandDAO brandDAO = new BrandDAO();
        boolean success = brandDAO.insertBrand(newBrand);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/brand/show");
        } else {
            request.setAttribute("errorMessage", "Không thể thêm hãng mới.");
            request.getRequestDispatcher("/brand/create.jsp").forward(request, response);
        }
    }
}
