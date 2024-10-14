package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.getAll());
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
        }
    }
}

/*
Домашнее задание HW02

3: Изменить MealRepository и InMemoryMealRepository таким образом, чтобы вся еда всех пользователей находилась в одном общем хранилище, но при этом каждый конкретный аутентифицированный пользователь мог видеть и редактировать только свою еду.

3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false (см. комментарии в MealRepository)

3.4: дополнительно: попробуйте сделать реализацию атомарной (те учесть коллизии при одновременном изменении еды одного пользователя)
4: Реализовать слои приложения для функциональности "еда". API контроллера должна удовлетворять все потребности
демо приложения и ничего лишнего (см. демо). Поиск и изменение порядка сортировки в таблице демо приложения
реализованы на клиенте в браузере (плагин DataTables), сейчас делать не нужно.
Смотрите на реализацию слоя для user и делаете по аналогии! Если там что-то непонятно,
не надо исправлять или делать по своему. Задавайте вопросы. Если действительно нужна правка - я сделаю и напишу всем.
4.1: после авторизации (сделаем позднее), id авторизованного юзера можно получить из SecurityUtil.authUserId().
 Запрос попадает в контроллер, методы которого будут доступны снаружи по http, т.е. запрос можно будет сделать с
 ЛЮБЫМ id для еды (не принадлежащем авторизированному пользователю). Нельзя позволять модифицировать/смотреть чужую еду.
4.2: SecurityUtil может использоваться только на слое web (см. реализацию ProfileRestController).
MealService можно тестировать без подмены логики авторизации, поэтому в методы сервиса и репозитория мы
передаем параметр userId: id авторизованного пользователя (предполагаемого владельца еды).
4.3: если еда не принадлежит авторизированному пользователю или отсутствует, в MealService бросать NotFoundException.
4.4: конвертацию в MealTo можно делать как в слое web, так и в service (Mapping Entity->DTO: Controller or Service?)
4.5: в MealService постараться сделать в каждом методе только одни запрос к MealRepository
4.6 еще раз: не надо в названиях методов повторять названия класса (Meal).
image

5: включить классы еды в контекст Spring (добавить аннотации) и вызвать из SpringMain любой метод MealRestController (проверить что Spring все корректно заинжектил)

Optional
6: в MealServlet сделать инициализацию Spring, достать MealRestController из контекста и работать с
 едой через него (как в SpringMain). pom.xml НЕ менять, работаем со spring-context.
 Сервлет обращается к контролеру, контроллер вызывает сервис, сервис - репозиторий.
  Когда будем работать через Spring MVC, MealServlet удалим, останется только контроллер.
image

7: добавить в meals.jsp и MealServlet фильтрацию еды по дате и по времени (см. демо). Сброс фильтра делать не надо (реализуем через ajax в HW8). ВНИМАНИЕ: в приложении фильтрация делается не по интервалу дата-время, а отдельно по датам и затем отдельно по времени.
8: добавить выбор текущего залогиненного пользователя (имитация аутентификации, сделать Select с двумя элементами со значениями 1 и 2 в index.html и SecurityUtil.setAuthUserId(userId) в UserServlet). От выбора user или admin будет зависеть отображение еды: user-а или admin-а. Настоящая аутентификация будет через Spring Security позже.
Итоги занятия после выполнения ДЗ:
Мы создали архитектуру нашего приложения с разделением на слои и внедрили в наш проект фреймворк Spring, который их связывает.
Далее мы реализовали функционал нашего приложения для работы с едой, как он сделан в демо приложении (но с фиктивной аутентификацией)
 */
