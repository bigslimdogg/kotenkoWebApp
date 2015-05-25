package mvc.controller;

import mvc.model.dao.DaoFactory;
import mvc.model.dao.ModelDao;
import mvc.model.my_exceptions.AnsupportException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Nick on 21.05.2015.
 */
public class CreationServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            ModelDao modelDao = DaoFactory.getModelDao(DaoFactory.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String type = request.getParameter("typeOfNewElement");
        
        /*
        switch (type){
            case "pc":
                break;
            case "cable":
                break;
            case "firewall":
                break;
            case "hub":
                break;
            case "route":
                break;
            case "switch":
                break;
            default:
                System.out.println("такого типа не существует!");
        }
        */
    }
}
