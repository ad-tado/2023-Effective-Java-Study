# Item01 생성자 대신 정적 팩터리 메서드를 고려하라

> 발제에서 주어진 과제에 맞게 설명하겠습니다

## 1. 생성자 대신 정적 팩터리를 사용한 예를 들어주시고, 예를 든 이유를 설명해주세요.

예시는 `Item01ProductResponseTest`의 `fromInstanceTest()`를 참고해주시면 되겠습니다.
`ProductResponse`는 `ProductApiResponse`을 인자로 받아 `ProductResponse`에 맞게 형식을 바꾸는 역할을 합니다.

이 예를 든 이유는 생성자와 비슷하지만,
1. 정적 팩터리도 쓰는 경우가 있어 실습하며 왜 사용하는지 이해하고 싶었고
2. Enum 타입을 활용해서 같이 이해해보고 싶었습니다.(정말 잘 몰랐음)

생성자는 인스턴스로 생성할 때 동시에 일정한 초기값을 가지고 생성하기 위해 사용합니다.

만약, 옷 재고를 담을 때 그 옷이 상의라면 사이즈가 S/M/L/XL 등으로 나뉠 수 있지만,
상의가 아닌 다른 종류의 경우에는 사이즈가 달라집니다.

그럴 때마다 매번 같은 이름의 생성자를 인스턴스만 바꾸거나 추가해서 나열하면 관리하기 복잡해질 수 있습니다.

정적 팩토리 메서드를 활용하면
1. 무슨 목적으로 만든 건지 파악할 수 있어 코드의 가독성을 높일 수 있습니다.
2. Enum, String, int 등의 다른 타입이 왔을 때 형식을 바꿀 수 있습니다.
3. 호출할 때마다 새로운 객체를 생성할 필요가 없습니다.

## 2. 생성자로 바꿀 수 있다면, 생성자로도 바꿔주세요.

`Item01ProductResponseTest`의 `constructorInstanceTest()`를 참고해주세요

---

예시 1 참고 레퍼런스

- [생성자가 필요한 이유](https://www.inflearn.com/questions/277163/%EC%83%9D%EC%84%B1%EC%9E%90%EB%A5%BC-%EB%A7%8C%EB%93%9C%EB%8A%94-%EC%9D%B4%EC%9C%A0%EA%B0%80-%EB%A8%BC%EA%B0%80%EC%9A%94)
- [정적 팩토리 메서드](https://velog.io/@holidenty/%EC%9D%B4%ED%8E%99%ED%8B%B0%EB%B8%8C-%EC%9E%90%EB%B0%94-%EA%B0%9D%EC%B2%B4%EC%9D%98-%EC%83%9D%EC%84%B1%EA%B3%BC-%ED%8C%8C%EA%B4%B4-Item1-nul1xqx9#span-stylecolord683655-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EC%9E%91%EC%84%B1%ED%95%98%EB%8A%94-%EC%8B%9C%EC%A0%90%EC%97%90%EC%84%9C-%EB%B0%98%ED%99%98%ED%95%A0-%EA%B0%9D%EC%B2%B4%EC%9D%98-%ED%81%B4%EB%9E%98%EC%8A%A4%EA%B0%80-%EC%A1%B4%EC%9E%AC%ED%95%98%EC%A7%80-%EC%95%8A%EC%95%84%EB%8F%84-%EB%90%9C%EB%8B%A4span)
- 카펀님 좋은 예시 감사해요