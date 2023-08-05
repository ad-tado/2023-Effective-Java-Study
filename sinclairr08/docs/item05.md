# Item 05 + Example

## 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

- 클래스는 내부적으로 하나 이상의 자원 (다른 클래스)에 의존하는 경우가 많음
- 이 자원에 따라 사용하는 동작이 달라지는 경우가 존재 가능
    - 이 때는 정적 유틸리티 클래스 혹은 싱글턴을 사용하는 것은 좋은 선택이 아님
    - 또한 클래스가 직접 자원을 만들게 하는 것도 좋은 방법이 아님 (유연성이 떨어짐)
- 이를 해결하기 위해 의존 객체 주입 방식을 사용하면 됨
    - 대표적인 의존 객체 주입 방식은 인스턴스를 생성할 때, 생성자에 필요한 자원을 넘겨주면 됨
    - 혹은 생성자에 `자원`의 팩터리를 넘겨주는 방법이 존재
- 그러나 이렇게 의존성을 주입하는 방식은 코드를 과하게 복잡하게 만들 수 있음
    - Dagger, Guice, Spring 등의 의존 객체 주입 프레임워크를 활용하면 이 문제를 해결 가능

## Example

- 모든 역은 이름과 id를 가짐

```java
public abstract class Station {
    int stationId;
    String stationName;
    public Station(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }
    abstract String getStationInfo();
}
```

- 역은 `환승역`과, 환승역이 아닌 `일반역`으로 구분됨
- 일반역은 하나의 호선과, 해당 호선 내의 번호를 가짐

```java
private String lineName;
private int lineIndex;

public NormalStation(int stationId, String stationName, String lineName, int lineIndex) {
    super(stationId, stationName);
    this.lineName = lineName;
    this.lineIndex = lineIndex;
}

@Override
String getStationInfo() {
    String result = String.format("이번 역은 %s 호선의 %s 번째 역인 %s역 입니다",
                                  this.lineName,
                                  this.lineIndex,
                                  this.stationName
                                 );
    return result;
}
```

- 환승역은 여러 호선과, 각각의 호선에 대한 번호를 가짐

```java
private List<String> lineNames;
private List<Integer> lineIndices;

public TransferStation(int stationId, String stationName, List<String> lineNames, List<Integer> lineIndices) {
    super(stationId, stationName);
    this.lineNames = lineNames;
    this.lineIndices = lineIndices;
}

@Override
public String getStationInfo() {
    String result = "이번 역은 ";

    for (String lineName : lineNames) {
        if (lineName.equals(lineNames.get(0))) {
            result += lineName;
            continue;
        }
        result += ", " + lineName;
    }

    result += " 호선이 교차하는 " + this.stationName + "역 입니다";
    return result;
}
```

- 이제 각 역 정보를 활용하는 `stationService`를 가정해 볼 수 있음
- 이 `stationService`에, `Station`클래스를 주입하는 방식으로 구현해도 됨
- 그러나 문제에서 제시한 대로 `한정적 와일드 카드`와 `Supplier`를 활용해 다음과 같이 팩터리를 주입하는 방법도 있음
    - 여담이지만 이 방법을 통해 **Station의 하위 타입 인스턴스를** 얻을 수 있고, **Lazy한 연산**도 가능함

```java
private Supplier<? extends Station> stationFactory;

public StationService(Supplier<? extends Station> stationFactory) {
    this.stationFactory = stationFactory;
}
```

- 다음과 같이 환승역, 일반역 중 하나를 선택해 이를 공급해 주는 팩토리를 주입하면 됨

```java
// 환승역
TransferStation miguem = new TransferStation(178, "미금", List.of("수인분당", "신분당"), List.of(231, 13));

Supplier<? extends Station> transferStationSupplier = new Supplier<TransferStation>() {
    @Override
    public TransferStation get() {
        return miguem;
    }
};

StationService stationService = new StationService(transferStationSupplier);        

// 일반역
NormalStation sanghyun = new NormalStation(289, "상현", "신분당", 17);

Supplier<? extends Station> transferStationSupplier = new Supplier<NormalStation>() {
    @Override
    public NormalStation get() {
        return sanghyun;
    }
};

StationService stationService = new StationService(transferStationSupplier);
```

## References

1. 조슈아 블로크 - Effective Java 3/E
