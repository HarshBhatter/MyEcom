import React from 'react'
import './Navbar.css'
import { FaCartShopping } from "react-icons/fa6";
import { Link, NavLink } from 'react-router-dom';

export const Navbar = () => {
  return (
    <>
      <div className="navbar">
        <div className="left-navbar">
          <div><NavLink to="/">Logo</NavLink></div>
          <div><NavLink to="/#about-us" >About Us</NavLink><hr></hr></div>
          <div><NavLink to="/#contact-us">Contact Us</NavLink><hr /></div>
        </div>

        <div className="right-navbar">
          <div><NavLink to="/cart"><FaCartShopping /></NavLink><hr /></div>
          <div><NavLink to="/orders">My Orders</NavLink><hr /></div>
          <div><NavLink to="/login">My Id</NavLink><hr /></div>
        </div>
      </div>
    </>
  )
}
