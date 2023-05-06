package com.example.demo.service.cqrs;

public interface ICommand<T,U,V>{
        T handle(V repo);
}
