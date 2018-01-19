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
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
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
        log(bt);
                
        switch (bt) {
            case "nuevoProducto":
                nuevoProducto(request, response);
                break;
            
        }
                
        
        
//        String name;
//        try {
//            FileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            List<FileItem> items = upload.parseRequest(request);
//
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    name = item.getFieldName();
//                    if (name.equalsIgnoreCase("bt")) {
//                        switch (item.getString()) {
//                            case "nuevoProducto":
//                                nuevoProducto(request, response);
//                                break;
//
//                        }
//                    }
//
//                }
//            }
//
//        } catch (FileUploadException ex) {
//            log("Error de lectura");
//        }

    }

    protected void nuevoProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream stream=null;

        String nombre = request.getParameter("nombre");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int unidad = Integer.parseInt(request.getParameter("unidad"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        String descripcion = request.getParameter("descripcion");
        
        
        
        
        
//        String name, nombre="", descripcion="";
//        int precio=0, unidad=0, idCategoria=0;
//        try {
//            FileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            List<FileItem> items = upload.parseRequest(request);
//
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    name = item.getFieldName();
//                    switch (name) {
//                        case "nombre":
//                            nombre = item.getString();
//                            break;
//                        case "precio":
//                            precio = Integer.parseInt(item.getString());    
//                            break;
//                        case "unidad":
//                            unidad = Integer.parseInt(item.getString());
//                            break;
//                        case "descripcion":
//                            descripcion = item.getString();
//                            break;
//                        case "categoria":
//                            idCategoria = Integer.parseInt(item.getString());
//                            break;
//                        case "foto":
//                            log("es foto");
//                            break;
//                        
//                    }
//
//                }else{
//                    log("no inout field");
//                    stream = item.getInputStream();
//                }
//            }
//
//        } catch (FileUploadException ex) {
//            log("Error de lectura");
//        }
//;
        Categoria cat = adminBean.findCategoria(idCategoria);

        Part foto = request.getPart("foto");
        log("nombre archivo:" + foto.getName());
        log("tamaño:" + foto.getSize());
        log("tipo de archivo:" + foto.getContentType());
        stream = foto.getInputStream();

        Producto p = new Producto();
        p.setNombreProducto(nombre);
        p.setPrecioProducto(precio);
        p.setUnidadesProducto(unidad);
        p.setDescripcionProducto(descripcion);
        log("ss:"+stream);
        p.setCategoria(cat);
        
        p.setFotoProducto(IOUtils.toByteArray(stream));
        adminBean.insert(p);
        log("creado!");

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
