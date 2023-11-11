import "./sidebar.css";
import {
  HomeRounded,
  Chat,
  PlayCircleFilledOutlined,
  Notifications,
  Bookmark,
  ExitToAppRounded,
  CreditCard,
} from "@material-ui/icons";
import { useContext, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

export default function Sidebar() {
  const { user } = useContext(AuthContext);
  const { dispatch } = useContext(AuthContext);

  const handleLogout = async () => {
    dispatch({ type: "LOGOUT", payload: null });
  };

  const PF = process.env.REACT_APP_PUBLIC_FOLDER;
  return (
    <div className="sidebar">
      <div className="sidebarWrapper">
        <ul className="sidebarList">
          <li className="sidebarListLogo">
            <Link to="/">
              <img alt="Fanhub" src={PF + "logo/Fanhub1.png"} />
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <HomeRounded className="sidebarListItemIcon" fontSize="large" />
              <span className="sidebarListItemText">Home</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <Notifications className="sidebarListItemIcon" fontSize="large" />
              <span className="sidebarListItemText">Notifications</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <Chat className="sidebarListItemIcon" fontSize="large" />
              <span className="sidebarListItemText">Chats</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <PlayCircleFilledOutlined
                className="sidebarListItemIcon"
                fontSize="large"
              />
              <span className="sidebarListItemText">Videos</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <Bookmark className="sidebarListItemIcon" fontSize="large" />
              <span className="sidebarListItemText">Bookmarks</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to="/">
              <CreditCard className="sidebarListItemIcon" fontSize="large" />
              <span className="sidebarListItemText">Add card</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to={`/${user.username}`}>
              <img
                src={
                  user.profilepic
                    ? PF + user.profilepic
                    : PF + "person/noAvatar.png"
                }
                alt=""
                className="sidebarImg"
              />
              <span className="sidebarListItemText">{user.username}</span>
            </Link>
          </li>
          <li className="sidebarListItem">
            <Link to={`/login`} onClick={handleLogout}>
              <ExitToAppRounded
                className="sidebarListItemIcon"
                fontSize="large"
              />
              <span className="sidebarListItemText">Logout</span>
            </Link>
          </li>
        </ul>
        <button className="sidebarButton">More...</button>
      </div>
    </div>
  );
}
