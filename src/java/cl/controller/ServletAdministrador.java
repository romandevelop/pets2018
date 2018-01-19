/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.beans.AdminBeanLocal;
import cl.model.*;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author roman
 */
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "ServletAdministrador", urlPatterns = {"/ServletAdministrador"})
public class ServletAdministrador extends HttpServlet {

    @EJB
    private AdminBeanLocal adminBean;

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
            case "nuevoProducto":
                nuevoProducto(request, response);
                break;

        }

    }

    protected void nuevoProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream stream = null;
        try {

            String nombre = request.getParameter("nombre");
            int precio = Integer.parseInt(request.getParameter("precio"));
            int unidad = Integer.parseInt(request.getParameter("unidad"));
            int idCategoria = Integer.parseInt(request.getParameter("categoria"));
            String descripcion = request.getParameter("descripcion");

            Categoria cat = adminBean.findCategoria(idCategoria);

            Part foto = request.getPart("foto");
            stream = foto.getInputStream();

            Producto p = new Producto();
            p.setNombreProducto(nombre);
            p.setPrecioProducto(precio);
            p.setUnidadesProducto(unidad);
            p.setDescripcionProducto(descripcion);
            p.setCategoria(cat);

            p.setFotoProducto(IOUtils.toByteArray(stream));
            adminBean.insert(p);
            log("creado!");
            request.setAttribute("msg", "Producto creado con exito");
            

        } catch (Exception e) {
            request.setAttribute("msg", "Hubo un error intenta nuevamente");
        }
        
        request.getRequestDispatcher("crudProducto.jsp").forward(request, response);

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
