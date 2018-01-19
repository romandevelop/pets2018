<%@page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="cl.model.*"%>
<%@page import="java.util.List"%>
<%@page import="cl.beans.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@include file="templates/header.jsp" %>

<%@include file="templates/menu.jsp" %>

<%!
    AdminBeanLocal service;%>

<%
    InitialContext ctx = new InitialContext();
    service = (AdminBeanLocal) //        ctx.lookup("java:global/adminBean");
            ctx.lookup("java:global/EjercicioCurso2018/AdminBean!cl.beans.AdminBeanLocal");
    //ctx.lookup("java:comp/env/AdministradorSessionBean");
    List<Categoria> categorias = service.categoriaList();
    List<Producto> productos = service.productoList();
%>

<c:set scope="page" var="categorias" value="<%=categorias%>"/>
<c:set scope="page" var="productos" value="<%=productos%>"/>

<div class="row">
    <div class="col s6">
        <div class="card-panel z-depth-3">
            <h4>Nuevo Producto</h4>
            <form name="data" action="ServletAdministrador" method="post" enctype="multipart/form-data">
                <div class="input-field">
                    <input id="nombre" name="nombre" type="text" class="validate">
                    <label for="nombre">Nombre</label>
                </div>
                <div class="input-field">
                    <input id="precio" type="text" name="precio" class="validate">
                    <label for="precio">Precio</label>
                </div>
                <div class="input-field">
                    <input id="unidad" type="text" name="unidad" class="validate">
                    <label for="unidad">Unidad</label>
                </div>
                <div class="input-field">
                    <select name="categoria">
                        <c:forEach items="${pageScope.categorias}" var="cat">
                            <option value="${cat.idCategoria}">
                                ${cat.nombreCategoria}
                            </option>
                        </c:forEach>
                    </select>
                    <label>Categoria</label>
                </div>

                <div class="file-field input-field">
                    <div class="btn">
                        <span>File</span>
                        <input type="file" name="foto">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" name="foto_path">
                    </div>
                </div>


                <div class="input-field">
                    <textarea id="descripcion" name="descripcion" class="materialize-textarea"></textarea>
                    <label for="descripcion">Descripcion</label>
                </div>

                <button type="submit" name="bt" value="nuevoProducto" class="btn">
                    crear
                </button>
            </form>
        </div>
    </div>


    <div class="col s6">
        <table class="bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Unidad</th>
                    <th>Categoria</th>
                    <th>Foto</th>

                </tr>
            </thead>

            <tbody>
                <%
                    String base_64;
                    for (Producto p : productos) {
                        out.println("<tr>");
                        out.println("<td>"+p.getIdProducto()+"</td>");
                        out.println("<td>"+p.getNombreProducto()+"</td>");
                        out.println("<td>"+p.getPrecioProducto()+"</td>");
                        out.println("<td>"+p.getUnidadesProducto()+"</td>");
                        out.println("<td>"+p.getCategoria().getNombreCategoria()+"</td>");
                        
                        if (p.getFotoProducto() != null) {
                            out.print("<td><img width='50' src='data:image/*;base64," + Base64.encode(p.getFotoProducto()) + "'/></td>");
                        }
                        out.println("</tr>");
                    }
                %>
            </tbody>
        </table>

    </div>

</div>


<%@include file="templates/footer.jsp" %>  


