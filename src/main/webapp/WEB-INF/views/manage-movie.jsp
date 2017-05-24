<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/movie-list.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>

<div class="container">

    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li>
                    <a href="main_page">Main page</a>
                </li>
                <li>
                    <a href="movie/movie-all">List of all movies</a>
                </li>
                <li>
                    <a href="user/user-all">List of all users</a>
                </li>
                <li>
                    <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                </li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </nav>
    <h2 id="hello" align="center">Welcome ${pageContext.request.userPrincipal.name}</h2>

    <ul class="pagination col-lg-6">

    </ul>
    <div id="add" class="col-lg-6">
        <button id="add-movie" type="button" class="btn btn-primary">Add movie</button>
    </div>

    <form id="edit-movie-form" class="form-inline" hidden>
        <div id="movie-id" hidden></div>
        <div class="form-group">
            <label class="sr-only">Name</label>
            <input id="movie-name" type="text" class="form-control" placeholder="Name">
        </div>
        <div class="form-group">
            <label class="sr-only">Year</label>
            <input id="movie-year" type="number" class="form-control" placeholder="Year">
        </div>
        <div class="form-group">
            <label class="sr-only">Director</label>
            <input id="movie-director" type="text" class="form-control" placeholder="Director">
        </div>
        <div class="form-group">
            <label class="sr-only">Budget</label>
            <input id="movie-budget" type="number" class="form-control" placeholder="Budget">
        </div>
        <div class="form-group">
            <label class="sr-only">Box office</label>
            <input id="movie-box" type="number" class="form-control" placeholder="Box_office">
        </div>
        <div class="form-group">
            <label class="sr-only">Description</label>
            <input id="movie-desc" type="text" class="form-control" placeholder="Description">
        </div>
        <button id="confirm-booking" type="submit" class="btn btn-default">Confirm</button>
    </form>

    <div class="col-lg-12 col-md-12">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">List of all movies</div>
            <!-- Table -->
            <table class="table">
                <thead>
                <tr>
                    <td>N</td>
                    <td>Name</td>
                    <td>Year</td>
                    <td>Director</td>
                    <td>Budget</td>
                    <td>Box-office</td>
                    <td>Description</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
                </thead>
                <tbody id="movies">

                </tbody>
            </table>
        </div>
    </div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/manage-movie.js"></script>
</body>
</html>