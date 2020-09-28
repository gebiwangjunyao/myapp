package quest;

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
import dto.Quest;
import dto.Rutine;
import dto.User;

/**
 * Servlet implementation class NewQuest
 */
@WebServlet("/NewQuest")
public class NewQuest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewQuest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        EntityManager em = DAO.createEntityManager();

        Integer rutineId =(Integer) request.getSession().getAttribute("rutineId");
        if(rutineId!=null){
        List<Quest> quests= em.createNamedQuery("getQuest", Quest.class).setParameter("rutineId", rutineId).getResultList();
        Rutine rutine=em.find(Rutine.class, rutineId);

        request.setAttribute("rutine", rutine.getRutine());
        request.setAttribute("quests", quests);
        }
        User user = (User)request.getSession().getAttribute("LoginUser");
        List<Rutine> rutines= em.createNamedQuery("getMyRutines", Rutine.class).setParameter("id", user.getId()).getResultList();
        em.close();
        request.setAttribute("rutines", rutines);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/NewQuest.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            Integer rutineId = Integer.parseInt(request.getParameter("rutineId"));


            request.getSession().setAttribute("rutineId", rutineId);
            response.sendRedirect(request.getContextPath() + "/NewQuest");
        }
    }

}
