<%--
  Created by IntelliJ IDEA.
  User: abdo
  Date: 3/28/2023
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of emprunts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../style.css">
</head>
<body>
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Livre.do">
            Livres
        </a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Adherent.do">
            Adherents
        </a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Emprunt.do">
            Emprunter
        </a>
        <a class="navbar-brand" size="1px" href="${pageContext.request.contextPath}/session/logout">
            Deconnection(${account.username})
        </a>
    </div>
</nav>

<form class="form" method="post" action="${pageContext.request.contextPath}/Emprunt.do">
    <input type="hidden" name="action" value="Emprunter" />
    <div class="row mb-3">
        <label for="idAdherent" class="col-sm-2 col-form-label">Adherent</label>
        <select id="idAdherent" class="form-select" aria-label="Default select example" name="idAdherent">
            <c:forEach items="${adherents}" var="adherent">
                <option value="${adherent.id}">
                        ${adherent.prenomAdherent} ${adherent.nomAdherent}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="row mb-3">
        <label for="empruntexemplaire" class="col-sm-2 col-form-label">Exemplaire</label>
        <select id="empruntexemplaire" class="form-select" aria-label="Default select example" name="empruntexemplaire">
            <c:forEach items="${books}" var="book">
                <option value="${book.id}">
                        ${book.titre}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="row mb-3">
        <label for="dateRetour" class="col-sm-2 col-form-label">Date de retour</label>
        <div class="col-sm-10">
            <input type="date" class="form-control" id="dateRetour" name="dateRetour">
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form>
<%--<h1></h1>--%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Adhérent</th>
            <th scope="col">Livre</th>
            <th scope="col">Date d'emprunt</th>
            <th scope="col">Date de retour</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>

            <tbody>
            <c:forEach items="${copies}" var="emprunt">
            <tr>
                <td>${emprunt.id}</td>
                <td>${emprunt.adherent.nomAdherent} ${emprunt.adherent.prenomAdherent}</td>
                <td>${emprunt.exemplaire.livre.titre}</td>
                <td>${ emprunt.dateEmprunt }</td>
                <td>${ emprunt.dateRetour }</td>
                <td>

                    <form method="post" action="${pageContext.request.contextPath}/Emprunt.do">
                        <input type="hidden" name="idemprunt" value="${emprunt.id}" />
                        <input type="hidden" name="action" value="Retourner" />
                        <button type="submit" class="btn btn-outline-primary">Return</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/Emprunt.do">
                        <input type="hidden" name="idemprunt" value="${emprunt.id}" />
                        <input type="hidden" name="action" value="Supprimer" />
                        <button type="submit" class="btn btn-outline-dark">Delete</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
            </tbody>

    </table>



</div>
</body>
</html>


