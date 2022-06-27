import React from 'react';

function About() {


    React.useEffect(() => {
        document.title = "Več o nas";
    })

    return <div>
        <h1>O nas</h1>
        <div>
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Dolores assumenda alias provident optio commodi. Exercitationem sed rem distinctio doloribus veritatis velit? Dolores quo corrupti amet officia eaque sequi ratione consequatur.
        </div>

    </div>
}

export default About;


/* // PRIMER Z RAZREDNIMI KOMPONENTAMI
export default class About extends React.Component {
    constructor(props: any) {
        super(props);
    }

    componentDidMount() {
        document.title = "Več o nas";
    }

    componentDidUpdate() {
        document.title = "Več o nas";
    }

    render() {
        return <div>
            <h1>O nas</h1>
            <div>
                Lorem ipsum dolor sit, amet consectetur adipisicing elit. Dolores assumenda alias provident optio commodi. Exercitationem sed rem distinctio doloribus veritatis velit? Dolores quo corrupti amet officia eaque sequi ratione consequatur.
        </div>
        </div>
    }
} */