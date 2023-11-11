import { useContext, useEffect, useState } from "react";
import Post from "../post/Post";
import Share from "../share/Share";
import "./feed.css";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";

export default function Feed({ username, notFound, subscribed }) {
  const PF = process.env.REACT_APP_PUBLIC_FOLDER;
  const [posts, setPosts] = useState([]);
  const { user } = useContext(AuthContext);

  useEffect(() => {
    const fetchPosts = async () => {
      const res = username
        ? await axios.get("/posts/timeline/" + username)
        : await axios.get("/posts/feed/" + user.id);
      setPosts(
        res.data.sort((p1, p2) => {
          return new Date(p2.date) - new Date(p1.date);
        })
      );
      setPosts(res.data);
    };
    fetchPosts();
  }, [username, user.id]);

  return (
    <div className="feed">
      {notFound ? (
        <div className="feedWrapper">
          <div className="post">
            <div className="postWrapper">
              <div className="postCenter">
                <img className="postImg" src={PF + "404.webp"} alt="" />
              </div>
              <div className="postMessage">
                <span>The Requested Page Cannot Be Found</span>
              </div>
            </div>
          </div>
        </div>
      ) : (
        <div className="feedWrapper">
          {(!username || username === user.username) && <Share />}
          {posts.map((p) => (
            <Post key={p.id} post={p} subscribed={subscribed} />
          ))}
        </div>
      )}
    </div>
  );
}
