package model;

import java.util.List;

public class GeneralInformation {

        private String title;
        private String author;
        private String summary;
        private List<String> genres;
        private Integer publication_year;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public Integer getPublication_year() {
            return publication_year;
        }

        public void setPublication_year(Integer publication_year) {
            this.publication_year = publication_year;
        }
    }


