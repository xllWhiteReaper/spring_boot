const API_BASE_URL = "http://localhost:8080/api";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};
const SUBMIT_BUTTON = document.querySelector("#submit-btn");

$(document).ready(function () {});

function registerUser() {
  const name = document.getElementById("name").value,
    lastName = document.getElementById("lastName").value,
    email = document.getElementById("email").value,
    password = document.getElementById("password").value,
    repeatPassword = document.getElementById("repeatPassword").value;

  console.log(name);
  console.log(name);
  console.log(lastName);
  console.log(lastName);
  console.log(email);
  console.log(email);
  console.log(password);
  console.log(password);
  console.log(repeatPassword);
  console.log(repeatPassword);
  
  if (password !== repeatPassword) {
    alert("Passwords don't match");
    return;
  }
  const data = {};
  fetch(`${API_BASE_URL}/users`, {
    method: "POST",
    headers,
    body: JSON.stringify(data),
  });
}

SUBMIT_BUTTON.addEventListener("click", ()=>{
  registerUser();
})
