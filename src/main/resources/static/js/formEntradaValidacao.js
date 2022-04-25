$(document).ready(function() {
	$("#formEntrada").validate({

		rules: {
			
			descricao: {
				required: true,
				minlength: 3,
				maxlength: 32
			},
			tipo: {
				required: true,
				minlength: 3,
				maxlength: 10				 
			},
			valor: {
				required: true,
				minlength: 2,
				maxlength: 10				 
			},
			data: {
				required: true,
				minlength: 3,
				maxlength: 10				 
			},
			Status: {
				required: true,
				minlength: 3,
				maxlength: 10				 
			}
			
		}
	})
})