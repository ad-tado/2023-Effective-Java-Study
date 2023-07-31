# Item 01. 생성자 대신 정적 팩터리 메서드를 고려하라

## 1. 생성자 대신 정적 팩터리를 사용한 예를 들어주시고, 예를 든 이유를 설명해주세요.

정적 팩토리 메소드를 사용한 예시는 `Item01SomethingResponseTest`의 `fromInstanceTest()`를 참고해 주세요.  
`SomethingResponse`는 `SomethingApiResponse`를 인자로 받아, `SomethingResponse`에 맞게 형식을 convert하여 만들어 주는 역할을 합니다.  
아래의 `constructorInstanceTest()`를 보시면 장점이 두드러지는데요. `SomethingResponse`에는 primitive type 외에 Enum, LocalDate 등 다양한 타입의 필드가
존재합니다. 이들은 각각 해당 타입으로의 변환이 필요한 경우가 많습니다.  
팩토리 메서드를 사용하면, 이러한 변환 내용을 사전에 정의해 둘 수 있습니다. 이로 인해,

* 코드가 읽기 간결해짐
* 매번 각 필드에 맞추어 값을 converting하는 중에 생길 수 있는 휴먼 에러 방지
* 인스턴스 생성 시 각 필드의 형식에 맞추는 책임을 별도로 분리 가능

따라서 이런 경우에는 정적 팩토리 메서드가 생성자보다 이점을 가집니다.

## 2. 생성자로 바꿀 수 있다면, 생성자로도 바꿔주세요.

`Item01SomethingResponseTest`의 `constructorInstanceTest()`



