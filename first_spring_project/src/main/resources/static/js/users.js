import { getHeaders } from "./get-headers.js";
const API_BASE_URL = "http://localhost:8080/api";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};
const TABLE_REFERENCE = document.querySelector("#users-table");
const EMAIL_KEY_FOR_LOCAL_STORAGE = "email";

// Call the dataTables jQuery plugin
$(document).ready(async function () {
  const users = await getUsers();
  fillTable(users);
  $("#users-table").DataTable();
  getAndFillUserEmailFromLocalStorage();
});

async function getUsers() {
  return fetch(`${API_BASE_URL}/users`, {
    method: "GET",
    headers: getHeaders("authorization"),
  }).then((response) => response.json());
}

function fillTable(users) {
  const USER_ROW_TEMPLATE = document.querySelector("#user-information").content;
  const documentFragment = document.createDocumentFragment();
  users.forEach((user) => {
    const rowCopy = document.importNode(USER_ROW_TEMPLATE, true);
    rowCopy.querySelector(".user_id").innerText = user.id;
    rowCopy.querySelector(
      ".user_name"
    ).innerText = `${user.name} ${user.lastName}`;
    rowCopy.querySelector(".user_email").innerText = user.email;
    rowCopy.querySelector(".user_phone").innerText =
      user.phone === "" ? "NOT PROVIDED" : user.phone;
    rowCopy.querySelector("a").onclick = () => removeUser(user.id);
    documentFragment.appendChild(rowCopy);
  });
  TABLE_REFERENCE.appendChild(documentFragment);
}

function removeUser(id) {
  if (!confirm("Are you user you want to remove the user?")) return;
  fetch(`${API_BASE_URL}/users/${id}`, {
    method: "DELETE",
    headers: getHeaders("authorization"),
  });
}

function getAndFillUserEmailFromLocalStorage() {
  document.getElementById("user-email").innerText =
    localStorage.getItem(EMAIL_KEY_FOR_LOCAL_STORAGE) ?? "Log In";
}
