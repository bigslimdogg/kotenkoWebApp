package mvc.controller;

import mvc.model.dao.DaoFactory;
import mvc.model.dao.DataDao;

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
@WebServlet(name = "DataServlet")
public class DataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DataDao dataDao = DaoFactory.getDataDao();
            HttpSession session = request.getSession();
            String type = request.getParameter("typeData");
            session.setAttribute("typeData", type);
            String attribute = request.getParameter("attributeOfData");
            session.setAttribute("attributeOfData", attribute);
            switch (type) {
                case "cable_type":
                    dataDao.addTypeOfCable(attribute);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "ip":
                    dataDao.addIp(attribute);
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "wrong_ips":
                    dataDao.addWrongIp(attribute);
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request, response);
            }
        }catch (SQLException e){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
