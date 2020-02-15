package demo.firstdemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import demo.firstdemo.model.User;

public interface UserRepository<U> extends CrudRepository<User,Long>{
    List<User> findByFirstName(String firstName);
}