// send a POST request
document.getElementById('pizza_create').addEventListener('submit', async function(event){
    event.preventDefault();
    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;
    const pic = document.getElementById("pic").value;
    const price = document.getElementById("price").value;
    
    try{
        await axios.post('http://localhost:8080/api/v1/pizzas', {
            name,
            description,
            pic,
            price,
            ingredients: []
        })
        window.location.href = "index.html";
    }catch(error){
        console.error(error);
    }
    
   
    
    
})
    



    



