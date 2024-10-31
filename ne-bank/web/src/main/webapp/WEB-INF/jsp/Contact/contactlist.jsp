<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 31.10.2024
  Time: 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NeBank</title>
    <style><%@include file="/WEB-INF/css/Contact/contactlist.css"%></style>
</head>
<body>
<header>
    <div class="head-upper">
        <div class="dropdown" style="float:left;">
            <button class="dropbtn">&#9776</button>
            <div class="dropdown-content" style="left:0;">
                <a href="${pageContext.request.contextPath}/profile">Аккаунт</a>
                <a href="${pageContext.request.contextPath}/walletlist">Кошелёк</a>
                <a href="${pageContext.request.contextPath}/contacts">Контакты</a>
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
        <div class="head-lower-name">Контакты</div>
        <div class="create"><a href="${pageContext.request.contextPath}/createcontact"><button type="button">Добавить</button></a></div>
    </div>
</header>
<section>
    <div class="cards">
        <c:forEach var="contact" items="${requestScope.contactList}">
            <div class="wallet-card">
                <div class="wallet-image">
                    <img class="wallet" src="${pageContext.request.contextPath}/images/${contact.first_user.user_image}.jpg" alt="wallet">
                </div>
                <div class="wallet-info">
                    <div class="info">
                        <div class="lable-name">
                            Логин:
                        </div>
                        <div class="wallet-value">
                                ${contact.first_user.user_surname}
                        </div>
                    </div>
                    <div class="info">
                        <div class="lable-name">
                            Почта:
                        </div>
                        <div class="wallet-value">
                                ${contact.first_user.user_email}
                        </div>
                    </div>
                    <div class="info">
                        <div class="lable-name">
                            Номер телефона:
                        </div>
                        <div class="wallet-value">
                                ${contact.first_user.user_phone_number}₽
                        </div>
                    </div>
                    <form method="post">
                        <button type="submit" name="wallet_id" value="${contact.first_user.user_id}">Перейти</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="sum">
        <div class="sum-info">Общая информация:</div>
        <div class="cards-sum-info">
            <div class="lable-name">
                Кол-во контактов:
            </div>
            <div class="wallet-value">
                ${fn:length(requestScope.contactList)}
            </div>
        </div>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: darkred">
                <c:forEach var="error" items="${requestScope.errors}">
                    <p>${error.code} - ${error.message}</p>
                </c:forEach>
            </div>
        </c:if>
    </div>
</section>
<footer>
    <div class="footer">
        <span class="footer-grey">По вопросам обращайтесь </span>
        <a href=""><span class="footer-orange">в чат поддержки</span></a>
    </div>
</footer>
</body>
</html>
