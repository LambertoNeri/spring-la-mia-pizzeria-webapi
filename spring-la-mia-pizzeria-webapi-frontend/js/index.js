/* COSTANTI */

const apiUrlTotal =  'http://localhost:8080/api/v1/pizzas';

let search = 0;

const root = document.getElementById("root");





/* FUNZIONI */


const doSearch = () => {
    search = document.getElementById("search").value;
    getPizzas(search);
}

// funzione che renderizza la card della pizza
const renderPizza = (element) => {
    return `
    <div class="card mt-4" style="width: 95%;">
        <img src="${element.pic}" class="card-img-top" style="height: 200px" alt="...">
        <div class="card-body">
            <h5 class="overflow card-title text-center">${element.name}</h5>
            <p class="overflow card-text text-center">${element.description}</p>
            <div class="d-flex justify-content-center">
                <a href="create.html" class="btn btn-primary"  >Details(to be implemented)</a>
            </div>
        </div>
    </div>
    `
    
} 


// funzione che renderizza la lista di pizze
const renderPizzaList = (data) => {
    let content;
    if(data.length >0 ) {
        content = '<div class="d-flex flex-wrap">';
        data.forEach((element) => {
            content += '<div class="col-3 d-flex">';
            content += renderPizza(element);
            content += '</div>';
        });
        content += '</div>';
    } else {
        content = '<div class="alert alert-info">The list is empty</div>';
    }
    root.innerHTML = content;
};

//funzione che chiama l'API e ottiene il json con l'array di pizzas
const getPizzas = async (search) => {
    
    if (!search == 0) {
        try {
            const apiUrlSearch = 'http://localhost:8080/api/v1/pizzas?search=' + search;
            const response = await axios.get(apiUrlSearch);
            renderPizzaList(response.data)
        }catch(error){
            console.log(error);
        }
    } else {
        try {
            const response = await axios.get(apiUrlTotal);
            renderPizzaList(response.data)
        }catch(error){
            console.log(error);
        }
    }
};





/* CODICE GLOBALE EESEGUITO AL LOAD DELLO SCRIPT */
getPizzas();

