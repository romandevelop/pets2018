/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.beans.CuentaSessionBeanLocal;
import cl.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roman
 */
@WebServlet(name = "ServletCuenta", urlPatterns = {"/ServletCuenta"})
public class ServletCuenta extends HttpServlet {

    @EJB
    private CuentaSessionBeanLocal serviceBean;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String bt = request.getParameter("bt");
        switch (bt) {
            case "login":
                login(request, response);
                break;
            case "registro":
                registro(request, response);
               break;
        }
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String rut = request.getParameter("rut");
        String clave = request.getParameter("clave");        
        
        Usuario user = serviceBean.login(rut, Hash.md5(clave));
        if (user!=null && user.getPerfil().getNombrePerfil().equalsIgnoreCase("cliente")) {
            request.getSession().setAttribute("cliente", user);
            response.sendRedirect("index.jsp");
            log("cliente");
        } else if(user!=null && user.getPerfil().getNombrePerfil().equalsIgnoreCase("administrador")){
            request.getSession().setAttribute("administrador", user);
            response.sendRedirect("index.jsp");
            log("admin");
        }else{
            response.sendRedirect("login.jsp");
            log("usuario no valido");
        }
       
    }
    
    protected void registro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
