
$(document).ready(function() {

	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');
		var text = $(this).text();
		if (text == 'edit') {
			$.get(href, function(usuario) {

				$('.myForm #id').val(usuario.id);
				$('.myForm #nome').val(usuario.nome);
				$('.myForm #sobrenome').val(usuario.sobrenome);
				$('.myForm #email').val(usuario.email);
				$('.myForm #senha').val('');


			});
			
			$('.myForm #exampleModal').modal('show');
		} else {
			$('.myForm2 #descricao').val('');
			$('.myForm2 #tipo').val('');
			$('.myForm2 #valor').val('');
			$('.myForm2 #data').val('');
			$('.myForm2 #status').val('');
			$('.myForm2 #exampleModal').modal('show');

		}


	});

	$('.table .delBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #delRef').attr('href', href);
		$('#myModal').modal('show');
	});

});

