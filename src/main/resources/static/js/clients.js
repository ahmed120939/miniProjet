function deleteClient(cin) {

	$.ajax({
		url: "/clients/compteByCin",
		data: { 'cin': cin },
		method: 'POST',
		success: function(response) {
			if (response == "") {
				swal({
					title: "Vous etes sur?",
					text: "Ce client va etre supprimer !!!!",
					icon: "warning",
					buttons: true,
					dangerMode: true,
				})
					.then((willDelete) => {
						if (willDelete) {
							$.ajax({
								url: "/clients/delete-ajax",
								data: { 'cin': cin },
								method: 'POST',
								success: function() {
									$("#" + cin).remove();
									swal("Votre client est supprimer!", {
										icon: "success",
									});
								}
							});
						} else {
							swal("Votre client est protéger!");
						}
					});
			}
			else {
				swal({
					title: "Impossible!!",
					text: "Ce client est déja attaché a un compte, impossible de supprimer!",
					icon: "warning",
					dangerMode: true,
				})

			}
		}
	});

}