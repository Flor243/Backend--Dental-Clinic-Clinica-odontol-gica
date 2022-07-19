window.addEventListener('load', function(){

 const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();
        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())


    })




})

function findBy(id){
    const url = '/odontologos/'+id;
    const settings = {
                  method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(o => {
         let odontologo = o;
         document.querySelector('#odontologo_id').value = odontologo.id;
         document.querySelector('#nombre').value = odontologo.nombre;
         document.querySelector('#apellido').value = odontologo.apellido;
         document.querySelector('#matricula').value = odontologo.matricula;
         //el formulario por default esta oculto y al editar se habilita
        document.querySelector('#div_odontologo_updating').style.display = 'block';
    }).catch(error => {
        alert("Error: " + error);
    })
    }

