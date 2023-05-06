package com.example.demo.service.cqrs;

public class Mediator<T, U, V> implements IMediator<T, U, V> {
    private V repository;

    public Mediator(V repository) {
        this.repository = repository;
    }

    @Override
    public <U, V> T execute(ICommand<T, U, V> command) {
        return command.handle((V) repository);
    }
}
