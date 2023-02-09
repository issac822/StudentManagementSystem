package org.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.StudentMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("all")
@CrossOrigin(origins = {"*","null"})
public class Controller {
    @Autowired
    private UserMapper userMapper;
    private Gson gson = new Gson();
    @Autowired
    private StudentMapper studentMapper;
    @GetMapping("/students")
    public String getStudents(){
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);

    }
    @PostMapping("/add")
    public void addStudent(@RequestBody Student student){
        studentMapper.insert(student);
    }
    @PostMapping("/remove")
    public void removeStudent(@RequestBody Student student){
        studentMapper.deleteById(student);
    }
    @PostMapping("/update")
    public void updateStudent(@RequestBody Student student){
        studentMapper.updateById(student);
    }
    @PostMapping("/login")
    public String loginStudent(@RequestBody User user){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        User user_selected = userMapper.selectOne(userQueryWrapper);
        if(user_selected ==null){
            return "0";
        }else{
            return "1";
        }
    }
    @PostMapping("/register")
    public void register(@RequestBody User user){
        userMapper.insert(user);
    }

}
