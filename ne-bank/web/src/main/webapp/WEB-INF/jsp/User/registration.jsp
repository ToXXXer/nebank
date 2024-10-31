<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.10.2024
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NeBank</title>
    <style><%@include file="/WEB-INF/css/User/registration.css"%></style>
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
        <a href="${pageContext.request.contextPath}/main" class="type"><div class="reg">Регистрация</div></a>
        <a href="#" class="type"><div class="def">/</div></a>
        <a href="${pageContext.request.contextPath}/login" class="type"><div class="log">Вход</div></a>
    </div>
</header>
<section>
    <form class="reg" method="post">
        <c:if test="${sessionScope.user_privilege eq 'corporation'}">
            <p><input type="text" name="user_phone_number" placeholder="(XXX)XXX-XX-XX" autocomplete="off"/></p>
            <p><input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/></p>
            <p><input type="text" name="user_corporation_name" placeholder="Название компании" autocomplete="off"/></p>
            <p><input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/></p>
            <p><input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/></p>
            <p><input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/></p>
        </c:if>

        <c:if test="${sessionScope.user_privilege eq 'businessman'}">
            <p><input type="text" name="user_last_name" placeholder="Введите фамилию" autocomplete="off"/></p>
            <p><input type="text" name="user_first_name" placeholder="Введите имя" autocomplete="off"/></p>
            <p><input type="text" name="user_patronymic_name" placeholder="Введите отчество" autocomplete="off"/></p>
            <p><input type="text" name="user_phone_number" placeholder="(XXX)XXX-XX-XX" autocomplete="off"/></p>
            <p><input type="email" name="user_email" placeholder="user@gmail.com" autocomplete="off"/></p>
            <p><input type="text" name="user_corporation_name" placeholder="Название компании" autocomplete="off"/></p>
            <p><input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/></p>
            <p><input type="password" name="user_password" placeholder="Введите пароль" autocomplete="off"/></p>
            <p><input type="password" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/></p>
        </c:if>

        <c:if test="${sessionScope.user_privilege eq 'private'}">
            <p><input type="text" name="user_last_name" placeholder="Введите фамилию" autocomplete="off"/></p>
            <p><input type="text" name="user_first_name" placeholder="Введите имя" autocomplete="off"/></p>
            <p><input type="text" name="user_patronymic_name" placeholder="Введите отчество" autocomplete="off"/></p>
            <p><input type="text" name="user_phone_number" placeholder="(XXX)XXX-XX-XX" autocomplete="off"/></p>
            <p><input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/></p>
            <p><input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/></p>
            <p><input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/></p>
            <p><input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/></p>
        </c:if>
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
