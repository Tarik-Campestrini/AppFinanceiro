
$(document).ready(function() {
	
	$('.select').on('change', function(){
		var selectValor = $(this).val();	
		var mes = selectValor;
		$("#mes1").html(mes);
		window.location.href =('/home/entradas?mes='+mes);
		
	});
	
	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');
		var text = $(this).text();
		if (text == 'edit') {
			$.get(href, function(entrada) {
				
				$('.myForm #id').val(entrada.id);
				$('.myForm #descricao').val(entrada.descricao);
				$('.myForm #tipo').val(entrada.tipo);
				$('.myForm #valor').val(entrada.valor);
				$('.myForm #data').val(entrada.data);
				$('.myForm #status').val(entrada.status);
				
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

