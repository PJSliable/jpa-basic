package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code

        try{
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            findMember.setName("HelloJPA");

//
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }


//            //persist 한 뒤에 commit 할 때 쿼리를 날림
////            //비영속
//            Member memberA = new Member(11L, "A");
//            Member memberB = new Member(12L, "B");
//
//            //영속
//            System.out.println("Before");
//            em.persist(memberA);
//            em.persist(memberB);
//            System.out.println("After");


//            // 찾은 것을 제거했으나 제거 쿼리를 날리기 전이고 영속성 컨텍스트에는 남아있기에 member 에 대한 접근이 가능함
//            Member member = em.find(Member.class, 100L);
//
//            em.remove(member);
//            member.setName("D");
//            System.out.println("member.getName() = " + member.getName());


//            //1차 캐시에 존재하므로 DB에 접근하기 전에 find 할 수 있음.
//            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HELLOJPA");
//
//            //영속
//            System.out.println("BEFORE");
//            em.persist(member);
//            System.out.println("AFTER");
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());


            //영속성 컨텍스트에서 관리하여 일치를 보일 수 있음
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("result = " + (findMember1 == findMember2));


//            Member member = new Member(200L, "member");
//            em.persist(member);


//            영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화 하는 것.
//            flush 하더라도 1차 캐시(영속성 컨텍스트)는 유지됨. 비워지지 않음
//            em.flush();
//            // 쓰기 지연 SQL 저장소, Dirty checking 하는 것.
//            System.out.println("======");


//            JPQL 쿼리 실행 시 플러시가 자동으로 호출됨. 옵션으로 설정 변경 가능
//            - 플러시 모드 옵션(em.setFlushMode(FlushModeType.COMMIT);)
//                    FlushModeType.AUTO: 커밋이나 쿼리를 실행할 때 플러시(기본값)
//                    FlushModeType.COMMIT: 커밋할 때만 플러시


//            준영속 상태로 만드는 법: 영속 상태였다가 영속성 컨텍스트에서 빠진 상황
//            - em.detach(entity): 특정 엔티티만 준영속상태로 전환
//            - em.clear(): 영속성 컨텍스트를 완전히 초기화
//            - em.close(): 영속성 컨텍스트를 종료


//            Member member = em.find(Member.class, 100L);
//            member.setName("AAAAAA");
//            em.detach(member); // 영속성 컨텍스트에서 제거하므로 update 쿼리가 나가지 않음.
//            System.out.println("---------");


            //엔티티의 생명 주기
//            - 비영속 (new/transient): 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
//            - 영속 (managed): 영속성 컨텍스트에 관리되는 상태
//            - 준영속 (detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
//            - 삭제 (removed): 삭제된 상태


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();



    }
}
