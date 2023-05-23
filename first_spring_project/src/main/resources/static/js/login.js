import { getHeaders } from "./get-headers.js";

const API_BASE_URL = "http://localhost:8080/api";
const SUBMIT_BUTTON = document.querySelector("#submit-btn");
const JWT_TOKEN_KEY_FOR_LOCAL_STORAGE = "jwtToken";

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
    headers: getHeaders(),
    body: JSON.stringify(data),
  }).then((res) => res.text());
  response !== "INCORRECT"
    ? ((window.location.href = "users.html"),
      localStorage.setItem(JWT_TOKEN_KEY_FOR_LOCAL_STORAGE, response),
      localStorage.setItem("email", data.email))
    : alert("Wrong credentials! Please try again.");
}

function addButtonEvent() {
  SUBMIT_BUTTON.addEventListener("click", () => {
    login();
  });
}
