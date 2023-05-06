package com.example.demo.service.cqrs;

public interface IMediator<T, U,V> {
    <U,V> T execute(ICommand<T,U,V> command);
}
