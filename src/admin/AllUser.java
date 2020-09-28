package admin;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dta.DAO;
import dto.User;

/**
 * Servlet implementation class AllUser
 */
@WebServlet("/AllUser")
public class AllUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DAO.createEntityManager();

        List<User> users= em.createNamedQuery("getUsers", User.class).getResultList();

        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("users", users);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/AllUser.jsp");
        rd.forward(request, response);
    }

}
