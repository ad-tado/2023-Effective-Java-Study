# Item03 private 생성자나 열거 타입으로 싱글턴임을 보증하라

## public static final field 방식의 싱글턴

특징
- 생성자는 private으로 감춰두고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static 멤버를 하나 마련해둔다.

장점
- 해당 클래스가 싱글턴임이 API에 명백히 드러난다.
  - public static field가 final이니 절대로 다른 객체를 참조할 수 없다.
- 코드가 간결하다.

`Item03FinalSingletonTest`는 public static final field 방식의 싱글턴을 깨는 테스트이다. 
reflection을 통해 이를 깰 수 있음을 확인할 수 있다.

`Item03FinalSingletonDefenseTest`는 public static final field 방식의 싱글턴에서
reflection 방어 코드를 통해 2개 이상의 인스턴스가 존재하는 것을 막는 테스트이다.

즉, 인스턴스의 개수가 오로지 1개만 존재하는 것을 확인할 수 있다.

## static factory 방식의 싱글턴

특징
- 생성자는 private으로 감춰두고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static 멤버를 하나 마련해둔다.

장점
- API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
  - 유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있다.
- 상황에 따라 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
- 정적 팩터리의 메서드 참조를 공급자`supplier`로 사용할 수 있다.

단점
- 싱글턴 클래스를 직렬화하려면, 단순히 Serializable을 구현하는 것뿐만 아니라,
  - 모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드를 제공해야 한다.
  - 안 그러면 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어진다.

`Item03FactorySingletonTest`는 static factory 방식의 싱글턴을 깨는 테스트이다.
reflection을 통해 이를 깰 수 있음을 확인할 수 있다.

`Item03FactorySingletonDefenseTest`는 static factory 방식의 싱글턴에서
reflection 방어 코드를 통해 2개 이상의 인스턴스가 존재하는 것을 막는 테스트이다.

위의 타 방식과 마찬가지로 인스턴스의 개수가 오로지 1개만 존재하는 것을 확인할 수 있다.

# 원소가 한 개인 Enum 타입 방식의 싱글턴

장점
- public 필드 방식과 비슷하지만, 더 간결하고 추가 노력 없이 직렬화할 수 있다.
  - 아주 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.

단점
- 만들려는 싱글턴이 Enum 외에 클래스를 상속해야 한다면, 이 방법은 사용할 수 없다.
  - Enum 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다.

`Item03EnumSingletonTest`로 싱글톤 방식에서 직렬화나 리플렉션 공격에 완벽하게 방어할 수 있음을 확인할 수 있다.