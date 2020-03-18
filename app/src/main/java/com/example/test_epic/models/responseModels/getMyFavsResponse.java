package com.example.test_epic.models.responseModels;

import com.example.test_epic.models.Images;

import java.util.List;

public class getMyFavsResponse {
    public boolean success;
    public int status;
    public List<getAlbum> data;

    public static class getAlbum {
        public String id;
        public String title;
        public String description;
        public String cover;
        public int cover_width;
        public int cover_height;
        public int width;
        public int height;
        public String account_url;
        public int account_id;
        public String privacy;
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
        public String topic_id;
        public int images_count;
        public int datetime;
        public boolean in_gallery;
        public boolean is_most_viral;
        public String tags;
        public List<Images> images;
        public boolean has_sound;
        public boolean animated;
        public int size;

        @Override
        public String toString() {
            return "getImage{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", cover='" + cover + '\'' +
                    ", cover_width='" + cover_width + '\'' +
                    ", cover_height='" + cover_height + '\'' +
                    ", width='" + width + '\'' +
                    ", height='" + height + '\'' +
                    ", account_url='" + account_url + '\'' +
                    ", account_id='" + account_id + '\'' +
                    ", privacy='" + privacy + '\'' +
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
                    ", datetime=" + datetime + '\'' +
                    ", in_gallery=" + in_gallery + '\'' +
                    ", is_most_viral=" + is_most_viral + '\'' +
                    ", tags=" + tags + '\'' +
                    ", images=" + images + '\'' +
                    ", has_sound=" + has_sound + '\'' +
                    ", animated=" + animated + '\'' +
                    ", size=" + size + '\'' +
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
