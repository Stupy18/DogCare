//package com.Project.springboot.Service;
//
//import com.Project.springboot.BaseClasses.Breed;
//import com.Project.springboot.BaseClasses.Dog;
//import com.Project.springboot.Repository.BreedRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BreedService {
//
//    private final BreedRepository breedRepository;
//    private final WebClient webClient;
//    private final String apiKey = "live_StlZNPMyGC31fefMQBBrCEgXSc0hcVJbCM7O7UAGbbw5uMRj9DB2zAgpzho2mSkF";
//
//    @Autowired
//    public BreedService(BreedRepository breedRepository, WebClient.Builder webClientBuilder) {
//        this.breedRepository = breedRepository;
//        this.webClient = webClientBuilder.baseUrl("https://api.thedogapi.com/v1").build();
//    }
//
//    public Breed addBreed(String breedName) {
//        // First, try to find the breed in the database
//        Optional<Breed> existingBreed = breedRepository.findByBreedName(breedName);
//        return existingBreed.orElseGet(() -> fetchAndSaveBreed(breedName));
//    }
//
//    public Breed addBreedsimple(Breed breed) {
//        // Optionally add logic here to check if breed info exists and fetch from The Dog API if not
//        return breedRepository.save(breed);
//    }
//
//    private Breed fetchAndSaveBreed(String breedName) {
//        // Fetch breed info from The Dog API
//        Breed breedResponse = this.webClient.get()
//                .uri("/breeds/search?q=" + breedName)
//                .header("x-api-key", apiKey)
//                .retrieve()
//                .bodyToMono(Breed[].class)
//                .flatMap(breedDataArray -> {
//                    if (breedDataArray.length > 0) {
//                        Breed breedData = breedDataArray[0];
//                        // You need to map the BreedData to your Breed entity here
//                        Breed newBreed = mapBreedDataToBreed(breedData);
//                        return Mono.just(breedRepository.save(newBreed));
//                    }
//                    return Mono.empty();
//                })
//                .block(); // Note: .block() is used here for simplicity, consider using async handling
//
//        if (breedResponse != null) {
//            return breedResponse;
//        } else {
//            throw new IllegalStateException("Breed data could not be fetched from the API.");
//        }
//    }
//
//    private Breed mapBreedDataToBreed(Breed breedData) {
//        // Map the data from the API to your Breed entity
//        // Implement logic to parse and map data here
//        Breed newBreed = new Breed();
//        // Set fields on newBreed from breedData...
//        return newBreed;
//    }
//
//    // Fetch breed information by breed name
//    public Optional<Breed> fetchBreedInfo(String breedName) {
//        // You might want to update this method to return a Breed instead of Optional<Breed>
//        // if you're ensuring that the breed always exists after this method is called.
//        return breedRepository.findByBreedName(breedName);
//    }
//
//    // Find all breeds in the database
//    public List<Breed> findAllBreeds() {
//        return breedRepository.findAll();
//    }
//}
