package acme.back.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;




/**

* Servlet implementation class Produits

*/




public class Produits extends HttpServlet {

private static final long serialVersionUID = 1L;




public Produits() {

super();

// TODO Auto-generated constructor stub

}







protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String produits = "TOTO";

request.setAttribute("produits", produits);

getServletConfig().getServletContext().getRequestDispatcher("/jsp/produits.jsp").forward(request, response);

}







protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// TODO Auto-generated method stub

doGet(request, response);

}




}
