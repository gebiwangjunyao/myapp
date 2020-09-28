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

/**
 * Servlet implementation class UpdateRutine
 */
@WebServlet("/UpdateRutine")
public class UpdateRutine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRutine() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DAO.createEntityManager();
            Integer rutineId= Integer.parseInt(request.getParameter("rutineId"));
            Rutine data= em.find(Rutine.class, rutineId);

            Integer data0=data.getShareCheck();
            if(data0==0){
                data.setShareCheck(1);

            }else{
            data.setShareCheck(0);
            }
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/NewRutine");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
