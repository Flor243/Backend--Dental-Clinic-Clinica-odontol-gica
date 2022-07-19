window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontologos del JSON
            let turnosLista = data;
         for(turno of turnosLista){
            //por cada odontologo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.idTurno;
            turnoRow.id = tr_id;

            //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un odontologo
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.idTurno + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.idTurno+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar al odontologo que queremos
            //modificar y mostrar los datos del mismo en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.idTurno + '\"' +
                                      ' type="button" onclick="findTurnoBy('+turno.idTurno+')" class="btn btn-info btn_id">' +
                                      turno.idTurno +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del odontologo
            //como ultima columna el boton eliminar
            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td class=\"td_hora\">' + turno.hora + '</td>' +
                    '<td class=\"td_idP\">' + turno.pacienteDTO.nombre +", "+ turno.pacienteDTO.apellido+ '</td>' +
                    '<td class=\"td_idO\">' + turno.odontologoDTO.nombre +" "+ turno.odontologoDTO.matricula+ '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnoListar.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })