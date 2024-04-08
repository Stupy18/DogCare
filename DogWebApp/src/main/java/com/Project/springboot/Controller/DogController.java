package com.Project.springboot.Controller;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.BaseClasses.User;
import com.Project.springboot.Dto.DogDTO;
import com.Project.springboot.Repository.UserRepository;
import com.Project.springboot.Service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/dogs")
public class DogController {

    private final DogService dogService;
    private final UserRepository userRepository;

    @Autowired
    public DogController(DogService dogService, UserRepository userRepository) {

        this.dogService = dogService;
        this.userRepository = userRepository;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dog>> getDogsByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Dog> dogs = dogService.findDogsByUser(user.get());
        return ResponseEntity.ok(dogs);
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

    @GetMapping("/user/{userId}/name/{name}")
    public ResponseEntity<Dog> getDogByUserIdAndName(@PathVariable Long userId, @PathVariable String name) {
        Optional<Dog> dog = dogService.findDogByUserIdAndName(userId, name);
        return dog.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
