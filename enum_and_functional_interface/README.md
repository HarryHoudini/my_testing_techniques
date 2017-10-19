Если совместить фичу Java 1.8 и возможность джавовских перечислений иметь поля экземпляров, то можно получить очень мощный механизм, позволяющий в некоторых случаях (один из таких и приведен) избежать дублирования кода.

На проекте я столкнулся с ситуацией, когда мне необходимо было создавать для узлов в дереве сети 22 сенсора. У каждого такого сенсора была своя страница (визард), на которой выполнялась настройка этого сенсора. Для приемочных тестов было принято решение, создавать сенсоры с обязательными полями для заполнения, а опциональные оставлять с дефолтными значениями. У некоторых сенсоров единственным основным полем было имя сенсора, но у половины были и другие уникальные поля. Создавать для узла 22 разных метода (createDefaultPingSensor()...), внутри которых бы вызывался соответствующий метод PageObject-ов визардов создания сенсоров. 
Добавив в перечисления поле, которое хранит конструктор PageObject-ов визардов создания сенсоров, мне позволило реализовать в узле один метод ****addDefaultSensor()****, который принимает параметром тип сенсора (перечисление SelectWizardSensor ) и имя сенсора. Также такое решение позволило в последствии использовать этот механизм и в другом методе ***NodeOfNetworkTree*** - editSensor

`SelectWizardSensor` - перечисление, реализующее интерфейс ***MyLocatorInterface***. Кроме этого имеет поле, которые хранит метод конструктора для создания экземпляров классов. наследников ****ConfigSensorsWizard****

`NodeOfNetworkTree` - класс узла, для которого можно создать сенсор. Не используемые методы в примере почищены.

`HttpContentConfigSensorsWizard.java` - класс, где и реализован метод создания дефолтного сенсора. Класс имеет другие методы и имеет опциональные поля, которые используются в других тестах. Для данного примера этот класс очищен от лишнего.

`ExampleOfUse.java` - сам пример


`Примечания:`
 - ****GroupOfNetworkTree**** наследник ****NodeOfNetworkTree****
 - Примененный метод в ***NodeOfNetworkTree.shoudHasSensor()*** использует дженерик метод ****MyConditions.waitingBy()****, на который можно посмотреть в [generic_waiting](https://github.com/savegExample/my_testing_techniques/tree/master/generic_waiting)