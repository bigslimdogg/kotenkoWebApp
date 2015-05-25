package mvc.controller;

import mvc.model.dao.ConnectionDao;
import mvc.model.dao.DaoFactory;
import mvc.model.dao.ModelDao;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;
import mvc.model.route_providers.RouteProviderWithLessPrice;
import sun.nio.ch.Net;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Полина on 22.05.2015.
 */
@WebServlet(name = "ConnectionServlet")
public class InformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("idForInfo"));
        Network net = new Network();
        ModelDao modelDao = DaoFactory.getModelDao();
        HttpSession session = request.getSession();
        PathElement elementToShow = null;
        try {
            elementToShow = modelDao.findElement(id, net);
        } catch (SQLException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);;
        }
        session.setAttribute("elementToShow", elementToShow);


    }
}

