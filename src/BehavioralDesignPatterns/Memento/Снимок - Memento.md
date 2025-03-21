# Снимок - Memento

Категория: Поведенческий

**Снимок** — это поведенческий паттерн проектирования, который позволяет сохранять и восстанавливать прошлые состояния объектов, не раскрывая подробностей их реализации.

---

# 🛠️ Структура

### Классическая реализация на вложенных классах

Классическая реализация паттерна полагается на механизм вложенных классов, который доступен лишь в некоторых языках программирования (C++, C#, Java).

![image](https://github.com/user-attachments/assets/eb021d4a-33ba-4716-b8e4-92a030a00ab5)


1. **Originator** может производить снимки своего состояния, а также воспроизводить прошлое состояние, если подать в него готовый снимок.
2. **Memento** — это простой объект данных, содержащий состояние создателя. Надёжнее всего сделать объекты снимков неизменяемыми, передавая в них состояние только через конструктор.
3. **Caretaker** должен знать, когда делать снимок создателя и когда его нужно восстанавливать.
    
    Опекун может хранить историю прошлых состояний создателя в виде стека из снимков. Когда понадобится отменить выполненную операцию, он возьмёт «верхний» снимок из стека и передаст его создателю для восстановления.
    
4. В данной реализации снимок — это внутренний класс по отношению к классу создателя. Именно поэтому он имеет полный доступ к полям и методам создателя, даже приватным. С другой стороны, опекун не имеет доступа ни к состоянию, ни к методам снимков и может всего лишь хранить ссылки на эти объекты.

### Реализация с пустым промежуточным интерфейсом

Подходит для языков, не имеющих механизма вложенных классов (например, PHP).

![image 1](https://github.com/user-attachments/assets/194a8c61-4689-4561-a9bf-335704f2e48f)


1. В этой реализации создатель работает напрямую с конкретным классом снимка, а опекун — только с его ограниченным интерфейсом.
2. Благодаря этому достигается тот же эффект, что и в классической реализации. Создатель имеет полный доступ к снимку, а опекун — нет.

### Снимок с повышенной защитой

Когда нужно полностью исключить возможность доступа к состоянию создателей и снимков.

![image 2](https://github.com/user-attachments/assets/33798213-c710-463d-a383-1b87ab978d64)


1. Эта реализация разрешает иметь несколько видов создателей и снимков. Каждому классу создателей соответствует свой класс снимков. Ни создатели, ни снимки не позволяют другим объектам прочесть своё состояние.
2. Здесь опекун ещё более жёстко ограничен в доступе к состоянию создателей и снимков. Но, с другой стороны, опекун становится независим от создателей, поскольку метод восстановления теперь находится в самих снимках.
3. Снимки теперь связаны с теми создателями, из которых они сделаны. Они по-прежнему получают состояние через конструктор. Благодаря близкой связи между классами, снимки знают, как восстановить состояние своих создателей.

---

# ⌗ Псевдокод

В этом примере паттерн **Снимок** используется совместно с паттерном **Команда** и позволяет хранить резервные копии сложного состояния текстового редактора и восстанавливать его, если потребуется.

![image 3](https://github.com/user-attachments/assets/c6fd1323-e984-4471-8a2c-b36738be4746)


Объекты команд выступают в роли опекунов и запрашивают снимки у редактора перед тем, как выполнить своё действие. Если потребуется отменить операцию, команда сможет восстановить состояние редактора, используя сохранённый снимок.

При этом снимок не имеет публичных полей, поэтому другие объекты не имеют доступа к его внутренним данным. Снимки связаны с определённым редактором, который их создал. Они же и восстанавливают состояние своего редактора. Это позволяет программе иметь одновременно несколько объектов редакторов, например, разбитых по разным вкладкам программы.

```
// Класс создателя должен иметь специальный метод, который
// сохраняет состояние создателя в новом объекте-снимке.
class Editor is
	private field text, curX, curY, selectionWidth

	method setText(text) is
		this.text = text

	method setCursor(x, y) is
		this.curX = x
		this.curY = y

	method setSelectionWidth(width) is
		this.selectionWidth = width

	method createSnapshot():Snapshot is
		// Снимок — неизменяемый объект, поэтому Создатель
    // передаёт все своё состояние через параметры
    // конструктора.
		return new Snapshot(this, text, curX, curY, selectionWidth)

// Снимок хранит прошлое состояние редактора.
class Snapshot is
	private field editor: Editor
	private field text, curX, curY, selectionWidth

	constructor Snapshot(editor, text, curX, curY, selectionWidth) is
		this.editor = editor
		this.text = text
		this.curX = x
		this.curY = y
		this.selectionWidth = selectionWidth

  // В нужный момент владелец снимка может восстановить
  // состояние редактора.
	method restore() is
		editor.setText(text)
    editor.setCursor(curX, curY)
    editor.setSelectionWidth(selectionWidth)

// Опекуном может выступать класс команд (см. паттерн Команда).
// В этом случае команда сохраняет снимок состояния объекта-
// получателя, перед тем как передать ему своё действие. А в
// случае отмены команда вернёт объект в прежнее состояние.
class Command is
	private field backup: Snapshot

	method makeBackup() is
		backup = editor.createSnapshot()

	method undo() is
		if (backup !=null)
	    backup.restore()
  // ...
```
