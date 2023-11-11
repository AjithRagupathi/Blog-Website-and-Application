import { useContext, useRef } from "react";
import "./login.css";
import { loginCall } from "../../services/loginService";
import { AuthContext } from "../../context/AuthContext";
import { CircularProgress } from "@material-ui/core";
import { Link } from "react-router-dom";

export default function Login() {
  const email = useRef();
  const password = useRef();
  const { isFetching, dispatch } = useContext(AuthContext);
  const PF = process.env.REACT_APP_PUBLIC_FOLDER;

  const handleClick = (e) => {
    e.preventDefault();
    loginCall(
      { email: email.current.value, password: password.current.value },
      dispatch
    );
  };

  return (
    <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          <img
            alt="Fanhub"
            src={PF + "logo/Fanhub1.png"}
            style={{ height: "146px", width: "400px", paddingRight: "10px" }}
          />
          <span className="loginDesc">
            Sign up to support your favorite creators
          </span>
        </div>
        <div className="loginRight">
          <form className="loginBox" onSubmit={handleClick}>
            <input
              placeholder="Email"
              type="email"
              required
              className="loginInput"
              ref={email}
            />
            <input
              placeholder="Password"
              type="password"
              required
              minLength="6"
              className="loginInput"
              ref={password}
            />
            <button className="loginButton" type="submit" disabled={isFetching}>
              {isFetching ? (
                <CircularProgress color="white" size="20px" />
              ) : (
                "Log In"
              )}
            </button>
            <span className="loginForgot">Forgot Password?</span>
            <Link to={`/register`} className="loginRegisterButton">
              <button className="loginRegisterButton">Become a Fan</button>
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
