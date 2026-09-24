package vn.iotstar.dao;


import jakarta.persistence.*;

import vn.iotstar.entity.User;
import vn.iotstar.util.JPAUtil;


public class UserDAO {


    public User login(String email,String password){


        EntityManager em =
                JPAUtil.getEntityManager();


        try {


            return em.createQuery(
                "SELECT u FROM User u WHERE u.email=:email AND u.passwd=:password",
                User.class)

                .setParameter("email", email)
                .setParameter("password", password)

                .getSingleResult();



        }catch(Exception e){

            return null;

        }finally{

            em.close();

        }


    }

}