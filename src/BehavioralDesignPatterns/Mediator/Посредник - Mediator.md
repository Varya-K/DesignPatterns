# Посредник - Mediator

Категория: Поведенческий

**Посредник** — это поведенческий паттерн проектирования, который позволяет уменьшить связанность множества классов между собой, благодаря перемещению этих связей в один класс-посредник.

---

# 🛠️ Структура

![image](https://github.com/user-attachments/assets/ebe1930f-8002-4e3d-a617-4b4a012d8c5d)

1. **Components** — это разнородные объекты, содержащие бизнес-логику программы. Каждый компонент хранит ссылку на объект посредника, но работает с ним только через абстрактный интерфейс посредников. Благодаря этому, компоненты можно повторно использовать в другой программе, связав их с посредником другого типа.
2. **Mediator** определяет интерфейс для обмена информацией с компонентами. Обычно хватает одного метода, чтобы оповещать посредника о событиях, произошедших в компонентах. В параметрах этого метода можно передавать детали события: ссылку на компонент, в котором оно произошло, и любые другие данные.
3. **Concrete Mediator** содержит код взаимодействия нескольких компонентов между собой. Зачастую этот объект не только хранит ссылки на все свои компоненты, но и сам их создаёт, управляя дальнейшим жизненным циклом.
4. Компоненты не должны общаться друг с другом напрямую. Если в компоненте происходит важное событие, он должен оповестить своего посредника, а тот сам решит — касается ли событие других компонентов, и стоит ли их оповещать. При этом компонент-отправитель не знает кто обработает его запрос, а компонент-получатель не знает кто его прислал.

---

# ⌗ Псевдокод

В этом примере **Посредник** помогает избавиться от зависимостей между классами различных элементов пользовательского интерфейса: кнопками, чекбоксами и надписями.

![image 1](https://github.com/user-attachments/assets/60029f6e-7846-4c72-a22e-8cea41e98fc6)

По реакции на действия пользователей элементы не взаимодействуют напрямую, а всего лишь уведомляют посредника о том, что они изменились.

Посредник в виде диалога авторизации знает, как конкретные элементы должны взаимодействовать. Поэтому при получении уведомлений он может перенаправить вызов тому или иному элементу.

```
// Общий интерфейс посредников.
interface Mediator is 
	method notify(sender: Component, event: string)

// Конкретный посредник. Все связи между конкретными
// компонентами переехали в код посредника. Он получает
// извещения от своих компонентов и знает, как на них
// реагировать.
class AuthenticationDialog implements Mediator is
	private field title: string
	private field loginOrRegisterChkBx: Checkbox
	private field loginUsername, loginPassword: Textbox
	private field registrationUsername, registrationPassword,
                  registrationEmail: Textbox
	private field okBtn, cancelBtn: Button

	constructor AuthenticationDialog() is
		// Здесь нужно создать объекты всех компонентов, подав
    // текущий объект-посредник в их конструктор.

  // Когда что-то случается с компонентом, он шлёт посреднику
  // оповещение. После получения извещения посредник может
  // либо сделать что-то самостоятельно, либо перенаправить
  // запрос другому компоненту.
	method notify(sender, event) is
		if (sender == loginOrRegisterChkBxand event == "check")
			if (loginOrRegisterChkBx.checked)
	      title = "Log in"
        // 1. Показать компоненты формы входа.
        // 2. Скрыть компоненты формы регистрации.
			else
				title = "Register"
        // 1. Показать компоненты формы регистрации.
        // 2. Скрыть компоненты формы входа.

		if (sender == okBtn && event == "click")
			if (loginOrRegister.checked)
	      // Попробовать найти пользователя с данными из
        // формы логина.
				if (!found)
	        // Показать ошибку над формой логина.
			else
				// 1. Создать пользовательский аккаунт с данными
	      // из формы регистрации.
        // 2. Авторизировать этого пользователя.
        // ...

// Классы компонентов общаются с посредниками через их общий
// интерфейс. Благодаря этому одни и те же компоненты можно
// использовать в разных посредниках.
class Component is
	field dialog: Mediator

	constructor Component(dialog) is
		this.dialog = dialog

	method click() is
		dialog.notify(this, "click")

	method keypress() is
		dialog.notify(this, "keypress")

// Конкретные компоненты не связаны между собой напрямую. У них
// есть только один канал общения — через отправку уведомлений
// посреднику.
class Button extends Component is
	// ...

class Textbox extends Component is
	// ...

class Checkbox extends Component is
	method check() is
		dialog.notify(this, "check")
  // ...
```
