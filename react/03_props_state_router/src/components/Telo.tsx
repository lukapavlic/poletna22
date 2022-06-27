import React from 'react';
import Element from './Element';

import { Link } from 'react-router-dom';

interface Element {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

interface TeloProps {
    elementi: Element[];
}

function Telo(props: TeloProps) {
    const { elementi } = props;
    return (
        <div>
            <ul>

                {elementi.map((el) => (
                    <Link key={el.id} to={`/more/${el.id}`}><Element id={el.id} zakljuceno={el.zakljuceno} vsebina={el.vsebina} /></Link>
                ))}

            </ul>
        </div>
    )
}

export default Telo;