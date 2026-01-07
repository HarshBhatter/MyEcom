import React from 'react'
import './Orders.css'
import { OrdersData } from './OrdersData'
import { Link,NavLink } from 'react-router-dom';

export const Orders = () => {
  const data=OrdersData();
  console.log(data)
  return (
    <>
      <div className='header'>My Orders</div>
      <div className='Cart'><hr />
        <table>
          <thead>
            <tr className='header'>
              <th className='serial'>Serial no.</th>
              <th >Order Id</th>
              <th>Order Date</th>   
              <th >Status</th>   
              <th >Product Names</th>
              <th >Price</th>
            </tr>
          </thead>

          {data.map((order,index)=>(
            <tbody>
              <tr>
                <td>{index + 1}</td>
                <td><NavLink to={`/order/${order.id}`}>{order.orderId}</NavLink></td>
                <td>{order.orderDate}</td>
                <td>{order.status}</td>
                <td>{order.items.map((items)=>(
                  <div>{items.productName}</div>
                ))}</td>
                <td>{order.total}</td>
              </tr>
            </tbody>
        ))}
        </table>
        

      </div>
    </>
  )
}
