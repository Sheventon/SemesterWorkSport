<%@ page import="model.User" %>
<%@ page import="javax.enterprise.context.RequestScoped" %>
<%@ page import="model.Record" %>
<%@ page import="java.util.List" %><%--
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
    <link rel="shortcut icon" href="../img/icon2.png" type="image/x-icon">
</head>
<%
    List<String> userSections = (List<String>) request.getAttribute("sections");

//    String name = String.valueOf(request.getAttribute("name"));
//    String surname = String.valueOf(request.getAttribute("surname"));
//    String patronymic = String.valueOf(request.getAttribute("patronymic"));
//    String email = String.valueOf(request.getAttribute("email"));
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
            <li><a class="menu__item" href="/sections">Секции</a></li>
            <li><a class="menu__item" href="/timetable">Расписание</a></li>
            <li><a class="menu__item" href="#">Тренеры</a></li>
            <li><a class="menu__item" href="/recording">Записаться</a></li>
            <li><a class="menu__item" href="mailto:tores.fernando.real@gmail.com">Написать на почту</a></li>
            <li><a class="menu__item" href="/out"><span class="red">Выйти</span></a></li>
        </ul>
    </nav>
</header>

<div class="main">

    <div class="content">
        <div class="section-1">
            <h3>${requestScope.name} ${requestScope.surname} ${requestScope.patronymic}</h3>
            <p class="age">19 лет</p>
            <div class="update">
                <input type="button" class="btn update-btn" value="Редактировать"
                       onclick="update_visibility('wrapper')"/>
            </div>
            <p class="email">${requestScope.email}</p>
            <div class="sections">
                <h4>Секции</h4>
                <%--                <p>Мини-футбол</p>--%>
                <%--                <p>Баскетбол</p>--%>
                <%--                <p>Бокс</p>--%>
                <%--                <p>Плавание</p>--%>
                <%--                <p>Дзюдо</p>--%>
                <%--                <p>Волейбол</p>--%>
                <%
                    for (String section : userSections) {
                %>
                <p><%=section%>
                </p>
                <%
                    }
                %>
            </div>
        </div>

        <div class="section-2">
        </div>

        <div class="section-3">
            <h3>Расписание</h3>
            <input class="btn" type="button" id="timetable-button" value="Показать"
                   onclick="toogle_visibility('div1')">
            <div class="timetable-data" id="div1">
                <div class="data">
                    <div class="wrap football">
                        <h4>Мини-футбол</h4>
                        <p>Понедельник 15:30 - 17:00</p>
                        <p>Четверг 14:00 - 15:30</p>
                        <p>Суббота 14:00 - 15:30</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap basketball">
                        <h4>Баскетбол</h4>
                        <p>Понедельник 17:00 - 18:30</p>
                        <p>Среда 17:00 - 18:30</p>
                        <p>Пятница 18:30 - 20:00</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap boxing">
                        <h4>Бокс</h4>
                        <p>Понедельник 18:30 - 20:00</p>
                        <p>Среда 18:30 - 20:00</p>
                        <p>Пятница 20:00 - 21:30</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap swimming">
                        <h4>Плавание</h4>
                        <p>Вторник 18:30 - 20:00</p>
                        <p>Четверг 17:00 - 18:30</p>
                        <p>Суббота 15:30 - 17:00</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap volleyball">
                        <h4>Волейбол</h4>
                        <p>Вторник 14:00 - 15:30</p>
                        <p>Четверг 20:00 - 21:30</p>
                        <p>Воскресенье 14:00 - 15:30</p>
                    </div>
                </div>
                <div class="data">
                    <div class="wrap judo">
                        <h4>Дзюдо</h4>
                        <p>Вторник 15:30 - 17:00</p>
                        <p>Среда 20:00 - 21:30</p>
                        <p>Пятница 15:30 - 17:00</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="wrapper" id="wrapper">
        <div class="form" id="upd">
            <form id="update-form" action="/account" method="post">
                <label for="lastname">Отчество</label>
                <input id="lastname" type="text" name="lastname" placeholder="По желанию"/>

                <label for="age">Возраст</label>
                <input id="age" type="text" name="age" placeholder="По желанию"/>

                <label for="new-password">Новый пароль</label>
                <input id="new-password" type="password" name="password" placeholder="По желанию"/>

                <input type="submit" class="btn form-btn" name="submit" value="Редактировать"/>
                <input type="reset" class="btn reset-btn" value="Сбросить">
                <input type="button" class="btn cancel-btn" value="Закрыть" onclick="hide_update_visibility('wrapper')">

            </form>
        </div>
    </div>
</div>
</body>
</html>