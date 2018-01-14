<%-- 
    Document   : index
    Created on : 13-ene-2018, 20:26:34
    Author     : roman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body class="green darken-1" >
        <div class="row" >
            <div class="col s4 offset-s4 white z-depth-5">
                <h4 class="center-align">Pets App</h4>
                <br>
                <form action="ServletCuenta" method="post">
                    <div class="input-field">
                        <input placeholder="Ingresa tu Rut" id="rut" type="text" name="rut" class="validate">
                        <label for="rut">Rut</label>
                    </div>
                    <div class="input-field">
                        <input  placeholder="Ingresa tu clave" id="pass" type="password" name="pass" class="validate">
                        <label for="pass">Clave</label>
                    </div>
                    <p class="center-align" >
                        <a href="registro.jsp">Si no tienes cuenta registrate pulsando aqui </a>
                    </p>
                    <button type="submit" name="bt" class="btn right" value="login">
                        Ingresar
                    </button>                    
                    <br><br>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>