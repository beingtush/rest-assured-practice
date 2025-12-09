package com.restassured.practice.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * File Upload and Download Examples
 * Topics: Multipart form data, File download, Binary data handling
 */
public class FileUploadDownloadTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test(description = "File upload - Single file")
    public void testSingleFileUpload() {
        // Create a temporary test file
        File testFile = createTempFile("test-upload.txt", "This is test file content");

        given()
            .multiPart("file", testFile)
            .log().all()
        .when()
            .post("/post")
        .then()
            .log().all()
            .statusCode(200)
            .body("files", notNullValue());

        // Clean up
        testFile.delete();
    }

    @Test(description = "File upload with additional form data")
    public void testFileUploadWithFormData() {
        File testFile = createTempFile("document.txt", "Document content");

        given()
            .multiPart("file", testFile)
            .multiPart("description", "Test document")
            .multiPart("category", "general")
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("form.description", equalTo("Test document"))
            .body("form.category", equalTo("general"));

        testFile.delete();
    }

    @Test(description = "Download file - Image")
    public void testDownloadImage() {
        byte[] imageBytes = 
            given()
            .when()
                .get("/image/png")
            .then()
                .statusCode(200)
                .contentType("image/png")
                .extract()
                .asByteArray();

        System.out.println("Downloaded image size: " + imageBytes.length + " bytes");
        assert imageBytes.length > 0;
    }

    @Test(description = "Download file - JPEG")
    public void testDownloadJpeg() {
        given()
        .when()
            .get("/image/jpeg")
        .then()
            .statusCode(200)
            .contentType("image/jpeg");
    }

    @Test(description = "Upload with content type")
    public void testUploadWithContentType() {
        File jsonFile = createTempFile("data.json", "{\"name\": \"test\", \"value\": 123}");

        given()
            .multiPart("file", jsonFile, "application/json")
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("files", notNullValue());

        jsonFile.delete();
    }

    @Test(description = "Binary data handling")
    public void testBinaryDataHandling() {
        given()
        .when()
            .get("/bytes/1024")
        .then()
            .statusCode(200)
            .contentType("application/octet-stream");
    }

    // Helper method to create temporary files
    private File createTempFile(String fileName, String content) {
        try {
            File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);
            java.nio.file.Files.write(tempFile.toPath(), content.getBytes());
            return tempFile;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create temp file", e);
        }
    }
}
