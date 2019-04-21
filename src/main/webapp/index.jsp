<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User CRUD Operations</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<h1>User Manage System</h1>
<c:url value="/register" var="registerUrl" />
<form action="${registerUrl}" method="post">
    <table id="users">
        <c:if test="${user.id ne null}">
            <tr>
                <td>User ID:</td>
                <td><label>
                    <input type="number" name="id" value="${user.id}" readonly="readonly">
                </label></td>
            </tr>
        </c:if>
        <tr>
            <td>Name:</td>
            <td><label>
                <input type="text" name="name" value="${user.name}" required>
            </label></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><label>
                <input type="number" min="18" name="age" value="${user.age}" required>
            </label></td>
        </tr>
        <tr>
            <td>Salary:</td>
            <td><label>
                <input type="number" min="0" name="salary" value="${user.salary}" required>
            </label></td>
        </tr>

        <c:if test="${user.id ne null}">
            <tr>
                <td colspan="2"><input type="submit" class="button-update" value="Update User"></td>
            </tr>
        </c:if>
        <c:if test="${user.id eq null}">
            <tr>
                <td colspan="2"><input type="submit" class="button-save" value="Add User"></td>
            </tr>
        </c:if>
    </table>
</form>
<br>
<h1>List of Users</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Salary</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <%--@elvariable id="userslist" type="java.util.List"--%>
    <c:forEach items="${userslist}" var="user">
        <tr>
            <td class="id-field">${user.id}</td>
            <td>${user.name}</td>
            <td class="column">${user.age}</td>
            <td class="column">${user.salary}</td>

            <td class="action-column">
                <form action="<c:url value="/update"/>" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input class="button-update" type="submit" value="Update">
                </form>
            <td class="action-column">
                <form action="<c:url value="/delete"/>" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input class="button-delete" type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>