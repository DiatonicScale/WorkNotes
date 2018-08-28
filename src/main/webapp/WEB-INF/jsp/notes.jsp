<%@ page import="java.util.List" %>
<%@ page import="diatonicscale.worknotes.model.Note" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: DiatonicScale
  Date: 27.08.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Записи пользователя</title>
</head>
<body>
<jsp:useBean id="category" scope="request" type="diatonicscale.worknotes.model.Category"/>
<h1>Записи категории <span class="categoryName">${category.name}</span>:</h1>

<p><a href="notes?action=addNote&categoryId=${category.id}">Добавить запись</a></p>

    <c:if test="${isEmpty}">
        <div class="note">
            <p>В категории нет записей...</p>
        </div>
    </c:if>

    <c:forEach items="${categoryNotes}" var="note">
        <jsp:useBean id="note" scope="page" type="diatonicscale.worknotes.model.Note"/>
        <div class="note">
            <p>${note.name}</p>
            <p class="date">Дата создания: ${note.creationTime}</p>
            <hr>
            <p>${note.value}</p>
            <hr>
            <p><a href="notes?action=deleteNote&id=${note.id}&categoryId=${category.id}">Удалить</a></p>
            <p><a href="notes?action=updateNote&id=${note.id}&categoryId=${category.id}">Редактировать</a> (Дата последнего изменения: ${note.lastEditTime})</p>
        </div>
    </c:forEach>

<p><a href="notes?action=deleteCategoryNotes&id=${category.id}">Удалить все записи из категории</a></p>
<p><a href="notes?action=allNotes">Показать все записи</a></p>
</body>
</html>
