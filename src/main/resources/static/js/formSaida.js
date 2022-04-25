$(document).ready(function() {

	$('.select').on('change', function() {
		var selectValor = $(this).val();
		var mes = selectValor;
		$("#mes1").html(mes);
		window.location.href = ('/home/saidas?mes=' + mes);

	});

	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();
		
		var href = $(this).attr('href');
		var text = $(this).text();
		if (text == 'edit') {
			$.get(href, function(saida) {
				
				$('.myForm #id').val(saida.id);
				$('.myForm #descricao').val(saida.descricao);
				$('.myForm #tipo').val(saida.tipo);
				$('.myForm #valor').val(saida.valor);
				$('.myForm #data').val(saida.data);
				$('.myForm #status').val(saida.status);

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

