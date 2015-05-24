package mvc.controller;

import mvc.model.dao.ConnectionDao;
import mvc.model.dao.DaoFactory;

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
public class ConnectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ConnectionDao connectionDao = DaoFactory.getConnectionDao();
            HttpSession session = request.getSession();
            int id1 = Integer.valueOf(request.getParameter("id1"));
            session.setAttribute("id1", id1);
            int id2 = Integer.valueOf(request.getParameter("id2"));
            session.setAttribute("id2", id2);
            String command = request.getParameter("command");
            session.setAttribute("command", command);
            switch (command) {
                case "disconnect":
                    connectionDao.deleteConnections(id1, id2);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "connect":
                    connectionDao.createConnection(id1, id2);
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }catch (Exception e){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);;
        }


    }
}
