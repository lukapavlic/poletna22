import React from 'react';

interface Element {
    zakljuceno: boolean;
    vsebina: string;
}

function Telo() {
    let elementi: Element[] = [];
    elementi.push({ zakljuceno: false, vsebina: 'Pomij posodo' });
    elementi.push({ zakljuceno: true, vsebina: 'Posesaj stanovanje' });

    return (
        <div>
            <ul>
                {elementi.map((el) => <li>[{el.zakljuceno ? "X" : " "}] {el.vsebina}</li>)}
            </ul>
        </div>
    )
}

export default Telo;