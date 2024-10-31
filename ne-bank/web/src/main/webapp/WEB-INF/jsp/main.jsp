<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.10.2024
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>NeBank</title>
  <style><%@include file="/WEB-INF/css/main.css"%></style>
</head>
<body>
<header>
  <div class="head-upper">
    <div class="dropdown" style="float:left;">
      <button class="dropbtn">&#9776</button>
      <div class="dropdown-content" style="left:0;">
        <a href="${pageContext.request.contextPath}/profile">Аккаунт</a>
        <a href="${pageContext.request.contextPath}/walletlist">Кошелёк</a>
        <a href="${pageContext.request.contextPath}/contactlist">Контакты</a>
        <c:if test="${not empty sessionScope.user}">
          <a href="${pageContext.request.contextPath}/logout">Выход</a>
        </c:if>
        <c:if test="${empty sessionScope.user}">
          <a href="${pageContext.request.contextPath}/login">Вход</a>
        </c:if>
      </div>
    </div>
    <div class="image">
      <img class="logo" src="${pageContext.request.contextPath}/images/logotip.jpg" alt="logotip">
    </div>
  </div>
  <div class="head-lower">
  </div>
</header>
<section>
  <form method="post">
    <p>
      <label for="user">Выберите решение:</label>
      <select id="user" name="user_privilege">
        <option name = "privilege" value="corporation">Компания</option>
        <option name = "privilege" value="businessman">Предпрениматель</option>
        <option name = "privilege" value="private">Частное лицо</option>
      </select>
    </p>
    <p>
      <button type="submit">Отправить</button>
    </p>
  </form>
</section>
<footer>
  <div class="footer">
    <span class="footer-grey">По вопросам обращайтесь </span>
    <a href=""><span class="footer-orange">в чат поддержки</span></a>
  </div>
</footer>
</body>
</html>
