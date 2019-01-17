package com.rest.mock.restmock;

import com.rest.mock.restmock.domain.User;
import com.rest.mock.restmock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public List<User> getUserByName(@RequestParam String name) {
        return repository.findAllByFirstNameContainingIgnoreCase(name);
    }

}

