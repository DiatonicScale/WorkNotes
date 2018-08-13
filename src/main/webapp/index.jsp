<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<jsp:include page="WEB-INF/jsp/headTag.jsp"/>

<body>
<div>
    <form method="post" action="users">
        Выбрать пользователя:
        <select name="userId">
            <option value="0" selected>User1</option>
            <option value="1">User2</option>
        </select>
        <button type="submit">Выбрать</button>
    </form>
</div>

</body>
</html>