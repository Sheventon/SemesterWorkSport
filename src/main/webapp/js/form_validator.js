function email_validator() {
    var form = document.getElementById("form");
    var email = document.getElementById("email").value;
    var email_message = document.getElementById("email-message");
    var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,4}$/;
    if (email.match(pattern)) {
        form.classList.add("valid");
        form.classList.remove("invalid");
        email_message.classList.remove("visible");
        email_message.innerHTML = "";
        return true;
    } else if (email === "") {
        form.classList.remove("valid");
        form.classList.remove("invalid");
        email_message.classList.remove("visible");
        email_message.innerHTML = "";
        return null;
    } else {
        form.classList.remove("valid");
        form.classList.add("invalid");
        email_message.innerHTML = "Введите корректный адрес";
        email_message.classList.add("visible");
        return false;
    }
}

function name_validator() {
    var form = document.getElementById("form")
    var name = document.getElementById("fio").value;
    var name_message = document.getElementById("name-message");
    if (name.length >= 3 && name.length <= 50) {
        name_message.classList.remove("visible");
        name_message.innerHTML = "";
        return true;
    } else if (name === "") {
        name_message.classList.remove("visible");
        name_message.innerHTML = "";
        return null;
    } else {
        name_message.classList.add("visible");
        name_message.innerHTML = "Длина поля от 3 до 50 символов";
        return false;
    }
}

function submit_validator() {
    var form = document.getElementById("form");
    var password = document.getElementById("password").value;
    if (email_validator() && name_validator() ) {
        form.submit();
    } else if (email_validator() == null && name_validator() == null && password === "") {
        alert("Пожалуйста, заполните все поля");
    } else {

    }
}

function login_validator() {
    var form = document.getElementById("form");
    var password = document.getElementById("password").value;
    if (email_validator()) {
        form.submit();
    } else if (email_validator() == null && password === "") {
        alert("Пожалуйста, заполните все поля");
    } else {

    }
}