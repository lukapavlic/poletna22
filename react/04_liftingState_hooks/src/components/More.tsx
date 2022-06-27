import React from 'react';

import { useParams } from 'react-router-dom';

interface Element {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

interface ElementProps {
    elementi: Element[];
    onChange: (elementi: Element[]) => any;
}

interface ParamsProps {
    id: string
}


function More(props: ElementProps) {

    const { id } = useParams<ParamsProps>();
    const idNumber = parseInt(id);

    const [element, setElement] = React.useState<Element>(props.elementi[idNumber]);;

    const handleElementClick = () => {
        let el = { ...element }; // deep copy
        el.zakljuceno = !el.zakljuceno;
        setElement(el);
        let novoPolje = props.elementi;
        novoPolje[idNumber] = el;
        props.onChange(novoPolje);
    }

    return (
        <div>
            <h3>{element.vsebina}</h3>
            <div><b>Zaključeno: </b> <span onClick={handleElementClick}>{element.zakljuceno ? "DA" : "NE"}</span></div>
        </div>
    );
}

export default More;