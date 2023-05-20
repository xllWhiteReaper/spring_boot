package com.first_spring_project.first_spring_project.utils;

import com.first_spring_project.first_spring_project.models.User;

import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2;

public class Encrypter {

    final static int ENCRYPTION_CYCLES = 1;
    final static int MEMORY = 1024;
    final static int NUMBER_OF_THREADS = 1;
    final static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public static void encryptUserPass(User user) {
        String hashedPassword = argon2.hash(ENCRYPTION_CYCLES, MEMORY,
                NUMBER_OF_THREADS, user.getPassword());
        user.setPassword(hashedPassword);
    }

    public static boolean verify(User storedUser, User loginUser) {
        return argon2.verify(storedUser.getPassword(), loginUser.getPassword());
    }
}
