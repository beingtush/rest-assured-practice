package com.restassured.practice.utils;

import com.restassured.practice.models.Post;
import com.restassured.practice.models.User;

import java.util.Random;
import java.util.UUID;

/**
 * Utility class for generating test data
 */
public class TestDataGenerator {

    private static final Random random = new Random();
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
    private static final String[] DOMAINS = {"example.com", "test.com", "demo.com", "sample.com"};

    /**
     * Generate a random user with unique data
     */
    public static User generateRandomUser() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        
        return User.builder()
                .name(firstName + " " + lastName)
                .username(firstName.toLowerCase() + uniqueId)
                .email(firstName.toLowerCase() + "." + lastName.toLowerCase() + uniqueId + "@" + 
                       DOMAINS[random.nextInt(DOMAINS.length)])
                .build();
    }

    /**
     * Generate a random post with unique data
     */
    public static Post generateRandomPost() {
        return Post.builder()
                .title("Test Post " + UUID.randomUUID().toString().substring(0, 8))
                .body("This is a test post body with random content: " + UUID.randomUUID().toString())
                .userId(random.nextInt(10) + 1)
                .build();
    }

    /**
     * Generate a random email address
     */
    public static String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@" + 
               DOMAINS[random.nextInt(DOMAINS.length)];
    }

    /**
     * Generate a random string of specified length
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Generate a random integer between min and max (inclusive)
     */
    public static int generateRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
