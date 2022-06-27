import React from 'react';

import { Route, useParams } from 'react-router-dom';

interface Element {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

interface ElementProps {
    elementi: Element[];
}

interface RouteParams {
    id: string
}

function More(props: ElementProps) {

    const { id } = useParams<RouteParams>();
    const idNumber = parseInt(id);

    return (
        <div>
            <h3>{props.elementi[idNumber].vsebina}</h3>
            <div><b>Zaključeno: </b> [{props.elementi[idNumber].zakljuceno ? "X" : " "}]</div>
        </div>
    );
}

export default More;