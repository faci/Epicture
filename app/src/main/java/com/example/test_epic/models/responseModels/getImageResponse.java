package com.example.test_epic.models.responseModels;

import java.util.ArrayList;
import java.util.List;

public class getImageResponse {
    public boolean success;
    public int status;
    public List<ImageResponse.UploadedImage> data;

    @Override
    public String toString() {
        return "ImageResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data.toString() +
                '}';
    }
}
