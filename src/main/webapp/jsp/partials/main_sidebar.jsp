  <%@ page language="java" contentType="text/html; charset=UTF-8" import="acme.front.AuthentificationBean"
    pageEncoding="UTF-8"%>
  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
      <img src="resources/images/AdminLTELogo.png" alt="Acme Station Logo" class="brand-image img-rounded elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">Station v1.0</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <!-- <div class="image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div> -->
        <div class="info">
          <a href="#" class="d-block">
            <i class="fas fa-user"></i> ${authentification.getLogin()}</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <% AuthentificationBean ab = (AuthentificationBean) session.getAttribute("authentification"); %>
          <li class="nav-item">
            <a href="./Commande?parametre=menuCommande" class="nav-link">
              <i class="nav-icon fas fa-file-invoice"></i>
              <p>
                Commande
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="./Client" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
                Client
                <!-- <span class="right badge badge-danger">New</span> -->
              </p>
            </a>
          </li>
          <% if(ab.hasPermissionToReadProduct()){ %>
	          <li class="nav-item">
	            <a href="./Produits" class="nav-link">
	              <i class="nav-icon fas fa-book"></i>
	              <p>
	                Produit
	              </p>
	            </a>
	          </li>
		  <%}%> 
          <% if(ab.hasPermissionToReadStats()){ %>
	          <li class="nav-item">
	            <a href="./Statistique" class="nav-link">
	              <i class="nav-icon fas fa-chart-bar"></i>
	              <p>
	                Statistique
	              </p>
	            </a>
	          </li>	
         <%}%>			
         <% if(ab.hasPermissionToReadUser()){ %>
 	          <li class="nav-item">
	            <a href="./Utilisateur" class="nav-link">
	              <i class="nav-icon fas fa-user-alt"></i>
	              <p>
	                Utilisateur
	              </p>
	            </a>
	          </li>
			<%}%> 
          <li class="nav-item">
            <a href="./Authentification?parametre=menuDeconnexion" class="nav-link">
              <i class="nav-icon fas fa-cog"></i>
              <p>
                Déconnexion
              </p>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
