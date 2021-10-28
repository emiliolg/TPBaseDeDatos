<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Biblioteca - Camiones</title>
</head>
<body>

<a href="/">
    <img src="/img/logo.jpg">
</a>

<h1>Camiones</h1>

<p>${msg}</p>


<table>
    <tr>
        <th>Patente</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Max.Toneladas</th>
        <th>Capacidad Tanque</th>
        <th>Consumo l/km</th>
    </tr>

    <%--Formulario para guardar un nuevo camion--%>
    <form action="trucks" method="post">

        <tr>
            <td><input type="number" name="plate_number"/></td>
            <td><input type="text" name="brand"/></td>
            <td><input type="text" name="model"/></td>
            <td><input type="number" name="max_tons"/></td>
            <td><input type="number" name="tank_capacity"/></td>
            <td><input type="number" name="consuption"/></td>
            <td><button type="submit">Agregar</button></td>
        </tr>
    </form>
    <c:forEach var="truck" items="${trucks}">

        <tr>
            <td>${truck.plateNumber}</td>
            <td>${truck.brand}</td>
            <td>${truck.model}</td>
            <td>${truck.maxTons}</td>
            <td>${truck.tankCapacity}</td>
            <td>${truck.consumption}</td>

            <td>
                    <%--Hace un post pero q despues lo checkea con un if para llamar al doDelete--%>
                <form action="trucks" method="post">
                    <input type="hidden" name="id" value="${truck.plateNumber}"/>
                    <button type="submit">Borrar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>