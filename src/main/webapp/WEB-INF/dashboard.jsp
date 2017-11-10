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
        <h1>Welcome
            <c:out value="${currentUser.firstName}"></c:out>
        </h1>
        <div>
            <table >
                <tr>
                    <td>First Name:</td>
                    <td><c:out value="${currentUser.firstName}"></c:out></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td>
                        <c:out value="${currentUser.lastName}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <c:out value="${currentUser.username}"></c:out>
                    </td>
                </tr>
                <tr>
                    <td>Sign Up date:</td>
                    <td>
                        <fmt:formatDate  pattern = "yyyy-MM-dd" value="${currentUser.createdAt}" />
                    </td>
                </tr>
                <tr>
                    <td>Last Sign in:</td>
                    <td>
                        <fmt:formatDate pattern = "yyyy-MM-dd" value="${currentUser.updatedAt}"/>
                    </td>
                </tr>
            </table>
        </div>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Logout!" />
    </form>
    </div>
</body>

</html>