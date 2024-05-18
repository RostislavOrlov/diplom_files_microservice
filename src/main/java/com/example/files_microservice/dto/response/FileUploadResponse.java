package com.example.files_microservice.dto.response;

public record FileUploadResponse(
   String id,
   String name,
   String url
//   String size
) {
}
