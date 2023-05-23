const JWT_TOKEN_KEY_FOR_LOCAL_STORAGE = "jwtToken";

export function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getJwtToken(),
  };
}

function getJwtToken() {
  return localStorage.getItem(JWT_TOKEN_KEY_FOR_LOCAL_STORAGE) ?? "";
}
