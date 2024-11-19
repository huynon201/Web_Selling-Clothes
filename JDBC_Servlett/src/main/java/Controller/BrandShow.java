package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Brand;
import Model.BrandDAO;

@WebServlet("/brand/show")
public class BrandShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BrandDAO brandDAO = new BrandDAO();
        List<Brand> brands = brandDAO.getAllBrands();

   
        System.out.println("Số lượng hãng: " + brands.size());

      
        request.setAttribute("brands", brands);
        request.getRequestDispatcher("/brand/show.jsp").forward(request, response);
    }
}
