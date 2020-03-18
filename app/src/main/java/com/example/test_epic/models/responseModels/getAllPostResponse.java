package com.example.test_epic.models.responseModels;

import com.example.test_epic.models.Images;

import java.util.List;

public class getAllPostResponse {
    public boolean success;
    public int status;
    public List<getAlbum> data;

    public static class getAlbum {
        public String id;
        public String title;
        public String description;
        public int datetime;
        public String cover;
        public int cover_width;
        public int cover_height;
        public String account_url;
        public int account_id;
        public String privacy;
        public String layout;
        public int views;
        public String link;
        public int ups;
        public int downs;
        public int points;
        public int score;
        public boolean is_album;
        public String vote;
        public boolean favorite;
        public boolean nsfw;
        public String section;
        public int comment_count;
        public int favorite_count;
        public String topic;
        public int topic_id;
        public int images_count;
        public boolean in_gallery;
        public boolean is_ad;
        public List<Images> images;

        @Override
        public String toString() {
            return "getImage{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", cover='" + cover + '\'' +
                    ", cover_width='" + cover_width + '\'' +
                    ", cover_height='" + cover_height + '\'' +
                    ", account_url='" + account_url + '\'' +
                    ", account_id='" + account_id + '\'' +
                    ", privacy='" + privacy + '\'' +
                    ", layout='" + layout + '\'' +
                    ", views=" + views +
                    ", link='" + link + '\'' +
                    ", ups='" + ups + '\'' +
                    ", downs='" + downs + '\'' +
                    ", points='" + points + '\'' +
                    ", score='" + score + '\'' +
                    ", is_album='" + is_album + '\'' +
                    ", vote='" + vote + '\'' +
                    ", favorite=" + favorite + '\'' +
                    ", nsfw=" + nsfw + '\'' +
                    ", section=" + section + '\'' +
                    ", comment_count=" + comment_count + '\'' +
                    ", favorite_count=" + favorite_count + '\'' +
                    ", topic=" + topic + '\'' +
                    ", topic_id=" + topic_id + '\'' +
                    ", images_count=" + images_count + '\'' +
                    ", in_gallery=" + in_gallery + '\'' +
                    ", is_ad=" + is_ad + '\'' +
                    ", images=" + images + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data.toString() +
                '}';
    }
}
