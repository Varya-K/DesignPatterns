# Стратегия - Strategy

Категория: Поведенческий

**Стратегия** — это поведенческий паттерн проектирования, который определяет семейство схожих алгоритмов и помещает каждый из них в собственный класс, после чего алгоритмы можно взаимозаменять прямо во время исполнения программы.

---

# 🛠️ Структура

![image](https://github.com/user-attachments/assets/fc4eecc1-5da7-4751-a026-c1206fa11ec3)


1. **Context** хранит ссылку на объект конкретной стратегии, работая с ним через общий интерфейс стратегий.
2. **Strategy** определяет интерфейс, общий для всех вариаций алгоритма. Контекст использует этот интерфейс для вызова алгоритма.
    
    Для контекста неважно, какая именно вариация алгоритма будет выбрана, так как все они имеют одинаковый интерфейс.
    
3. **Concrete Strategies** реализуют различные вариации алгоритма.
4. Во время выполнения программы контекст получает вызовы от клиента и делегирует их объекту конкретной стратегии.
5. **Client** должен создать объект конкретной стратегии и передать его в конструктор контекста. Кроме этого, клиент должен иметь возможность заменить стратегию на лету, используя сеттер. Благодаря этому, контекст не будет знать о том, какая именно стратегия сейчас выбрана.

---

# ⌗ Псевдокод

В этом примере контекст использует **Стратегию** для выполнения той или иной арифметической операции.

```
// Общий интерфейс всех стратегий.
interface Strategy is
	method execute(a, b)

// Каждая конкретная стратегия реализует общий интерфейс своим
// способом.
class ConcreteStrategyAdd implements Strategy is
	method execute(a, b) is
		return a + b

class ConcreteStrategySubtract implements Strategy is
	method execute(a, b) is
		return a - b

class ConcreteStrategyMultiply implements Strategy is
	method execute(a, b) is
		return a * b

// Контекст всегда работает со стратегиями через общий
// интерфейс. Он не знает, какая именно стратегия ему подана.
class Context is
	private strategy: Strategy

	method setStrategy(Strategy strategy) is
		this.strategy = strategy

	method executeStrategy(int a, int b) is
		return strategy.execute(a, b)

// Конкретная стратегия выбирается на более высоком уровне,
// например, конфигуратором всего приложения. Готовый объект-
// стратегия подаётся в клиентский объект, а затем может быть
// заменён другой стратегией в любой момент на лету.
class ExampleApplication is
	method main() is
		// 1. Создать объект контекста.
    // 2. Получить первое число (n1).
    // 3. Получить второе число (n2).
    // 4. Получить желаемую операцию.
    // 5. Затем, выбрать стратегию:

		if (action == addition) then
			context.setStrategy(new ConcreteStrategyAdd())

		if (action == subtraction) then
			context.setStrategy(new ConcreteStrategySubtract())

		if (action == multiplication) then
			context.setStrategy(new ConcreteStrategyMultiply())

		// 6. Выполнить операцию с помощью стратегии:
    result = context.executeStrategy(n1, n2)

    // 7. Вывести результат на экран.
```
