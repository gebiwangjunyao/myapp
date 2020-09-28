package rutine;

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

/**
 * Servlet implementation class ShowRutine
 */
@WebServlet("/ShowRutine")
public class ShowRutine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRutine() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DAO.createEntityManager();
        Integer rutineId= Integer.parseInt(request.getParameter("rutineId"));
        List<Rutine> thisShareRutine= em.createNamedQuery("getThisShareRutine", Rutine.class).setParameter("rutineId", rutineId).getResultList();
        if(thisShareRutine.size()>0){

            List<Quest>quests= (ArrayList<Quest>) em.createNamedQuery("getQuest", Quest.class).setParameter("rutineId", rutineId).getResultList();
            Rutine thisRutine= em.find(Rutine.class, rutineId);
            em.close();
            request.setAttribute("thisRutine", thisRutine);
            request.setAttribute("quests", quests);

        }else{
            em.close();

            request.getSession().setAttribute("flush", "データみつかりませんでした");

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ShowRutine.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
