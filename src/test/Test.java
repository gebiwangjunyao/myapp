package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dta.DAO;
import dto.Quest;

public class Test {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        EntityManager em = DAO.createEntityManager();
        List<Quest> qs= em.createNamedQuery("getQuests", Quest.class).getResultList();
        em.close();

        int startTime=201;
        int endTime=202;


        ArrayList<Integer> start=new ArrayList<Integer>();
        ArrayList<Integer> end=new ArrayList<Integer>();
        for(Quest b:qs){
            start.add(Integer.parseInt(b.getStartTime().replace(":","")));
            end.add(Integer.parseInt(b.getEndTime().replace(":","")));
        }

        int i=0;
        String ans= "maru";
        for(Integer d:start){
            if(startTime<d&&endTime>d||startTime>end.get(i)&&endTime<end.get(i)||startTime==d||endTime==end.get(i)||startTime>d&&endTime<end.get(i)){
                ans="batsu";
            }
            i++;
        }
        System.out.print(ans);
    }

}
