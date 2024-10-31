<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 24.10.2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Профиль</title>
    <style><%@include file="/WEB-INF/css/User/profile.css"%></style>
</head>
<body>
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
        <div class="name">Мой профиль</div>
    </div>
</header>
<section>
    <div class="user-info">
        <div class="text">

            <c:if test="${sessionScope.user.user_privilege eq 'admin'}">
                <div class="type">Логин: </div>
                <div class="info">${sessionScope.user.user_surname}</div>
                <div class="type">Телефон: </div>
                <div class="info">${sessionScope.user.user_phone_number}</div>
                <div class="type">Почта: </div>
                <div class="info">${sessionScope.user.user_email}</div>
                <a href="${pageContext.request.contextPath}/redactor"><button type="button" style="display: inline-block">Редактировать</button></a>
            </c:if>

            <c:if test="${sessionScope.user.user_privilege eq 'corporation'}">
                <div class="type">Логин: </div>
                <div class="info">${sessionScope.user.user_surname}</div>
                <div class="type">Компания: </div>
                <div class="info">${sessionScope.user.user_corporation_name}</div>
                <div class="type">Телефон: </div>
                <div class="info">${sessionScope.user.user_phone_number}</div>
                <div class="type">Почта: </div>
                <div class="info">${sessionScope.user.user_email}</div>
                <a href="${pageContext.request.contextPath}/redactor"><button type="button" style="display: inline-block">Редактировать</button></a>
            </c:if>

            <c:if test="${sessionScope.user.user_privilege eq 'businessman'}">
                <div class="type">Фамилия: </div>
                <div class="info">${sessionScope.user.user_last_name}</div>
                <div class="type">Имя: </div>
                <div class="info">${sessionScope.user.user_first_name}</div>
                <div class="type">Отчество: </div>
                <div class="info">${sessionScope.user.user_patronymic_name}</div>
                <div class="type">Логин: </div>
                <div class="info">${sessionScope.user.user_surname}</div>
                <div class="type">Компания: </div>
                <div class="info">${sessionScope.user.user_corporation_name}</div>
                <div class="type">Телефон: </div>
                <div class="info">${sessionScope.user.user_phone_number}</div>
                <div class="type">Почта: </div>
                <div class="info">${sessionScope.user.user_email}</div>
                <a href="${pageContext.request.contextPath}/redactor"><button type="button" style="display: inline-block">Редактировать</button></a>
            </c:if>

            <c:if test="${sessionScope.user.user_privilege eq 'private'}">
                <div class="type">Фамилия:</div>
                <div class="info">${sessionScope.user.user_last_name}</div>
                <div class="type">Имя: </div>
                <div class="info">${sessionScope.user.user_first_name}</div>
                <div class="type">Отчество: </div>
                <div class="info">${sessionScope.user.user_patronymic_name}</div>
                <div class="type">Логин: </div>
                <div class="info">${sessionScope.user.user_surname}</div>
                <div class="type">Телефон: </div>
                <div class="info">${sessionScope.user.user_phone_number}</div>
                <div class="type">Почта: </div>
                <div class="info">${sessionScope.user.user_email}</div>
                <a href="${pageContext.request.contextPath}/redactor"><button type="button" style="display: inline-block">Редактировать</button></a>
            </c:if>

        </div>
        <div class="image">
            <img src="${pageContext.request.contextPath}/images/${sessionScope.user.user_image}.jpg">
        </div>
    </div>
    <div class="decision-info">
        <div>HGtyf5DrytuUU897656RDTCFhgjhoi07rrdtdGH</div>
    </div>
</section>
<footer>
    <div class="footer">
        <span class="footer-grey">По вопросам обращайтесь </span>
        <a href=""><span class="footer-orange">в чат поддержки</span></a>
    </div>
</footer>
</body>
</body>
</html>
