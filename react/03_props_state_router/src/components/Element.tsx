import React from 'react';

interface ElementProps {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

function Element(props: ElementProps) {
    return <li>[{props.zakljuceno ? "X" : " "}] {props.vsebina}</li>;
}

export default Element;