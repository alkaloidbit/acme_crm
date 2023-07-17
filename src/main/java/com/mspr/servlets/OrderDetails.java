package com.mspr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.mspr.beans.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/order_details")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPrenom("Fred");
		utilisateur.setNom("Oudjoudi");
		utilisateur.setActif(true);
		request.setAttribute("utilisateur", utilisateur);
		request.setAttribute("page_name", "Order Details");
		request.setAttribute("page_content", "content_invoice");
		this.getServletContext().getRequestDispatcher("/WEB-INF/order_details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
