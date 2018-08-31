<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: DiatonicScale
  Date: 27.08.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Все записи</title>
</head>
<body>
<h1> Все записи пользователя: ${LoggedInUser.getId()}</h1>

<c:if test="${isEmpty}">
    <div class="note">
        <p>Записей нет...</p>
    </div>
</c:if>

<c:forEach items="${userNotes}" var="note">
    <jsp:useBean id="note" scope="page" type="diatonicscale.worknotes.model.Note"/>
    <div class="note">
        <p>${note.name}</p>
        <p class="date">Дата создания: ${note.creationTime}</p>
        <hr>
        <p>${note.value}</p>
        <hr>
        <p><a href="notes?action=deleteNote&id=${note.id}&categoryId=${note.categoryId}&allNotes=true">Удалить</a></p>
        <p><a href="notes?action=updateNote&id=${note.id}&categoryId=${note.categoryId}">Редактировать</a> (Дата последнего изменения: ${note.creationTime})</p>
    </div>
</c:forEach>

<%--TODO:
<p><a href="notes?action=deleteCategoryNotes&id=${category.id}">Удалить все записи</a></p>
--%>
</body>
</html>
