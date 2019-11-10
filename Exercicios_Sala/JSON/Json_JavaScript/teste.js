/*function Carro(marca, modelo, ano){
	this.marca = marca;
	this.modelo = modelo;
	this.ano = ano;
}

let carro = new Carro("Honda", "Civic", 2010);
*/
/*
let obj = {
			nome:"Anderson", 
			idade:25,
			universidade : {
				nome: "Universidade Federal do Ceará",
				sigla: "UFC",
				notas: [4, 5, 2, 5, 6]
			},
		  };

let json = JSON.stringify(obj);
localStorage.setItem("meuJson", json);
// console.log(json);
*/

/*
let json = '{"nome":"Anderson","idade":25,"universidade":{"nome":"Universidade Federal do Ceará","sigla":"UFC","notas":[4,5,2,5,6]}}';
let obj = JSON.parse(json);
console.log(obj.nome);
*/


let json = localStorage.getItem("meuJson");
let obj = JSON.parse(json);
alert(obj.nome);















