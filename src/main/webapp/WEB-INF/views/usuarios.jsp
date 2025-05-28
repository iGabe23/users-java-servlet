<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Lista de Usuarios - Jobify</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container mt-4">
      <h1>Lista de Usuarios</h1>

      <a
        href="${pageContext.request.contextPath}/usuarios/crear"
        class="btn btn-primary mb-3"
      >
        Crear Nuevo Usuario
      </a>

      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Tipo de Usuario</th>
            <th>Fecha de Registro</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${usuarios}" var="usuario">
            <tr>
              <td>${usuario.idUsuario}</td>
              <td>${usuario.nombre}</td>
              <td>${usuario.correo}</td>
              <td>
                <span
                  class="badge ${usuario.tipoUsuario == 'admin' ? 'bg-danger' : usuario.tipoUsuario == 'proveedor' ? 'bg-warning' : 'bg-primary'}"
                >
                  ${usuario.tipoUsuario}
                </span>
              </td>
              <td>${usuario.createdAt}</td>
              <td>
                <a
                  href="${pageContext.request.contextPath}/usuarios/editar/${usuario.idUsuario}"
                  class="btn btn-sm btn-warning"
                  >Editar</a
                >
                <a
                  href="${pageContext.request.contextPath}/usuarios/eliminar/${usuario.idUsuario}"
                  class="btn btn-sm btn-danger"
                  onclick="return confirm('¿Está seguro de eliminar este usuario?')"
                  >Eliminar</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
