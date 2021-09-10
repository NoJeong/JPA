package hellojpa;

import org.hibernate.mapping.PrimaryKey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class hellojpa {
    public static void main(String[] args) {
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        //code
        try {

//            Member findMember = em.find(Member.class, 1L);
//콘솔에 찍어보기
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//값 바꾸기
//            findMember.setName("HelloJPA");
//전체 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)//1번부터 10개 가져와
                    .getResultList();
//iter 적으면 자동생성
            for (Member member : result) {
                   //sout 적으면 자동 생성
                System.out.println("member = " + member.getName());

            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
