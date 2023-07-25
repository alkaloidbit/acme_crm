<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${ page_name }</title>

  <link rel="icon" type="image/ico" href="mspr.ico" />
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- Datatables -->
<link rel="stylesheet" href="resources/AdminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="resources/AdminLTE/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet" href="resources/AdminLTE/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4@5.0.15/bootstrap-4.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="resources/AdminLTE/dist/css/adminlte.min.css">
</head>
<body>
  <div class="wrapper">
    <%@ include file="partials/navbar.jsp"%>
    <%@ include file="partials/main_sidebar.jsp"%>
    <div class="content-wrapper">
      <%@ include file="partials/content_header.jsp"%>
      <%@ include file="partials/main_frame.jsp" %>
    </div>
    <%@ include file="partials/control_sidebar.jsp"%>
    <%@ include file="partials/footer.jsp"%>
  </div>
  <!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="resources/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="resources/AdminLTE/plugins/jszip/jszip.min.js"></script>
<script src="resources/AdminLTE/plugins/pdfmake/pdfmake.min.js"></script>
<script src="resources/AdminLTE/plugins/pdfmake/vfs_fonts.js"></script>
<script src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script src="resources/AdminLTE/plugins/sweetalert2/sweetalert2.all.min.js"></script>

<!-- AdminLTE App -->
<script src="resources/AdminLTE/dist/js/adminlte.min.js"></script>
<script>
(function (window, $, swal) {
    $("#example1").DataTable({
      "ordering": false,
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');

    /*
    const deleteUser = function ($link) {
	let deleteUrl = $link.attr('href');
	return $.ajax({
	  url: deleteUrl,
	  method: 'DELETE'
	  }).then(
	    function(res) {
	      console.log(res);
	      Swal.fire('Suppression effectuée!', '', 'success')
	      $link.closest('tr').fadeOut();
	      $link.remove();
	    },
	    function(res) {
	      console.log(res.responseText.resultat);
	      Swal.fire( res.responseText.resultat, '', 'info')
	      console.log(res);
	    }
	  );
      };

      $('.btn-delete').on("click", function(e) {
	e.preventDefault();
	var $link = $(e.currentTarget);
	Swal.fire({
	  title: 'Etes-vous sur de vouloir supprimer ce client ?',
	  showDenyButton: true,
	  showCancelButton: true,
	  confirmButtonText: 'Confirmer',
	  denyButtonText: `Annuler`,
	}).then(
	  function (result) {
	    return deleteUser($link);
	  },
	  function () {
	      Swal.fire('Suppression annulée', '', 'info')
	  }
	);
    });
    */

    $('.btn-delete').on("click", function(e) {
      e.preventDefault();
      var $link = $(e.currentTarget);
      let deleteUrl = $link.attr('href');
      Swal.fire({
	title: 'Etes-vous sur de vouloir supprimer ce client ?',
	showCancelButton: true,
	confirmButtonText: 'Confirmer',
	showLoaderOnConfirm: true,
	preConfirm: () => {
	  var myInit = { method: 'DELETE'};
	  return fetch(deleteUrl, myInit)
	    .then((response) => response.json())
	    .then((data) => {
	      console.log(data);
	    });
	},
	allowOutsideClick: () => !Swal.isLoading()
      }).then((result) => {
	console.log
	if (result.isConfirmed) {
	  Swal.fire({
	    title: result.value
	  })
	}
      })
    });
  })(window, jQuery, swal);
</script>
</body>
</html>
