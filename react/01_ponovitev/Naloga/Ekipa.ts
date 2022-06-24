import { Igralec, Funkcionar } from './Oseba';

class Ekipa {
    ime: string;
    letoUstanovitve: number;
    direktor: Funkcionar;
    trener: Funkcionar;
    igralci: Igralec[] = new Array();
    dodajIgralca = (igralec: Igralec) => { this.igralci.push(igralec) };
    izpisiPodatke = () => { return `ime: ${this.ime}, leto ustanovitve: ${this.letoUstanovitve}, direktor: ${this.direktor.ime + " " + this.direktor.priimek}, trener: ${this.trener.ime + " " + this.trener.priimek}` };
    constructor(ime: string, letoUstanovitve: number, direktor: Funkcionar, trener: Funkcionar) {
        this.ime = ime;
        this.letoUstanovitve = letoUstanovitve;
        this.direktor = direktor;
        this.trener = trener;
    }
}

export default Ekipa;