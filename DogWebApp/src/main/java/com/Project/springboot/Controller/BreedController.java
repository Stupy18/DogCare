//package com.Project.springboot.Controller;
//
//import com.Project.springboot.BaseClasses.Breed;
//import com.Project.springboot.BaseClasses.User;
//import com.Project.springboot.Dto.BreedDTO;
//import com.Project.springboot.Service.BreedService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/breeds")
//public class BreedController {
//
//    private final BreedService breedService;
//
//    @Autowired
//    public BreedController(BreedService breedService) {
//        this.breedService = breedService;
//    }
//
////    @PostMapping
////    public ResponseEntity<Breed> addBreed(@RequestBody BreedDTO breedDTO) {
////        try {
////            Breed breed = breedService.addBreed(breedDTO.getBreedName());
////            return ResponseEntity.ok(breed);
////        } catch (Exception e) {
////            // Handle exception appropriately
////            return ResponseEntity.badRequest().body(null);
////        }
////    }
//
//    @PostMapping
//    public ResponseEntity<Breed> createBreed(@RequestBody Breed breed) {
//        Breed createdBreed = breedService.addBreedsimple(breed);
//        return ResponseEntity.ok(createdBreed);
//    }
//
//    @GetMapping("/{breedName}")
//    public ResponseEntity<Breed> getBreedInfo(@PathVariable String breedName) {
//        return breedService.fetchBreedInfo(breedName)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Breed>> getAllBreeds() {
//        List<Breed> breeds = breedService.findAllBreeds();
//        return ResponseEntity.ok(breeds);
//    }
//
//    // Additional BreedController methods as needed...
//
//}
