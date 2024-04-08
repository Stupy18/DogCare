package com.Project.springboot.Service;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.BaseClasses.User;
import com.Project.springboot.Dto.DogDTO;
import com.Project.springboot.Repository.DogRepository;
import com.Project.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    @Autowired
    public DogService(DogRepository dogRepository, UserRepository userRepository) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
    }

    // In DogService.java
    public Dog addDog(DogDTO dogDTO) {
        Dog dog = new Dog();
        dog.setName(dogDTO.getName());
        dog.setImageURL(dogDTO.getImageURL());
        dog.setWeight(dogDTO.getWeight());
        dog.setAge(dogDTO.getAge());
        dog.setHeight(dogDTO.getHeight());
        dog.setBreedName(dogDTO.getBreedName()); // Use the breed name as a simple string
        dog.setUser(userRepository.findById(dogDTO.getUserID()).orElse(null)); // Handle user association similarly

        return dogRepository.save(dog);
    }

    public Dog addDogForUser(Long userId, DogDTO dogDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Dog dog = new Dog();
        dog.setName(dogDTO.getName());
        dog.setImageURL(dogDTO.getImageURL());
        dog.setWeight(dogDTO.getWeight());
        dog.setAge(dogDTO.getAge());
        dog.setHeight(dogDTO.getHeight());
        dog.setBreedName(dogDTO.getBreedName()); // Assuming you've changed Breed to a String 'breedName'
        dog.setUser(user);
        return dogRepository.save(dog);
    }

    public Dog updateDog(Long dogID, DogDTO dogDetails) {
        Dog dog = dogRepository.findById(dogID)
                .orElseThrow(() -> new RuntimeException("Dog not found"));
        dog.setName(dogDetails.getName());
        dog.setImageURL(dogDetails.getImageURL());
        dog.setWeight(dogDetails.getWeight());
        dog.setAge(dogDetails.getAge());
        dog.setHeight(dogDetails.getHeight());
        dog.setBreedName(dogDetails.getBreedName()); // Use the breed name as a simple string
        // User association is not being updated here, but you can add it if needed
        return dogRepository.save(dog);
    }

    public Optional<Dog> findDogById(Integer id) {
        return dogRepository.findById(Long.valueOf(id));
    }

    public List<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public void deleteDog(Integer id) {
        dogRepository.deleteById(Long.valueOf(id));
    }

    public List<Dog> findDogsByUser(User user) {
        return dogRepository.findByUser(user);
    }

    public Optional<Long> findDogIdByUserIdAndName(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return dogRepository.findDogIdByUserAndName(user, name);
    }



}
