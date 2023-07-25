<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${ page_name }</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css" integrity="sha512-3PN6gfRNZEX4YFyz+sIyTF6pGlQiryJu9NlGhu9LrLMQ7eDjNgudQoFDK3WSNAayeIKc6B8WXXpo4a7HqxjKwg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Datatables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
<!-- Theme style -->
<!-- <link rel="stylesheet" href="dist/css/adminlte.min.css"> -->

<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">
<script language="javascript">
function loadGauche() {
	parent.frames['leftFrame'].window.location='jsp/gauche.jsp';
	return true;
}

</script>
</head>
<body onload="loadGauche()">
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
<!-- <script src="plugins/jquery/jquery.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<!-- <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
      crossorigin="anonymous"></script>
<!-- DataTables -->
<script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
<!-- AdminLTE App -->
<!-- <script src="dist/js/adminlte.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
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
<script src="resources/AdminLTE/plugins/sweetalert2/sweetalert2.all.min.js"></script>

<!-- AdminLTE App -->
<script src="resources/AdminLTE/dist/js/adminlte.min.js"></script>

<script>
  $(function () {
    const deleteProduct = function ($link) {
      $link.addClass('text-danger');
      $link.find('.fas')
        .removeClass('fa-trash')
        .addClass('fa-spinner')
        .addClass('fa-spin');

      let deleteUrl = $link.attr('href');
      let $row = $link.closest('tr');

      $.ajax({
        url: deleteUrl,
        method: 'DELETE',
        dataType: 'json',
        success: function(response) {
          if (response.resultat === 'success') {
            // Suppression réussie
            $row.fadeOut(function () {
              $row.remove();
            });
            Swal.fire('Suppression effectuée!', '', 'success');
          } else {
            // Suppression échouée
            $link.removeClass('text-danger');
            $link.find('.fas')
              .removeClass('fa-spinner')
              .removeClass('fa-spin')
              .addClass('fa-trash');
            Swal.fire('Erreur de suppression', 'Le produit est peut-être présent dans une commande', 'error');
          }
        },
        error: function(xhr, status, error) {
          // Erreur lors de la requête AJAX
          $link.removeClass('text-danger');
          $link.find('.fas')
            .removeClass('fa-spinner')
            .removeClass('fa-spin')
            .addClass('fa-trash');
          Swal.fire('Erreur', 'Une erreur est survenue lors de la suppression.', 'error');
        }
      });
    };

    $('.btn-delete').on("click", function(e) {
      e.preventDefault();
      var $link = $(e.currentTarget);
      Swal.fire({
        title: 'Êtes-vous sûr de vouloir supprimer ce produit ?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Confirmer',
        denyButtonText: 'Annuler',
      }).then((result) => {
        if (result.isConfirmed) {
          deleteProduct($link);
        } else if (result.isDenied) {
          Swal.fire('Suppression annulée', '', 'info');
        }
      });
    });
  });
</script>

</body>
</html>
