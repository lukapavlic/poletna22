import Ekipa from './Ekipa';
import { Igralec, Funkcionar } from './Oseba';

let direktor: Funkcionar = { ime: "Janez", priimek: "Novak", krajRojstva: "Maribor", letoRojstva: 1965, veljavnost: 2024, vloga: "direktor" };

let trener: Funkcionar = { ime: "Miha", priimek: "Kranjc", letoRojstva: 1980, veljavnost: 2022, vloga: "trener" };

let prviIgralec: Igralec = { ime: "Marcos", priimek: "Tavares", poskodovan: false, letoRojstva: 1982, teza: 85, visina: 175 };

let drugiIgralec: Igralec = { ime: "Luka", priimek: "Zahovic", poskodovan: true, letoRojstva: 1996, teza: 80, visina: 180 };

let tretjiIgralec: Igralec = { ime: "Nemanja", priimek: "Mitrovic", poskodovan: false, letoRojstva: 1993, teza: 89, visina: 189 };

let team: Ekipa = new Ekipa("Maribor", 1960, direktor, trener);
team.dodajIgralca(prviIgralec);
team.dodajIgralca(drugiIgralec);
team.dodajIgralca(tretjiIgralec);
console.log(team.izpisiPodatke());
console.log(team.igralci);