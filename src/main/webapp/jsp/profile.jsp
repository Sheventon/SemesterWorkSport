<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: dinar
  Date: 16.10.2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="x-apple-disable-message-reformatting">
    <meta name="viewport" content="width=device-width, initial-scale=0.86, maximum-scale=3.0, minimum-scale=0.86">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/profile.css">
    <script src="../js/open_hide_window.js"></script>

</head>
<%
    String name = String.valueOf(request.getAttribute("name"));
    String surname = String.valueOf(request.getAttribute("surname"));
    String patronymic = String.valueOf(request.getAttribute("patronymic"));
    String email = String.valueOf(request.getAttribute("email"));
%>
<body>
<header>
    <div class="logotype">LOGO<span class="red"></span></div>
    <nav>
        <input id="menu__toggle" type="checkbox"/>
        <label class="menu__btn" for="menu__toggle">
            <span></span>
        </label>
        <ul class="menu__box">
            <li><a class="menu__item" href="/home"><span class="red">Домой</span></a></li>
            <li><a class="menu__item" href="#">Секции</a></li>
            <li><a class="menu__item" href="#">Расписание</a></li>
            <li><a class="menu__item" href="#">Тренеры</a></li>
            <li><a class="menu__item" href="#">Достижения</a></li>
            <li><a class="menu__item" href="mailto:tores.fernando.real@gmail.com">Написать на почту</a></li>
            <li><a class="menu__item" href="/sign_out"><span class="red">Выйти</span></a></li>
        </ul>
    </nav>
</header>

<div class="main">

    <div class="content">
        <div class="section-1">
            <div class="profile-image"></div>
            <h3><%=name%> <%=surname%> <%=patronymic%></h3>
            <p class="email"><%=email%></p>
            <div class="sections">
                <h4>Секции</h4>
                <p>Мини-футбол</p>
                <p>Баскетбол</p>
                <p>Бокс</p>
                <p>Плавание</p>
                <p>Дзюдо</p>
                <p>Волейбол</p>
            </div>
        </div>

        <div class="section-2">
        </div>

        <div class="section-3">
            <h3>Расписание</h3>
            <input class="btn" type="button" id="timetable-button" value="Показать" onclick="toogle_visibility('div1')">
            <div class="timetable-data" id="div1">
                <div class="data">
                    <div class="wrap">
                        <h4>Мини-футбол</h4>
                        <p>понедельник, четверг</p>
                        <p class="time">16:30 - 18:30</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap">
                        <h4>Баскетбол</h4>
                        <p>вторник, пятница</p>
                        <p class="time">17:00 - 18:30</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap">
                        <h4>Бокс</h4>
                        <p>среда, воскресенье</p>
                        <p class="time">18:00 - 20:00</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap">
                        <h4>Плавание</h4>
                        <p>среда, воскресенье</p>
                        <p class="time">18:00 - 20:00</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap">
                        <h4>Волейбол</h4>
                        <p>среда, воскресенье</p>
                        <p class="time">18:00 - 20:00</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap">
                        <h4>Дзюдо</h4>
                        <p>среда, воскресенье</p>
                        <p class="time">18:00 - 20:00</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>