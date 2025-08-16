package com.forohub.forohub_creative_ai;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Clase de prueba para generar una contraseña encriptada
public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456"; // Aquí escríbes la contraseña que quieres encriptar
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}
