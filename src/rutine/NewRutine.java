package rutine;

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
import dto.Rutine;
import dto.User;

/**
 * Servlet implementation class NewRutine
 */
@WebServlet("/NewRutine")
public class NewRutine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRutine() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        EntityManager em = DAO.createEntityManager();




        User u=(User) request.getSession().getAttribute("LoginUser");
        List<Rutine> myRutines= em.createNamedQuery("getMyRutines", Rutine.class).setParameter("id", u.getId()).getResultList();
        List<Rutine> shareRutines= em.createNamedQuery("getMyShareRutines", Rutine.class).setParameter("id", u.getId()).getResultList();
        em.close();


        request.getSession().setAttribute("id", u.getId());
        request.setAttribute("myRutines", myRutines);
        request.setAttribute("shareRutines", shareRutines);
        request.setAttribute("_token", request.getSession().getId());
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/NewRutine.jsp");
        rd.forward(request, response);
    }

}
