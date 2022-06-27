"use strict";
exports.__esModule = true;
var Ekipa_1 = require("./Ekipa");
var direktor = { ime: "Janez", priimek: "Novak", krajRojstva: "Maribor", letoRojstva: 1965, veljavnost: 2024, vloga: "direktor" };
var trener = { ime: "Miha", priimek: "Kranjc", letoRojstva: 1980, veljavnost: 2022, vloga: "trener" };
var prviIgralec = { ime: "Marcos", priimek: "Tavares", poskodovan: false, letoRojstva: 1982, teza: 85, visina: 175 };
var drugiIgralec = { ime: "Luka", priimek: "Zahovic", poskodovan: true, letoRojstva: 1996, teza: 80, visina: 180 };
var tretjiIgralec = { ime: "Nemanja", priimek: "Mitrovic", poskodovan: false, letoRojstva: 1993, teza: 89, visina: 189 };
var team = new Ekipa_1["default"]("Maribor", 1960, direktor, trener);
team.dodajIgralca(prviIgralec);
team.dodajIgralca(drugiIgralec);
team.dodajIgralca(tretjiIgralec);
console.log(team.izpisiPodatke());
console.log(team.igralci);