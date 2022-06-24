import React from 'react';
import { Link } from 'react-router-dom';
interface MenuProps {
    naslov?: string;
}

function Menu(props: MenuProps) {
    return (
        <>
            <h1>
                {props.naslov || "Naslov"}
            </h1>
            <h3>
                <ul>
                    <li><Link to="/">Domov</Link></li>
                    <li><Link to="/about">O nas</Link></li>
                </ul>
            </h3>
        </>
    );
}

export default Menu;