function showErrorMessage(message) {
  swal({
  					title: "Impossible!!",
  					text: "invalid login et mot de passe!",
  					icon: "warning",
  					dangerMode: true,
  				})
}


function loginUser(login,password) {
	$.ajax({
		url: "/utilisateur/login",
		data: { 'login': login,
		'password': password

		 },
		method: 'POST',
		success: function(response) {
		alert(response)
			if (response == true) {
				swal({
					title: "Vous etes sur?",
					text: "Ce client va etre supprimer !!!!",
					icon: "warning",
					buttons: true,
					dangerMode: true,
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


