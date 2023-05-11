const API_BASE_URL = "http://localhost:8080/api";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};
const TABLE_REFERENCE = document.querySelector("#users-table");

// Call the dataTables jQuery plugin
$(document).ready(async function () {
  const users = await getUsers();
  fillTable(users);
  $("#users-table").DataTable();
});

async function getUsers() {
  return fetch(`${API_BASE_URL}/users`, {
    method: "GET",
    headers,
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
    rowCopy.querySelector(".user_phone").innerText = user.phone;
    rowCopy.querySelector("a").onclick = () => removeUser(user.id);
    documentFragment.appendChild(rowCopy);
  });
  TABLE_REFERENCE.appendChild(documentFragment);
}

function removeUser(id) {
  fetch(`${API_BASE_URL}/users/${id}`, {
    method: "DELETE",
    headers,
  })
}
