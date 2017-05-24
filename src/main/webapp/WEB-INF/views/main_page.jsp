<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/movie-list.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="main_page">Main page</a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="manage-movie">Edit movie list</a>
                        </li>
                    </sec:authorize>
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
                        <input id="search" type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </nav>

        <h2 id="hello" align="center">Welcome ${pageContext.request.userPrincipal.name}</h2>
        <div class="col-lg-6 col-md-6">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Movies: Top-10</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                        <tr>
                            <td>N</td>
                            <td>Name</td>
                            <td>Year</td>
                            <td>Voices</td>
                            <td>Rate</td>
                            <td>Your rate</td>
                        </tr>
                    </thead>
                    <tbody id="movies">

                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-lg-6 col-md-6">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Users: Top-10</div>
                <!-- Table -->
                <table class="table">
                    <thead>
                    <tr>
                        <td>N</td>
                        <td>Name</td>
                        <td>Voices</td>
                        <td>Average Rate</td>
                    </tr>
                    </thead>
                    <tbody id="users">

                    </tbody>
                </table>
            </div>
        </div>


    </c:if>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/movie.js"></script>
<script src="${contextPath}/resources/js/util.js"></script>
</body>
</html>