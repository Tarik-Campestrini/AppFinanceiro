$(document).ready(function() {
	$("#formUsuario").validate({

		rules: {
			nome: {
				required: true,
				minlength: 3,
				maxlength: 15			
			},
			sobrenome: {
				required: true,
				minlength: 3,
				maxlength: 32
			},
			email: {
				required: true,
				email: true 
			},
			senha: {
				required: true,
				minlength: 6,
				maxlength: 10			
				 
			},
			csenha: {
				required: true,
				equalTo: '#senha'			
				 
			}
		}
	})
});