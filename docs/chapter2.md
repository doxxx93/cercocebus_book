# Chapter 2. 입출금 내역 분석기

## 목표

한 개의 클래스로 문제를 구현해보고, 바뀌는 요구사항이나 유지보수에 대응하며 기존 구조의 한계가 무엇인지 확인한다.

단일 책임 원칙(SRP)과 응집도, 결합도의 특징을 소개한다.

## 요구사항

- 은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가? 결과가 양수인가 음수인가?
- 특정 달엔 몇 건의 입출금 내역이 발생했는가?
- 지출이 가장 높은 상위 10건은 무엇인가?
- 돈을 가장 많이 소비하는 항목은 무엇인가?

## KISS 원칙

> Keep It Short and Simple

한 개의 클래스로 구현한다.

[BankTransactionAnalyzerSimple](../src/main/java/chap_02/BankTransactionAnalyzerSimple.java)클래스는 어떤
동작을 수행할까?

- 콤마로 열 분리
- 금액 추출
- 금액을 double로 파싱

### 고려할만한 예외

- 파일이 비어 있다면?
- 데이터에 문제가 있어서 금액을 파싱하지 못 한다면?
- 행의 데이터가 완벽하지 않다면?

> 3장에서 예외 처리에 대해 더 자세히 다룬다.

[BankStatementAnalyzerProblematic](../src/main/java/chap_02/BankStatementAnalyzerProblematic.java)
클래스는 주어진 월을 선택할 수 있도록 로직을 바꾸었다.

### final 변수

가능한 많은 변수를 final로 표시하면 어떤 객체의 상태가 바뀔 수 있고, 어떤 객체의 상태가 바뀔 수 없는지 명확하게 알 수 있다.

## 코드 유지보수성과 안티 패턴

구현하는 코드가 가졌으면 하는 속성을 목록으로 정리해보자.

- 특정 기능을 담당하는 코드를 쉽게 찾을 수 있어야 한다.
- 코드가 어떤 일을 수행하는지 쉽게 이해할 수 있어야 한다.
- 새로운 기능을 쉽게 추가하거나 기존 기능을 쉽게 제거할 수 있어야 한다.
- 캡슐화가 잘 되어 있어야 한다. 즉 코드 사용자에게는 세부 구현 내용이 갑춰져 있으므로 사용자가 쉽게 코드를 이해하고 기능을 바꿀 수 있어야 한다.

### 안티 패턴

1. 갓 클래스(God Class): 한 개의 파일에 모든 코드를 구현한다. 클래스의 목적이 무엇인지 이해하기 어려워진다.
2. 코드 중복: 한 문제를 작은 개별 문제로 분리해 더 쉽게 관리할 수 있는지 파악한다. 이 과정을 통해 더 이해하기 쉽고, 쉽게 유지보수하며 새로운 요구 사항도 쉽게 적용하는
   코드를 만들 수 있다.

## 단일 책임 원칙(SRP)

- 한 클래스는 한 기능만 책임진다.
- 클래스가 바뀌어야 하는 이유는 오직 하나뿐이어야 한다.

[BankStatementCSVParser.java](../src/main/java/chap_02/BankStatementCSVParser.java)는 CSV 파싱 로직을 분리한 클래스이다.

[BankTransaction.java](../src/main/java/chap_02/BankTransaction.java)는 입출금 내역을 담는 도메인 클래스이다.

[BankStatementAnalyzerSRP.java](../src/main/java/chap_02/BankStatementAnalyzerSRP.java)에서 확인할 수 있다.

## 응집도

KISS, DRY, SRP 원칙을 배웠다.

코드 품질과 관련한 특징인 응집도에 대해 알아보자.

응집도는 서로 어떻게 관련되어 있는지를 가리킨다. 응집도는 클래스나 메서드의 책임이 서로 얼마나 강하게 연결되어 있는지를 측정한다.

현재 BankStatementAnalyzerSRP 클래스는 파서, 계산, 화면으로 결과 전송 등 다양한 부분을 연결하고 있다.

하지만 계산 작업을 하는 로직은 정적 메서드로 선언되어 있다. 이는 응집도가 낮다는 것을 의미한다.

[BankStatementProcessor.java](../src/main/java/chap_02/BankStatementProcessor.java)는 계산 로직을 분리한 클래스이다.

### 클래스 수준 응집도

실무에서는 다음과 같은 여섯 가지 방법으로 그룹화한다.

1. 기능: `BankStatementCSVParser`를 구현할 때 기능이 비슷한 메서드를 그룹화했다.
2. 정보: 같은 데이터나 도메인 객체를 처리하는 메서드를 그룹화하는 방법도 있다. `BankTransaction` 객체를 CRUD하는 기능을 제공하는 클래스를 만들 수 있다.
   - `BankTransactionDAO`
3. 유틸리티: 관련성이 없는 메서드를 한 클래스로 포함한다. 
4. 논리: 같은 논리를 수행하는 메서드를 그룹화한다. SRP를 위배하는 경우가 발생할 수 있다.
5. 순차: 여러 동작이 어떻게 함께 수행되는지 쉽게 이해할 수 있지만, 한 클래스를 바꿔야할 여러 이유가 존재하게 된다.
6. 시간: 초기화, 종료 작업을 수행하는 메서드를 그룹화한다.

### 메서드 수준 응집도

일반적으로 클래스나 메서드 파라미터의 여러 필드를 바꾸는 `if/else` 블록이 여러 개 포함되어 있다면, 메서드의 응집도가 낮다고 볼 수 있다.

## 결합도

결합도는 한 기능이 다른 클래스에 얼마나 의존하고 있는지를 가늠한다. 코드가 서로 어떻게 의존하는지와 관련이 있는 척도이다.

[BankStatementParser.java](../src/main/java/chap_02/BankStatementParser.java)는 파서 인터페이스이다.

[BankStatementCSVParser.java](../src/main/java/chap_02/BankStatementCSVParser.java)는 파서 인터페이스를 구현한다.

[BankStatementAnalyzer.java](../src/main/java/chap_02/BankStatementAnalyzer.java)에서 특정 파서 구현의 결합을 제거하기
위하여, `BankTransactionParser`를 인수로 받는 `analyze()`메서드를 추가하고, 특정 구현에 종속되지 않도록 개선한다.

코드의 다양한 컴포넌트가 내부와 세부 구현에 의존하지 않도록 한다.
