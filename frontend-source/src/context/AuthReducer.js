const AuthReducer = (state, action) => {
  switch (action.type) {
    case "LOGIN_START":
      return {
        user: null,
        isFetching: true,
        error: false,
      };
    case "LOGIN_SUCCESS":
      return {
        user: action.payload,
        isFetching: false,
        error: false,
      };
    case "LOGIN_FAILURE":
      return {
        user: null,
        isFetching: false,
        error: true,
      };
    case "LOGOUT":
      return {
        user: null,
        isFetching: false,
        error: false,
      };
    case "SUBSCRIBE":
      return {
        ...state,
        user: {
          ...state.user,
          subscriptionids: [...state.user.subscriptionids, action.payload],
        },
      };
    case "UNSUBSCRIBE":
      return {
        ...state,
        user: {
          ...state.user,
          subscriptionids: state.user.subscriptionids.filter(
            (subscriptionids) => subscriptionids !== action.payload
          ),
        },
      };
    default:
      return state;
  }
};

export default AuthReducer;
