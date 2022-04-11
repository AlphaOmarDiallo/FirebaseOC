package com.ocr.firebaseoc.repository;

public final class UserRepository {

    private static volatile UserRepository instance;

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        UserRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized(UserRepository.class) {
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }
}
