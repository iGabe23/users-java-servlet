<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>${usuario == null ? 'Crear' : 'Editar'} Usuario - Jobify</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container mt-4">
      <h1>${usuario == null ? 'Crear' : 'Editar'} Usuario</h1>

      <form
        action="${pageContext.request.contextPath}${usuario == null ? '/usuarios/crear' : '/usuarios/editar/'.concat(usuario.idUsuario)}"
        method="post"
        class="mt-4"
      >
        <div class="mb-3">
          <label for="nombre" class="form-label">Nombre</label>
          <input
            type="text"
            class="form-control"
            id="nombre"
            name="nombre"
            value="${usuario.nombre}"
            required
          />
        </div>

        <div class="mb-3">
          <label for="correo" class="form-label">Correo</label>
          <input
            type="email"
            class="form-control"
            id="correo"
            name="correo"
            value="${usuario.correo}"
            required
          />
        </div>

        <div class="mb-3">
          <label for="contrasena" class="form-label">Contraseña</label>
          <input type="password" class="form-control" id="contrasena"
          name="contrasena" ${usuario == null ? 'required' : ''}>
          <c:if test="${usuario != null}">
            <small class="form-text text-muted"
              >Dejar en blanco para mantener la contraseña actual</small
            >
          </c:if>
        </div>

        <div class="mb-3">
          <label for="tipoUsuario" class="form-label">Tipo de Usuario</label>
          <select class="form-select" id="tipoUsuario" name="tipoUsuario" required>
            <option value="cliente" ${usuario.tipoUsuario == 'cliente' ? 'selected' : ''}>Cliente</option>
            <option value="admin" ${usuario.tipoUsuario == 'admin' ? 'selected' : ''}>Administrador</option>
            <option value="proveedor" ${usuario.tipoUsuario == 'proveedor' ? 'selected' : ''}>Proveedor</option>
          </select>
        </div>

        <div class="mb-3">
          <a
            href="${pageContext.request.contextPath}/usuarios"
            class="btn btn-secondary"
            >Cancelar</a
          >
          <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
      </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
