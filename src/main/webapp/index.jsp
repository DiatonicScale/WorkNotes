<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<jsp:include page="WEB-INF/jsp/headTag.jsp"/>

<body>
<div>
    <form method="post" action="users">
        Выбрать пользователя:
        <select name="userId">
            <option value="10000" selected>User0</option>
            <option value="10001">User1</option>
        </select>
        <button type="submit">Выбрать</button>
    </form>
</div>

</body>
</html>