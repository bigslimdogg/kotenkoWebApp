package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import mvc.model.dao.DaoFactory;
import mvc.model.dao.ModelDao;
import mvc.model.models.*;
import mvc.model.my_exceptions.DaoException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 * Created by Полина on 22.05.2015.
 */
public class EditServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("idToEdit"));
        Network net = new Network();
        ModelDao modelDao = DaoFactory.getModelDao();
        PathElement model = null;
        try {
            model = modelDao.findElement(id, net);
        } catch (SQLException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);;
        }
        String comand = request.getParameter("command");
        String attribute = request.getParameter("atribute");
        try {
            switch (comand) {
                case "set_delay":
                    modelDao.updateDelay(model, Integer.valueOf(attribute));
                    model.setDelay(Integer.valueOf(attribute));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "set_info":
                    modelDao.updateInfo(model, attribute);
                    model.setInfo(attribute);
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "set_ip":
                    try {
                        modelDao.updateIp(model, attribute);
                        model.setIP(attribute);
                    } catch (DaoException e) {
                        dispatcher = request.getRequestDispatcher("/error.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "set_price":
                    modelDao.updatePrice(model, Double.valueOf(attribute));
                    model.setPrice(Double.valueOf(attribute));
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "set_notAllowedIp":
                    if (model instanceof Firewall) {
                        Firewall el = (Firewall) model;
                        try {
                            modelDao.updateIp(el, attribute);
                            el.setNotAllowedIP(attribute);
                        } catch (DaoException e) {
                            dispatcher = request.getRequestDispatcher("/error.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    } else
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                case "set_turnOn":
                    if (model instanceof Route) {
                        Route el = (Route) model;
                        modelDao.updateTurnOn(el, attribute);
                        if (Boolean.valueOf(attribute) == true)
                            el.turnON();
                        else
                            el.turnOFF();
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    } else
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                case "set_typeOfCable":
                    if (model instanceof Cable) {
                        Cable el = (Cable) model;
                        try {
                            modelDao.updateIp(el, attribute);
                            el.setType(TypeOfCable.valueOf(attribute));
                        } catch (DaoException e) {
                            dispatcher = request.getRequestDispatcher("/error.jsp");
                            dispatcher.forward(request, response);;
                            break;
                        }
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    } else
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                case "set_unitAmount":
                    if (model instanceof Switch) {
                        Switch el = (Switch) model;
                        modelDao.updateUnitAmount(el, Integer.valueOf(attribute));
                        el.setUnitAmount(Integer.valueOf(attribute));
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    if (model instanceof Hub) {
                        Hub el = (Hub) model;
                        modelDao.updateUnitAmount(el, Integer.valueOf(attribute));
                        el.setUnitAmount(Integer.valueOf(attribute));
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                    } else
                        dispatcher = request.getRequestDispatcher("/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                default:
                    dispatcher = request.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}

