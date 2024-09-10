# Шаблонный метод - Template Method

Категория: Поведенческий

**Шаблонный метод** — это поведенческий паттерн проектирования, который определяет скелет алгоритма, перекладывая ответственность за некоторые его шаги на подклассы. Паттерн позволяет подклассам переопределять шаги алгоритма, не меняя его общей структуры.

---

# 🛠️ Структура

![image](https://github.com/user-attachments/assets/626706bd-3bd2-4a6a-86e7-fc4b533dff57)


1. **Abstract Class** определяет шаги алгоритма и содержит шаблонный метод, состоящий из вызовов этих шагов. Шаги могут быть как абстрактными, так и содержать реализацию по умолчанию.
2. **Concrete class** переопределяет некоторые (или все) шаги алгоритма. Конкретные классы не переопределяют сам шаблонный метод.

---

# ⌗ Псевдокод

В этом примере **Шаблонный метод** используется как заготовка для стандартного искусственного интеллекта в простой игре-стратегии. Для введения в игру новой расы достаточно создать подкласс и реализовать в нём недостающие методы.

![image 1](https://github.com/user-attachments/assets/33d1b859-0485-429e-adf9-6fd05400475e)


Все расы игры будут содержать примерно такие же типы юнитов и строений, поэтому структура ИИ будет одинаковой. Но разные расы могут по-разному реализовать эти шаги. Так, например, орки будут агрессивней в атаке, люди — более активны в защите, а дикие монстры вообще не будут заниматься строительством.

```
class GameAI is
	// Шаблонный метод должен быть задан в базовом классе. Он
  // состоит из вызовов методов в определённом порядке. Чаще
  // всего эти методы являются шагами некоего алгоритма.
	method turn() is
		collectResources()
    buildStructures()
    buildUnits()
    attack()

  // Некоторые из этих методов могут быть реализованы прямо в
  // базовом классе.
	method collectResources() is
		foreach (s inthis.builtStructures) do
	    s.collect()

  // А некоторые могут быть полностью абстрактными.
	abstract method buildStructures()
	abstract method buildUnits()

  // Кстати, шаблонных методов в классе может быть несколько.
	method attack() is
		enemy = closestEnemy()
		if (enemy ==null)
	    sendScouts(map.center)
		else
			sendWarriors(enemy.position)

	abstract method sendScouts(position)
	abstract method sendWarriors(position)

// Подклассы могут предоставлять свою реализацию шагов
// алгоритма, не изменяя сам шаблонный метод.
class OrcsAI extends GameAI is
	method buildStructures() is
		if (there are some resources) then
			// Строить фермы, затем бараки, а потом цитадель.

	method buildUnits() is
		if (there are plenty of resources) then
			if (there are no scouts)
	      // Построить раба и добавить в группу
        // разведчиков.
			else
				// Построить пехотинца и добавить в группу
        // воинов.

		// ...

	method sendScouts(position) is
		if (scouts.length > 0) then
			// Отправить разведчиков на позицию.

	method sendWarriors(position) is
		if (warriors.length > 5) then
			// Отправить воинов на позицию.

// Подклассы могут не только реализовывать абстрактные шаги, но
// и переопределять шаги, уже реализованные в базовом классе.
class MonstersAI extends GameAI is
	method collectResources() is
		// Ничего не делать.

	method buildStructures() is
		// Ничего не делать.

	method buildUnits() is
		// Ничего не делать.
```
