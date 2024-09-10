# Заместитель - Proxy

Категория: Структурный

**Заместитель** — это структурный паттерн проектирования, который позволяет подставлять вместо реальных объектов специальные объекты-заменители. Эти объекты перехватывают вызовы к оригинальному объекту, позволяя сделать что-то *до* или *после* передачи вызова оригиналу.

---

# 🛠️ Структура

![image.png](%D0%97%D0%B0%D0%BC%D0%B5%D1%81%D1%82%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%20-%20Proxy%208cd20d9be9244264bb6ba22139bda28b/image.png)

1. **Service Interface** определяет общий интерфейс для сервиса и заместителя. Благодаря этому, объект заместителя можно использовать там, где ожидается объект сервиса.
2. **Service** содержит полезную бизнес-логику.
3. **Proxy** хранит ссылку на объект сервиса. После того как заместитель заканчивает свою работу (например, инициализацию, логирование, защиту или другое), он передаёт вызовы вложенному сервису.
    
    Заместитель может сам отвечать за создание и удаление объекта сервиса.
    
4. **Client** работает с объектами через интерфейс сервиса. Благодаря этому, его можно «одурачить», подменив объект сервиса объектом заместителя.

---

# ⌗ Псевдокод

В этом примере **Заместитель** помогает добавить в программу механизм ленивой инициализации и кеширования результатов работы библиотеки интеграции с YouTube.

![image.png](%D0%97%D0%B0%D0%BC%D0%B5%D1%81%D1%82%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%20-%20Proxy%208cd20d9be9244264bb6ba22139bda28b/image%201.png)

Оригинальный объект начинал загрузку по сети, даже если пользователь запрашивал одно и то же видео. Заместитель же загружает видео только один раз, используя для этого служебный объект, но в остальных случаях возвращает закешированный файл.

```
// Интерфейс удалённого сервиса.
interface ThirdPartyYouTubeLib is
	method listVideos()
	method getVideoInfo(id)
	method downloadVideo(id)

// Конкретная реализация сервиса. Методы этого класса
// запрашивают у YouTube различную информацию. Скорость запроса
// зависит не только от качества интернет-канала пользователя,
// но и от состояния самого YouTube. Значит, чем больше будет
// вызовов к сервису, тем менее отзывчивой станет программа.
class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib is
	method listVideos() is
		// Получить список видеороликов с помощью API YouTube.

	method getVideoInfo(id) is
		// Получить детальную информацию о каком-то видеоролике.

	method downloadVideo(id) is
		// Скачать видео с YouTube.

// С другой стороны, можно кешировать запросы к YouTube и не
// повторять их какое-то время, пока кеш не устареет. Но внести
// этот код напрямую в сервисный класс нельзя, так как он
// находится в сторонней библиотеке. Поэтому мы поместим логику
// кеширования в отдельный класс-обёртку. Он будет делегировать
// запросы к сервисному объекту, только если нужно
// непосредственно выслать запрос.
class CachedYouTubeClass implements ThirdPartyYouTubeLib is
	private field service: ThirdPartyYouTubeLib
	private field listCache, videoCache
	field needReset

	constructor CachedYouTubeClass(service: ThirdPartyYouTubeLib) is
		this.service = service

	method listVideos() is
		if (listCache == null || needReset)
	    listCache = service.listVideos()
		return listCache

	method getVideoInfo(id) is
		if (videoCache == null || needReset)
	    videoCache = service.getVideoInfo(id)
		return videoCache

	method downloadVideo(id) is
		if (!downloadExists(id) || needReset)
	    service.downloadVideo(id)

// Класс GUI, который использует сервисный объект. Вместо
// реального сервиса, мы подсунем ему объект-заместитель. Клиент
// ничего не заметит, так как заместитель имеет тот же
// интерфейс, что и сервис.
class YouTubeManager is 
	protected field service: ThirdPartyYouTubeLib

	constructor YouTubeManager(service: ThirdPartyYouTubeLib) is
		this.service = service

	method renderVideoPage(id) is
		info = service.getVideoInfo(id)
    // Отобразить страницу видеоролика.

	method renderListPanel() is
		list = service.listVideos()
    // Отобразить список превьюшек видеороликов.

	method reactOnUserInput() is
		renderVideoPage()
    renderListPanel()

// Конфигурационная часть приложения создаёт и передаёт клиентам
// объект заместителя.
class Application is
	method init()is
		YouTubeService =new ThirdPartyYouTubeClass()
    YouTubeProxy =new CachedYouTubeClass(YouTubeService)
    manager =new YouTubeManager(YouTubeProxy)
    manager.reactOnUserInput()
```