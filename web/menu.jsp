<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav-extended">
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">Pets</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${not empty sessionScope.cliente}">
                <li><a href="Mi Cuenta.jsp">Historial</a></li>
                <li><a href="historial.jsp">Historial</a></li>
                <li><a href="carro.jsp">Carro</a></li>
                <li><a href="salir.jsp">Salir</a></li>                
            </c:if>
            <c:if test="${not empty sessionScope.administrador}">
                <li><a href="crudProducto.jsp">Gestión Productos</a></li>
                <li><a href="ventas.jsp">Ventas</a></li>
                <li><a href="salir.jsp">Salir</a></li>                
            </c:if>
            <c:if test="${empty sessionScope.administrador and empty sessionScope.cliente}">
                <li><a href="mascotas.jsp">Mascotas</a></li>
                <li><a href="accesorios.jsp">Accesorios</a></li>
                <li><a href="carro.jsp">Carro</a></li>
                <li><a href="login.jsp">Login</a></li>
            </c:if>

        </ul>
    </div>
    <c:if test="${not empty sessionScope.administrador}">
        <nav class="nav-content">
            Bienvenido (a) ${sessionScope.administrador.nombreUser}
                           ${sessionScope.administrador.apellidoUser}
        </nav>
    </c:if>
    
    <c:if test="${not empty sessionScope.cliente}">
        <nav class="nav-content">
            Bienvenido (a) ${sessionScope.cliente.nombreUser}
                           ${sessionScope.cliente.apellidoUser}
        </nav>
    </c:if>
    
</nav>