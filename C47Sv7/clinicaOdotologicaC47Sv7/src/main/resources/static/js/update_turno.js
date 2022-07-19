window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del odontolohgo
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            idTurno: document.querySelector('#turno_id').value,
            fecha: document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value,
            pacienteDTO: {
                            id: document.querySelector('#paciente_id').value,
                            nombre: document.querySelector('#nombreP').value,
                            apellido: document.querySelector('#apellidoP').value,
                            dni: document.querySelector('#dni').value,
                            fechaIngreso: document.querySelector('#fechaIngreso').value,
                            email: document.querySelector('#email').value,
                            domicilioDTO: {
                                            id: document.querySelector('#id_domicilio').value,
                                            calle: document.querySelector('#calle').value,
                                            numero: document.querySelector('#numero').value,
                                            localidad: document.querySelector('#localidad').value,
                                            provincia: document.querySelector('#provincia').value
                            }
            },
            odontologoDTO: {
                id: document.querySelector('#odontologo_id').value,
                            nombre: document.querySelector('#nombreO').value,
                            apellido: document.querySelector('#apellidoO').value,
                            matricula: document.querySelector('#matriculaO').value
            }

            //agregar todos los queryselector del form en pacienteList, incluidos los id
        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //al odontologo que enviaremos en formato JSON
        const url = '/turnos/actualizar';
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

    //Es la funcion que se invoca cuando se hace click sobre el id de un odontologo del listado
    //se encarga de llenar el formulario con los datos del odontologo
    //que se desea modificar
    function findTurnoBy(id) {
          const url = '/turnos/'+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.idTurno;
              document.querySelector('#fecha').value = turno.fecha;
              document.querySelector('#hora').value = turno.hora;
              document.querySelector('#paciente_id').value = turno.pacienteDTO.id;
              document.querySelector('#nombreP').value = turno.pacienteDTO.nombre;
              document.querySelector('#apellidoP').value = turno.pacienteDTO.apellido;
              document.querySelector('#dni').value = turno.pacienteDTO.dni;
              document.querySelector('#fechaIngreso').value = turno.pacienteDTO.fechaIngreso;
              document.querySelector('#email').value = turno.pacienteDTO.email;
              document.querySelector('#id_domicilio').value = turno.pacienteDTO.domicilioDTO.id;
              document.querySelector('#calle').value = turno.pacienteDTO.domicilioDTO.calle;
              document.querySelector('#numero').value = turno.pacienteDTO.domicilioDTO.numero;
              document.querySelector('#localidad').value = turno.pacienteDTO.domicilioDTO.localidad;
              document.querySelector('#provincia').value = turno.pacienteDTO.domicilioDTO.provincia;
              document.querySelector('#odontologo_id').value = turno.odontologoDTO.id;
              document.querySelector('#nombreO').value = turno.odontologoDTO.nombre;
              document.querySelector('#apellidoO').value = turno.odontologoDTO.apellido;
              document.querySelector('#matriculaO').value = turno.odontologoDTO.matricula;

              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }