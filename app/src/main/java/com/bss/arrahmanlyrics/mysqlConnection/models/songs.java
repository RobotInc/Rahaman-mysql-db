package com.bss.arrahmanlyrics.mysqlConnection.models;

/**
 * Created by immoh on 20/12/2017.
 */

public class songs {

    int song_id;
    String song_title;
    String download_link;
    String lyrics_one;
    String lyrics_two;
    String lyrics_three;
    String lyrics_four;
    String lyricist;
    int track_no;

    public songs(int song_id, String song_title, String download_link, String lyrics_one, String lyrics_two, String lyrics_three, String lyrics_four, String lyricist, int track_no) {
        this.song_id = song_id;
        this.song_title = song_title;
        this.download_link = download_link;
        this.lyrics_one = lyrics_one;
        this.lyrics_two = lyrics_two;
        this.lyrics_three = lyrics_three;
        this.lyrics_four = lyrics_four;
        this.lyricist = lyricist;
        this.track_no = track_no;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_title() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public String getLyrics_one() {
        return lyrics_one;
    }

    public void setLyrics_one(String lyrics_one) {
        this.lyrics_one = lyrics_one;
    }

    public String getLyrics_two() {
        return lyrics_two;
    }

    public void setLyrics_two(String lyrics_two) {
        this.lyrics_two = lyrics_two;
    }

    public String getLyrics_three() {
        return lyrics_three;
    }

    public void setLyrics_three(String lyrics_three) {
        this.lyrics_three = lyrics_three;
    }

    public String getLyrics_four() {
        return lyrics_four;
    }

    public void setLyrics_four(String lyrics_four) {
        this.lyrics_four = lyrics_four;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public int getTrack_no() {
        return track_no;
    }

    public void setTrack_no(int track_no) {
        this.track_no = track_no;
    }
}
