const API_BASE_URL = "http://localhost:8080/api";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};
const SUBMIT_BUTTON = document.querySelector("#submit-btn");

$(document).ready(function () {
  addButtonEvent();
});

async function login() {
  const email = document.getElementById("email").value,
    password = document.getElementById("password").value;

  const data = {
    email,
    password,
  };

  console.log(data);

  const response = await fetch(`${API_BASE_URL}/login`, {
    method: "POST",
    headers,
    body: JSON.stringify(data),
  }).then((res) => res.text());

  response === "OK"
    ? (window.location.href = "users.html")
    : alert("Wrong credentials! Please try again.");
}

function addButtonEvent() {
  SUBMIT_BUTTON.addEventListener("click", () => {
    login();
  });
}
