package com.example.demo.dto;

import com.example.demo.enity.Dream;

public class DreamMapper {

    public static Dream transformToEntity(DreamDto dreamDto){
        Dream dream = new Dream();
        dream.setDescriere(dreamDto.getDescriere());
        dream.setStres(dreamDto.getStres());
        dream.setDurata(dreamDto.getDurata());
        dream.setEnergie(dreamDto.getEnergie());
        dream.setTag(dreamDto.getTag());
        dream.setLocalDate(dreamDto.getData());
        return dream;
    }
    public static DreamDto transformToDto(Dream dream){
        DreamDto dreamDto = new DreamDto();
        dreamDto.setDescriere(dream.getDescriere());
        dreamDto.setStres(dream.getStres());
        dreamDto.setDurata(dream.getDurata());
        dreamDto.setEnergie(dream.getEnergie());
        dreamDto.setTag(dream.getTag());
        dreamDto.setData(dream.getData());
        return dreamDto;
    }

}
