package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Brand;
import Model.BrandDAO;

@WebServlet("/brand/detail/*")
public class BrandDetail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/")) {
            pathInfo = pathInfo.substring(1); 
        }

        try {
            int brandId = Integer.parseInt(pathInfo);
          
            BrandDAO brandDAO = new BrandDAO();
            Brand brand = brandDAO.getBrandById(brandId);

            if (brand != null) {
                request.setAttribute("brand", brand);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/brand/detail.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Brand not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Brand ID");
        }
    }
}


