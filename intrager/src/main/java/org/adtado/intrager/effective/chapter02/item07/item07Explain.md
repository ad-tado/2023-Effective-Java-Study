# Item07 다 쓴 객체 참조를 해제하라

-----

## 메모리 누수가 일어나는 위치는 어디인가?

---

Java에 Garbage Collector가 있기 때문에 메모리 관리에 대해 신경쓰지 않아도 될 것이라고 생각하기 쉽지만, 그렇지 않을 수 있습니다.

스택 자료구조를 토대로 예시를 들겠습니다.

```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        this.ensureCapacity();
        this.elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /**
     * Ensure space for at least one more elements,
     * roughly doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if(this.elements.length == size) {
            this.elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
```

스택에 계속 쌓다가 빼냈다고 했을 때, 
스택이 차지하고 있는 메모리는 줄어들지 않습니다. 이는 위 스택의 구현체는 필요없는 객체에 대한 레퍼런스를 그대로 가지고 있기 때문입니다.

가용한 범위는 `size` 보다 작은 부분이고, 이보다 큰 부분에 있는 값들은 불필요하게 메모리를 차지하고 있는 부분입니다.

누수의 근원인 `pop()`을 수정하면 다음과 같습니다.
```java
public Object pop() {
    if(size == 0) {
        throw new EmptyStackException();    
    }    
    
    Object value = this.elements[--size];
    this.elements[size] = null;
    return value;
}
```

스택에서 꺼낼 때 그 위치에 있는 객체를 꺼내주고 그 자리를 `null`로 설정해서 다음 GC가 발생할 때 레퍼런스가 정리되게 합니다.
실수로 해당 위치에 있는 객체를 다시 꺼내는 경우에는 `NullPointerException`이 발생할 수 있기는 하지만,
그 자리에 있는 객체를 비우지 않고 실수로 잘못된 객체를 돌려주는 것보다는 괜찮습니다.
그렇다고 필요없는 객체를 볼 때마다 `null`로 설정하는 코드를 작성하는 건 지양해야 합니다. 
객체를 `null`로 설정하는 건 예외적인 상황에서 아주 가끔씩 해줘야합니다.

필요없는 객체 레퍼런스를 정리하는 최선책은 그 레퍼런스를 가리키는 변수를 특정한 범위(스코프) 안에서만 사용하는 것입니다.
로컬 변수는 그 영역을 넘어가면 정리되기 때문이죠.
변수를 가능한 가장 최소의 범위(scope)로 사용하면 자연스럽게 그렇게 될 것입니다.


그러면 언제 레퍼런스를 `null`로 설정해야 할까요?
**메모리를 직접 관리할 때**입니다.
`Stack` 구현체처럼 `elements`라는 배열을 관리하는 경우에 GC는 어떤 객체가 필요없는 객체인지 알 수 없습니다.
오직 프로그래머만 `elements`에서 가용한 부분(size보다 작은 부분)과 필요없는 부분(size보다 큰 부분)을 알 수 있습니다.

따라서, 프로그래머가 해당 레퍼런스를 `null`로 만들어서 GC한테 필요없는 객체들이라고 알려줘야 합니다.

**메모리를 직접 관리하는 클래스를 다룰 때 프로그래머는 메모리 누수를 조심해야 합니다.**

## 캐시 역시 메모리 누수를 일으키는 주범

---

캐시를 사용할 때도 메모리 누수 문제를 조심해야 합니다. 객체의 레퍼런스를 캐시에 넣어 놓고 캐시를 비우는 것을 잊기 쉽습니다.
여러 해결책이 있지만, 
**캐시의 키**에 대한 레퍼런스가 캐시 밖에서 필요 없어지면 해당 엔트리를 캐시에서 자동으로 비워주는 `WeakHashMap`을 쓸 수 있습니다.

또는 특정 시간이 지나면 캐시값이 의미가 없어지는 경우에 `ScheduledThreadPoolExecutor`로 백그라운드 쓰레드를 사용하거나,
새로운 엔트리를 추가할 때 부가적인 작업으로 기존 캐시를 비우는 일을 할 것입니다.
`LinkedHashMap`의 `removeEldestEntry()`를 캐시에 새 엔트리를 추가할 때 부수 작업으로 활용할 수 있습니다.


## 콜백도 역시 메모리 누수를 일으키는 주범

---

세번째로 흔하게 메모리 누수가 발생할 수 있는 지점으로 Listener와 Callback이 있습니다.

클라이언트 코드가 콜백을 등록할 수 있는 API를 만들고 콜백을 뺄 수 있는 방법을 제공하지 않는다면, 계속해서 콜백이 쌓일 것입니다.
이 역시 `WeakHashMap`을 사용해서 해결할 수 있습니다.


## 메모리 누수를 일으키는 주범 중 하나를 골라 누수를 방지하는 코드를 작성하세요.

`Item07CacheKeyTest`를 참고해주세요.

2번째 발생 원인인 `캐시`를 선택해 누수를 방지하는 코드를 작성했습니다.

- 메모리 누수가 일어나는 테스트
- 약한 참조로 메모리 누수를 방지하는 테스트
- 약한 참조를 역으로 수행했지만 메모리 누수를 방어하지 못하는 테스트

HashMap 객체의 Key의 타입이 참조하는 타입일 때, 

테스트를 하면서 느낀 점
- GC는 약한 참조로 되어있는 객체의 생명주기가 끝날 때 회수해가는 것을 깨달았습니다.
- 그런데 Map같은 경우에 key가 `null`이어도 value 부분에서 또 다른 Wrapper를 참조하고 있다면, GC는 그걸 회수해가지 않습니다.
  - `약한 참조를 역으로 수행했지만 메모리 누수를 방어하지 못하는 테스트` 에서의 그 예를 들었습니다. 
- map 객체의 key 자체를 `remove()`로 지우는 건 매핑을 제거하는 것입니다. 공식문서에서도 map이 key를 `null`에 매핑했을 수도 있다고 합니다.








