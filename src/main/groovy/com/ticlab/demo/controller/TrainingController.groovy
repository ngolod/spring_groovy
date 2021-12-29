package com.ticlab.demo.controller

import com.ticlab.demo.entities.Training
import com.ticlab.demo.repositories.TrainingRepository
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(value = "Training Rest Controller", description = "REST API for Training")
@RestController
@RequestMapping("/api")
class TrainingController {

    @Autowired
    TrainingRepository trainingRepository

    @GetMapping("/training")
    ResponseEntity<List<Training>> getAllTrainings(@RequestParam(required = false) String name) {
        try {
            List<Training> training = new ArrayList<Training>()

            if (name == null)
                trainingRepository.findAll().forEach(training::add)
            else
                trainingRepository.findByName(name).forEach(training::add)

            if (training.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT)
            }

            return new ResponseEntity<>(training, HttpStatus.OK)
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/training/{id}")
    ResponseEntity<Training> getTrainingById(@PathVariable("id") long id) {
        Optional<Training> trainingData = trainingRepository.findById(id)

        if (trainingData.isPresent()) {
            return new ResponseEntity<>(trainingData.get(), HttpStatus.OK)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/training")
    ResponseEntity<Training> createTraining(@RequestBody Training training) {
        try {
            Training _training = trainingRepository
                    .save(new Training(name: training.getName(), age: training.getAge(),sex: training.getSex()))
            return new ResponseEntity<>(_training, HttpStatus.CREATED)
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/training/{id}")
    ResponseEntity<Training> updateTraining(@PathVariable("id") long id, @RequestBody Training training) {
        Optional<Training> trainingData = trainingRepository.findById(id)

        if (trainingData.isPresent()) {
            Training _training = trainingData.get()
            _training.setName(training.getName())
            _training.setAge(training.getAge())
            _training.setSex(training.getSex())
            return new ResponseEntity<>(trainingRepository.save(_training), HttpStatus.OK)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/training/{id}")
    ResponseEntity<HttpStatus> deleteTraining(@PathVariable("id") long id) {
        try {
            trainingRepository.deleteById(id)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/training")
    ResponseEntity<HttpStatus> deleteAllTrainings() {
        try {
            trainingRepository.deleteAll()
            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @GetMapping("/training/homme")
    ResponseEntity<List<Training>> findBySex() {
        try {
            List<Training> training = trainingRepository.findBySex("H")

            if (training.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT)
            }
            return new ResponseEntity<>(training, HttpStatus.OK)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
