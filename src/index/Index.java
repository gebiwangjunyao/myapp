package index;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("_token", request.getSession().getId());


        EntityManager em = DAO.createEntityManager();

        Integer rutineId= (Integer) request.getSession().getAttribute("rutineId");
        Rutine rutine= new Rutine();
        ArrayList<Quest> quests= new ArrayList<Quest>();
        if(rutineId!=null){
            rutine= em.find(Rutine.class,rutineId);
            quests= (ArrayList<Quest>) em.createNamedQuery("getQuest", Quest.class).setParameter("rutineId", rutineId).getResultList();
        }
        User user = (User)request.getSession().getAttribute("LoginUser");
        List<Rutine> rutines= em.createNamedQuery("getMyRutines", Rutine.class).setParameter("id", user.getId()).getResultList();
        List<Rutine> myShareRutines= em.createNamedQuery("getMyShareRutines", Rutine.class).setParameter("id", user.getId()).getResultList();



        em.close();




        request.setAttribute("myShareRutines", myShareRutines);
        request.setAttribute("count", quests.size());
        request.setAttribute("thisRutine", rutine);
        request.setAttribute("quests", quests);
        request.setAttribute("rutines", rutines);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Index.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            Integer rutineId = Integer.parseInt(request.getParameter("rutineId"));


            request.getSession().setAttribute("rutineId", rutineId);
            response.sendRedirect(request.getContextPath() + "/Index");
        }
    }
}
