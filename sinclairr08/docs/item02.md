# Item 02 + Example

## 생성자에 매개변수가 많다면 빌더를 고려하라

- 생성자에 매개변수가 아주 많고, 대부분의 경우 각 필드의 기본 값이 있는 경우, 점층적 생성자 패턴 사용 가능
    - 이 패턴은 사용이 불가능한 것은 아니나, 매개변수가 많아질 수록 코드를 작성하고 읽기 어려움
- 생성자에 각 필드의 기본값을 세팅하고 이후 setter를 사용하는 방법 자바빈즈 패턴도 존재
    - 이 패턴은 객체 하나를 만들려면 setter 메서드를 여러 개 호출해야 함
    - 또한 객체가 완전히 생성되기 전에 `일관성이 깨진 상태`로 존재할 수 있다는 점이 문제
    - 또한 클래스를 immutable로 만들 수 없음
- 이런 방법들의 대안으로 나온 것이 빌더 패턴
    - 필수 매개변수만으로 생성자를 호출해 `빌더 객체`를 획득
    - 이후 빌더 객체가 제공하는 메서드들을 호출해서 매개변수를 설정
    - 마지막으로 매개변수가 없는 build 메서드를 호출해서 목표한 (불변) 객체를 획득
    - 읽고 쓰기 쉬우며, 유연하게 여러 타입의 객체를 만들 수 있음

## 계층적 클래스에서

- 계층적으로 설계된 클래스에서 각 계층의 클래스에 관련된 빌더를 멤버로 정의
- 추상 클래스는 추상 빌더를, 구현하는 클래스에서는 실제로 구현된 빌더를 작성하면 됨
- Simulated self-type 이라는 방법을 통해 하위 클래스에서는 형 변환 없이 메서드 연쇄를 지원함
    - self 타입이 없는 자바를 위한 방법. 추후 추가 설명 작성 예정

## 주의점

- 무조건적인 장점만 있는 패턴은 아님. 다음 사항을 주의해야 함
- 빌더 자체의 생성 비용이 크지는 않지만, 성능에 아주 민감한 경우 문제가 될 수는 있음
- 매개변수가 4개 이상인 경우에만 점층적 생성자 패턴 대비 장점이 있음
    - 그러나 지금 당장 매개변수가 적더라도 시간이 지나면서 나중에 많아질 수 있음
    - 확장을 대비해 처음부터 builder를 사용하는 것이 좋을 때가 많음

## Example

- 소스 코드와 테스트 코드은 Item02 패키지를 참조해 주세요
- Student 클래스는 다음과 같은 필드로 구성되어 있습니다
    - `name`,`grade`: 필수
    - `description`, `level`, `power`, `balance`: 선택
- 이제 이 Student 클래스를 각각 `점층적 생성자 패턴`, `자바 빈즈 패턴`, `빌더 패턴` 세 가지 방식으로 구현하려고 합니다

### 점층적 생성자 패턴

```Java
public StudentA(String name, int grade) {
    this(name, grade, "", 0, 0, 0);
}
public StudentA(String name, int grade, String description) {
    this(name, grade, description, 0, 0, 0);
}
public StudentA(String name, int grade, String description, int level) {
    this(name, grade, description, level, 0, 0);
}
public StudentA(String name, int grade, String description, int level, int power) {
    this(name, grade, description, level, power, 0);
}
public StudentA(String name, int grade, String description, int level, int power, int balance) {
    this.name = name;
    this.grade = grade;
    this.description = description;
    this.level = level;
    this.power = power;
    this.balance = balance;
}
```

- `name`, `grade`는 반드시 입력받아야 하는 필드이므로 입력을 받고, 점층적 생성자 패턴을 통해 모든 경우의 생성자를 만들면 됩니다
- 필수가 아닌 필드의 경우, 값을 입력하지 않으면 기본 값이 들어가게 됩니다
- 문제는, 이 방식은 순서에 아주 큰 영향을 받는다는 점입니다
    - 저희가 `level`과 `balance` 필드만 값을 주고, `description`과 `power` 필드는 값을 기본 값으로 설정하고 싶은 경우 다음과 같이 구현하게 됩니다

```Java
StudentA s1 = new StudentA("ej", 1, "", 100, 0, 5000);
```

- `description`과 `power` 필드는, 순서상 `balance` 앞에 위치해 있기 때문에, 기본 값을 개발자가 설정해 줘야 하는 상황이 발생합니다
- 필드가 아주 많은 경우, 마지막 필드에만 값을 넣고 싶다면, 앞의 모든 값에 다 기본 값을 넣어줘야 하므로, 굉장히 힘듭니다.
- 한 번에 객체가 생성되기 때문에, 멀티스레드에서 문제가 생길 일은 적습니다

### 자바 빈즈 패턴

```Java
public StudentB() {
}

public void setName(String name) {
    this.name = name;
}

public void setGrade(int grade) {
    this.grade = grade;
}

public void setDescription(String description) {
    this.description = description;
}

public void setLevel(int level) {
    this.level = level;
}

public void setPower(int power) {
    this.power = power;
}

public void setBalance(int balance) {
    this.balance = balance;
}

public int getBalance() {
    return balance;
}

public void addMoney(int money) {
    this.balance += money;
}
```

- 이 방법은 setter를 이용하고, 다음과 같이 사용해서 값을 수정할 수 있습니다

```Java
StudentB s2 = new StudentB();
s2.setName("ej");
s2.setGrade(3);
s2.setLevel(100);
s2.setBalance(5000);
```

- 위와 같이 필요한 필드에만 값을 입력하면 되므로, 훨씬 편하다는 장점이 있습니다.
- 그러나 다음과 같이 멀티스레드에서 문제가 발생할 수 있습니다.

```Java
StudentB s2 = new StudentB();
for (int i = 0; i < 5; i++) {
    if (i == 0) {
        new Thread() {
            public void run() {
                s2.setName("ej");
                s2.setGrade(3);
                s2.setLevel(100);
                s2.setBalance(5000);
            }
        }.start();
    }

    else {
        new Thread() {
            public void run() {
                s2.addMoney(500);
            }
        }.start();
    }
}

// Following result should be the one of (5000, 5500, 6000, 6500, 7000)
System.out.println(s2.getBalance());
```

- `s2.addMoney(500)` 은 잔고에 500원을 더하는 코드인데, 이는 `s2.setBalance(5000)` 이전에 실행될 수 있습니다.
- 따라서 스레드의 실행 순서에 따라 실행 결과가 다르게 나오는 문제가 발생합니다

### 빌더 패턴

- 다음은 StudentC 클래스 내에 `static`한 `StudenctCBuilder` 클래스를 만드는 예시입니다

```Java
// static class
public static class StudentCBuilder {
    private final String name;      // 필수, 초기화 하지 않음
    private final int grade;        // 필수, 초기화 하지 않음
    private String description = "";
    private int level = 0;
    private int power = 0;
    private int balance = 0;

    public StudentCBuilder(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public StudentCBuilder description(String val) {
        this.description = val;
        return this;
    }

    public StudentCBuilder level(int val) {
        this.level = val;
        return this;
    }

    public StudentCBuilder power(int val) {
        this.power = val;
        return this;
    }

    public StudentCBuilder balance(int val) {
        this.balance = val;
        return this;
    }

    public StudentC build() {
        return new StudentC(this);
    }
}

private StudentC(StudentCBuilder builder) {
    this.name = builder.name;
    this.grade = builder.grade;
    this.description = builder.description;
    this.level = builder.level;
    this.power = builder.power;
    this.balance = builder.balance;
}
```

- 위와 같이 StudentCBuilder의 여러 함수를 통해 값을 세팅하고, `build` 함수를 통해 객체를 만듭니다

```Java
StudentC s3 = new StudentC.StudentCBuilder("ej", 5)
        .level(100)
        .balance(5000)
        .build();
```

- 이 방법은 StudentCBuilder 객체를 계속 만들다가, `build` 메서드가 수행되는 시점에 `StudentC` 객체가 만들어지므로, 멀티스레드에서도 비교적 값의 변경에 안전합니다
- 물론 당연하게도 코드를 작성하는 데 많은 노력이 들어가므로, 최소 인자가 4개 이상일 때 부터 사용하는 것이 권장됩니다

## 각 패턴의 장단점 정리

|     패턴      |                                         장점                                         |                                            단점                                             |
| :-----------: | :----------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------: |
| 점층적 생성자 | 생성자 한 번의 호출로 객체가 만들어지기 때문에, 멀티스레드 환경에서의 안정성이 높음  |                        필드의 수가 많아질 수록 코드를 작성하기 힘듦                         |
|   자바 빈즈   |             기본 값이 아닌 필드만 수정하면 되므로, 코드의 가독성이 높음              | 객체가 일관성이 무너진 상태에 존재할 수 있으므로, 멀티스레드 환경에서 문제가 발생할 수 있음 |
|     빌더      | 불변 객체를 만들기 쉬우며, 유연하게 객체를 만들기 쉬움. 멀티스레드에서도 영향이 적음 |                        매개변수가 너무 적은 경우에는 오히려 비효율적                        |

## References

1. 조슈아 블로크 - Effective Java 3/E
