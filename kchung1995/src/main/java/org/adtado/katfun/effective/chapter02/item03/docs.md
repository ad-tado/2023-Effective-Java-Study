# Item 03. private 생성자나 열거 타입으로 싱글턴임을 보증하라

## 1. public static final 필드 방식의 싱글턴

`Item03FinalSingletonTest`를 참고하시면 싱글톤임을 확인하고, reflection을 통해 이를 깰 수 있음을 확인할 수 있습니다.  
`Item03FinalSingletonDefenseTest`를 참고하시면 reflection 방어 코드를 통해 2개 이상이 인스턴스 생성을 막는 것을 확인할 수 있습니다.

## 2. 정적 팩터리 방식의 싱글턴

`Item03FactorySingletonTest`를 참고하시면 싱글톤임을 확인하고, reflection을 통해 이를 깰 수 있음을 확인할 수 있습니다.  
`Item03FactorySingletonDefenseTest`를 참고하시면 reflection 방어 코드를 통해 2개 이상이 인스턴스 생성을 막는 것을 확인할 수 있습니다.

## 3. 원소가 1개인 Enum 타입 방식의 싱글턴

`Item03EnumSingletonTest`를 참고하시면 싱글톤임을 확인할 수 있습니다.  