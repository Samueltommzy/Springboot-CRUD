package demo.firstdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import demo.firstdemo.drivers.DbInterface;
import demo.firstdemo.model.User;
import demo.firstdemo.repository.UserRepository;

//Indicate spring framework that this should be treated as a service class
@Service
public class UserService {
    @Autowired
    UserRepository<User> userRepository;
    
    //annotation indicates that the method will be executed in the transaction.
    //Spring will take care of transaction management.
    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public List<User> findByName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Transactional
    public Optional<User> getById(Long id){
        return userRepository.findById(id); 
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
    @Transactional
    public boolean addUser(User user){
        return userRepository.save(user) != null;
    }

    @Transactional
    public boolean updateUser(User user){
        return userRepository.save(user) != null;
    }
}