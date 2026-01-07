import { useState } from "react";
import { useEffect } from "react";

import React from 'react'

export const CartData = () => {

    const [cart,setProducts]=useState([])

    useEffect(() => {
        fetch('http://localhost:8080/Cart')
            .then(res => res.json())
            .then(data => setProducts(data))
            .catch(err => console.log(err))
    }, [])
    
    return cart;
}

