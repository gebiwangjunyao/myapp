package dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dta.DAO;

public class UserInputCheck {
    public static List<String> check(User u,Boolean nameCheck,Boolean passwordCheck){
        List<String> errors= new ArrayList<String>();

        String nameError= checkName(u.getName(),nameCheck);
        if(!nameError.equals("")){
            errors.add(nameError);
        }

        String passwordError= checkPassword(u.getPassword(),passwordCheck);
        if(!passwordError.equals("")){
            errors.add(passwordError);
        }
        return errors;
    }


    private static String checkName(String name,Boolean nameCheck){
        if(name==null||name.equals("")){
            return "ユーザー名を入力してください";
        }

        if(nameCheck){
            EntityManager em = DAO.createEntityManager();

            long checkUser= (long)em.createNamedQuery("checkUser",Long.class).setParameter("name", name).getSingleResult();

            em.close();
            if(checkUser>0){
                return "ユーザー名存在していました";
            }
        }
        return "";
    }

    private static String checkPassword(String password,Boolean passwordCheck){
        if(password==null||password.equals("")){
            return "パスワードを入力してください";
        }
        return "";
    }
}
