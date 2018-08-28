<%@ page import="diatonicscale.worknotes.controller.LoggedInUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: DiatonicScale
  Date: 13.08.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Список категорий</title>
</head>
<body>

<h1> Категории пользователя: <%=LoggedInUser.getId()%>
</h1>

<p><a href="notes?action=addCategory">Добавить категорию</a></p>

<table border="1" cellpadding="6">
    <tr>
        <th>Название</th>
        <th>Дата создания</th>
        <th>Дата последнего изменения</th>
        <th>Удаление</th>
        <th>Редактирование</th>
    </tr>

    <c:forEach items="${userCategories}" var="category">
        <jsp:useBean id="category" scope="page" type="diatonicscale.worknotes.model.Category"/>
    <tr>
        <td><a href="notes?action=categoryNotes&id=${category.id}">${category.name}</a></td>
        <td>${category.creationTime}</td>
        <td>${category.lastEditTime}</td>
        <td><a href="notes?action=deleteCategory&id=${category.id}">Удалить</a></td>
        <td><a href="notes?action=updateCategory&id=${category.id}">Редактировать</a></td>
    </tr>
    </c:forEach>

</table>

<p><a href="notes?action=allNotes">Показать все записи</a></p>

</body>
</html>
