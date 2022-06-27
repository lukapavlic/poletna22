import React, { ChangeEvent, FormEvent } from 'react';

interface Element {
    id: number;
    zakljuceno: boolean;
    vsebina: string;
}

interface AddProps {
    onAdd: (element: Element) => any;
    id: number;
}

function Add(props: AddProps) {
    const [vsebina, setVsebina] = React.useState<string>("");

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        props.onAdd({ id: props.id, zakljuceno: false, vsebina: vsebina });
    }
    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setVsebina(e.target.value);
    }
    return (
        <form onSubmit={handleSubmit}>
            <label>Opravilo:
            <input type="text" value={vsebina} onChange={handleChange} />
            </label>
            <input type="submit" value="Dodaj" />
        </form>
    );
}

export default Add;