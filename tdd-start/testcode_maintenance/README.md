# 테스트 코드와 유지보수 

테스트 코드도 결국 코드이기 떄문에 유지보수가 필요하다. 

여기서는 테스트 코드를 유지보수 하지 않아서 테스트 코드의 역할을 하지 못하도록 만들지 마라. 라고 말한다.
- 테스트 코드의 역할을 내가 작성한 코드가 내가 생각한 대로 작동하는가 를 검증하는 역할이다.
- 근데 테스트 코드를 유지보수 하지 않아서 깨지는 테스트 코드가 생기고 이를 관리하지 않는다면 미래에 테스트 코드가 째기는 것에 대해서 무신경 해지게 된다. 
결국 테스트 코드의 역할을 할 수 없게 된다.
- 이걸 깨진 유리창 이론이라고 말하기도 하더라. 

그래서 이런 상황 자체를 만들도록 하지 말고 테스트 코드 자체를 유지보수 하게 쉽게 만들자. 

### 테스트 코드가 유지보수 하기 좋다는 말은 무엇일까? 
- 테스트 코드의 본질은 잘 작동하는 지 검증하는 것이다. 그 역할만 제대로 해주면 된다.
- 근데 이제 기능은 잘 작동하는데 별 이상한 이유 때문에 테스트 코드가 깨지는 상황이 나오면 안된다. 
- 그 이상한 이유를 만들지 않아서 테스트 코드가 깨지지 않도록 하는게 유지보수가 좋은 테스트 코드가 아닐까 싶다. 
- 테스트 코드를 쉽게 작성할 수 있어야 한다. 
  - 테스트 코드는 어떤 테스트인지 명확해야한다. 어떤 걸 검증하는지 명확하게 알 수 있어야 어떤 테스트를 추가할 지 알 수 있다.
  - 테스트 코드가 의존하는 대상이 있을수록 알아야 할 사전 지식이 많아진다. 그러므로 작성의 비용이 커질 수 있다. 

## 테스트 코드의 유지보수를 높이는 방법

### 변수나 필드를 사용해서 기댓값 표현하지 않기

다음 코드를 비교해보자.

Assertion 기댓값에 변수를 사용하는 경우 

```java
void testMethod() {
        ...
    assertAll(
        () -> assertEquals(answers.size(), savedAnswer.getRespondentId()),
        () -> assertEquals(answer.get(0), savedAnswer.getAnswers().get(0))
        () -> assertEquals(answer.get(1), savedAnswer.getAnswers().get(1))
        () -> assertEquals(answer.get(2), savedAnswer.getAnswers().get(2))
        () -> assertEquals(answer.get(3), savedAnswer.getAnswers().get(3))
        () -> assertEquals(answer.get(4), savedAnswer.getAnswers().get(4))
    )    
}
```

Assertion 기대값에 변수를 사용하지 않는 경우  

```java
void testMethod() {
        ...
    assertAll(
        () -> assertEquals(4, savedAnswer.getRespondentId()),
        () -> assertEquals(0, savedAnswer.getAnswers().get(0))
        () -> assertEquals(1, savedAnswer.getAnswers().get(1))
        () -> assertEquals(2, savedAnswer.getAnswers().get(2))
        () -> assertEquals(3, savedAnswer.getAnswers().get(3))
        () -> assertEquals(4, savedAnswer.getAnswers().get(4))
    )    
}
```

차이가 느껴지는가? 

변수를 사용하지 않은 쪽이 코드를 파악하는데 더 쉽다. 

무슨 테스트를 하는지 파악하기가 쉽다.  

변수를 사용한 쪽은 기대값이 무엇인지 확인하는 과정이 필요하다. 

코드를 분석하는 시간이 조금 더 걸린다.

### 두 개 이상을 검증하지 말기. 

하나의 테스트에서 두 개 이상을 검증하지 말자. 

이 말을 잘못 이해할 수 있는데 Assertion 문을 여러개 쓰지 말자가 아니다.

하나의 검증에서 여러 테스트 케이스가 필요한 경우에는 여러 Assertion 을 쓸 수 있다. 

근데 이제 하나의 테스트 메소드에서 (= 하나의 검증에서) 여러 검증을 넣으면 문제가 될 수 있다. 

한 검증의 실패가 다음 검증을 실행하지 않도록 해준다던지.

실패를 했으면 누가 했는지 명확하지 않다던지. 

여러 검증이 테스트 메소드에 섞여있다면 필요한 테스트를 찾기도 힘들어져서 테스트 코드를 추가하는 작업이 힘들어질 수 있다. 코드 분석을 해야해서. 

### 정확하게 일치하는 값으로 모의 객체 설정하지 않기.

다음과 같이 Mock 객체를 설정할 때 정확한 값으로 설정하지 말자.

```java
void weakPassword() {
    Mockito.given(mockPasswordChecker.checkPassword("pw"))
            .willReturn(true); 
        ... 
}
```

이렇게 설정하고 테스트를 하는 경우 테스트 할려는 password 가 조금만 달라져도 바로 테스트 케이스가 실패할 것이다.

Mock 객체를 사용할 땐 범용적인 값을 사용해서 기술하도록 하자. 

### 과도하게 구현 검증하지 않기

테스트 코드를 작성할 때 대상의 내부 구현까지 검증하지는 않도록 하자. 

예를 들면 내부 구현의 특정한 메소드가 호출 되었는지. 

이런거까지 검사하게 된다면 테스트 코드가 실패할 경우가 너무나 많다. 기능이 동작하는데 

잘 작동하는데 내부 구현이 바꼈다고 테스트가 실패한다면 고칠 비용이 크다. 

### 셋업을 이용해서 중복된 상황을 설정하지 않기. 

테스트 코드를 작성할 때 상황을 만들기 위해서 @BeforeEach 를 사용할 수 있다.

이를 통햄 모든 테스트 메소드에서 발생하는 중복 코드를 제거해줄 수 있다는 장점이 있다.

근데 이렇게 하면 문제점이 해당 클래스에서 테스트를 작성하기 위해서는 @BeforeEach 를 이해해야 하는 상황이 발생한다. 

테스트 작성 비용이 커진다. 알아야 할 사전 지식이 늘어나서.

그 다음 @BeforeEach 가 조금만 바껴도 실패할 수 있는 테스트 케이스는 엄청나게 늘어날 수 있다. 

그러니까 테스트 메소드 마다 독립적인 걸로 보자. 

각 테스트 메소드에서 중복되는 코드가 있더라도 신경쓰지말자. 

### 통합 테스트의 상황 설정을 위한 보조 클래스 사용하기 

DB 연동과 관련된 통합 테스트의 경우 특정 테스트는 추가적인 데이터가 필요할 수 있다.

이를 매번 그떄그때 마다 작성하면 중복이 발생할 수 있다. 

이를 다음과 같은 헬퍼 클래스를 만들면 테스트를 이해하는데 도움을 줄 수 있고 중복을 제거할 수 있다.  

```java
public class UserGivenHelper { 
    private JdbcTemplate jdbcTemplate; 
    
    public void givenUser(String id, String pw, String email) {
        jdbcTemplate.update(
                "insert into user values(?,?,?)" +
                "on duplicate key update password = ?, email = ?",
                id,pw,email,pw,email
        );
    }
}
``` 

### 실행 환경이 다르다고 실패하지 않기

당연한 소리겠지만 같은 테스트가 실행 환경이 다르다고 테스트에 실패하면 안된다.

OS 마다 다를 수 있는 경우나 내 로컬에 있는 특정 파일 경로를 이용하는 경우 를 조심하자.

특정 파일 경로는 프로젝트를 기준으로 하는 상대경로인 클래스 패스를 적극적으로 이용하자. 

### 실행 시점이 다르다고 실패하지 않기

다음과 같은 경우는 실행 시점에 따라서 실패할 수 있다.

```java
import java.time.LocalDateTime;

public class Member {
    private LocalDateTime expiryDate; 
    
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now()); 
    }
}
```

expiryDate 를 언제로 실행했느냐에 따라서 이 클래스를 테스트하는 메소드는 실행 시점에 따라서 실패할 수 있다. 

이 경우 실행 시점에 따라서 실패하지 않도록 코드의 설계를 바꿀 수 있다.

```java
import java.time.LocalDateTime;

public class Member {
    private LocalDateTime expiryDate;
    
    public boolean isExpired(LocalDateTime dateTime) {
        return expiryDate.isBefore(dateTime); 
    }
}
```

이렇게 파라미터를 통해서 현재 시점을 컨트롤 할 수도 있고 

LocalDateTime 을 감싸는 클래스를 만든 후 LocalDateTime.now() 메소드를 래핑하는 메소드를 만들 수도 있다.

그런 후 새 클래스를 Mocking 하면 된다. 

### 랜덤하게 실패하지 않기 

Random 을 이옹해서 매번 랜덤값을 사용해서 테스트 하는 경우 이 경우도 랜덤 값을 파라미터로 받도록 해서 컨트롤 할 수 있게 만들어주면 테스트하기 쉽다.

### 필요하지 않은 값 설정하지 않기

객체를 생성할 때 빌더 패턴으로 생성하는 경우에 필요한 필드만 세팅하고 필요하지 않은 필드는 세팅하지 말자. 

딱 필요한 것만 세팅하도록 해서 명확하게 정보를 제공해줄 수 있다. 

### 단위 테스트를 위한 객체 생성 보조 클래스

테스트 상황을 만들기 위해서 필요한 객체들을 팩토리 메소드 패턴을 이용해서 객체를 만들면 좀 더 명확한 테스트 케이스르 작성할 수 있고 테스트 코드를 줄일 수도 있다. 

### 통합 테스트는 필요하지 않은 범위까지 연동하지 않기 

DB 연동만을 위한 테스트인데 @SpringBootTest 를 통해서 모든 빈을 초기화 한다고 하면 테스트를 돌리는 시간이 길어진다. 

### 더 이상 쓸모없는 테스트 코드 

특정한 기능을 확인해보기 위해서 학습용 테스트 케이스를 작성할 수 있다.

이런 학습용 테스트 케이스는 학습이 끝나면 지우도록하자. 의미없는 테스트 케이스다. 