<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 24.10.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редавктирование профиля</title>
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
    <form method="post" enctype="multipart/form-data">
        <div class="user-info">
            <div class="text">

                <c:if test="${sessionScope.user.user_privilege eq 'admin'}">
                    <div class="type">Логин: </div>
                    <input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/>
                    <div class="type">Телефон: </div>
                    <input type="text" name="user_phone_number" placeholder="(XXX)-XXX-XXXX" autocomplete="off"/>
                    <div class="type">Почта: </div>
                    <input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/>
                    <div class="type">Пароль: </div>
                    <input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/>
                    <div class="type">Подтверждение пароля: </div>
                    <input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/>
                    <button type="submit" style="display: inline-block">Редактировать</button>
                    <button type="reset" style="display: inline-block">Отмена</button>
                </c:if>

                <c:if test="${sessionScope.user.user_privilege eq 'corporation'}">
                    <div class="type">Логин: </div>
                    <input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/>
                    <div class="type">Телефон: </div>
                    <input type="text" name="user_phone_number" placeholder="(XXX)-XXX-XXXX" autocomplete="off"/>
                    <div class="type">Почта: </div>
                    <input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/>
                    <div class="type">Компания: </div>
                    <input type="text" name="user_corporation_name" placeholder="Название компании" autocomplete="off"/>
                    <div class="type">Пароль: </div>
                    <input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/>
                    <div class="type">Подтверждение пароля: </div>
                    <input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/>
                    <button type="submit" style="display: inline-block">Редактировать</button>
                    <button type="reset" style="display: inline-block">Отмена</button>
                </c:if>

                <c:if test="${sessionScope.user.user_privilege eq 'businessman'}">
                    <div class="type">Фамилия: </div>
                    <input type="text" name="user_last_name" placeholder="Введите фамилию" autocomplete="off"/>
                    <div class="type">Имя: </div>
                    <input type="text" name="user_first_name" placeholder="Введите имя" autocomplete="off"/>
                    <div class="type">Отчество: </div>
                    <input type="text" name="user_patronymic_name" placeholder="Введите отчество" autocomplete="off"/>
                    <div class="type">Логин: </div>
                    <input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/>
                    <div class="type">Телефон: </div>
                    <input type="text" name="user_phone_number" placeholder="(XXX)-XXX-XXXX" autocomplete="off"/>
                    <div class="type">Почта: </div>
                    <input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/>
                    <div class="type">Компания: </div>
                    <input type="text" name="user_corporation_name" placeholder="Название компании" autocomplete="off"/>
                    <div class="type">Пароль: </div>
                    <input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/>
                    <div class="type">Подтверждение пароля: </div>
                    <input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/>
                    <button type="submit" style="display: inline-block">Редактировать</button>
                    <button type="reset" style="display: inline-block">Отмена</button>
                </c:if>

                <c:if test="${sessionScope.user.user_privilege eq 'private'}">
                    <div class="type">Фамилия: </div>
                    <input type="text" name="user_last_name" placeholder="Введите фамилию" autocomplete="off"/>
                    <div class="type">Имя: </div>
                    <input type="text" name="user_first_name" placeholder="Введите имя" autocomplete="off"/>
                    <div class="type">Отчество: </div>
                    <input type="text" name="user_patronymic_name" placeholder="Введите отчество" autocomplete="off"/>
                    <div class="type">Логин: </div>
                    <input type="text" name="user_surname" placeholder="Введите логин" autocomplete="off"/>
                    <div class="type">Телефон: </div>
                    <input type="text" name="user_phone_number" placeholder="(XXX)-XXX-XXXX" autocomplete="off"/>
                    <div class="type">Почта: </div>
                    <input type="text" name="user_email" placeholder="user@gmail.com" autocomplete="off"/>
                    <div class="type">Пароль: </div>
                    <input type="text" name="user_password" placeholder="Введите пароль" autocomplete="off"/>
                    <div class="type">Подтверждение пароля: </div>
                    <input type="text" name="user_password_confirm" placeholder="Повторите пароль" autocomplete="off"/>
                    <button type="submit" style="display: inline-block">Редактировать</button>
                    <button type="reset" style="display: inline-block">Отмена</button>
                </c:if>

            </div>
            <div class="image">
                <img src="${pageContext.request.contextPath}/images/${sessionScope.user.user_image}.jpg">
                <input type="file" name="user_image">
            </div>
        </div>
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
</body>
</html>
