<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
    <div class="container">
        <h1>Welcome to the Admin page
            <c:out value="${currentUser.firstName}"></c:out>
        </h1>
        <div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <c:out value="${user.firstName}"></c:out>
                                <c:out value="${user.lastName}"></c:out>
                            </td>
                            <td>
                                <c:out value="${user.username}"></c:out>
                            </td>
                            <td>
                                <c:forEach items="${user.roles}" var="role">
                                    <c:if test="${role.name.equals('ROLE_USER')}">
                                        <a href="/admin/removeUser/${user.id}">delete</a> | <a href="/admin/addAdmin/${user.id}">make-admin</a>
                                    </c:if>
                                    <c:if test="${role.name.equals('ROLE_ADMIN')}">
                                        <p>admin</p>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Logout!" />
    </form>
    <a href="/dashboard">User Dashboard</a>
    </div>
</body>

</html>