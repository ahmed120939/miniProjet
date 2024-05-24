function deleteCompte(rib) {

	swal({
		title: "Vous etes sur?",
		text: "Vous ne pouvez pas récupérer votre compte!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				$.ajax({
					url: "/comptes/delete-ajax",
					data: { 'rib': rib },
					method: 'POST',
					success: function() {
						$("#" + rib).remove();
						swal("Votre compte est supprimer avec succès!", {
							icon: "success",
						});
					}
				});
			} else {
				swal("Votre compte est protéger!");
			}
		});
}