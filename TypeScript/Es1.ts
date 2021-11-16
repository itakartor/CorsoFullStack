class Persona{
    nome:string;
    congome:string;
    eta:number;
    dataNascita:Date;
}
class Materia
{
    id:string;
    descrizione:string;
    constructor(id:string , descrizione:string)
    {
        this.descrizione = descrizione;
        this.id = id;
    }
}
class Studente extends Persona
{
    matricola:string;
    voti:Array<[string,Date]> = [];
    listaMaterie:Array<Materia> = [];
}

let studente:Studente = new Studente();
studente.congome = "asso";
studente.dataNascita = new Date();
studente.eta = 18;
for(let i=0;i<3;i++)
{
    studente.listaMaterie.push(new Materia(i.toString() , "abc"));
    studente.voti.push([(i + Math.random()*20).toFixed(0),new Date(2017, i, 5 + i)]);
}
studente.matricola = "897847392";
studente.nome = "gianni";
console.log(studente);
let valore:string;
switch(valore)
{
    case"valore":
    {

        break;
    }
    default:
    {
        break;
    }
}

studente.voti.forEach(value =>{
    let x:number = +value;
    if(x >= 18)
        console.log("passato con " + value[0]);
    else
        console.log("bocciato con " + value[0])
})
