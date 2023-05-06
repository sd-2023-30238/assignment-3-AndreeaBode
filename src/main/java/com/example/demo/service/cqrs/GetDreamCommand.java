package com.example.demo.service.cqrs;

import com.example.demo.dto.DreamDto;
import com.example.demo.enity.Dream;
import com.example.demo.repository.DreamRepository;

import java.util.ArrayList;
import java.util.List;

public class GetDreamCommand implements ICommand<List<DreamDto>, Void, DreamRepository> {

        @Override
        public List<DreamDto> handle(DreamRepository dreamRepository) {
            List<Dream> dreams = dreamRepository.findAll();
            System.out.println(dreams.toString());
            List<DreamDto> dreamDtos = new ArrayList<>();
            for (Dream dream : dreams) {
                DreamDto dreamDto = new DreamDto();
                dreamDto.setDescriere(dream.getDescriere());
                dreamDto.setStres(dream.getStres());
                dreamDto.setEnergie(dream.getEnergie());
                dreamDto.setDurata(dream.getDurata());
                dreamDto.setTag(dream.getTag());
                dreamDto.setData(dream.getData());
                dreamDtos.add(dreamDto);
            }

            return dreamDtos;
        }
    }


