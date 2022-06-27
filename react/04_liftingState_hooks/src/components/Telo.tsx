import React from 'react';
import Element from './Element';
import Dodajanje from './Dodajanje';

import { Link } from 'react-router-dom';

interface Element {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

interface TeloProps {
    elementi: Element[];
    onAdd: (element: Element) => any
}

function Telo(props: TeloProps) {
    const { elementi } = props;
    const [stevec, setStevec] = React.useState(0);

    const handleAdd = (element: Element) => {
        props.onAdd(element);
    }

    React.useEffect(() => {
        setStevec(prestejNedokoncane());
    }, [elementi]);

    const prestejNedokoncane = () => {
        let stevec: number = 0;
        for (const element of elementi) {
            if (!element.zakljuceno)
                stevec++;
        }
        return stevec;
    }

    return (
        <div>
            <h3>Število nedokončanih opravil: {stevec}</h3>
            <ul>
                {elementi.map((el) => (
                    <Link key={el.id} to={`/more/${el.id}`}><Element id={el.id} zakljuceno={el.zakljuceno} vsebina={el.vsebina} /></Link>
                ))}
            </ul>
            <div>
                <Dodajanje id={elementi.length} onAdd={handleAdd} />
            </div>
        </div>
    )
}

export default Telo;