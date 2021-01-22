package com.nickhuang.springLab.controller;

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

import com.nickhuang.springLab.model.Tutorial;
import com.nickhuang.springLab.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    TutorialRepository tutorialRepository;


    @GetMapping("/tutorials")
    public ResponseEntity<Object> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            Map<String, Object> dto = new HashMap<>();

            if (title == null){
                tutorialRepository.findAll().stream().forEach(playerCrab -> {
//                    getname
                    Field[] fields = playerCrab.getClass().getDeclaredFields();
                    for(int i = 0; i < fields.length; i++) {
//                        Object nick = Tutorial.newInstance();

                        String attribute = fields[i].getName();
//                        PropertyDescriptor pd = new PropertyDescriptor(attribute, nick);
//                        Method rM = pd.getReadMethod();

//                        dto.put( attribute, rM.invoke(playerCrab));
                        dto.put( attribute, i);
                    }

                    System.out.println(playerCrab);
                });
            }


            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
