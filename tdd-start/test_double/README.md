# 테스트 대역 

## 대역이 필요한 이유 

- 외부 요인 때문에 테스트가 실패하는 경우를 막기 위해서
- 실제와 똑같은 환경을 만들어서 테스트 하기에는 어려워서.
  - 즉 대역을 통해 테스트 작성을 쉽게 하기 위해서 (e.g 계좌의 여러가지 상태에 따라 처리해야하는 로직이 있다고 생각해보자. 여러가지 상태의 계좌를 실제로 요청해서 받는건은 상식적으로 힘들다.)
- 테스트를 하기위해 실제 상황과 똑같이 만들어주려면 해줘야하는 작업이 너무 많은 경우에.
  - https://jojoldu.tistory.com/226
- 이런 경우에는 외부 요인을 대신해주는 대역을 이용해서 처리해주면 편하다. 

### 외부 요인의 예

- 외부와의 통신이 필요한 경우 (HTTP)
- 외부 파일 시스템을 이용하는 경우
- 외부 데이터베이스와 연결하는 경우

## 대역의 종류

- Stub: 구현이 단순해진 대역을 말한다. 
- Faker: Stub 보다는 좀 더 복잡하고 일반적인 행동을 할 수 있는 구현을 제공한다. 
- Spy: 호출된 내역을 기록한다. (? Mockito 의 @SpyBean 은 진짜처럼 행동하는 가짜 빈을 말하는건데 좀 정의가 다른 것 같다.) 
- Mock: 예상한대로 상호작용을 하기 위해서 사용하는 대역이다. Stub 이나 Spy 가 될 수 있다고 한다. 

### 대역을 사용하는 예
- Repository 에는 @SpyBean 을 사용하지 않는 것. (Repository 만 해도 테스트의 범위가 충분히 넓다.)
  - 그러므로 Repository 를 이용하는 다른 빈에서는 Mocking 을 사용하는 걸 추천 
