package com.example.springjwt.global.common;

public interface Mapper<E, R> {

    R toResponse(E e);
}
