<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Biblioteca - Empleados</title>
</head>
<body>

<a href="/">
    <img src="/img/logo.jpg">
</a>

<h1>Empleados</h1>

<p>${msg}</p>


<table>
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Fecha Nacimiento</th>
        <th>Turno</th>
    </tr>

    <%--Formulario de AB de los empleados--%>
    <form action="empleados" method="post">

        <tr>
            <td><input type="number" name="dni"/></td>
            <td><input type="text" name="nombre"/></td>
            <td><input type="text" name="apellido"/></td>
            <td><input type="date" name="fecha_nacimiento"/></td>
            <td><select name="turno">
                <option value="Mañana">Mañana</option>
                <option value="Tarde">Tarde</option>
                <option value="Noche">Noche</option>
            </select> </td>
            <td><button type="submit">Agregar</button></td>
        </tr>
    </form>
    <jsp:useBean id="empleados" scope="request" type="java.util.List"/>
    <c:forEach var="empleado" items="${empleados}">

        <tr>
            <td>${empleado.dni}</td>
            <td>${empleado.nombre}</td>
            <td>${empleado.apellido}</td>
            <td>${empleado.fechaNacimiento}</td>
            <td>${empleado.turno.toString()}</td>
            <td>
                    <%--Hace un post pero q despues lo checkea con un if para llamar al doDelete--%>
                <form action="empleados" method="post">
                    <input type="hidden" name="id" value="${empleado.dni}"/>
                    <button type="submit">Borrar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>