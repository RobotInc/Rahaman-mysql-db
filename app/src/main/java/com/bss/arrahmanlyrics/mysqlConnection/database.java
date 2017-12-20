package com.bss.arrahmanlyrics.mysqlConnection;

import android.util.Log;

import com.bss.arrahmanlyrics.mysqlConnection.models.albums;
import com.bss.arrahmanlyrics.mysqlConnection.models.songs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by immoh on 20/12/2017.
 */

public class database {
    static Connection con = null;
    private static final String TAG = "database";
    public static ArrayList<albums> albumlist = new ArrayList<>();

    public static void connect() {
        Log.d(TAG, "connect: inside connect");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager
                    .getConnection("jdbc:mysql://139.99.8.128/beyonity_albums?useUnicode=true&characterEncoding=utf-8&"
                            + "user=beyonity_admin&password=@Beyonity2017");
            Log.d(TAG, "run: connected with mysql server");
            setAlbumList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setAlbumList() {
        if (con != null) {
            try {
                Statement s = con.createStatement();
                ResultSet set = s.executeQuery("Select * from albums");
                while (set.next()){
                    int album_id = set.getInt(1);
                    String album_name = set.getString(2);

                    String hero = set.getString(4);
                    String heroin = set.getString(5);

                    int year = set.getInt(7);
                    String image_link = set.getString(8);
                    albums a = new albums(
                            album_id,
                            album_name,
                            hero,
                            heroin,
                            year,
                            image_link
                    );
                    albumlist.add(a);
                }

                Log.d(TAG, "setAlbumList: "+albumlist.size());
                setSongList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setSongList() {
        if (con != null) {
            for(albums a : albumlist){
                int index  = albumlist.indexOf(a);
                try {
                    Statement s = con.createStatement();
                    ResultSet set = s.executeQuery("Select * from songs where album_id = "+a.getAlbum_id());

                    while (set.next()){
                        int song_id = set.getInt(1);
                        String song_title = set.getString(3);
                        String download_link = set.getString(4);
                        String lyrics_one = set.getString(5);
                        String lyrics_two = set.getString(6);
                        String lyrics_three = set.getString(7);
                        String lyrics_four = set.getString(8);
                        String lyricist = set.getString(9);
                        int track_no = set.getInt(10);

                        songs song = new songs(
                                song_id,
                                song_title,
                                download_link,
                                lyrics_one,
                                lyrics_two,
                                lyrics_three,
                                lyrics_four,
                                lyricist,
                                track_no
                        );


                        albumlist.get(index).setSonglist(song);

                    }
                    Log.d(TAG, "setSongList: "+albumlist.get(index).getSonglist().size());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public static String getLyricsOne(String moviename,String songName){
        String lyrics = "";
        for(albums a : albumlist){
            if(a.getAlbum_name().toLowerCase().equals(moviename.toLowerCase())){
                for(songs s : a.getSonglist()){
                    if(s.getSong_title().toLowerCase().equals(songName.toLowerCase())){
                        lyrics = s.getLyrics_one();
                    }
                }
            }
        }
        return lyrics;
    } public static String getLyricsTwo(String moviename,String songName){
        String lyrics = "";
        for(albums a : albumlist){
            if(a.getAlbum_name().toLowerCase().equals(moviename.toLowerCase())){
                for(songs s : a.getSonglist()){
                    if(s.getSong_title().toLowerCase().equals(songName.toLowerCase())){
                        lyrics = s.getLyrics_two();
                    }
                }
            }
        }
        return lyrics;
    } public static String getLyricsThree(String moviename,String songName){
        String lyrics = "";
        for(albums a : albumlist){
            if(a.getAlbum_name().toLowerCase().equals(moviename.toLowerCase())){
                for(songs s : a.getSonglist()){
                    if(s.getSong_title().toLowerCase().equals(songName.toLowerCase())){
                        lyrics = s.getLyrics_three();
                    }
                }
            }
        }
        return lyrics;
    } public static String getLyricsFour(String moviename,String songName){
        String lyrics = "";
        for(albums a : albumlist){
            if(a.getAlbum_name().toLowerCase().equals(moviename.toLowerCase())){
                for(songs s : a.getSonglist()){
                    if(s.getSong_title().toLowerCase().equals(songName.toLowerCase())){
                        lyrics = s.getLyrics_four();
                    }
                }
            }
        }
        return lyrics;
    }

}
