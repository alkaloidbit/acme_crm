<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <%@ include file="head.jsp" %>
<head>
<body>
  <%@ include file="top_menu.jsp" %>
  <div class="container-fluid">
    <div class="row"> 
      <%@ include file="lateral_menu.jsp" %>
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
          <h1 class="h2">Dashboard</h1>
          <div class="btn-toolbar mb-2 mb-md-0">
            <div class="btn-group mr-2">
              <button class="btn btn-sm btn-outline-secondary">Share</button>
              <button class="btn btn-sm btn-outline-secondary">Export</button>
            </div>
            <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
              <span data-feather="calendar"></span>
              This week
            </button>
          </div>
        </div>
        <h1>Employee Register Form:</h1>
        <div class="card">
          <div class="card-body">
            <form action="<%=request.getContextPath()%>/register" method="post">
              <div class="form-group row">
                <label for="firstName" class="col-sm-2 col-form-label">First
                Name</label>
                <div class="col-sm-7">
                <input type="text" class="form-control" name="firstName"
                  placeholder="Enter first name">
                </div>
              </div>

              <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Last
                Name</label>
                <div class="col-sm-7">
                <input type="text" class="form-control" name="lastName"
                  placeholder="Enter last name">
                </div>
              </div>

              <div class=" form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">User
                Name</label>
                <div class="col-sm-7">
                <input type="text" class="form-control" name="username"
                  placeholder="Enter user name">
                </div>
              </div>

              <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Passwrod</label>
                <div class="col-sm-7">
                <input type="password" class="form-control" name="password"
                  placeholder="Enter Password">
                </div>
              </div>

              <div class="form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-7">
                <input type="text" class="form-control" name="address"
                  placeholder="Enter Address">
                </div>
              </div>

              <div class="form-group row">
                <label for="contact" class="col-sm-2 col-form-label">Contact
                No</label>
                <div class="col-sm-7">
                <input type="text" class="form-control" name="contact"
                  placeholder="Enter Contact Address">
                </div>
              </div>

              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
          </div>
        </div>
      </main>

    </div>
 </div>
</body>
</html>
