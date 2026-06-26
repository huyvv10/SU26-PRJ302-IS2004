/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.CategoryDao;
import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Product;

/**
 *
 * @author VU VAN HUY
 */
public class DisplayProductServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayProductServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            CategoryDao catDao = new CategoryDao();
            List<Category> catList = new ArrayList<>();
            catList=catDao.getAllCategories();
            request.setAttribute("catList", catList);
            
            ProductDao prodDao = new ProductDao();
            List<Product> prdList = new ArrayList<>();
            prdList = prodDao.getAllProducts();
            request.setAttribute("prdList", prdList);
            int sumProduct = prdList.size();
            int n = prodDao.numberProductPerPage;
            int totalPages = sumProduct%n == 0 ? sumProduct/n :  sumProduct/n + 1;
            request.setAttribute("totalPages", totalPages);
            
            String strPage = request.getParameter("page");
            int page = strPage==null ? 1 : Integer.parseInt(strPage);
            
            prdList = prodDao.getProductsPaging(page);
            request.setAttribute("prdList", prdList);
            
            request.getRequestDispatcher("productslist.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String strKw = request.getParameter("txtKw");
            String strCatKw = request.getParameter("ddCat");

            ProductDao prodDao = new ProductDao();
            List<Product> prdList = new ArrayList<>();
            prdList = prodDao.searchProducts(strKw, strCatKw);
            request.setAttribute("prdList", prdList);

            CategoryDao catDao = new CategoryDao();
            List<Category> catList = new ArrayList<>();
            catList=catDao.getAllCategories();
            request.setAttribute("catList", catList);
            
            request.getRequestDispatcher("productslist.jsp")
                    .forward(request, response);            
        } catch (Exception e) {
            System.out.println("");
        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
