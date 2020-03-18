package com.example.test_epic.models.responseModels;

public class favResponse {
    public boolean success;
    public int status;
    public boolean data;

    @Override
    public String toString() {
        return "ImageResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
