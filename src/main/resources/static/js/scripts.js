//função que seta o mes na url (entradas, saidas)
$(document).ready(function () {
			var data = new Date();
			var mesAtual = String(data.getMonth() + 1).padStart(2, '0');
			$('#mesAtualEnt').attr('href', '/home/entradas?mes=' + mesAtual);
			$('#mesAtualSai').attr('href', '/home/saidas?mes=' + mesAtual);
		});

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
