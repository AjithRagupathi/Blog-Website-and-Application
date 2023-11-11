import "./rightbar.css";
import { useContext, useEffect, useRef, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import { Add, Remove, Search } from "@material-ui/icons";

export default function Rightbar({ user, subscribed }) {
  const PF = process.env.REACT_APP_PUBLIC_FOLDER;
  const [creators, setCreators] = useState([]);
  const [suggestions, setSuggestions] = useState([]);
  const [searchResults, setSearchResults] = useState([]);
  const { user: currentUser, dispatch } = useContext(AuthContext);
  const searchInput = useRef();

  useEffect(() => {
    const fetchCreators = async () => {
      try {
        const creators = await axios.get(
          "/users/subscriptions/" + currentUser.id
        );
        setCreators(creators.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchCreators();
  }, [user, currentUser.id]);

  useEffect(() => {
    const fetchSuggestions = async () => {
      try {
        const suggestions = await axios.get(
          "/users/suggestions/" + currentUser.id
        );
        setSuggestions(suggestions.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchSuggestions();
  }, [user, currentUser.id]);

  const handleClick = async () => {
    try {
      if (subscribed) {
        await axios.put(`/users/${user.id}/unsubscribe/${currentUser.id}`);
        dispatch({ type: "UNSUBSCRIBE", payload: user.id });
      } else {
        await axios.put(`/users/${user.id}/subscribe/${currentUser.id}`);
        dispatch({ type: "SUBSCRIBE", payload: user.id });
      }
      subscribed = !subscribed;
    } catch (err) {
      console.log(err);
    }
  };

  const submitHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("users/search/" + searchInput.current.value);
      setSearchResults(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  const HomeRightbar = () => {
    return (
      <>
        <div className="rightbarCenter">
          <form className="searchUser" onSubmit={submitHandler}>
            <div className="searchbar">
              <input
                placeholder="Search Fanhub"
                className="searchInput"
                minLength="2"
                required
                ref={searchInput}
              />
              <button className="searchButton" type="submit">
                <Search className="searchIcon" />
              </button>
            </div>
          </form>
        </div>
        <div className="rightbarSearchResults">
          {searchResults.map((searchResults) => (
            <Link
              to={"/" + searchResults.username}
              style={{ textDecoration: "none" }}
            >
              <div className="rightbarSearchResult">
                <img
                  src={
                    searchResults.profilepic
                      ? PF + searchResults.profilepic
                      : PF + "person/noAvatar.png"
                  }
                  alt=""
                  className="rightbarSearchResultImg"
                />
                <span className="rightbarSearchResultName">
                  {searchResults.username}
                </span>
              </div>
            </Link>
          ))}
        </div>
        <h4 className="rightbarTitle">Suggestions</h4>
        <div className="rightbarFollowings">
          {suggestions.map((suggestions) => (
            <Link
              to={"/" + suggestions.username}
              style={{ textDecoration: "none" }}
            >
              <div className="rightbarFollowing">
                <img
                  src={
                    suggestions.profilepic
                      ? PF + suggestions.profilepic
                      : PF + "person/noAvatar.png"
                  }
                  alt=""
                  className="rightbarFollowingImg"
                />
                <span className="rightbarFollowingName">
                  {suggestions.username}
                </span>
              </div>
            </Link>
          ))}
        </div>
      </>
    );
  };

  const ProfileRightbar = () => {
    return (
      <>
        {user.username !== currentUser.username && (
          <button className="rightbarFollowButton" onClick={handleClick}>
            {subscribed ? "Unsubscribe" : "Subscribe"}
            {subscribed ? <Remove /> : <Add />}
          </button>
        )}
        <h4 className="rightbarTitle">Intro</h4>
        <div className="rightbarInfo">
          <div className="rightbarInfoItem">
            <span className="rightbarInfoKey">City:</span>
            <span className="rightbarInfoValue">{user.city}</span>
          </div>
          <div className="rightbarInfoItem">
            <span className="rightbarInfoKey">Relationship:</span>
            <span className="rightbarInfoValue">
              {user.relationship ? user.relationship : "Complicated"}
            </span>
          </div>
        </div>
        {user.username === currentUser.username && (
          <h4 className="rightbarTitle">Subscriptions</h4>
        )}
        {user.username === currentUser.username && (
          <div className="rightbarFollowings">
            {creators.map((creator) => (
              <Link
                to={"/" + creator.username}
                style={{ textDecoration: "none" }}
              >
                <div className="rightbarFollowing">
                  <img
                    src={
                      creator.profilepic
                        ? PF + creator.profilepic
                        : PF + "person/noAvatar.png"
                    }
                    alt=""
                    className="rightbarFollowingImg"
                  />
                  <span className="rightbarFollowingName">
                    {creator.username}
                  </span>
                </div>
              </Link>
            ))}
          </div>
        )}
      </>
    );
  };

  return (
    <div className="rightbar">
      <div className="rightbarWrapper">
        {user ? <ProfileRightbar /> : <HomeRightbar />}
      </div>
    </div>
  );
}
