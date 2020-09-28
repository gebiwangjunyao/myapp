package user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dta.DAO;
import dta.EncryptUtil;
import dto.User;
import dto.UserInputCheck;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DAO.createEntityManager();

            User u= new User();

            u.setName(request.getParameter("name"));
            u.setPassword(EncryptUtil.getPasswordEncrypt(
                    request.getParameter("password"),
                    (String)this.getServletContext().getAttribute("pepper")));

            Timestamp time = new Timestamp(System.currentTimeMillis());
            u.setAdmin_flag(0);
            u.setCreated_at(time);
            u.setUpdated_at(time);
            u.setDelete_flag(0);

            List<String> errors = UserInputCheck.check(u,true,true);

            if(errors.size()>0){

                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/NewUser.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/NewUser");
            }
        }
    }

}
