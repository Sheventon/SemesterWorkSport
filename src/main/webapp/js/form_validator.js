function email_validator() {
    var form = document.getElementById("form");
    var email = document.getElementById("email").value;
    var email_message = document.getElementById("email-message");
    var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,4}$/;
    if (email.match(pattern) && checkEmail()) {
        form.classList.add("valid");
        form.classList.remove("invalid");
        email_message.classList.remove("visible");
        email_message.innerHTML = "";
        return true;
    } else if (email.match(pattern) && !checkEmail()) {
        alert("this email is already use");
        form.classList.remove("valid");
        form.classList.add("invalid");
        email_message.innerHTML = "Данный адрес уже используется";
        email_message.classList.add("visible");
        return false;
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

function checkEmail() {
    const email = document.getElementById('email');
    const xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8080/check_email?email=" + email.value, false);
    xhr.send();

    if (xhr.status !== 200) {
        alert("...Something went wrong...");
    } else {
        const response = xhr.responseText;
        if (response === "incorrect") {
            alert("This email is already in use");
            return false;
        }
        return true;
    }
}

function name_validator() {
    var form = document.getElementById("form")
    var name = document.getElementById("name").value;
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

function surname_validator() {
    var form = document.getElementById("form")
    var name = document.getElementById("surname").value;
    var name_message = document.getElementById("surname-message");
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
    if (email_validator() && surname_validator() && name_validator() && password.length > 0) {
        form.submit();
    } else if (email_validator() == null || name_validator() == null || surname_validator() == null || password.length === 0) {
        alert("Пожалуйста, заполните все поля");
    } else {

    }
}

function login_validator() {
    var form = document.getElementById("form");
    var password = document.getElementById("password").value;
    if (email_validator() && password.length > 0) {
        form.submit();
    } else if (email_validator() == null || password === "") {
        alert("Пожалуйста, заполните все поля");
    } else {

    }
}