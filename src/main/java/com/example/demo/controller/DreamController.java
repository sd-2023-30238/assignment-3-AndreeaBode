package com.example.demo.controller;

import com.example.demo.dto.DreamDto;
import com.example.demo.dto.DreamMapper;
import com.example.demo.enity.Dream;
import com.example.demo.repository.DreamRepository;
import com.example.demo.service.DreamService;
import com.example.demo.service.cqrs.GetDreamCommand;
import com.example.demo.service.cqrs.Mediator;
import com.example.demo.service.cqrs.SaveDreamCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dreamService")
public class DreamController {

    @Autowired
    private DreamService dreamService;
    @Autowired
    private DreamRepository dreamRepository;

    @PostMapping("/save")
    public Dream saveDream(@RequestBody Dream body) {
        Mediator<Dream, DreamDto, DreamRepository> mediator = new Mediator<>(dreamRepository);
        DreamDto dreamDto = new DreamDto(body);
        SaveDreamCommand command = new SaveDreamCommand(dreamDto);
        return mediator.execute(command);
    }

    @GetMapping("/getAllDreams")
    public ResponseEntity<List<DreamDto>> retrieveAllDreams() {
        Mediator<List<DreamDto>, Void, DreamRepository> mediator = new Mediator<>(dreamRepository);
        GetDreamCommand command = new GetDreamCommand();
        List<DreamDto> dreams = mediator.execute(command);
        return new ResponseEntity<>(dreams, HttpStatus.OK);
    }


}
