import { useEffect } from 'react'
import { useState } from 'react'

export const ProductsData = () => {
    const [products, setProducts] = useState([])

    useEffect(() => {
        fetch('http://localhost:8080/All')
            .then(res => res.json())
            .then(data => setProducts(data))
            .catch(err => console.log(err))
    }, [])

    return products
}