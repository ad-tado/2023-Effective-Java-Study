# Item 03 + Example

## Private 생성자나 열거 타입으로 싱글턴임을 보증하라

- 싱글턴이란 인스턴스를 하나만 생성할 수 있는 클래스
    - 여러 HTTP 요청에 응답하는 인스턴스를 매 요청마다 만드는 경우 메모리 낭비가 심각
    - 이런 요청에 응답하는, `상태를 가지지 않는` 단 하나의 인스턴스로 처리하는 것이 좋음
- 싱글턴은 다음과 같은 단점 존재
    - 싱글턴으로 클래스를 만든 경우 이를 사용하는 클라이언트를 테스트하기 힘듦
    - 각 테스트마다 독립적인 인스턴스를 만들 수 없기 때문
    - 싱글턴 인스턴스는 정확히 하나만 존재하기 때문에, `mock`으로 대체할 수 없음
- 싱글턴 인스턴스를 만들기 위해서는 private 생성자를 사용하거나 열거 타입을 사용 가능

## Private 생성자 - public 필드 사용

```Java
public class Demian {
    // 싱글턴 인스턴스, 이 값을 초기화 할 때 딱 한번만 생성됨
    public static final Demian instance = new Demian();

    // private 생성자이므로, 외부에서 이 클래스의 인스턴스를 생성 불가능함
    private Demain() {
        ...
    }
}
```

- public 혹은 protected 생성자가 없으므로, `Demian.instance`외의 다른 인스턴스는 존재하지 않음
- 다만 reflection API를 사용하면 private 생성자를 호출할 수 있음!
    - 이를 방지하기 위해서는 두 번째 객체를 생성하려고 하면 예외를 던지도록 생성자를 설정하면 됨
- 다음 번에 소개할 방법에 비해 싱글턴임이 명확하고, 코드가 간결

## Private 생성자 - private 필드 사용

```Java
public class Demian {
    // `Private`하기 때문에 직접 접근 불가
    private static final Demian instance = new Demian();

    private Demain() {
        ...
    }

    // 정적 팩터리 메서드를 통해서만 얻을 수 있다
    public static Demian getInstance() {
        return instance;
    }
}
```

- 위와 같이 인스턴스를 private으로 설정하고 정적 팩터리 메서드를 사용하는 방법도 존재
- 이 방법을 사용하면 public 필드를 사용하는 방법과 달리 API의 변경 없이 싱글턴이 아니게 변경 가능

## 원소가 하나인 열거 타입 사용

```Java
public enum Demian {
    INSTANCE;

    public void leaveEva() {
        ...
    }
}
```

- 위와 같이 원소가 하나인 열거 타입을 사용하면, 인스턴스가 하나만 생기는 것을 완벽하게 보장함
    - 복잡한 직렬화 상황이나 리플렉션을 신경쓰지 않아도 새로운 인스턴스가 만들어 지는 일이 없음
- 이 방법이 싱글턴을 만드는 가장 좋은 방법
- 물론 이 방법을 사용한다면, 싱글턴으로 만들고자 하는 이 클래스는 다른 클래스를 상속받지는 못함

## Example

- 소스 코드와 테스트 코드는 Item03을 참고해 주세요
- 다음과 같은 싱글턴 클래스를 다뤄 보겠습니다

```Java
public class RootUser {
    private static final RootUser INSTANCE = new RootUser("admin");

    private String name;

    private RootUser(String name) {
        this.name = name;
    }

    public static RootUser getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }
}
```

- Reflection을 이용해 다음과 같이 생성자의 접근 권한을 변경해 싱글턴이 깨지는 코드를 작성 가능합니다

```Java
Constructor<RootUser> constructor = RootUser.class.getDeclaredConstructor(String.class);
constructor.setAccessible(true);

RootUser rootUser = RootUser.getInstance();
RootUser anotherUser = constructor.newInstance("attacker");

assertThat(rootUser.getName()).isNotEqualTo(anotherUser.getName());
```

- 다음과 같이 enum을 사용한다면, 어떤 방식을 사용해도 싱글턴 인스턴스 외에 다른 인스턴스를 만들 수 없습니다

```Java
public enum RootUserEnum {
    INSTANCE("admin");

    private String name;

    RootUserEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

## References

1. 조슈아 블로크 - Effective Java 3/E
