package petrossov.service.models;

import java.util.ArrayList;
import java.util.List;

public class FakeStorage {
    // переменная, которая хранит ссылку на единственный экземпляр объекта класса FakeStorage
    private static final FakeStorage storage;

    // статически инициализатор, создающий объект класса FakeStorage. Вызывается один раз при загрузке класса в JVM
    static {
        storage = new FakeStorage();
    }

    // поле-список, хранящее список пользователей системы
    private List<User> users;

    // приватный констуктор, выполняющий инициализацию списка
    private FakeStorage() {
        this.users = new ArrayList<>();
        User user1 = new User(1,"Petrossov1","Nikita","07071988","07071988","petrossov",Role.USER,State.ACTIVE);
        User user2 = new User(2,"Petrossov2","Nikita","07071988","07071988","petrossov",Role.USER,State.ACTIVE);
        User user3 = new User(3,"Petrossov3","Nikita","07071988","07071988","petrossov",Role.USER,State.ACTIVE);
        User user4 = new User(4,"Petrossov4","Nikita","07071988","07071988","petrossov",Role.USER,State.ACTIVE);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    // метод, предоставляющий доступ к объекту класса
    public static FakeStorage storage() {
        return storage;
    }

    // метод, возвращающий список пользователей
    public List<User> users() {
        return users;
    }
    public User getUserById(Integer id){
        return users.get(id);
    }
}
