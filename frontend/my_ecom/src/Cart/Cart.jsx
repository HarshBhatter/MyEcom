import React from 'react'
import './Cart.css'
import { CartData } from './CartData'
import { NavLink } from 'react-router-dom';
import { FaMinus } from "react-icons/fa";
import { FaPlus } from "react-icons/fa";

export const Cart = () => {
    const data=CartData();
    console.log(data);
    if(data.length==0) 
        return <div className='header'>Your Cart is Empty</div>
    return (
    <>
        <div className='header'>Your Cart</div>
        <div className="Cart">
            <table >
                <thead>
                <tr className='header'>
                    <th className='serial'>Serial No.</th>
                    <th>Product Name</th>
                    <th>Color</th>
                    <th>Size</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                </thead>

                {data.map((product,index)=>(
                    <tbody>
                    <tr>
                        <td>{index+1}</td>
                        <td><NavLink to={`/product/${product.productId}`}>{product.name}</NavLink></td>
                        <td>{product.color}</td>
                        <td>{product.size}</td>
                        <td className='quantity'><FaMinus cursor="pointer" onClick={
                            ()=>{
                                fetch('http://localhost:8080/RemoveFromCart', {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                body: JSON.stringify({
                                    productid: product.productId,
                                    color: product.color,
                                    size: product.size,
                                    quantity: 1
                                })
                            }).then(() => window.location.reload())
                            }
                        }/>{product.quantity}<FaPlus cursor="pointer" onClick={
                            ()=>{
                                fetch('http://localhost:8080/AddToCart', {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                body: JSON.stringify({
                                    productid: product.productId,
                                    color: product.color,
                                    size: product.size,
                                    quantity: 1
                                })
                            }).then(() => window.location.reload())
                        }}/></td>
                        <td>{product.total}</td>
                    </tr>
                    </tbody>
                ))}

                
            </table>
        </div>
        <div className='PlaceOrder' ><button onClick={
            () => {
                fetch('http://localhost:8080/PlaceOrder', {
                    method: 'POST',
                }).then(() => window.location.reload())
            }
        }>Place Order</button></div>

    </>
  )
}
