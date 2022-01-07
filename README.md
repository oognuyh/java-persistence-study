# Java Persistence Study

## 1. Associations

<details>
<summary>Item 1: How to Effectively Shape the @OneToMany Association</summary>

1. 외래키를 통해 참조하는 쪽이 자식 참조되는 쪽이 부모  
1. 일반적으로 단방향 관계가 쿼리하는 것이 양방향보다 어렵기 때문에 큰 애플리케이션에서는 양방향 관계를 선호([Hibernate docs](http://docs.jboss.org/hibernate/core/3.3/reference/en/html/best-practices.html))
1. 부모없이 자식이 존재할 수 없으므로 항상 Cascade는 부모에서 자식 방향으로 설정
1. 부모 쪽에 mappedBy/orphanRemoval 설정
1. 부모 쪽에 addSomething(), removeSomething()과 같은 헬퍼 메서드를 통해 양방향 연결 관계를 동기화
1. 자식 쪽에서 equals()와 hashCode()를 재정의 필요하며, Lombok을 통한 구현보다 id가 할당되는 경우 hashCode() 값이 바뀌기 때문에 hashCode()는 항상 고정된 값을 반환하고 equals()의 경우 객체의 유형과 기본키를 비교하고 널이면 false를 반환하도록 구현. @NaturalId 또는 데이터베이스에 구애받지 않는 UUID 그리고 두 함수를 재정의하는 것이 좋음[The best way to implement equals, hashCode, and toString with JPA and Hibernate](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)
1. @OneToMany은 FetchTYpe.LAZY가 기본값이지만, @ManyToOne은 EAGER이므로 자식 쪽에서도 FetchTYpe.LAZY 설정
1. 별도의 SQL 문이나 LazyInitializationException 그리고 순환 참조를 피하기 위해 toString()을 재정의할 때 기본 속성들만 포함
1. 잠재적 혼란/실수 등을 초래할 수 있어 조인 컬럼명을 구체화하기 위한 @JoinColumn 사용

</details>

<details>
<summary>Item 2: Why You Should Avoid the Unidirectional @OneToMany Association</summary>

1. 단방향 연결일 경우 각각의 외래키를 포함한 연결 테이블을 따로 생성하여 양방향 연결보다 더 많은 메모리를 소비
1. 연결 테이블을 따로 생성할 경우 연결 테이블에 추가/삭제하는 하위 엔터티 개수의 추가적인 쿼리가 실행
1. 단방향 연결에서 부모 쪽에서 마지막 자식을 삭제하는 경우, 연결 테이블에서 부모와 관련된 모든 관계를 지우고 나머지 자식을 재삽입
1. 첫 번째 자식을 삭제하는 경우도 그렇고 단방향 연결은 양방향 연결에 비해 성능 패널티가 존재
1. 단방향 연결에서 @OrderColumn을 사용하면 연결 테이블에서 모든 관계를 지우는 방식을 사용하지 않지만, 수정문이 필요
1. 단방향 연결에서 @JoinColumn을 사용하면 기존에 비해 성능을 올릴 수는 있지만, 마찬가지로 수정문이 필요

</details>

<details>
<summary>Item 3: How Efficient Is the Unidirectional @ManyToOne</summary>

1. 단방향 연결일 경우 각각의 외래키를 포함한 연결 테이블을 따로 생성하여 양방향 연결보다 더 많은 메모리를 소비
1. 연결 테이블을 따로 생성할 경우 연결 테이블에 추가/삭제하는 하위 엔터티 개수의 추가적인 쿼리가 실행
1. 단방향 연결에서 부모 쪽에서 마지막 자식을 삭제하는 경우, 연결 테이블에서 부모와 관련된 모든 관계를 지우고 나머지 자식을 재삽입
1. 첫 번째 자식을 삭제하는 경우도 그렇고 단방향 연결은 양방향 연결에 비해 성능 패널티가 존재
1. 단방향 연결에서 @OrderColumn을 사용하면 연결 테이블에서 모든 관계를 지우는 방식을 사용하지 않지만, 수정문이 필요
1. 단방향 연결에서 @JoinColumn을 사용하면 기존에 비해 성능을 올릴 수는 있지만, 마찬가지로 수정문이 필요

</details>

<details>
<summary>Item 4: How to Effectively Shape the @ManyToMany Association</summary>

1. 양방향 @ManyToMany는 두 개체가 서로 부모가 될 수 있고 두 외래키가 연결 테이블에 존재
1. 두 개체 중에서 관계의 주인을 정해 변화를 전파. 반대 쪽은 mappedBy를 추가
1. 항상 Set을 사용
1. 두 개체 간 연관 관계 동기화
1. 한 쪽을 제거할 경우 다른 개체에 의해 참조될 경우가 있기 때문에 CascadeType.All/REMOVE을 지양
1. 개발자가 혼란없이 참조할 수 있도록 @JoinTable 사용
1. 지연 로딩 사용(기본값)
1. equals()와 hashCode() 재정의
1. 별도 SQL 문, 순환 참조 등을 피하기 위해 toString() 재정의 시 주의 필요
1. @ManyToMany 두 양방향 @OneToMany로 대체될 수 있음

</details>

<details>
<summary>Item 5: Why Set Is Better than List in @ManyToMany</summary>

1. List를 사용할 경우 개체 삭제 시 관련된 개체 모두 삭제 후 재삽입하는 반면, Set을 사용하면 단일 삭제문이 실행
1. 데이터를 가져올 때 주어진 컬럼을 통해 정렬하는 @OrderBy와 여분의 컬럼을 사용해 영구적으로 정렬하는 @OrderColumn을 통해 정렬 가능
1. 정렬이 필요한 경우, 하이버네이트가 LinkedHashSet을 통해 정렬된 데이터를 보존하므로 완전한 일관성을 위해 HashSet보다 LinkedHashSet을 사용

</details>

<details>
<summary>Item 6: Why and When to Avoid Removing Child Entities with CascadeType.Remove and orphanRemoval=true</summary>

1. 참조되는 쪽을 삭제하는 경우 CascadeType.REMOVE 또는 orphanRemoval=true가 존재하면 참조하는 쪽에 자동적으로 전파한다는 점에서 동일
1. CascadeType.REMOVE 여부 상관없이 orphanRemoval=true를 통해 관계를 끊으면 삭제문을 통해 연관 개체를 자동적으로 삭제하고 false인 경우는 수정문을 실행. 관계를 끊는 것을 삭제로 보지 않기 때문에 참조하는 개체없이 존재할 수 없는 개체를 삭제할 때 true가 유용
1. CascadeType.REMOVE를 통해 부모 쪽을 삭제할 경우, Persistence Context에 연관 개체들이 존재해야 그렇지 않으면 영향을 받지 않음. 게다가, 부모 삭제문과 자식 삭제문이 개수만큼 실행되므로 성능 패널티 존재
1. orphanRemoval=true를 통해 부모 쪽을 삭제하는 경우에도 동일한 쿼리를 수행
1. 위와 같은 방식은 애플리케이션이 산발적인 삭제를 실행하는 경우나 특히, 관리되는 개체를 삭제할 때 개체 상태 전이를 위해 하이버네이트가 필요하므로 유용. 게다가, Automatic Optimistic Locking mechanism(e.g., @Version)으로부터 이점. 상충되지만, DML문을 줄여 삭제를 효율적으로 해야할 경우 고려해야 함
1. 효율적으로 삭제하기 위해서는 Bulk operation을 사용해야하므로 Automatic Optimistic Locking mechanism을 사용할 수 없음. Persistence Context 동기화 문제는 flushAutomatically = true, clearAutomatically = true를 통해 관리
1. 효율적으로 삭제할 수 있는 4가지 경우:
    1. 하나의 부모가 Persistence Context에 있고 연관된 자식은 없을 때
        ```
        // ChildRepository.java
        public int deleteByParentId(String parentId);
        // ParentRepository.java
        public int deleteById(String id);

        Parent parent = parentRepository.findById(1L);
        childRepository.deleteByParentId(parent.getId());
        parentRepository.deleteById(parent.getId());
        ```
        연관된 자식들이 로드되지 않고 하나의 Parent 삭제문과 하나의 연관된 자식 개체 삭제문이 실행
    1. 다수의 부모가 Persistence Context에 있고 연관된 자식은 없을 때
        ```
        // ChildRepository.java
        @Query("DELETE FROM child c WHERE c.parent IN ?1")
        public int deleteByParents(List<Parent> parents);

        List<Parent> parents = parenRepository.findAll();
        childRepository.deleteByParents(parents);
        parentRepository.deleteAllInBatch(parents);
        ```
        > deleteInBatch(Iterable<T> entities) is deprecated. Use deleteAllInBatch()

        연관된 자식들이 로드되지 않고 다수의 Parent 삭제문과 하나의 연관된 자식 개체 삭제문이 실행
    1. 하나의 부모와 연관된 자식이 Persistence Context에 존재할 때
        ```
        Parent parent = parentRepository.findById(1L);
        childRepository.deleteAllInBatch(parent.getChildern());
        parentRepository.deleteAllInBatch(parent);
        ```
        > deleteAllInBatch(Iterable<T> entities)는 기본적으로 Persistence Context에 대해 flush/clear하지 않으므로 오래된 상태일 수 있음. flushAutomatically = true, clearAutomatically = true, flush() 등을 상황에 따라 추가
            
        하나의 Parent 삭제문과 하나의 연관된 자식 개체 삭제문이 실행
    1. 부모와 자식이 Persistence Context에 없을 때
        ```
        childRepository.deleteByParentId(1L);
        parentRepository.deleteById(1L);
        ```
        하나의 Parent 삭제문과 하나의 연관된 자식 개체 삭제문이 실행
    > 모든 개체를 삭제하는 가장 효율적인 방법은 Bulk Operation을 실행하는 deleteAllInBatch()

</details>


## Reference

[Spring Boot Persistence Best Practices](https://link.springer.com/book/10.1007/978-1-4842-5626-8) by Anghel Leonard