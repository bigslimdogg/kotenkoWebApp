package mvc.controller;

import mvc.model.dao.DaoFactory;
import mvc.model.dao.ModelDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Полина on 22.05.2015.
 */
@WebServlet(name = "deleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelDao modelDao = DaoFactory.getModelDao();
        HttpSession session = request.getSession();
        int id = Integer.valueOf(request.getParameter("idOfDeletedElement"));
        session.setAttribute("idToDelete", id);
        String type = request.getParameter("typeOfDeletedElement");
        session.setAttribute("typeToDelete", type);

        switch (type){
            case "pc":
                modelDao.deletePc(id);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "cable":
                modelDao.deleteCable(id);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "firewall":
                modelDao.deleteFirewall(id);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "hub":
                modelDao.deleteHub(id);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "route":
                modelDao.deleteRoute(id);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "switch":
                modelDao.deleteSwitch(id);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
                break;

        }
    }
}
