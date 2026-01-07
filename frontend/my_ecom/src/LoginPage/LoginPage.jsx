import React from 'react'
import './LoginPage.css'
import { FcGoogle } from "react-icons/fc";
import { RxCross2 } from "react-icons/rx";

export const LoginPage = () => {
  return (
    <div className='overlay'>
        <div className='LoginPage'>
            <div className='header'>Log in/Sign in</div>
            <div className='cross'><RxCross2 /></div>
            <form onSubmit={(e) => {
                e.preventDefault();
                fetch('http://localhost:8080/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        username: e.target.username.value,
                        password: e.target.password.value
                    })
                }).then(() => window.location.href = "/")
            }}>
                <div>
                    <label htmlFor="username">Username</label>
                    <input type="text" id="username" name="username" required />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password" required />
                </div>
                <button type="submit">Login</button>
            </form>
            <div>do not have an account?<a href="#">Create Account</a></div>
            <div >OR</div>
            <div className='google' onClick={() => {
                window.location.href = "http://localhost:8080/oauth2/authorization/google"
            }}><FcGoogle /> Continue with google</div>
        </div>
    </div>
  )
}
