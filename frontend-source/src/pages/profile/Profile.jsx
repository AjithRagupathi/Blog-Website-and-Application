import "./profile.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Feed from "../../components/feed/Feed";
import Rightbar from "../../components/rightbar/Rightbar";
import { useContext, useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router";
import { AuthContext } from "../../context/AuthContext";

export default function Profile() {
  const PF = process.env.REACT_APP_PUBLIC_FOLDER;
  const [user, setUser] = useState({});
  const { user: currentUser, dispatch } = useContext(AuthContext);
  const [userLoaded, setUserLoaded] = useState(false);
  const username = useParams().username;
  const [subscribed, setSubscribed] = useState(false);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await axios.get(`/users/profile?username=${username}`);
        return { success: true, data: res.data };
      } catch (error) {
        console.log(error);
        return { success: false };
      }
    };
    const getUser = async () => {
      setUserLoaded(false);
      const res = await fetchUser();
      if (res.success) {
        setUser(res.data);
        setUserLoaded(true);
      } else {
        setUser();
      }
    };
    getUser();
  }, [username]);

  useEffect(() => {
    const fetchSubscribed = async () => {
      try {
        const isSubscriber =
          currentUser.subscriptionids.includes(user?.id) ||
          currentUser.id === user.id;
        setSubscribed(isSubscriber);
      } catch (err) {
        console.log(err);
      }
    };
    fetchSubscribed();
  }, [user, currentUser.subscriptionids]);

  return (
    <>
      <div className="profile">
        <Sidebar />
        {!userLoaded && !user && (
          <div className="profileRight">
            <div className="profileRightBottom">
              <Feed username={username} notFound={true} />
              <Rightbar />
            </div>
          </div>
        )}
        {userLoaded && user && (
          <div className="profileRight">
            <div className="profileRightTop">
              <div className="profileCover">
                <img
                  className="profileCoverImg"
                  src={
                    user.coverpic
                      ? PF + user.coverpic
                      : PF + "person/noCover.jpg"
                  }
                  alt=""
                />
                <img
                  className="profileUserImg"
                  src={
                    user.profilepic
                      ? PF + user.profilepic
                      : PF + "person/noAvatar.png"
                  }
                  alt=""
                />
              </div>
              <div className="profileInfo">
                <h4 className="profileInfoName">
                  {user.name} @{user.username}
                </h4>
                <span className="profileInfoDesc">{user.bio}</span>
              </div>
            </div>
            <div className="profileRightBottom">
              <Feed username={username} subscribed={subscribed} />
              <Rightbar user={user} subscribed={subscribed} />
            </div>
          </div>
        )}
      </div>
    </>
  );
}
