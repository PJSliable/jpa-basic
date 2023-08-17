package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // DTYPE Field 생성
public abstract class Item {
    // JPA는 기본적으로 단일 테이블 전략을 택하기에
    // 별도의 명시 없이 상속만 한다면 한 테이블에 전부 생성됨

    // .JOINED: 조인 전략
    //      - 장점: 테이블 정규화, 외래 키 참조 무결성 제약조건 활용 가능, 저장 공간 효율화
    //      - 단점: 조회시 조인을 많이 사용, 성능 저하, 조회 쿼리가 복잡함, 데이터 저장시 INSERT SQL 2번 호출
    // .SINGLE_TABLE: 단일 테이블 전략, DiscriminatorColumn이 없어도 DTYPE Field 생성
    //      - 장점: 조인이 필요 없으므로 일반적으로 조회 성능이 빠름, 조회 쿼리가 단순함
    //      - 단점: 자식 엔티티가 매핑한 컬럼은 모두 null 허용, 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있어 상황에 따라서(임계점을 넘으면, 보통 잘 안넘음) 조회 성능이 오히려 느려질 수 있다.
    // .TABLE_PER_CLASS: 구현 클래스마다 테이블 전략, 부모 클래스로 조회할 때 모든 테이블을 다 찾아야 함, 쓰면 안되는 전략. 데이터베이스 설계자와 ORM 전문가 둘 다 추천 X
    //      - 장점: 서브 타입을 명확하게 구분해서 처리할 때 효과적, not null 제약조건 사용 가능
    //      - 단점: 여러 자식 테이블을 함께 조회할 때 성능이 느림(UNION SQL), 자식 테이블을 통합해서 쿼리하기 어려움
    // @DiscriminatorColumn(name = "DIS_TYPE"): Field 이름을 DIS_TYPE로 설정
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
