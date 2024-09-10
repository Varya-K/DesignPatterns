# Итератор - Iterator

Категория: Поведенческий

**Итератор** — это поведенческий паттерн проектирования, который даёт возможность последовательно обходить элементы составных объектов, не раскрывая их внутреннего представления.

---

# 🛠️ Структура

![image](https://github.com/user-attachments/assets/dd062c42-d2d8-48b4-8bc4-6200e54ef02c)


1. **Iterator** описывает интерфейс для доступа и обхода элементов коллекции.
2. **Concrete Iterator** реализует алгоритм обхода какой-то конкретной коллекции. Объект итератора должен сам отслеживать текущую позицию при обходе коллекции, чтобы отдельные итераторы могли обходить одну и ту же коллекцию независимо.
3. **Iterable Collection** описывает интерфейс получения итератора из коллекции. Как мы уже говорили, коллекции не всегда являются списком. Это может быть и база данных, и удалённое API, и даже дерево **Компоновщика**. Поэтому сама коллекция может создавать итераторы, так как она знает, какие именно итераторы способны с ней работать.
4. **Concrete Collection** возвращает новый экземпляр определённого конкретного итератора, связав его с текущим объектом коллекции. Обратите внимание, что сигнатура метода возвращает интерфейс итератора. Это позволяет клиенту не зависеть от конкретных классов итераторов.
5. **Client** работает со всеми объектами через интерфейсы коллекции и итератора. Так клиентский код не зависит от конкретных классов, что позволяет применять различные итераторы, не изменяя существующий код программы.
    
    В общем случае клиенты не создают объекты итераторов, а получают их из коллекций. Тем не менее, если клиенту требуется специальный итератор, он всегда может создать его самостоятельно.
    
    ---
    

# ⌗ Псевдокод

В этом примере паттерн **Итератор** используется для реализации обхода нестандартной коллекции, которая инкапсулирует доступ к социальному графу Facebook. Коллекция предоставляет несколько итераторов, которые могут по-разному обходить профили людей.

![image 1](https://github.com/user-attachments/assets/61f6f3b5-7500-4717-9a91-444cd60c52d2)


Так, итератор друзей перебирает всех друзей профиля, а итератор коллег — фильтрует друзей по принадлежности к компании профиля. Все итераторы реализуют общий интерфейс, который позволяет клиентам работать с профилями, не вникая в детали работы с социальной сетью (например, в авторизацию, отправку REST-запросов и т. д.)

Кроме того, Итератор избавляет код от привязки к конкретным классам коллекций. Это позволяет добавить поддержку другого вида коллекций (например, LinkedIn), не меняя клиентский код, который работает с итераторами и коллекциями.

```
// Общий интерфейс коллекций должен определить фабричный метод
// для производства итератора. Можно определить сразу несколько
// методов, чтобы дать пользователям различные варианты обхода
// одной и той же коллекции.
interface SocialNetwork is
	method createFriendsIterator(profileId):ProfileIterator
	method createCoworkersIterator(profileId):ProfileIterator

// Конкретная коллекция знает, объекты каких итераторов нужно
// создавать.
class Facebook implements SocialNetwork is
	// ...Основной код коллекции...

  // Код получения нужного итератора.
	method createFriendsIterator(profileId) is
		return new FacebookIterator(this, profileId, "friends")
	method createCoworkersIterator(profileId) is
		return new FacebookIterator(this, profileId, "coworkers")

// Общий интерфейс итераторов.
interface ProfileIterator is
	method getNext():Profile
	method hasMore():bool

// Конкретный итератор.
class FacebookIterator implements ProfileIterator is
	// Итератору нужна ссылка на коллекцию, которую он обходит.
	private field facebook: Facebook
	private field profileId, type: string

  // Но каждый итератор обходит коллекцию, независимо от
  // остальных, поэтому он содержит информацию о текущей
  // позиции обхода.
	private field currentPosition
	private field cache: array of Profile

	constructor FacebookIterator(facebook, profileId, type) is
		this.facebook = facebook
		this.profileId = profileId
		this.type = type

	private method lazyInit() is
		if (cache ==null)
	    cache = facebook.socialGraphRequest(profileId, type)

	// Итератор реализует методы базового интерфейса по-своему.
	method getNext() is
		if (hasMore())
	    result = cache[currentPosition]
	    currentPosition++
			return result

	method hasMore() is
		lazyInit()
		return currentPosition < cache.length

// Вот ещё полезная тактика: мы можем передавать объект
// итератора вместо коллекции в клиентские классы. При таком
// подходе клиентский код не будет иметь доступа к коллекциям, а
// значит, его не будут волновать подробности их реализаций. Ему
// будет доступен только общий интерфейс итераторов.
class SocialSpammer is
	method send(iterator: ProfileIterator, message: string) is
		while (iterator.hasMore())
	    profile = iterator.getNext()
      System.sendEmail(profile.getEmail(), message)

// Класс приложение конфигурирует классы, как захочет.
class Application is 
	field network: SocialNetwork
	field spammer: SocialSpammer

	method config() is
		if working with Facebook
			this.network =new Facebook()
		if working with LinkedIn
			this.network =new LinkedIn()
			this.spammer =new SocialSpammer()

	method sendSpamToFriends(profile) is 
		iterator = network.createFriendsIterator(profile.getId())
	  spammer.send(iterator, "Very important message")

	method sendSpamToCoworkers(profile) is
		iterator = network.createCoworkersIterator(profile.getId())
    spammer.send(iterator, "Very important message")
```
