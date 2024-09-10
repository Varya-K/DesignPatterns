# Наблюдатель - Observer

Категория: Поведенческий

**Наблюдатель** — это поведенческий паттерн проектирования, который создаёт механизм подписки, позволяющий одним объектам следить и реагировать на события, происходящие в других объектах.

---

# 🛠️ Структура

![image.png](%D0%9D%D0%B0%D0%B1%D0%BB%D1%8E%D0%B4%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%20-%20Observer%201536893af07141ac90300c81cc6b8b42/image.png)

1. **Publisher** владеет внутренним состоянием, изменение которого интересно отслеживать подписчикам. Издатель содержит механизм подписки: список подписчиков и методы подписки/отписки.
2. Когда внутреннее состояние издателя меняется, он оповещает своих подписчиков. Для этого издатель проходит по списку подписчиков и вызывает их метод оповещения, заданный в общем интерфейсе подписчиков.
3. **Subscriber** определяет интерфейс, которым пользуется издатель для отправки оповещения. В большинстве случаев для этого достаточно единственного метода.
4. **Concrete subscriber** выполняют что-то в ответ на оповещение, пришедшее от издателя. Эти классы должны следовать общему интерфейсу подписчиков, чтобы издатель не зависел от конкретных классов подписчиков.
5. По приходу оповещения подписчику нужно получить обновлённое состояние издателя. Издатель может передать это состояние через параметры метода оповещения. Более гибкий вариант — передавать через параметры весь объект издателя, чтобы подписчик мог сам получить требуемые данные. Как вариант, подписчик может постоянно хранить ссылку на объект издателя, переданный ему в конструкторе.
6. **Client** создаёт объекты издателей и подписчиков, а затем регистрирует подписчиков на обновления в издателях.

---

# ⌗ Псевдокод

В этом примере **Наблюдатель** позволяет объекту текстового редактора оповещать другие объекты об изменениях своего состояния.

![image.png](%D0%9D%D0%B0%D0%B1%D0%BB%D1%8E%D0%B4%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%20-%20Observer%201536893af07141ac90300c81cc6b8b42/image%201.png)

Список подписчиков составляется динамически, объекты могут как подписываться на определённые события, так и отписываться от них прямо во время выполнения программы.

В этой реализации редактор не ведёт список подписчиков самостоятельно, а делегирует это вложенному объекту. Это даёт возможность использовать механизм подписки не только в классе редактора, но и в других классах программы.

Для добавления в программу новых подписчиков не нужно менять классы издателей, пока они работают с подписчиками через общий интерфейс.

```
// Базовый класс-издатель. Содержит код управления подписчиками
// и их оповещения.
class EventManager is
	private field listeners: hash map of event types and listeners

	method subscribe(eventType, listener) is
		listeners.add(eventType, listener)

	method unsubscribe(eventType, listener) is
		listeners.remove(eventType, listener)

	method notify(eventType, data) is
		foreach (listener in listeners.of(eventType)) do
	    listener.update(data)

// Конкретный класс-издатель, содержащий интересную для других
// компонентов бизнес-логику. Мы могли бы сделать его прямым
// потомком EventManager, но в реальной жизни это не всегда
// возможно (например, если у класса уже есть родитель). Поэтому
// здесь мы подключаем механизм подписки при помощи композиции.
class Editor is
	public field events: EventManager
	private field file: File

	constructor Editor() is
		events = new EventManager()

    // Методы бизнес-логики, которые оповещают подписчиков об
    // изменениях.
		method openFile(path) is
			this.file = new File(path)
      events.notify("open", file.name)

		method saveFile() is
			file.write()
      events.notify("save", file.name)
    // ...

// Общий интерфейс подписчиков. Во многих языках, поддерживающих
// функциональные типы, можно обойтись без этого интерфейса и
// конкретных классов, заменив объекты подписчиков функциями.
interface EventListener is
	method update(filename)

// Набор конкретных подписчиков. Они реализуют добавочную
// функциональность, реагируя на извещения от издателя.
class LoggingListener implements EventListener is
	private field log: File
	private field message: string

	constructor LoggingListener(log_filename, message) is
		this.log =new File(log_filename)
		this.message = message

	method update(filename) is
		log.write(replace('%s',filename,message))

class EmailAlertsListener implements EventListener is
	private field email: string
	private field message: string

	constructor EmailAlertsListener(email, message) is
		this.email = email
		this.message = message

	method update(filename) is
		system.email(email, replace('%s',filename,message))

// Приложение может сконфигурировать издателей и подписчиков как
// угодно, в зависимости от целей и окружения.
class Application is
	method config() is
		editor = new Editor()

    logger = new LoggingListener(
            "/path/to/log.txt",
            "Someone has opened file: %s");
    editor.events.subscribe("open", logger)

    emailAlerts = new EmailAlertsListener(
            "admin@example.com",
            "Someone has changed the file: %s")
    editor.events.subscribe("save", emailAlerts)
```