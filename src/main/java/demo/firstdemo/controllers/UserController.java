package demo.firstdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import demo.firstdemo.model.User;
import demo.firstdemo.service.UserService;

import java.util.List;
import java.util.Optional;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController

public class UserController{

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public @ResponseBody Optional<User> getUser(@PathVariable Long id) {
        return userService.getById(id);
    }
    @RequestMapping(value = "/userByName/{name}",method = RequestMethod.GET)
    public @ResponseBody List<User> getByName(@PathVariable String name){
        return userService.findByName(name);
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAllUsers();
    }
    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    public HttpStatus deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return HttpStatus.NO_CONTENT;
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public HttpStatus createUser(@RequestBody User user){
       return userService.addUser(user)?HttpStatus.CREATED:HttpStatus.BAD_REQUEST;
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public HttpStatus updateUser(@RequestBody User user){
        return userService.updateUser(user)?HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST;
    }

    

}