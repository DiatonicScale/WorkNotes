<%--
  User: DiatonicScale
  Date: 27.08.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Сохранение категории</title>
</head>
<body>
    <h3>Заполнить данные для сохранения категории: </h3>
    <hr>
    <jsp:useBean id="category" type="diatonicscale.worknotes.model.Category" scope="request"/>
    <form method="post" action="notes?action=saveCategory">
        <input type="hidden" name="id" value="${category.id}">
        <input type="hidden" name="userId" value="${category.userId}">
        <dl>
            <dt>Навание категории:</dt>
            <dd><input type="text" value="${category.name}" name="name"></dd>
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отмена</button>
    </form>
</body>
</html>
