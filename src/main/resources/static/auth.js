window.GranAuth = {
    me: null,
    ready: null,
    load: function () {
        return fetch("/api/me", { credentials: "same-origin" })
            .then(function (response) {
                return response.json();
            })
            .then(function (me) {
                window.GranAuth.me = me;
                window.GranAuth.renderNav();
                return me;
            })
            .catch(function () {
                window.GranAuth.me = { authenticated: "false" };
                window.GranAuth.renderNav();
                return window.GranAuth.me;
            });
    },
    isAdmin: function () {
        return window.GranAuth.me
            && window.GranAuth.me.authenticated === "true"
            && window.GranAuth.me.role === "ADMIN";
    },
    isLoggedIn: function () {
        return window.GranAuth.me && window.GranAuth.me.authenticated === "true";
    },
    renderNav: function () {
        document.querySelectorAll("[data-auth-nav]").forEach(function (nav) {
            if (window.GranAuth.isLoggedIn()) {
                const email = window.GranAuth.me.email;
                const role = window.GranAuth.me.role;
                nav.innerHTML = `
                    <a href="/trails">Szlaki</a>
                    ${window.GranAuth.isAdmin() ? '<a href="/trails/new">Dodaj szlak</a>' : ""}
                    <span class="auth-user">${email} · ${role}</span>
                    <a href="/logout" data-logout>Wyloguj</a>
                `;
            } else {
                nav.innerHTML = `
                    <a href="/trails">Szlaki</a>
                    <a href="/login">Zaloguj</a>
                    <a href="/register">Rejestracja</a>
                `;
            }
        });
        document.querySelectorAll("[data-admin-only]").forEach(function (el) {
            el.hidden = !window.GranAuth.isAdmin();
        });
        document.querySelectorAll("[data-logout]").forEach(function (link) {
            link.addEventListener("click", function (event) {
                event.preventDefault();
                fetch("/logout", { method: "POST", credentials: "same-origin" })
                    .finally(function () {
                        location.href = "/";
                    });
            });
        });
    }
};

window.GranAuth.ready = window.GranAuth.load();
