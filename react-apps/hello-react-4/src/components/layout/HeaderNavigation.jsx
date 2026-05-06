/** @format */

import { NavLink } from "react-router-dom";
import Login from "../user/Login";

export const HeaderNavigation = () => {
  return (
    <header>
      <Login />
      <nav className="menu-navigation">
        <ul>
          <li>
            <NavLink to="/tmdb">TMDB</NavLink>
          </li>
          <li>
            <NavLink to="/todo">TODO</NavLink>
          </li>
          <li>
            <NavLink to="/article">ARTICLE</NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
};
