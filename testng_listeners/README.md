В проекте реализовано два лисенера testNG: **ReloadListener** реализует _ITestListener_ и **ScreenshotListener** реализовует _IInvokedMethodListener_.

Задача **ReloadListener** выполнять открытие базового урла после упавшего теста. А задача **ScreenshotListener** выполнять скриншот и отправлять его на сервер ReportPortal.

[ReloadListener](src/main/java/ReloadListener.java) - лисенер переопределяющий метод **ITestListener.onTestFailure**

[ScreenshotListener](src/main/java/ScreenshotListener.java) - лисенер переопределяющий метод **IInvokedMethodListener.afterInvocation**

[example](src/test/java/example.java) - пример использования. Добавление самих лисенеров выполняется в [pom.xml](pom.xml)
