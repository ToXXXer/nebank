<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 27.10.2024
  Time: 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NeBank</title>
    <style><%@include file="/WEB-INF/css/Wallet/wallet.css"%></style>
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
        <div class="head-lower-name">Кошелёк - ${sessionScope.wallet.wallet_name}</div>
        <div class="operation">
            <form method="post"><div class="create"><button type="submit" name="transaction_type" value="deposit">Пополнить</button></div></form>
            <form method="post"><div class="create"><button type="submit" name="transaction_type" value="replenishment">Перевести</button></div></form>
        </div>
    </div>
</header>
<section>
    <div class="wallet-info">
        <div class="card">
            <div class="name">
                Сумма на счёте:
            </div>
            <div class="value">
                ${sessionScope.wallet.amount_money}
            </div>
        </div>
        <div class="card">
            <div class="name">
                Владелец:
            </div>
            <div class="value">
                ${sessionScope.user.user_surname}
            </div>
        </div>
        <div class="card">
            <div class="name">
                Цель:
            </div>
            <div class="value">
                ${sessionScope.wallet.wallet_purpose}
            </div>
        </div>
        <div class="card">
            <div class="name">
                Комментарий:
            </div>
            <div class="value">
                ${sessionScope.wallet.wallet_comment}
            </div>
        </div>
        <div class="card">
            <form method="post">
                <div class="create"><button type="submit" name="transaction_type" value="delete">Удалить</button></div>
            </form>
        </div>
    </div>
    <div class="wallet-history">
        <c:forEach var="transaction" items="${requestScope.transactions}">
            <div class="transaction">
                ${transaction.transaction_type} ${transaction.surname_from} ${transaction.amount_money} ${transaction.transaction_date}
            </div>
        </c:forEach>
    </div>
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
