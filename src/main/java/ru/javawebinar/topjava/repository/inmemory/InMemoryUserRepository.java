package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Integer, User> repository = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    {
        final List<User> users = Arrays.asList(
                new User(null, "User", "user@gmail.com", "qwerty", Role.USER),
                new User(null, "Admin", "admin@gmail.com", "qwerty", Role.ADMIN)
        );
        users.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        repository.remove(id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            repository.put(counter.incrementAndGet(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        Comparator<User> comparatorByName = new Comparator<User>() {
            @Override
            public int compare(User user, User anotherUser) {
                return user.getName().compareTo(anotherUser.getName());
            }
        };

        return repository.values().stream().sorted(comparatorByName).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
