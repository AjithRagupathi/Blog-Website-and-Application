export const LoginStart = (userCredentials) => ({
  type: "LOGIN_START",
});

export const LoginSuccess = (user) => ({
  type: "LOGIN_SUCCESS",
  payload: user,
});

export const LoginFailure = () => ({
  type: "LOGIN_FAILURE",
});

export const Subscribe = (userId) => ({
  type: "SUBSCRIBE",
  payload: userId,
});

export const Unsubscribe = (userId) => ({
  type: "UNSUBSCRIBE",
  payload: userId,
});
