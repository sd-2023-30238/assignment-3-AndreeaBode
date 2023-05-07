package com.example.demo.service.cqrs;

public interface IQuery <T,U>{
    T handle (U repo);

}