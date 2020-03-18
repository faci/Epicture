package com.example.test_epic.models;

public class Images {
    public imageData data;

    public static class imageData{
        public String id;
        public String title;
        public String description;
        public int datetime;
        public String type;
        public boolean animated;
        public int width;
        public int height;
        public int size;
        public int views;
        public int bandwidth;
        public String vote;
        public boolean favorite;
        public boolean nsfw;
        public String section;
        public String account_url;
        public String accound_id;
        public boolean is_ad;
        public boolean in_most_viral;
        public boolean has_sound;
        public String link;
        public int comment_count;
        public int favorite_count;
        public int ups;
        public int downs;
        public int points;
        public int score;

        @Override
        public String toString() {
            return "UploadedImage{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", type='" + type + '\'' +
                    ", animated=" + animated +
                    ", width=" + width +
                    ", height=" + height +
                    ", size=" + size +
                    ", views=" + views +
                    ", bandwidth=" + bandwidth +
                    ", vote='" + vote + '\'' +
                    ", favorite=" + favorite +
                    ", nsfw='" + nsfw + '\'' +
                    ", section='" + section + '\'' +
                    ", account_url='" + account_url + '\'' +
                    ", accound_id='" + accound_id + '\'' +
                    ", is_ad='" + is_ad + '\'' +
                    ", in_most_viral='" + in_most_viral + '\'' +
                    ", has_sound='" + has_sound + '\'' +
                    ", link='" + link + '\'' +
                    ", comment_count='" + comment_count + '\'' +
                    ", favorite_count='" + favorite_count + '\'' +
                    ", ups='" + ups + '\'' +
                    ", downs='" + downs + '\'' +
                    ", points='" + points + '\'' +
                    ", score='" + score + '\'' +
                    '}';
        }
    }
}
