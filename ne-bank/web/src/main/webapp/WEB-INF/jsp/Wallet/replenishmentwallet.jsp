<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.10.2024
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>NeBank</title>
  <style><%@include file="/WEB-INF/css/Wallet/createwallet.css"%></style>
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
    <div class="head-lower-name">Создание кошелька</div>
    <div class="create"><a href="${pageContext.request.contextPath}/walletlist"><button type="button">Вернуться назад</button></a></div>
  </div>
</header>
<section>
  <form class="reg" method="post">
    <p><input type="text" name="wallet_name" placeholder="Введите название" autocomplete="off"/></p>
    <p><input type="text" name="wallet_purpose" placeholder="Введите цель" autocomplete="off"/></p>
    <p><input type="text" name="wallet_comment" placeholder="Введите комментарий" autocomplete="off"/></p>
    <p>
      <button type="submit" style="display: inline-block;">Отправить</button>
      <button type="reset" style="display: inline-block;">Отмена</button>
    </p>
  </form>
  <c:if test="${not empty requestScope.errors}">
    <div style="color: darkred">
      <c:forEach var="error" items="${requestScope.errors}">
        <p>${error.code} - ${error.message}</p>
      </c:forEach>
    </div>
  </c:if>
</section>
<footer>
  <div class="footer">
    <span class="footer-grey">По вопросам обращайтесь </span>
    <a href=""><span class="footer-orange">в чат поддержки</span></a>
  </div>
</footer>
</body>
</html>
