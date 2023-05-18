const API_BASE_URL = "http://localhost:8080/api";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};
const SUBMIT_BUTTON = document.querySelector("#submit-btn");

$(document).ready(function () {
  addButtonEvent();
});

function registerUser() {
  const name = document.getElementById("name").value,
    lastName = document.getElementById("lastName").value,
    email = document.getElementById("email").value,
    password = document.getElementById("password").value,
    repeatPassword = document.getElementById("repeatPassword").value;

  if (password !== repeatPassword) {
    alert("Passwords don't match");
    return;
  }

  const data = {
    name,
    lastName,
    email,
    password,
  };

  fetch(`${API_BASE_URL}/users`, {
    method: "POST",
    headers,
    body: JSON.stringify(data),
  });
}

function addButtonEvent() {
  SUBMIT_BUTTON.addEventListener("click", () => {
    registerUser();
  });
}
