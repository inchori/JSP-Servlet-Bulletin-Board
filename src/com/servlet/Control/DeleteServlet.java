package com.servlet.Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.DAO.BBSDAO;
import com.servlet.DTO.BBSDTO;

public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		try {
			BBSDAO bbsDAO = new BBSDAO();
			//System.out.println(bbsID);
			bbsDAO.delete(bbsID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
