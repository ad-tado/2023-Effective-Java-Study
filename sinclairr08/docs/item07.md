# Item 07 + Example

## 다 쓴 객체 참조를 해제하라

- 자바에서는 가비지 컬렉터가 존재하고, 이는 다 쓴 객체를 알아서 회수해 감
    - 프로그래머의 생산성을 높임
    - 그러나 이는 메모리 관리를 하지 않아도 된다는 뜻은 아님
- 더 이상 사용하지 않는 객체가 있어도, 참조를 남겨둔다면, gc의 대상이 되지 않음

## Stack 예시

- 보통 stack을 pop 할때 다음과 같이 구현

```Java
public Object pop() {
    if (size == 0) {
        throw new EmptyStackException();
    }

    return elements[--size];
}
```

- 이 방법을 수행하면, 원래 `element[size]` 위치에 존재하던 객체는 사용되지 않음
- 그러나 `element` 배열에서 객체에 대한 **참조** 자체는 남아있게 됨
- 따라서 gc가 객체를 회수해 가지 못하게 됨
- 다음과 같이 참조를 해제하는 코드를 추가

```Java
public Object pop() {
    if (size == 0) {
        throw new EmptyStackException();
    }

    Object result = elements[--size];
    elements[size] = null;      // 객체 대신 null을 넣어 객체에 대한 참조를 해제

    return result;
}
```

- 이 방법을 사용하면 잘못된 객체를 참조하려고 하는 경우, `NullPointerException`을 통해 확실하게 예외를 던질 수 있음

## 주의 사항

- 그러나 이렇게 null 처리를 매번 할 필요는 없음
    - null 처리는 프로그램을 필요 이상으로 지저분하게 만드므로, 예외적인 경우에만 수행하면 됨
    - 위 stack 예제와 같이 **직접 메모리를 관리하는 클래스**에서는 수행해 줘야 함
- 캐시 역시 메모리 누수를 일으킬 수 있음
    - 객체 참조를 캐시에 넣은 후, 오랜 기간 방치될 수 있기 때문
    - `WeakedHashMap`을 통해 캐시를 만들면 엔트리가 살아 있는 동안만 참조하고(약한 참조), 다 쓰이면 gc가 수거해 감
- `Listener` 혹은 `Callback` 역시 메모리 누수의 원인
    - 콜백을 등록하고 해지하지 않으면, 콜백이 계속 쌓이게 됨
    - 콜백을 약한 참조로 설정하면, gc의 수집 대상이 되어 수거해 감
- 메모리 누수는 겉으로 잘 드러나지 않기 때문에, 늘 예방법을 잘 알아야 함
    - 꼼꼼한 코드 리뷰 및 디버깅 도구를 잘 사용하는 것도 중요

## Example

- 다음과 같은 간단한 `역` 객체가 존재

```java
public class Item07Station {
    private static final String HEADER = "아무호선";
    private static final String FOOTER = "역";
    private String name;
    private final Integer id;

    public Item07Station(Integer id) {
        this.id = id;
        this.name = HEADER + id.toString() + FOOTER;
    }
}
```

- 이 역 객체를 `key`로 가지고, 정수를 `value`로 가지는 캐시를 `HashMap`으로 구현 가능
- 이렇게 10개의 객체를 해시에 넣었는데, 이 key 객체 중 9개를 다 사용한 경우가 존재할 수 있음
- 이 경우, 캐시에서도 해당 항목들이 자동으로 GC에 의해 삭제되기를 바라나, 그냥 `HashMap`을 쓰는 경우에는 수행되지 않음

```java
@DisplayName("메모리 누수가 일어나는 캐시 테스트")
@Test
void hashmap_test() throws Exception{
    Map<Item07Station, Integer> cache = new HashMap<>();
    List<Item07Station> item07Stations = new ArrayList<>();
    for (int i = 1; i < 11; i++) {
        Item07Station item07Station = new Item07Station(i * i * i * 8 + 6731);
        item07Stations.add(item07Station);
        cache.put(item07Station, i);
    }

    for (int i = 1; i < 10; i++) {
        item07Stations.remove(0);
    }

    System.gc();
    Thread.sleep(100);
    assertThat(cache.size()).isEqualTo(10);
}
```

- 다음과 같이 `WeakHashMap`을 사용하면, 다 쓴 객체에 약한 참조밖에 남아있지 않음
- `System.gc()`가 캐시의 항목들을 수거해 가고, 1개의 객체만 남게 됨

```java
@DisplayName("약한 참조로 메모리 누수를 지우는 캐시 테스트")
@Test
void weak_hashmap_test() throws Exception {
    Map<Item07Station, Integer> cache = new WeakHashMap<>();
    List<Item07Station> item07Stations = new ArrayList<>();
    for (int i = 1; i < 11; i++) {
        Item07Station item07Station = new Item07Station(i * i * i * 8 + 6731);
        item07Stations.add(item07Station);
        cache.put(item07Station, i);
    }

    for (int i = 1; i < 10; i++) {
        item07Stations.remove(0);
    }

    System.gc();
    Thread.sleep(100);
    assertThat(cache.size()).isEqualTo(1);
}
```

## 고민해 볼 점

- 처음에는 cache를 이와 반대로, ` Map<Integer, Item07Station>`으로 캐시를 만들어 봄

```java
@DisplayName("약한 참조로 메모리 누수를 지우는 캐시 테스트, 역으로 수행")
@Test
void weak_hashmap_inverse_test() throws Exception {
    Map<Integer, Item07Station> cache = new WeakHashMap<>();
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i < 11; i++) {
        Integer number = Integer.valueOf(i * i * i * 8 + 6731);
        numbers.add(number);
        cache.put(number, new Item07Station(number));
    }

    for (int i = 1; i < 10; i++) {
        numbers.remove(0);
    }

    System.gc();
    Thread.sleep(10000);
    assertThat(cache.size()).isNotEqualTo(1);
}
```

- 그러나 캐시 항목들이 시간이 지나도, GC가 수행되지 않음!
    - 이 문제에 대해서는 한 번 깊게 고민하고 조사해 보고, 추가적인 포스트를 작성할 예정
- 참고로 `value`에 `String` 혹은 `Wrapper 클래스 객체`를 넣으면 **정상적으로 GC가 동작함**
    - `key`가 `Integer`라서 발생하는 문제는 아닌 것으로 보임

## References

1. 조슈아 블로크 - Effective Java 3/E
