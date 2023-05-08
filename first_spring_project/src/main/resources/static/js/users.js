const API_BASE_URL = "http://localhost:8080";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
};

// Call the dataTables jQuery plugin
$(document).ready(function () {
  loadUsers();
  $("#users").DataTable();
});

async function loadUsers() {
  const request = await fetch(`${API_BASE_URL}/users`, {
    method: "GET",
    headers,
  });
  users = await request.json();
}
