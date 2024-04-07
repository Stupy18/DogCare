package com.Project.springboot.Controller;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.Dto.DogDTO;
import com.Project.springboot.Service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody DogDTO dogDTO) {
        Dog createdDog = dogService.addDog(dogDTO);
        return ResponseEntity.ok(createdDog);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Dog> createDogForUser(@PathVariable Long userId, @RequestBody DogDTO dogDTO) {
        Dog dog = dogService.addDogForUser(userId, dogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dog);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Integer id, @RequestBody DogDTO dogdto) {
        Dog updatedDog = dogService.updateDog(Long.valueOf(id), dogdto);
        return ResponseEntity.ok(updatedDog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Integer id) {
        return dogService.findDogById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogs = dogService.findAllDogs();
        return ResponseEntity.ok(dogs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDog(@PathVariable Integer id) {
        dogService.deleteDog(id);
        return ResponseEntity.ok().build();
    }
}
