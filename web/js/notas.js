/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener("DOMContentLoaded", function () {
	
    (async ()=>{
		let b = await initTable();
               
    $('#tableBody').on('dblclick', 'tr', function () {
         let table=$('#tableAlumnos').DataTable();
                  table.column( 0 ).visible( true );
    		console.log(table.row(this).data());
                setValues(table.row(this).data());
    	});
        }
)();
   let table=$('#tableAlumnos').DataTable();
    table.column( 0 ).visible( false);
});

async function initTable() {
    $('#tableAlumnos').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ Acuerdos",
            "zeroRecords": "No se encontraron resultados",
            "info": "",
            "infoEmpty": "",
            "infoFiltered": "(filtrado de un total de _MAX_ Acuerdos)",
            "sSearch": "Filtrar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "sProcessing": "Procesando..."
        },
        "lengthChange": false,
        "iDisplayLength": 5,
        "destroy": true,
        "searching": false

    });
}

function setValues(array){
     document.getElementById('grupo').value=array[0];
    document.getElementById('cedula').value=array[1];
    document.getElementById('name').value=array[2];
    document.getElementById('nota').value=array[3];
    $('#exampleModal').modal('show');
}

function closeModal(){
    
    let table=$('#tableAlumnos').DataTable();
    table.column( 0 ).visible( false);
     $('#exampleModal').modal('hide');
}

$("#modalForm").submit(function(e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var url = form.attr('action');

    $.ajax({
           type: "POST",
           url: url,
           data: form.serialize(), // serializes the form's elements.
           success: function(data,status,response)
           {
               	(async ()=>{
             bootbox.alert("Registro actualizado con Exito")
               console.log('Hola');
           }
           )();
              fetch('GruposServlet/updatePage')
                      .then(()=>window.location.reload(true) )
                      .catch(err=>console.log(err))
           },
           error:function(jqXHR, status,error){
               
               console.log(jqXHR);
               bootbox.alert("Ha Ocurrido un Error. Por favor intente de nuevo");
           }
         });


});


