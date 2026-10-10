const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const correo = document.getElementById("correo").value.trim();
        const contrasena = document.getElementById("contrasena").value.trim();

        if (correo === "" || contrasena === "") {
            alert("Por favor, completa todos los campos.");
            return;
        }

        if (!correo.includes("@")) {
            alert("Por favor, ingresa un correo electrónico válido.");
            return;
        }

        if (contrasena.length < 6) {
            alert("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        window.location.href = "bienvenido.html";
    });
}


const registroForm = document.getElementById("registroForm");

if (registroForm) {
    registroForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const nombre = document.getElementById("nombre").value.trim();
        const correo = document.getElementById("correo").value.trim();
        const contrasena = document.getElementById("contrasena").value.trim();
        const confirmar = document.getElementById("confirmar").value.trim();

        if (nombre === "" || correo === "" || contrasena === "" || confirmar === "") {
            alert("Por favor, completa todos los campos.");
            return;
        }

        if (!correo.includes("@")) {
            alert("Por favor, ingresa un correo electrónico válido.");
            return;
        }

        if (contrasena.length < 6) {
            alert("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        if (contrasena !== confirmar) {
            alert("Las contraseñas no coinciden.");
            return;
        }

        alert("Registro realizado correctamente.");
    });
}

const recuperarForm = document.getElementById("recuperarForm");

if (recuperarForm) {
    recuperarForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const correo = document.getElementById("correo").value.trim();

        if (correo === "") {
            alert("Por favor, ingresa tu correo electrónico.");
            return;
        }

        if (!correo.includes("@")) {
            alert("Por favor, ingresa un correo electrónico válido.");
            return;
        }

        alert("Se ha enviado un enlace para recuperar tu contraseña.");
    });
}
const menuBtn = document.getElementById("menuBtn");
const menu = document.getElementById("menu");

if (menuBtn && menu) {
    menuBtn.addEventListener("click", function() {
        menu.classList.toggle("mostrar");
    });
}