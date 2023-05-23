const JWT_TOKEN_KEY_FOR_LOCAL_STORAGE = "jwtToken";

const HEADERS = {
  Accept: "application/json",
  "Content-Type": "application/json",
};

export function getHeaders(headerType = "normal") {
  return headerType === "authorization"
    ? { ...HEADERS, Authorization: getJwtToken() }
    : HEADERS;
}

function getJwtToken() {
  return localStorage.getItem(JWT_TOKEN_KEY_FOR_LOCAL_STORAGE) ?? "";
}
