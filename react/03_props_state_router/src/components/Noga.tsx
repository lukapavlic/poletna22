import React from 'react';
import './Noga.css';

interface NogaProps {
    avtor: string;
}

interface NogaState {
    letnica: number;
}

/* class Noga extends React.Component<NogaProps, NogaState>{
    constructor(props: NogaProps) {
        super(props);
        this.state = { letnica: 0 };
    }

    componentDidMount() {
        this.pridobiLetnico();
    }

    pridobiLetnico(): void {
        let datum: Date = new Date();
        let leto: number = datum.getFullYear();
        this.setState({
            letnica: leto
        })
    }

    render() {
        return <div className="noga">
            &copy; {this.state.letnica} {this.props.avtor}
        </div>
    }
} */

function Noga(props: NogaProps) {
    const [letnica, setLetnica] = React.useState(0);

    const pridobiLetnico: () => void = () => {
        let datum: Date = new Date();
        let leto: number = datum.getFullYear();
        setLetnica(leto);
    }

    React.useEffect(() => {
        pridobiLetnico();
    })

    return (
        <div className="noga">
            &copy; {letnica} {props.avtor}
        </div>
    )
}

export default Noga;