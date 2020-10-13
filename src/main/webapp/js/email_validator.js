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
    } else {
        form.classList.remove("valid");
        form.classList.add("invalid");
        email_message.innerHTML = "Введите корректный адрес";
        email_message.classList.add("visible");
    }
}