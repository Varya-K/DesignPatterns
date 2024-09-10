# Команда - Command

Категория: Поведенческий

**Команда** — это поведенческий паттерн проектирования, который превращает запросы в объекты, позволяя передавать их как аргументы при вызове методов, ставить запросы в очередь, логировать их, а также поддерживать отмену операций.

---

# 🛠️ Структура

![image](https://github.com/user-attachments/assets/3d75c8fe-a0cd-4c0c-9100-df7e71b00c15)

1. **Invoker** хранит ссылку на объект команды и обращается к нему, когда нужно выполнить какое-то действие. Отправитель работает с командами только через их общий интерфейс. Он не знает, какую конкретно команду использует, так как получает готовый объект команды от клиента.
2. **Command** описывает общий для всех конкретных команд интерфейс. Обычно здесь описан всего один метод для запуска команды.
3. **Concrete Commands** реализуют различные запросы, следуя общему интерфейсу команд. Обычно команда не делает всю работу самостоятельно, а лишь передаёт вызов получателю, которым является один из объектов бизнес-логики.
    
    Параметры, с которыми команда обращается к получателю, следует хранить в виде полей. В большинстве случаев объекты команд можно сделать неизменяемыми, передавая в них все необходимые параметры только через конструктор.
    
4. **Receiver** содержит бизнес-логику программы. В этой роли может выступать практически любой объект. Обычно команды перенаправляют вызовы получателям. Но иногда, чтобы упростить программу, вы можете избавиться от получателей, «слив» их код в классы команд.
5. **Client** создаёт объекты конкретных команд, передавая в них все необходимые параметры, среди которых могут быть и ссылки на объекты получателей. После этого клиент связывает объекты отправителей с созданными командами.

---

# ⌗ Псевдокод

В этом примере паттерн **Команда** служит для ведения истории выполненных операций, позволяя отменять их, если потребуется.

![image 1](https://github.com/user-attachments/assets/231dee98-cc8f-48f4-a4e6-b7841ab85efe)

Команды, которые меняют состояние редактора (например, команда вставки текста из буфера обмена), сохраняют копию состояния редактора перед выполнением действия. Копии выполненных команд помещаются в историю команд, откуда они могут быть получены, если нужно будет сделать отмену операции.

Классы элементов интерфейса, истории команд и прочие не зависят от конкретных классов команд, так как работают с ними через общий интерфейс. Это позволяет добавлять в приложение новые команды, не изменяя существующий код.

```
я// Абстрактная команда задаёт общий интерфейс для конкретных
// классов команд и содержит базовое поведение отмены операции.
abstract classCommand is
	protected field app: Application
	protected field editor: Editor
	protected field backup: text

	constructor Command(app: Application, editor: Editor) is
		this.app = app
		this.editor = editor

  // Сохраняем состояние редактора.
	method saveBackup() is
		backup = editor.text

  // Восстанавливаем состояние редактора.
	method undo() is
		editor.text = backup

  // Главный метод команды остаётся абстрактным, чтобы каждая
  // конкретная команда определила его по-своему. Метод должен
  // возвратить true или false в зависимости о того, изменила
  // ли команда состояние редактора, а значит, нужно ли её
  // сохранить в истории.
	abstract method execute()

// Конкретные команды.
class CopyCommand extends Command is
	// Команда копирования не записывается в историю, так как
  // она не меняет состояние редактора.
	method execute() is
		app.clipboard = editor.getSelection()
		return false

class CutCommand extends Command is
	// Команды, меняющие состояние редактора, сохраняют
  // состояние редактора перед своим действием и сигнализируют
  // об изменении, возвращая true.
	method execute() is
		saveBackup()
	  app.clipboard = editor.getSelection()
    editor.deleteSelection()
		return true

class PasteCommand extends Command is
	method execute() is
		saveBackup()
    editor.replaceSelection(app.clipboard)
		return true

// Отмена — это тоже команда.
class UndoCommand extends Command is
	method execute() is
		app.undo()
		return false
		
// Глобальная история команд — это стек.
class CommandHistory is
	private field history: array of Command

  // Последний зашедший...
	method push(c: Command) is
		// Добавить команду в конец массива-истории.

  // ...выходит первым.
	method pop(): Command is
		// Достать последнюю команду из массива-истории.

// Класс редактора содержит непосредственные операции над
// текстом. Он отыгрывает роль получателя — команды делегируют
// ему свои действия.
class Editor is
	field text: string

	method getSelection() is
		// Вернуть выбранный текст.

	method deleteSelection() is
		// Удалить выбранный текст.

	method replaceSelection(text) is
		// Вставить текст из буфера обмена в текущей позиции.

// Класс приложения настраивает объекты для совместной работы.
// Он выступает в роли отправителя — создаёт команды, чтобы
// выполнить какие-то действия.
class Application is
	field clipboard: string
	field editors: array of Editors
	field activeEditor: Editor
	field history: CommandHistory

  // Код, привязывающий команды к элементам интерфейса, может
  // выглядеть примерно так.
	method createUI() is
		// ...
	  copy = function() {executeCommand(
			new CopyCommand(this, activeEditor)) }
    copyButton.setCommand(copy)
    shortcuts.onKeyPress("Ctrl+C", copy)

    cut = function() { executeCommand(
			new CutCommand(this, activeEditor)) }
    cutButton.setCommand(cut)
    shortcuts.onKeyPress("Ctrl+X", cut)

    paste = function() { executeCommand(
			new PasteCommand(this, activeEditor)) }
    pasteButton.setCommand(paste)
    shortcuts.onKeyPress("Ctrl+V", paste)

    undo = function() { executeCommand(
			new UndoCommand(this, activeEditor)) }
    undoButton.setCommand(undo)
    shortcuts.onKeyPress("Ctrl+Z", undo)

	// Запускаем команду и проверяем, надо ли добавить её в
	// историю.
	method executeCommand(command) is
		if (command.execute())
	    history.push(command)

	// Берём последнюю команду из истории и заставляем её все
  // отменить. Мы не знаем конкретный тип команды, но это и не
  // важно, так как каждая команда знает, как отменить своё
  // действие.
	method undo() is
		command = history.pop()
		if (command !=null)
	    command.undo()
```
