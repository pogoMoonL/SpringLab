package com.nickhuang.core.controller;

import java.util.concurrent.atomic.AtomicLong;
import java.beans.PropertyDescriptor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nickhuang.core.model.Tutorial;
import com.nickhuang.core.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class GreetingController {
    private static final String template = "Hello, %s!";

    @Autowired
    TutorialRepository tutorialRepository;


    @GetMapping("/tutorials")
    public ResponseEntity<Object> getAllTutorials(@RequestParam(required = false) String title) throws IllegalAccessException {
        try {
            Map<String, Object> dto = new HashMap<>();

            if (title == null){
                tutorialRepository.findAll().stream().forEach(playerCrab -> {
//                    getname
                    Field[] fields = playerCrab.getClass().getDeclaredFields();

                    try {
                        for(Field field : fields){
                            // 获取原来的访问控制权限
                            boolean accessFlag = field.isAccessible();
                            if(!field.isAccessible()){
                                field.setAccessible(true);
                            }
                            dto.put( field.getName(), field.get(playerCrab));

                        }
                    }
                    catch(Exception e) {
                        System.out.println(e.toString());
                    }

                });
            }


            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
