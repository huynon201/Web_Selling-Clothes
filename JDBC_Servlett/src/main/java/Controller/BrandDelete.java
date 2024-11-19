package Controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BrandDAO;

@WebServlet("/brand/delete/*")
public class BrandDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID hãng không hợp lệ.");
            return;
        }

        String idStr = pathInfo.substring(1);
        try {
            int brandId = Integer.parseInt(idStr);

            BrandDAO brandDAO = new BrandDAO();
            boolean isDeleted = brandDAO.deleteBrand(brandId);

            if (isDeleted) {
                response.sendRedirect(request.getContextPath() + "/brand/show");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Xóa hãng thất bại.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID hãng phải là một số hợp lệ.");
        }
    }
}
