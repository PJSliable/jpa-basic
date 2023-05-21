package hellojpa;

import javax.persistence.*;

@Entity
//@Table(name="MBR") // 테이블과 매핑, 카탈로그 스키마 구분 가능
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
//@TableGenerator(
//        name= "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ",
//        allocationSize = 1
//)
public class Member {

    //    어플리케이션 전체를 봤을 때는 Integer 를 Long 으로 하더라도 영향을 거의 주지 않음
//    오히려 10억이 넘어갈 때 타입을 바꾸는게 더 힘듦 : Long 권장

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "MEMBER_SEQ_GENERATOR")
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //DDL 생성 기능 - 제약 조건 추가: 회원 이름은 필수, 10자 초과 X
    //DDL을 자동 생성할 때만 사용되고 JPA 실행 로직에는 영향을 주지 않는다.

//    @Column(nullable = false, length = 10)
//    private String name;

    @Column(name = "name", nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    private Integer age;
//
////    DB에는 Enum타입이 기본적으로 없는데 설정 가능
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
////    Database에서는 날짜, 시간, 날짜시간을 구분하기에 매핑 정보를 줘야 함
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
////    Varchar를 넘어서는 큰 컨텐츠를 넣고 싶을 때 사용
//    @Lob
//    private String description;

//    매핑 어노테이션 정리
//    hibernate.hbm2ddl.auto
//    @Column: 컬럼 매핑
//    @Temporal: 날짜 타입 매핑
//    @Enumerated: enum 타입 매핑
//    @Lob: BLOB, CLOB 매핑
//    @Transient: 매핑하고 싶지 않을 때 DB에서 제외, 캐시 데이터를 넣어두는 등 메모리에서만 임시로 컨트롤하고 싶을 때 사용


//    @Column
//    name: 필드와 매핑할 테이블의 컬럼 이름
//    insertable, updatable: 등록, 변경 가능 여부
//    nullable(DDL): null 값의 허용 여부를 결정한다. false로 설정하면 DDL생성 시에 not null 제약 조건이 붙는다
//    unique(DDL): @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제약조건을 걸 때 사용한다.
//                 이름이 랜덤으로 나옴 -> @Table(uniqueConstraints = ) 을 선호
//    columnDefinition(DDL): 데이터베이스 컬럼 정보를 직접 줄 수 있다. ex) varchar(100) default 'EMPTY'
//    length(DDL): 문자 길이 제약 조건, String 타입에만 사용한다.
//    precision, scale(DDL): BigDecimal, BigInteger 타입에서 사용
//                           precision은 소수점을 포함한 전체 자릿수, scale은 소수의 자릿수
//                           double, float 타입에는 적용X
//                           아주 큰 숫자나 정밀한 소수를 다루어야 할 때만 사용

//    @Enumerated
//    EnumType.ORDINAL: enum 순서를 DB에 저장 -> 사용하면 안됨. Enum 의 순서가 바뀌면 역할이 바뀔 수 있음
//    EnumType.STRING: enum 이름을 DB에 저장

//    @Temporal
//    LocalDate(연, 월), LocalDateTime(연, 월, 일)을 사용할 때는 생략 가능
//    TemporalType.DATE: 날짜
//    TemporalType.TIME: 시간
//    TemporalType.TIMESTAMP: 날짜와 시간

//    @Lob
//    CLOB: String, char[], java.sql.COLB - 문자면 CLOB 매핑
//    BLOB: byte[], java.sql.BLOB - 나머지는 BLOB 매핑

    public Member() {

    }


//    기본 키 매핑 어노테이션
//    @Id: 직접 할당
//    @GeneratedValue: 자동 생성
//    - Identity: 데이터베이스에 위임, MYSQL
//            - 주로 MySQL, PostgreSQL, SQL Server, DB2에서 사용(예: MySQL의 AUTO_INCREMENT)
//            - JPA는 보통 트랜젝션 커밋 시점에 INSERT SQL 실행
//            - AUTO_INCREMENT는 DB에 INSERT SQL을 실행한 이후에 ID 값을 알 수 있음
//            - IDENTITY 전략은 commit 시점이 아니라 그 전인 em.persist() 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자를 조회
//                - 한 트랜젝션 안에서 INSERT QUERY를 여러 번 실행한다고 해서 성능의 차이가 크게 나진 않으므로 괜찮음

//    - SEQUENCE: 데이터베이스에 시퀀스 오브젝트 사용, 오라클
//            - @SequenceGenerator 필요: 테이블마다 시퀀스를 별도로 관리
//                - name: 식별자 생성기 이름
//                - sequenceName: 데이터베이스에 등록되어 있는 시퀀스 이름
//                - initialValue: DDL 생성 시에만 사용됨, 시퀀스 DDL을 생성할 때 처음 1 시작하는 수를 지정한다.
//                - allocationSize: 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용 됨), 50이나 100 정도가 적절함
//                    - 50으로 설정한다면 next call 한번 할 때 DB에 50개를 올려놓고 미리 50개를 가져와서 메모리에서 1씩 사용
//                        - 여러 웹서버가 있어도 동시성 이슈 없이 다양한 문제들이 해결됨
//                        - 너무 많은 값을 설정하면 웹서버를 내리는 시점에 날라감, 숫자 구멍이 생김 : 낭비
//                    - DB 시퀀스 값이 하나씩 증가하도록 설정되어 있으면 이 값을 반드시 1로 설정해야 함
//                - catalog, schema: DB catalog, schema 이름
//            - 데이터베이스 시퀸스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트(예: 오라클 시퀀스)
//            - 오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용
//            - INSERT QUERY는 기존대로 commit 시점에서 나감. PK 값만 DB 시퀀스를 통해 가져와서 ID 값에 넣어주는 것
//    - TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용 *잘안씀
//            - 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
//                - 장점: 모든 DB에 적용 가능
//                - 단점: 성능
//            - @TableGenerator
//                - name: 식별자 생성기 이름
//                - table: 키 생성 테이블명
//                - pkColumnName: 시퀀스 컬럼명
//                - valueColumnNa: 시퀀스 값 컬럼명
//                - pkColumnVale: 키로 사용할 값 이름
//                - initialValue: 초기 값, 마지막으로 생성된 값이 기준이다.(성능 최적화에 사용)
//                - allocationSize: 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용)
//                - catalog, schema: DB catalog, schema 이름
//                - uniqueConstraints(DDL): 유니크 제약 조건을 지정할 수 있다.
//    - AUTO: 방언에 따라 자동 지정, 기본값


//    권장하는 식별자 전략
//    - 기본 키 제약 조건: null 아님, 유일, 변하면 안된다
//    - 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자.
//    - 얘를들어 주민등록번호도 기본 키로 적절하지 않다.
//    - 권장: Long형 + 대체키 + 키 생성전략 사용


}