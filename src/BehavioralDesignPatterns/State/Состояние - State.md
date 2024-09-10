# Состояние - State

Категория: Поведенческий

**Состояние** — это поведенческий паттерн проектирования, который позволяет объектам менять поведение в зависимости от своего состояния. Извне создаётся впечатление, что изменился класс объекта.

---

# 🛠️ Структура

![image.png](%D0%A1%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5%20-%20State%20282616f297f048e6a9f440fb1600977a/image.png)

1. **Context** хранит ссылку на объект состояния и делегирует ему часть работы, зависящей от состояний. Контекст работает с этим объектом через общий интерфейс состояний. Контекст должен иметь метод для присваивания ему нового объекта-состояния.
2. **State** описывает общий интерфейс для всех конкретных состояний.
3. **Concrete States** реализуют поведения, связанные с определённым состоянием контекста. Иногда приходится создавать целые иерархии классов состояний, чтобы обобщить дублирующий код.
    
    Состояние может иметь обратную ссылку на объект контекста. Через неё не только удобно получать из контекста нужную информацию, но и осуществлять смену его состояния.
    
4. И контекст, и объекты конкретных состояний могут решать, когда и какое следующее состояние будет выбрано. Чтобы переключить состояние, нужно подать другой объект-состояние в контекст.

---

# ⌗ Псевдокод

В этом примере паттерн **Состояние** изменяет функциональность одних и тех же элементов управления музыкальным проигрывателем, в зависимости от того, в каком состоянии находится сейчас проигрыватель.

![image.png](%D0%A1%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5%20-%20State%20282616f297f048e6a9f440fb1600977a/image%201.png)

Объект проигрывателя содержит объект-состояние, которому и делегирует основную работу. Изменяя состояния, можно менять то, как ведут себя элементы управления проигрывателя.

```
// Общий интерфейс всех состояний.
abstract class State is
	protected field player: AudioPlayer

  // Контекст передаёт себя в конструктор состояния, чтобы
  // состояние могло обращаться к его данным и методам в
  // будущем, если потребуется.
	constructor State(player) is
		this.player = player

	abstract method clickLock()
	abstract method clickPlay()
	abstract method clickNext()
	abstract method clickPrevious()

// Конкретные состояния реализуют методы абстрактного состояния
// по-своему.
class LockedState extends State is
	// При разблокировке проигрователя с заблокированными
  // клавишами он может принять одно из двух состояний.
	method clickLock() is
		if (player.playing)
	    player.changeState(new PlayingState(player))
		else
			player.changeState(new ReadyState(player))

	method clickPlay() is
		// Ничего не делать.

	method clickNext() is
		// Ничего не делать.

	method clickPrevious() is
		// Ничего не делать.

// Конкретные состояния сами могут переводить контекст в другое
// состояние.
class ReadyState extends State is
	method clickLock() is
		player.changeState(new LockedState(player))

	method clickPlay() is
		player.startPlayback()
    player.changeState(new PlayingState(player))

	method clickNext() is
		player.nextSong()

	method clickPrevious() is
		player.previousSong()

class PlayingState extends State is
	method clickLock() is
		player.changeState(new LockedState(player))

	method clickPlay() is
		player.stopPlayback()
    player.changeState(new ReadyState(player))

	method clickNext() is
		if (event.doubleclick)
	    player.nextSong()
		else
			player.fastForward(5)

	method clickPrevious() is
		if (event.doubleclick)
	    player.previous()
		else
			player.rewind(5)

// Проигрыватель выступает в роли контекста.
class AudioPlayer is
	field state: State
	field UI, volume, playlist, currentSong

	constructor AudioPlayer() is
		this.state =new ReadyState(this)

    // Контекст заставляет состояние реагировать на
    // пользовательский ввод вместо себя. Реакция может быть
    // разной, в зависимости от того, какое состояние сейчас
    // активно.
    UI =new UserInterface()
    UI.lockButton.onClick(this.clickLock)
    UI.playButton.onClick(this.clickPlay)
    UI.nextButton.onClick(this.clickNext)
    UI.prevButton.onClick(this.clickPrevious)

  // Другие объекты тоже должны иметь возможность заменять
  // состояние проигрывателя.
	method changeState(state: State) is
		this.state = state

  // Методы UI будут делегировать работу активному состоянию.
	method clickLock() is
		state.clickLock()
	method clickPlay() is
		state.clickPlay()
	method clickNext() is
		state.clickNext()
	method clickPrevious() is
		state.clickPrevious()

  // Сервисные методы контекста, вызываемые состояниями.
	method startPlayback() is
		// ...
	method stopPlayback() is
		// ...
	method nextSong() is
		// ...
	method previousSong() is
		// ...
	method fastForward(time) is
		// ...
	method rewind(time) is
		// ...
```