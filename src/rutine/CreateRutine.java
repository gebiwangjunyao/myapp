package rutine;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dta.DAO;
import dto.Rutine;
import dto.User;

/**
 * Servlet implementation class CreaterRutine
 */
@WebServlet("/CreateRutine")
public class CreateRutine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRutine() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DAO.createEntityManager();


            Rutine r= new Rutine();


            User u=em.find(User.class,request.getSession().getAttribute("id"));

            r.setUser_id(u);
            r.setRutine(request.getParameter("rutine"));
            r.setShareCheck(Integer.parseInt(request.getParameter("share")));

            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "登録が完了しました。");

            response.sendRedirect(request.getContextPath() + "/NewRutine?id="+request.getSession().getAttribute("id"));
        }
    }

}
