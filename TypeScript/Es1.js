var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var Persona = /** @class */ (function () {
    function Persona() {
    }
    return Persona;
}());
var Materia = /** @class */ (function () {
    function Materia(id, descrizione) {
        this.descrizione = descrizione;
        this.id = id;
    }
    return Materia;
}());
var Studente = /** @class */ (function (_super) {
    __extends(Studente, _super);
    function Studente() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.voti = [];
        _this.listaMaterie = [];
        return _this;
    }
    return Studente;
}(Persona));
var studente = new Studente();
studente.congome = "asso";
studente.dataNascita = new Date();
studente.eta = 18;
for (var i = 0; i < 3; i++) {
    studente.listaMaterie.push(new Materia(i.toString(), "abc"));
    studente.voti.push([(i + Math.random() * 20).toFixed(0), new Date(2017, i, 5 + i)]);
}
studente.matricola = "897847392";
studente.nome = "gianni";
console.log(studente);
var valore;
switch (valore) {
    case "valore":
        {
            break;
        }
    default:
        {
            break;
        }
}
studente.voti.forEach(function (value) {
    var x = +value;
    if (x >= 18)
        console.log("passato con " + value[0]);
    else
        console.log("bocciato con " + value[0]);
});
