<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NeBank</title>
    <style><%@include file="/WEB-INF/css/Wallet/walletlist.css"%></style>
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
        <div class="head-lower-name">Кошелёк</div>
        <div class="create"><a href="${pageContext.request.contextPath}/createwallet"><button type="button">Создать</button></a></div>
    </div>
</header>
<section>
    <div class="cards">
        <c:forEach var="wallet" items="${requestScope.walletList}">
            <div class="wallet-card">
                <div class="wallet-image">
                    <img class="wallet" src="${pageContext.request.contextPath}/images/wallet.jpg" alt="wallet">
                </div>
                <div class="wallet-info">
                    <div class="info">
                        <div class="lable-name">
                            Название:
                        </div>
                        <div class="wallet-value">
                                ${wallet.wallet_name}
                        </div>
                    </div>
                    <div class="info">
                        <div class="lable-name">
                            Цель:
                        </div>
                        <div class="wallet-value">
                                ${wallet.wallet_purpose}
                        </div>
                    </div>
                    <div class="info">
                        <div class="lable-name">
                            Сумма:
                        </div>
                        <div class="wallet-value">
                                ${wallet.amount_money}₽
                        </div>
                    </div>
                    <form method="post">
                        <button type="submit" name="wallet_id" value="${wallet.wallet_id}">Перейти</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="sum">
        <div class="sum-info">Общая информация:</div>
        <div class="cards-sum-info">
            <div class="lable-name">
                Кол-во кошельков:
            </div>
            <div class="wallet-value">
                ${fn:length(requestScope.walletList)}
            </div>
        </div>
        <div class="cards-sum-info">
            <div class="lable-name">
                Кол-во денег:
            </div>
            <div class="wallet-value">
                ${requestScope.sumMoney}₽
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
