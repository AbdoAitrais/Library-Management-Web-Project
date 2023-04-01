
<%--
  Created by IntelliJ IDEA.
  User: abdo
  Date: 3/1/2023
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>List of Books</title>
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
          Adherent
        </a>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Emprunt.do">
          Emprunter
        </a>
        <a class="navbar-brand" size="1px" href="${pageContext.request.contextPath}/session/logout">
          Deconnection(${account.username})
        </a>
      </div>
    </nav>
    <%--<h1></h1>--%>
    <div class="container">
      <form class="form" method="post" action="${pageContext.request.contextPath}/Livre.do">
        <input type="hidden" name="action" value="Ajouter" />
        <div class="row mb-3">
          <label for="isbn" class="col-sm-2 col-form-label">Isbn</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="isbn" name="isbn">
          </div>
        </div>
        <div class="row mb-3">
          <label for="title" class="col-sm-2 col-form-label">Titre</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="title" name="title">
          </div>
        </div>
        <div class="row mb-3">
          <label for="author" class="col-sm-2 col-form-label">Auteur</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="author" name="author">
          </div>
        </div>
        <div class="row mb-3">
          <label for="example" class="col-sm-2 col-form-label">Disponible</label>
          <div class="col-sm-10">
            <input type="number" min="1" class="form-control" id="example" name="example">
          </div>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
      </form>

      <table class="table">
        <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">ISBN</th>
          <th scope="col">Title</th>
          <th scope="col">Auteur</th>
          <th scope="col">Disponible</th>
          <th scope="col"></th>
          <th scope="col"></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${books}" var="book">
        <tr>
          <td>${book.id}</td>
          <td>${book.isbn}</td>
          <td>${ book.titre }</td>
          <td>${ book.auteur }</td>
          <td>
            <c:catch var="error">
              <c:if test="${book.exemplaires != null}">
                ${book.exemplaires.size()}
              </c:if>
            </c:catch>

            <c:if test="${not empty error}">
              An error occurred: ${error.message}
            </c:if>
          </td>
          <td>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal${book.isbn}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <form method="post" action="${pageContext.request.contextPath}/Livre.do">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">

                      <input type="hidden" name="action" value="Modifier" />
                    <div class="row mb-3">
                      <label for="idLivreUpdate" class="col-sm-2 col-form-label">Id</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="idLivreUpdate" name="idLivre" value="${book.id}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="isbnUpdate" class="col-sm-2 col-form-label">Isbn</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="isbnUpdate" name="isbn" value="${book.isbn}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="titleUpdate" class="col-sm-2 col-form-label">Titre</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="titleUpdate" name="title" value="${ book.titre }">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="authorUpdate" class="col-sm-2 col-form-label">Auteur</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="authorUpdate" name="author" value="${ book.auteur }">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="nbrExampleUpdate" class="col-sm-2 col-form-label">Disponible</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="nbrExampleUpdate" name="example" value="${book.exemplaires.size()}">
                      </div>
                    </div>

                      <!-- Button trigger modal -->


                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                  </div>
                  </form>
                </div>
              </div>
            </div>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${book.isbn}">
              Update
            </button>
          </td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/Livre.do">
              <input type="hidden" name="idLivre" value="${book.id}" />
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
