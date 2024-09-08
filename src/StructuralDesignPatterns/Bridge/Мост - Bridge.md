# Мост - Bridge

Категория: Структурный

**Мост** — это структурный паттерн проектирования, который разделяет один или несколько классов на две отдельные иерархии — абстракцию и реализацию, позволяя изменять их независимо друг от друга.

---

# 🛠️ Структура

![image.png](image.png)

1. **Abstraction** содержит управляющую логику. Код абстракции делегирует реальную работу связанному объекту реализации.
2. **Implementation** задает общий интерфейс для всех реализаций. Все методы, которые здесь описаны, будут доступны из класса абстракции и его подклассов.
Интерфейсы абстракции и реализации могут как совпадать, так и быть совершенно разными. Но обычно в Implementation живут базовые операции, на которых строятся сложные операции Abstraction
3. **Concrete Implementations** содержат платформо-зависимый код.
4. **Refined Abstractions** содержат различные вариации управляющей логики. как и родитель, работает с реализацией только через общий интерфейс Implementation.
5. **Client** работает только с объектами Abstraction. Не считая начального связывания абстракции с одной из реализаций, клиентский код не имеет прямого доступа к объектам реализации.

---

# ⌗ Псевдокод

В этом примере **Мост** разделяет монолитный код приборов и пультов на две части: приборы (выступают реализацией) и пульты управления ими (выступают абстракцией).

![image.png](image%201.png)

Класс пульта имеет ссылку на объект прибора, которым он управляет. Пульты работают с приборами через общий интерфейс. Это даёт возможность связать пульты с различными приборами.

Сами пульты можно развивать независимо от приборов. Для этого достаточно создать новый подкласс абстракции. Вы можете создать как простой пульт с двумя кнопками, так и более сложный пульт с тач-интерфейсом.

Клиентскому коду остаётся выбрать версию абстракции и реализации, с которым он хочет работать, и связать их между собой.

```
// Класс пультов имеет ссылку на устройство, которым управляет.
// Методы этого класса делегируют работу методам связанного
// устройства.
class Remote is
	protectedfield device: Device
	constructor Remote(device: Device) is
		this.device = device
	method togglePower() is
		if (device.isEnabled()) then
			device.disable()
		else
			device.enable()
	method volumeDown() is
		device.setVolume(device.getVolume() - 10)
	method volumeUp() is
		device.setVolume(device.getVolume() + 10)
	method channelDown() is
		device.setChannel(device.getChannel() - 1)
	method channelUp() is 
		device.setChannel(device.getChannel() + 1)

// Вы можете расширять класс пультов, не трогая код устройств.
class AdvancedRemote extends Remote is
	method mute() is
		device.setVolume(0)

// Все устройства имеют общий интерфейс. Поэтому с ними может
// работать любой пульт.
interface Device is
	method isEnabled()
	method enable()
	method disable()
	method getVolume()
	method setVolume(percent)
	method getChannel()
	method setChannel(channel)

// Но каждое устройство имеет особую реализацию.
class Tvimplements Device is// ...

class Radioimplements Device is// ...

// Где-то в клиентском коде.
tv = new Tv()
remote =new Remote(tv)
remote.togglePower()

radio = new Radio()
remote = new AdvancedRemote(radio)
```