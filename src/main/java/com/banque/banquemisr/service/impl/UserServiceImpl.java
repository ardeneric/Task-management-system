package banque.banquemisr.service.impl;

import banque.banquemisr.entity.User;
import banque.banquemisr.repository.UserRepository;
import banque.banquemisr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}