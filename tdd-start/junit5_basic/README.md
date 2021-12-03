### 주요 Assertion 메소드 

- assertEquals(expected, actual) : 실제 값 (actual) 과 기대 값 (expected) 가 같은지 검사하는 것
- assetNotEquals(unexpected, actual) : 실제 값 (actual) 과 기대 값 (unexpected) 이 다른지 검사하는 것
- assertSame(Object expected, Object actual) : 두 객체가 같은지 검사하는 것
- assertNotSame(Object unexpected, Object actual) : 두 객체가 다른지 검사하는 것
- assertTrue(boolean condition) : 조건이 참인지 검사하는 것
- assertFalse(boolean condition) : 조건이 거짓인지 검사하는 것
- assertNull(Object actual) : 객체가 널인지 검사하는 것
- assertNotNull(Object actual) : 값이 null 이 아닌지 검사하는 것
- fail() : 테스트를 실패 처리하는 것. 주로 여기까지 코드 실행이 오면 안되는데 온 경우 fail() 을 통해서 테스트 실패를 줄 수 있다.
- assertThrows(Class<T> expectedType, Executable executable) : executable 을 실행했을 때 expectedType 의 예외가 발생하는지 검사하는 코드
- assertDoesNotThrow(Executable executable) : executable 을 실행 했을 때 예외가 나지 않는 다는 걸 검사하는 코드

### @BeforeEach 가 안티패턴인 이유 

- @BeforeEach 는 코드의 중복을 없애기 위해서 사용한다. 
- 그러나 테스트가 @BeforeEach 에 의존하는 문제가 생긴다. \ 
- 그리고 테스트를 만드는 입장에서 @BeforeEach @AfterEach 까지 고려하면서 생각하기에는 너무 고려할 게 많다. 라는 생각도 들고. 