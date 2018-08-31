<%--
  User: DiatonicScale
  Date: 27.08.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Сохранение записи</title>
</head>
<body>
    <h3>Заполнить данные для сохранения записи: </h3>
    <hr>
    <jsp:useBean id="note" type="diatonicscale.worknotes.model.Note" scope="request"/>
    <form method="post" action="notes?action=saveNote">
        <input type="hidden" name="id" value="${note.id}">
        <input type="hidden" name="categoryId" value="${note.categoryId}">
        <dl>
            <dt>Навание:</dt>
            <dd><input type="name" value="${note.name}" name="name"></dd>
        </dl>
        <dl>
            <dt>Содержимое:</dt>
            <dd><textarea name="value">${note.value}</textarea></dd>
        </dl>
        <input type="hidden" name="creationTime" value="${note.creationTime}">
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отмена</button>
    </form>
</body>
</html>
