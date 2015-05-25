package mvc.controller;

import java.io.IOException;

import mvc.model.dao.DaoFactory;
import mvc.model.dao.ModelDao;
import mvc.model.network.Network;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 * Created by Полина on 22.05.2015.
 */
public class CreationServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

   @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
       try {
           ModelDao modelDao = DaoFactory.getModelDao();
           HttpSession session = request.getSession();
           String type = request.getParameter("typeOfNewElement");
           session.setAttribute("typeOfNew", type);

           Network net = new Network();
           switch (type) {
               case "pc":
                   modelDao.readPc(modelDao.createPc(), net);
                   RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                   dispatcher.forward(request, response);
                   break;
               case "cable":
                   modelDao.readCable(modelDao.createCable(), net);
                   dispatcher = request.getRequestDispatcher("/index.jsp");
                   dispatcher.forward(request, response);
                   break;
               case "firewall":
                   modelDao.readFirewall(modelDao.createFirewall(), net);
                   dispatcher = request.getRequestDispatcher("/index.jsp");
                   dispatcher.forward(request, response);
                   break;
               case "hub":
                   modelDao.readHub(modelDao.createHub(), net);
                   dispatcher = request.getRequestDispatcher("/index.jsp");
                   dispatcher.forward(request, response);
                   break;
               case "route":
                   modelDao.readRoute(modelDao.createRoute(), net);
                   dispatcher = request.getRequestDispatcher("/index.jsp");
                   dispatcher.forward(request, response);
                   break;
               case "switch":
                   modelDao.readSwitch(modelDao.createSwitch(), net);
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
           dispatcher.forward(request, response);}
   }
}
