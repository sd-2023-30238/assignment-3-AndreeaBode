package com.example.demo.service.cqrs;

import com.example.demo.dto.DreamDto;
import com.example.demo.enity.Dream;
import com.example.demo.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class SaveDreamCommand implements ICommand<Dream, DreamDto, DreamRepository> {

    //public DreamRepository dreamRepository;
    private DreamDto dreamDto;

    public SaveDreamCommand(DreamDto dreamDto){
        this.dreamDto = dreamDto;
    }

    @Override
    public Dream handle(DreamRepository dreamRepository1) {
        Dream dream = dreamDto.getDream();
        dream.setLocalDate(LocalDate.now());
        System.out.println(dream.getData());
        return dreamRepository1.save(dream);
    }


}





