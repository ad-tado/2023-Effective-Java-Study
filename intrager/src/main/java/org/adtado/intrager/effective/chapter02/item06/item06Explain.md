# Item06 불필요한 객체 생성을 피하라

## 문자열 객체 생성

---
자바의 문자열, String을 `new`로 생성하면 항상 새로운 객체를 만들게 됩니다. 다음과 같이 String 객체를 생성하는 것이 올바릅니다.
```java
String s = "brucehan";
```

문자열 리터럴을 재사용하기 때문에 해당 JVM에 동일한 문자열 리터럴이 존재한다면 그 리터럴을 재사용합니다.

## static 팩토리 메서드 사용하기

---
Java 9에서 deprecated된 `Boolean(String)` 대신 `Boolean.valueOf(String)` 같은 static 팩토리 메서드를 사용할 수 있습니다.
생성자는 반드시 새로운 객체를 만들어야 하지만 팩토리 메서드는 그렇지 않습니다.

## 무거운 객체

---
만드는데 메모리나 시간이 오래 걸리는 객체, 
즉 `비싼 객체`를 반복적으로 만들어야 한다면 캐시해두고 재사용할 수 있는지 고려하는 것이 좋다.

정규 표현식을 잠깐 예시로 살펴보겠습니다. 문자열이 로마 숫자를 표현하는지 확인하는 코드는 다음과 같습니다.
```java
static boolean isRomanNumeral(String s) {
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");    
}
```

`String.matches`가 가장 쉽게 정규 표현식에 일치하는지 확인하는 방법이지만, 성능이 중요한 상황에서는 반복해서 사용하기에 적절하지 않습니다.

`String.matches`는 내부적으로 `Pattern` 객체를 만들어 쓰는데, 그 객체를 만들려면 정규 표현식으로 [유한 상태 기계](https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%95%9C_%EC%83%81%ED%83%9C_%EA%B8%B0%EA%B3%84)로 컴파일하는 과정이 필요합니다.
즉, 비싼 객체입니다.

성능을 개선하려면 `Pattern` 객체를 만들어 재사용하는 것이 좋습니다.

```java
public class RomanNumber {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    
    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```

하지만 이 코드에도 문제가 있습니다.
`isRomanNumeral()`이 호출되지 않는다면 불필요하게 `ROMAN`을 생성하는 뻘짓이 됩니다.
Lazy Initializing을 사용해서 최적화할 수 있지만 추천하지 않는 방법이라고 합니다.
보통 지연 초기화는 측정 가능한 성능 개선 없이 구현을 복잡하게 만듭니다.

## 어댑터

---
불변 객체인 경우에 안정하게 재사용하는 것이 명확합니다.
하지만, 몇몇 경우에 분명하지 않는 경우가 있어 오히려 반대로 보이기도 합니다.

어댑터를 예로 들면, 어댑터는 인터페이스를 통해서 뒤에 있는 객체로 연결해줘서 여러 개 만들 필요가 없습니다.

`Map` 인터페이스가 제공하는 `keySet()`은 `Map`이 뒤에 있는 `Set` 인터페이스의 View를 제공합니다.

`keySet()`을 호출할 때마다 새로운 객체가 나올 것 같지만 사실 같은 객체를 반환합니다.
그렇기 때문에 반환 받은 `Set` 타입의 객체를 변경하면, 결국에는 그 뒤에 있는 `Map` 객체를 변경하게 됩니다.

```java
public class UsingKeySet {
    public static void main(String[] args) {
        Map<String, Integer> menu = new HashMap<>();
        menu.put("Burger", 8);
        menu.put("Pizza", 9);
        
        Set<String> names1 = menu.keySet();
        Set<String> names2 = menu.keySet();
        
        names1.remove("Burger");
        System.out.println(names2.size()); // 1
        System.out.println(menu.size());   // 1
    }
}
```

## 오토박싱

---
불필요한 객체를 생성하는 또 다른 방법으로는 AutoBoxing이 있습니다.
오토박싱은 프로그래머가 primitive type과 Wrapper type을 섞어 쓸 수 있게 해주고, Boxing과 Unboxing을 자동으로 해줍니다.

오토박싱은 primitive type과 Wrapper 타입의 경계가 안 보이게 해주지만 그렇다고 그 경계를 없애주지는 않습니다.

예시로 `Item06AutoBoxing`의 `sumWithWrapperType()`과 `sumWithPrimitiveType()`을 참고해주세요.
테스트로 `Item06AutoBoxingTest`를 참고해주세요.

위 코드를 참고해보면, `sum`변수의 타입을 `Long`으로 만들었기 때문에, 불필요한 Long 객체를 2<sup>31</sup>개만큼 만들게 되고, 대략 6초쯤 걸립니다.
타입을 프리미티브 타입으로 바꾸면 900 밀리초로 대략 6~7배의 차이가 납니다.

### 불필요한 오토박싱을 피하려면 박스 타입보다는 프리미티브 타입을 사용해야 합니다.

이번 아이템으로 인해 객체를 만드는 것은 비싸며, 가급적이면 피해야 한다는 오해를 해서는 안 됩니다.
특히 방어적인 복사(Defensive copying)를 해야하는 경우에도 객체를 재사용하면 심각한 버그와 보안성에 문제가 생길 수 있습니다.
객체를 생성하면 그저 스타일과 성능에 영향을 줄 뿐이라고 합니다.


## 객체를 생성할 때 불필요한 객체가 생성되는 경우를 작성해 주세요. 
### 해당 경우에 불필요한 객체 생성을 피한다면 어느 만큼의 이득을 보는지 수치로 검증할 수 있는 테스트 코드를 같이 작성해 주세요.

테스트인 `Item06AutoBoxingTest`를 참고해주세요.