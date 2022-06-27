"use strict";
exports.__esModule = true;
var Ekipa = /** @class */ (function () {
    function Ekipa(ime, letoUstanovitve, direktor, trener) {
        var _this = this;
        this.igralci = new Array();
        this.dodajIgralca = function (igralec) { _this.igralci.push(igralec); };
        this.izpisiPodatke = function () { return "ime: " + _this.ime + ", leto ustanovitve: " + _this.letoUstanovitve + ", direktor: " + (_this.direktor.ime + " " + _this.direktor.priimek) + ", trener: " + (_this.trener.ime + " " + _this.trener.priimek); };
        this.ime = ime;
        this.letoUstanovitve = letoUstanovitve;
        this.direktor = direktor;
        this.trener = trener;
    }
    return Ekipa;
}());
exports["default"] = Ekipa;
