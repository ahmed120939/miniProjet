$(document).ready(function() {
    $('#popUpSollicite').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var congeId = button.data('conge-id'); // Extract info from data-* attributes

        // Use congeId in your update function or set it in a hidden field in the modal
        console.log("Congé ID:", congeId);

        // Optionally, store the congeId in a hidden input field in the modal
        $('#congeIdInput').val(congeId);
    });

       $('#popUpSolliciteEmploye').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var congeId = button.data('conge-id'); // Extract info from data-* attributes

            // Use congeId in your update function or set it in a hidden field in the modal
            console.log("Congé ID:", congeId);

            // Optionally, store the congeId in a hidden input field in the modal
            $('#congeIdInput').val(congeId);
        });

        $('#popUpArretEmploye').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var congeId = button.data('conge-id'); // Extract info from data-* attributes

                    // Use congeId in your update function or set it in a hidden field in the modal
                    console.log("Congé ID:", congeId);

                    // Optionally, store the congeId in a hidden input field in the modal
                    $('#congeIdInput').val(congeId);
                });


        pop_up_ArretEmploye
});




function findAllCongeByYears(year) {
	document.location.href = "/conge/findAllConge?year="+year;
}
function findAllCongeByEtat(etat) {
	document.location.href = "/conge/findAllConge?etat="+etat;
}

function findByEmploye(searchEmploye) {
document.location.href = "/conge/findAllConge?employe="+searchEmploye;
}

function verifSoldeConge(idEmploye) {

$.ajax({
		url: "/conge/verifSoldeConge/"+idEmploye,
		data: {},
		method: 'GET',
		success: function(response) {
			if (response == true) {

 swal({
   					title: "Impossible!!",
   					text: "Solde conge insuffisant !",
   					icon:  "warning",
   					dangerMode: false,
   				})
			}
			else {

			document.location.href = "/conge/detailsConge";
			}
		}
	});


}

function verifDate(date_debut,date_fin){
date_debut=date_debut.value;
date_fin=date_fin.value;


 let startDate = new Date(date_debut);
            let endDate = new Date(date_fin);

            // Calculer la différence en millisecondes
            let differenceInTime = endDate.getTime() - startDate.getTime();

            // Convertir la différence en jours
            let differenceEntreDate = differenceInTime / (1000 * 3600 * 24);


 nbCongeRest=$('#nbrestant').val();

if(nbCongeRest<differenceEntreDate){
swal({
   					title: "Impossible!!",
   					text: "Tu as Depasse nombres conges autorise !",
   					icon:  "warning",
   					dangerMode: false,
   				});
			}

if(date_debut>date_fin){

swal({
   					title: "Impossible!!",
   					text: "Date debut doit etre inferieur à date fin !",
   					icon:  "warning",
   					dangerMode: false,
   				});
			}


}




function updateEtatSollicite() {
   // Récupérer la valeur du bouton radio sélectionné
    var etat = $('input[name="gridRadios"]:checked').val();
        id=$('#congeIdInput').val();

if(etat == undefined){
etat="ANNULE"}

    if (etat) {

        $.ajax({
            url: '/conge/updateEtatSollicite/'+id+"?etat="+etat,
            method: 'POST',
            data: {},
            success: function(response) {
                // Traitement de la réponse
                console.log("Update successful:", response);
                // Fermer le modal si nécessaire
                $('#exampleModal').modal('hide');
                document.location.href = "/conge/findAllConge";

            },
            error: function(error) {
                // Traitement des erreurs
                console.error("Update failed:", error);
            }
        });
    } else {
        console.warn("Aucun bouton radio sélectionné.");
    }
}



function updateEtatArret() {

etat="ARRETE";
 id=$('#congeIdInput').val();
    if (etat) {

        $.ajax({
            url: '/conge/updateEtatSollicite/'+id+"?etat="+etat,
            method: 'POST',
            data: {},
            success: function(response) {
                // Traitement de la réponse
                console.log("Update successful:", response);
                // Fermer le modal si nécessaire
                $('#exampleModal').modal('hide');
                document.location.href = "/conge/findAllConge";

            },
            error: function(error) {
                // Traitement des erreurs
                console.error("Update failed:", error);
            }
        });
    } else {
        console.warn("Aucun bouton radio sélectionné.");
    }
}

