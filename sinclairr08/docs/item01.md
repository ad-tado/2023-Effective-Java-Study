# Item 01 + Example

## 생성자 대신 정적 팩터리 메서드를 고려하라

- 인스턴스를 얻기 위한 가장 고전적인 방법은 public 생성자
- 그러나 정적 팩터리 메서드라는 인스턴스를 반환하는 단순한 정적 메서드를 이용하는 방법이 있음
- 이 방법은 여러 장단점이 존재

## 장점

- 메서드기 때문에 이름을 가질 수 있음
    - 인스턴스를 생성할 때 의미를 명확하게 할 수 있음
- 호출될 때 마다 인스턴스를 새로 생성하지 않아도 될 수 있음
    - Immutable 클래스의 경우에는 캐싱한 인스턴스를 재활용 가능
- 반환 타입의 하위 타입 객체를 반환 가능
    - 반환할 객체의 클래스를 선택 가능하므로 엄청난 유연성 제공
    - 자바 8에서 인터페이스가 정적 메서드를 가질 수 있게 되면서 이 이점이 더욱 극대화됨
- 입력 매개변수에 따라 다른 클래스의 객체 반환 가능
    - 클라이언트는 어떤 클래스의 객체가 반환되는지 알 필요가 없음
    - 반환하는 클래스의의 하위 타입 클래스의 객체이기만 하면 됨
- 정적 팩토리 메소드를 작성할 시기에, 반환할 객체의 클래스가 없어도 됨
    - 이 부분은 인터페이스를 클라이언트에 넘겨줘도 되기 때문에, 구현을 나중으로 미뤄도 된다는 뜻으로 이해했습니다

## 단점

- 정적 팩터리 메서드만 제공하는 경우 상속을 통해 하위 클래스를 만들기가 불가능
    - 상속을 위해서는 `public` 혹은 `protected` 생성자가 필요하기 때문
- 정적 팩터리 메서드를 프로그래머가 찾기 힘듦
    - 생성자와 달리 API에 설명이 명확히 드러나지 않음
    - API 문서를 잘 쓰고, 메서드 이름도 컨벤션을 따르는 등의 노력이 필요

## 정적 팩터리 메서드 명명 방식

- `from`: 매개변수 하나를 받아서 해당 타입의 인스턴스를 반환
- `of`: 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환
- `valueOf`: from, of의 자세한 버전
- `instance / getInstance`: 매개변수로 명시한 타입의 인스턴스를 반환
- `create / newInstance`: 매개변수로 명시한 타입의 새로운 인스턴스를 만들어 반환
- `type / getType`: getInstance와 비슷하나, 다른 Type의 인스턴스를 만들 때 사용
- `type/ newType`: newInstance와 비슷하나, 다른 Type의 인스턴스를 새로 만들어 반환할 때 사용

## Example

- [소스 코드](https://github.com/sinclairr08/2023-Effective-Java-Study/blob/main/sinclairr08/src/main/java/org/adtado/sinclairr/effective/item01/Calculator.java)

- [테스트 코드](https://github.com/sinclairr08/2023-Effective-Java-Study/blob/main/sinclairr08/src/test/java/org/adtado/sinclairr/effective/item01/CalculatorTest.java)

- 다음과 같은 요구 사항을 가진 애플리케이션을 개발한다고 가정합시다

    1. 사용자가 숫자 여러 개 사이에 공백을 두고 입력하면, 해당 숫자들의 합을 출력해 주세요
    2. 만약 사용자가 입력의 첫 부분에 `/mul` 이라고 입력하고 숫자들을 입력한다면, 해당 숫자들의 곱을 출력해 주세요

- 이 문제를 Calculator라는 객체를 만들고, 입력받는 문자열을 인자로 받는 생성자를 만들 수 있습니다
  ```java
  public Calculator(String s) {
      if (s.startsWith("/mul")) {
          this.operands = parse(s.substring(5));
          this.operator = '*';
      }
      else {
          this.operands = parse(s);
          this.operator = '+';
      }
  }
  ```
- 이 방법을 통해 생성자를 만드는 것도 좋지만, 문제는 생성자 내부 로직이 너무 복잡해 집니다.

- `/mul` 이 있는 지 확인하는 행위와 필드에 값을 대입하는 역할을 모두 생성자 내부에서 수행하게 됩니다.

- 또한 이 생성자를 사용할 때, `new Calculator("1, 2, 3, 4")` 와 같은 코드의 의미를 명확하게 파악하기 힘들 수 있습니다

  ```java
  @Test
  void add_test_String() {
      Calculator calculator = new Calculator("1 2 3 4");

      int result = calculator.calculate();

      assertThat(result).isEqualTo(10);
  }
  ```

- 따라서 다음과 같이, **정적 팩터리 메서드**를 활용하면, 필드에 값을 넣는 기능과 요구사항의 기능을 분리할 수 있습니다.

  ```java
  public static Calculator generateFromString(String s) {
      if (s.startsWith("/mul")) {
          return new Calculator(parse(s.substring(5)), '*');
      }

      return new Calculator(parse(s), '+');
  }

  private Calculator(List<Integer> operands, char operator) {
      this.operands = operands;
      this.operator = operator;
  }

  ```

- 코드에서도 `"1 2 3 4"` 로 부터 `Calculator` 객체를 만든다는 뜻이 더욱 명확하게 전달 됩니다.

  ```java
  @Test
  void add_test() {
      Calculator calculator = Calculator.generateFromString("1 2 3 4");

      int result = calculator.calculate();

      assertThat(result).isEqualTo(10);
  }
  ```

- 생성자로도 분명 구현은 가능하지만, 정적 팩터리 메서드를 통해 메서드가 더 명확한 이름을 가지게 할 수 있습니다.

## References

1. 조슈아 블로크 - Effective Java 3/E
