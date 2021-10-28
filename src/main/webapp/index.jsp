<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<html>
<head>
    <title>Biblioteca</title>
</head>
<body>

<a href="/">
    <img src="/img/logo.jpg">
</a>

<div>
    <c:choose>
        <%-- Menu para el usuario que es admin --%>
        <c:when test="${sessionScope.user.admin}">
            <a href="/trucks">Camiones</a> |
            <%-- todo crear pagina drivers (jsp y servlet)--%>
            <a href="/empleados">Empleados</a> |
            <%-- todo crear pagina truck-drivers (jsp y servlet)--%>
            <a href="/truck-drivers">Choferes de camiones</a> |
            <%-- todo crear pagina trips (jsp y servlet)--%>
            <a href="/trips">Viajes</a> |
            <a href="/logout">Logout</a> |
            Admin ${sessionScope.user.userName}
        </c:when>

        <%-- Menu para el usuario que es chofer --%>
        <c:when test="${!sessionScope.user.admin}">
            <a href="/logout">Logout</a> |
            Chofer ${sessionScope.user.userName}
        </c:when>
    </c:choose>
</div>

<h1>Mis viajes</h1>

<%--Formulario para ver los viajes y marcar cuando arranca y termina un viaje--%>
<table>
    <tr>
        <th>Desde</th>
        <th>Hasta</th>
        <th>Camion</th>
        <th>Distancia</th>
        <th>Dias</th>
        <th>Tanques</th>
        <th>Iniciado</th>
        <th>Terminado</th>
    </tr>
    <c:forEach var="myTrip" items="${myTrips}">--%>
        <tr>
            <td>${myTrip.from}</td>
            <td>${myTrip.to}</td>
            <td>${myTrip.truckPlate}</td>
            <td>${myTrip.distance}</td>
            <td>${myTrip.timeDays}</td>
            <td>${myTrip.tanks}</td>
            <td>
                <form action="" method="post">
                    <input type="checkbox" name="started" value="${myTrip.start != null}"/>
                    <button type="submit">Guardar</button>
                </form>
            </td>
            <td>
                <form action="/" method="post">
                    <input type="checkbox" name="ended" value="${myTrip.end != null}"/>
                    <button type="submit">Guardar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>