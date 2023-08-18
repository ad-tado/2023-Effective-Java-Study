# Item 06 + Example

## 불필요한 객체 생성을 피하라

- 불필요하게 객체를 생성하는 과정을 줄이는 것이 좋음
- 만약 똑같은 기능의 객체를 여러번 사용해야 한다면, 매번 생성하기보다 객체 하나를 재사용하는 편이 나을 때가 많음
    - 특히 불변 객체는 재사용이 쉬움
- 다음과 같은 여러 예시 존재

## 불필요한 객체 생성 예시 - String

- 다음 코드는 생성자에 인자로 넘겨진 `"bikini"` 와 생성된 `s`가 완전히 똑같음

```Java
// 불필요한 객체 생성의 예시
String s = new String("bikini");
```

- 따라서, 불필요한 객체 생성을 수행할 필요 없이 다음과 같이 쓰면 됨

```Java
// 다음과 같이 쓰면 됨
String s = "bikini";
```

## 비싼 객체 생성 예시

- 다음 코드는 매 번 함수가 호출될 때마다 `String.matches` 함수가 실행됨
- 이 `matches` 함수는 내부에서 Pattern 객체를 생성하고 버리므로, 반복 사용에 적합하지 않음

```Java
static boolean isValidUserName(String s) {
    return s.matches(...);
}
```

- 다음과 같이 static한 Pattern 객체를 만들고, 매번 재사용하면 됨
- 불필요한 객체 생성을 막을 수 있을 뿐만 아니라, 코드도 더 명확해짐

```Java
public class UserName {
    private static final Pattern NAMEPATTERN = Pattern.compile(...);

    static boolean isValidUserName(String s) {
        return NAMEPATTERN.matcher(s).matches();
    }
}
```

## 불필요한 객체 생성 - Auto Boxing

- 오토 박싱은 primitive type과 boxing된 primitive type을 자동으로 변환해 주는 기술
- 의미상으로는 동일하지만, 성능상으로 동일하지는 않음

```Java
private static long sum() {
    Long sum = 0L;

    for (long i = 0; i <= Integer.MAX_VALUE; i++) {
        sum += i;
    }

    return sum;
}
```

- 위 예시는 `sum += i` 부분에서, auto boxing이 일어남
    - `long` 타입인 `i` 가, `Long`으로 바뀌면서 객체가 생성되는 과정이 발생
    - 이 때문에 엄청난 성능 저하 발생
- `Long sum = 0L;` 부분에서 `Long`을 `long`으로 바꾸기만 하면 엄청난 성능 향상
    - 객체 생성이 일어나지 않기 때문
- 따라서 boxing된 primitive type보다는 기본 타입을 사용하자

## 주의사항

- 객체 생성이 비싸니 피해야 한다는 뜻이 아님! 불필요한 객체 생성을 피하자는 뜻
    - 최신 JVM은 작은 객체 하나를 만드는 데 그렇게 큰 비용이 들지는 않음
- 아주 무거운 객체가 아닌 이상, custom한 객체 pool을 만들 필요가 없음
    - 직접 만들다가 잘못 만들면 오히려 메모리 성능이 저하됨
- **기존 객체를 재사용할 수 있다면 새로운 객체를 만들지 마라**는 **새로운 객체를 만들 때 기존 객체를 재사용하라는** 뜻은 아님
    - 이 부분은 Item 50에서 작성 예정

## Example

- 역 정보를 가지고 있는 `Item06Station` 클래스가 존재함

```java
public class Item06Station {
    private final String name;

    public Item06Station(String name) {
        this.name = name;
        
        // Very heavy logic
        Item06StationRepository.register(name);
    }

    public String getName() {
        return name;
    }

    public String visit() {
        return this.name + "방문" + "\n";
    }
}
```

- 이 클래스는 새로 생성될 때 마다 등록되는 굉장히 무거운 로직을 거치고, 이 때문에 한 번 객체를 생성하는 비용이 굉장히 비쌈
- 매 번 최소 `10000`번의 반복문을 돌아야 하기 때문

```java
public class Item06StationRepository {
    private static List<String> stationNames = init();
    private static List<String> init() {
        List<String> initialStationNames = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            initialStationNames.add(String.valueOf(i));
        }
        return initialStationNames;
    }
    
    public static void register(String name) {
        for (String stationName : stationNames) {
            if (stationName == name) {
                break;
            }
        }
        stationNames.add(name);
    }
}
```

- 10개의 이름 중 하나의 무작위 이름을 뽑아서 반환해 주는 `getRandomName()` 함수가 있다고 생각해 보자
- 이 10개의 역 중 랜덤한 역을 총 N번 방문하는 테스트 코드를 구현하고 싶음
- 이 경우, 다음과 같이 매 번 역을 방문할 때 마다 `Item06Station` 객체를 만드는 다음과 같은 코드는 굉장히 비효율적

```java
Instant start = Instant.now();
for (int i = 0; i < 100000; i++) {
    Item06Station item06Station = new Item06Station(getRandomName());
    item06Station.visit();
}
Instant end = Instant.now();

System.out.println(Duration.between(start, end).toMillis());
```

- 따라서 다음과 같이 `Item06StationCache`와 같은 클래스를 통해 생성될 역 이름에 대한 객체를 미리 생성해 두고 재활용하는 방법 존재
    - `name` 필드가 final 이기 때문에, 불변 역시 보장됨
- 이렇게 되면 처음 초기화한 후 불필요한 새 객체를 만들 필요가 없기 때문에, 메모리 및 시간적인 측면에서 굉장히 이득이 됨

```java
    List<Item06Station> stations = new ArrayList<>();
    Map<String, Integer> indices = new HashMap<>();

    public Item06StationCache(List<String> names) {
        int i = 0;
        for (String name : names) {
            this.stations.add(new Item06Station(name));
            this.indices.put(name, i++);
        }
    }

    public Item06Station from(String name) {
        return this.stations.get(indices.get(name));
    }
```

- 마찬가지로 아래와 같이 테스트 해 볼 수 있음

```java
Instant start = Instant.now();
Item06StationCache item06StationCache = new Item06StationCache(visitStationNames);
for (int i = 0; i < 100000; i++) {
    Item06Station item06Station =  item06StationCache.from(getRandomName());
    item06Station.visit();
}
Instant end = Instant.now();
System.out.println(Duration.between(start, end).toMillis());
```

- 결과: 객체를 매번 생성하는 경우, 객체를 재활용하는 것에 비해 반복 횟수가 커질 수록 소요 시간이 큰 비율로 증가하는 것을 확인 가능

| 반복 횟수 | 매번 객채 생성 시 소요 시간 | 객체를 재활용할 시 소요 시간 |
| :-------: | :-------------------------: | :--------------------------: |
|   1,000   |            24ms             |             8ms              |
|  10,000   |            79ms             |             11ms             |
|  100,000  |            604ms            |             22ms             |

## References

1. 조슈아 블로크 - Effective Java 3/E