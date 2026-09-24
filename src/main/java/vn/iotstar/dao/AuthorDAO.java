package vn.iotstar.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.iotstar.entity.Author;
import vn.iotstar.util.JPAUtil;

import java.util.List;



public class AuthorDAO {


    public List<Author> findAll(){


        EntityManager em =
                JPAUtil.getEntityManager();


        try{

            return em.createQuery(
                    "SELECT a FROM Author a",
                    Author.class
            ).getResultList();


        }finally{

            em.close();

        }

    }



    public Author findById(int id){


        EntityManager em =
                JPAUtil.getEntityManager();


        try{

            return em.find(Author.class,id);


        }finally{

            em.close();

        }

    }




    public void save(Author author){


        EntityManager em =
                JPAUtil.getEntityManager();


        EntityTransaction tr =
                em.getTransaction();



        try{


            tr.begin();



            if(author.getAuthor_id()==null){

                em.persist(author);

            }else{

                em.merge(author);

            }


            tr.commit();


        }finally{

            em.close();

        }


    }




    public void delete(int id){


        EntityManager em =
                JPAUtil.getEntityManager();


        EntityTransaction tr =
                em.getTransaction();



        try{


            tr.begin();


            Author a =
                    em.find(Author.class,id);


            if(a!=null){

                em.remove(a);

            }


            tr.commit();


        }finally{

            em.close();

        }


    }


}