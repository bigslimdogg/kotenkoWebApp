package mvc.controller;

        import mvc.model.dao.ConnectionDao;
        import mvc.model.dao.DaoFactory;
        import mvc.model.dao.ModelDao;
        import mvc.model.network.Network;
        import mvc.model.pe_model.PathElement;
        import mvc.model.route_providers.RouteProviderWithLessPrice;

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
public class RouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id1 = Integer.valueOf(request.getParameter("id1"));
        int id2 = Integer.valueOf(request.getParameter("id2"));
        String command = request.getParameter("search");
        Network net = new Network();
        ModelDao modelDao = DaoFactory.getModelDao();
        HttpSession session = request.getSession();
        PathElement element1 = null;
        PathElement element2 = null;
        try {
            element1 = modelDao.findElement(id1, net);
            element2 = modelDao.findElement(id2, net);
        } catch (SQLException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);;
        }
        session.setAttribute("element1", element1);
        session.setAttribute("element2", element2);
        session.setAttribute("command", command);
    }
}

