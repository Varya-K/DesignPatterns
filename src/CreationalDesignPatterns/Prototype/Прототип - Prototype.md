# Прототип - Prototype

Категория: Порождающий

**Прототип** — это порождающий паттерн проектирования, который позволяет копировать объекты, не вдаваясь в подробности их реализации.

---

# 🛠️ Структура

### Базовая реализация

![image](https://github.com/user-attachments/assets/275e5da3-ae14-45de-b618-66b43a24000f)


1. **Interface Prototype** описывает операции клонирования. В большинстве случаев - это единственный метод `clone`.
2. **Concrete Prototype** реализует операцию клонирования себя. Помимо банального копирования всех поле, здесь могут быть спрятаны различные сложности, о которых не нужно знать клиенту. Например, клонирование связанных объектов, распутывание рекурсивных зависимостей и прочее.
3. **Client** создает копию объекта, обращаясь к нему через общий интерфейс прототипов.

### Реализация с общим хранилищем прототипов

![image 1](https://github.com/user-attachments/assets/eb69dbf6-3681-4617-9779-14faeb07d1a7)


1. **Prototype Registry** облегчает доступ к часто используемым прототипам, храня набор предварительно созданных эталонных, готовых к копированию объектов. Простейшее хранилище может быть построено с помощью хеш-таблицы вида `имя-прототипа → прототип`. Но для удобства поиска прототипы можно маркировать и другими критериями, а не только условным именем.

---

# ⌗ Псевдокод

В этом примере **Прототип** позволяет производить точные копии объектов геометрических фигур, не привязываясь к их классам.

![image 2](https://github.com/user-attachments/assets/6aede519-e874-47dd-8145-4121276fec79)


Все фигуры реализуют интерфейс клонирования и предоставляют метод для воспроизводства самой себя. Подклассы используют метод клонирования родителя, а затем копируют собственные поля в получившийся объект.

```
// Базовый прототип.
abstract class Shape is 
	field X: int
	field Y: int
	field color: string

  // Обычный конструктор.
	constructor Shape() is
		// ...
  // Конструктор прототипа.
	constructor Shape(source: Shape) is
		this()
		this.X = source.X
		this.Y = source.Y
		this.color = source.color

  // Результатом операции клонирования всегда будет объект из
  // иерархии классов Shape.
	abstract method clone():Shape

// Конкретный прототип. Метод клонирования создаёт новый объект
// текущего класса, передавая в его конструктор ссылку на
// собственный объект. Благодаря этому операция клонирования
// получается атомарной — пока не выполнится конструктор, нового
// объекта ещё не существует. Но как только конструктор завершит
// работу, мы получим полностью готовый объект-клон, а не пустой
// объект, который нужно ещё заполнить.

class Rectangle extends Shape is 
	field width: int
	field height: int

	constructor Rectangle(source: Rectangle) is
		// Вызов родительского конструктора нужен, чтобы
	  // скопировать потенциальные приватные поля, объявленные
    // в родительском классе.
	super(source)
	this.width = source.width
	this.height = source.height

	method clone(): Shape is
		return new Rectangle(this)

class Circle extends Shape is
	field radius: int

	constructor Circle(source: Circle) is
		super(source)
		this.radius = source.radius

	method clone(): Shape is
		return new Circle(this)

// Где-то в клиентском коде.
class Application is 
	field shapes: array of Shape

	constructor Application() is
		Circle circle =new Circle()
    circle.X = 10
    circle.Y = 10
    circle.radius = 20
    shapes.add(circle)

    Circle anotherCircle = circle.clone()
    shapes.add(anotherCircle)
    // anotherCircle будет содержать точную копию circle.

    Rectangle rectangle =new Rectangle()
    rectangle.width = 10
    rectangle.height = 20
    shapes.add(rectangle)

method businessLogic() is
	// Плюс Прототипа в том, что вы можете клонировать набор
  // объектов, не зная их конкретные классы.
  Array shapesCopy =new Array of Shapes.

  // Например, мы не знаем, какие конкретно объекты
  // находятся внутри массива shapes, так как он объявлен
  // с типом Shape. Но благодаря полиморфизму, мы можем
  // клонировать все объекты «вслепую». Будет выполнен
  // метод clone того класса, которым является этот
  // объект.
	foreach (s in shapes) do
	  shapesCopy.add(s.clone())

    // Переменная shapesCopy будет содержать точные копии
    // элементов массива shapes.
```
