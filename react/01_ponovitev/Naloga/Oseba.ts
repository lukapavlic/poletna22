interface Oseba {
    ime: string;
    priimek: string;
    letoRojstva: number;
    krajRojstva?: string;
}

interface Igralec extends Oseba {
    visina: number;
    teza: number;
    poskodovan: boolean;
}

interface Funkcionar extends Oseba {
    vloga: string;
    veljavnost: number;
}

export { Igralec, Funkcionar };