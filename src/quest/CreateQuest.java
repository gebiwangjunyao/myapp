package quest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dta.DAO;
import dto.Quest;
import dto.Rutine;

/**
 * Servlet implementation class CreateQuset
 */
@WebServlet("/CreateQuest")
public class CreateQuest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            int ans= 0;
            EntityManager em = DAO.createEntityManager();


            Integer rutineId =(Integer) request.getSession().getAttribute("rutineId");
            List<Quest> qs= em.createNamedQuery("getQuest", Quest.class).setParameter("rutineId", rutineId).getResultList();
            try{
                Integer startTime=Integer.parseInt(request.getParameter("startTime").replace(":",""));
                Integer endTime=Integer.parseInt(request.getParameter("endTime").replace(":",""));
                if(startTime>endTime){
                    ans=1;
                }else{



                String quest= request.getParameter("quest");
                if(quest.equals("")){
                    ans=1;
                }else{
                    ArrayList<Integer> start=new ArrayList<Integer>();
                    ArrayList<Integer> end=new ArrayList<Integer>();
                    for(Quest b:qs){
                        start.add(Integer.parseInt(b.getStartTime().replace(":","")));
                        end.add(Integer.parseInt(b.getEndTime().replace(":","")));
                    }

                    int i=0;
                    for(Integer d:start){
                        if(startTime<d&&endTime>d||startTime>end.get(i)&&endTime<end.get(i)||startTime==d||endTime==end.get(i)||startTime>d&&endTime<end.get(i)){
                            ans=1;
                        }
                        i++;
                    }
                }
                }




            }catch(Exception e){
                ans=1;
            }

            if(ans==0){
                Quest q= new Quest();


                Rutine u=em.find(Rutine.class,request.getSession().getAttribute("rutineId"));

                q.setRutine_id(u);
                q.setQuest(request.getParameter("quest"));
                q.setStartTime(request.getParameter("startTime"));
                q.setEndTime(request.getParameter("endTime"));
                q.setContent(request.getParameter("content"));

                em.getTransaction().begin();
                em.persist(q);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "追加しました。");

                response.sendRedirect(request.getContextPath() + "/NewQuest");
            }else{
                em.close();

                request.getSession().setAttribute("flush", "追加が失敗しました。入力内容に不備があります。");

                response.sendRedirect(request.getContextPath() + "/NewQuest");

            }
        }

    }

}
