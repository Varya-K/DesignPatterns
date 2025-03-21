# Адаптер - Adapter

Категория: Структурный

**Адаптер** — это структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе.

---

# 🛠️ Структура

### Адаптер объектов

Эта реализация использует агрегацию: объект адаптера “оборачивает”, то есть содержит ссылку на служебный объект. Такой подход работает во всех языках программирования.

![image](https://github.com/user-attachments/assets/b052a09b-f30f-4b23-bbd3-3f8fdeecf1c9)


1. **Client** - это класс, который содержит существующую бизнес-логику программы.
2. **Client Interface** описывает протокол, через который Client может работать с другими классами.
3. **Service** - это какой-то полезный класс, обычно сторонний. Client не может использовать этот класс напрямую, так как Service имеет непонятный ему интерфейс.
4. **Adapter** - это класс, который может одновременно работать и с клиентом и с сервисом. Он реализует Client Interface и содержит ссылку на объект Service. Adapter получает вызовы от Client через методы Client Interface, а затем переводит их в вызовы методов обёрнутого объекта в правильном формате.
5. Работая с адаптером через интерфейс, клиент не привязывается к конкретному классу адаптера. Благодаря этому, вы можете добавлять в программу новые виды адаптеров, независимо от клиентского кода. Это может пригодиться, если интерфейс сервиса вдруг измениться, например, после выхода новой версии сторонней библиотеки. 

### Адаптер классов

Эта реализация базируется на наследовании: адаптер наследует оба интерфейса одновременно.

![image 1](https://github.com/user-attachments/assets/fc2b5c98-91b8-4215-8614-b31dce4c5807)


1. Adapter не нуждается во вложенном объекте, так как он может одновременно наследовать и часть существующего класса, и часть сервиса

# ⌗ Псевдокод

В этом шуточном примере **Адаптер** преобразует один интерфейс в другой, позволяя совместить квадратные колышки и круглые отверстия.

![image 2](https://github.com/user-attachments/assets/18aa919d-9e86-4a7a-a76d-875908515f77)


Адаптер вычисляет наименьший радиус окружности, в которую можно вписать квадратный колышек, и представляет его как круглый колышек с этим радиусом.

```
// Классы с совместимыми интерфейсами: RoundHole и RoundPeg.
class RoundHole is 
	constructor RoundHole(radius) { ... }

	method getRadius() is// Вернуть радиус отверстия.

	method fits(peg: RoundPeg) is
		return this.getRadius() >= peg.getRadius()

class RoundPeg is
	constructor RoundPeg(radius) { ... }

	method getRadius() is// Вернуть радиус круглого колышка.

// Устаревший, несовместимый класс: SquerePeg.
class SquarePeg is
	constructor SquarePeg(width) { ... }

	method getWidth() is // Вернуть ширину квадратного колышка.

// Адаптер позволяет использовать квадратные колышки и круглые
// отверстия вместе.
class SquarePegAdapter extends RoundPeg is 
	private field peg: SquarePeg

	constructor SquarePegAdapter(peg: SquarePeg) is
		this.peg = peg

	method getRadius() is
		// Вычислить половину диагонали квадратного колышка по
		// теореме Пифагора.
		return peg.getWidth() * Math.sqrt(2) / 2

// Где-то в клиентском коде.
hole = new RoundHole(5)
rpeg = new RoundPeg(5)
hole.fits(rpeg) // TRUE

small_sqpeg =new SquarePeg(5)
large_sqpeg =new SquarePeg(10)
hole.fits(small_sqpeg) // Ошибка компиляции, несовместимые типы

small_sqpeg_adapter = new SquarePegAdapter(small_sqpeg)
large_sqpeg_adapter = new SquarePegAdapter(large_sqpeg)
hole.fits(small_sqpeg_adapter) // TRUE
hole.fits(large_sqpeg_adapter) // FALSE
```
