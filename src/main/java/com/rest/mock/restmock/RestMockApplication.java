package com.rest.mock.restmock;

import com.rest.mock.restmock.domain.User;
import com.rest.mock.restmock.repository.UserRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RestMockApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestMockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Fairy fairy = Fairy.create();
        IntStream.rangeClosed(1, 200)
                .mapToObj(i->fairy.person())
                .map(this::mapToUser)
                .peek(u->log.info(u.toString()))
                .forEach(userRepository::save);
    }

    private User mapToUser(Person p) {
        return new User(p.firstName(), p.lastName(), p.email(), p.sex().toString(), p.getAddress().toString());
    }
}

