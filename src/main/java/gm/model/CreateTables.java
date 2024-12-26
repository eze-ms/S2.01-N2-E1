package gm.model;

import gm.conexion.Conexion;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    public static void createTables() {
        Connection connection = Conexion.getConexion();

        String User = "CREATE TABLE IF NOT EXISTS User (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "password VARCHAR(100) NOT NULL, " +
                "name VARCHAR(50) NOT NULL, " +
                "birth_date DATE NOT NULL, " +
                "gender ENUM('M', 'F', 'Other') NOT NULL, " +
                "country VARCHAR(50) NOT NULL, " +
                "postal_code VARCHAR(10) NOT NULL" +
                ") ENGINE=InnoDB;";

        String Video = "CREATE TABLE IF NOT EXISTS Video (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(100) NOT NULL, " +
                "description TEXT, " +
                "size FLOAT NOT NULL, " +
                "file_name VARCHAR(255) NOT NULL, " +
                "duration TIME NOT NULL, " +
                "thumbnail VARCHAR(255), " +
                "views INT DEFAULT 0, " +
                "likes INT DEFAULT 0, " +
                "dislikes INT DEFAULT 0, " +
                "state ENUM('public', 'hidden', 'private') NOT NULL, " +
                "user_id INT NOT NULL, " +
                "publication_date DATETIME NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String Tag = "CREATE TABLE IF NOT EXISTS Tag (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL UNIQUE" +
                ") ENGINE=InnoDB;";

        String VideoTag = "CREATE TABLE IF NOT EXISTS Video_Tag (" +
                "video_id INT NOT NULL, " +
                "tag_id INT NOT NULL, " +
                "PRIMARY KEY (video_id, tag_id), " +
                "FOREIGN KEY (video_id) REFERENCES Video(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String Channel = "CREATE TABLE IF NOT EXISTS Channel (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100) NOT NULL, " +
                "description TEXT, " +
                "create_date DATE NOT NULL, " +
                "user_id INT NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String Subscription = "CREATE TABLE IF NOT EXISTS Subscription (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "user_id INT NOT NULL, " +
                "channel_id INT NOT NULL, " +
                "subscription_date DATETIME NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (channel_id) REFERENCES Channel(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String Playlist = "CREATE TABLE IF NOT EXISTS Playlist (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100) NOT NULL, " +
                "create_date DATE NOT NULL, " +
                "state ENUM('public', 'private') NOT NULL, " +
                "user_id INT NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String PlaylistVideo = "CREATE TABLE IF NOT EXISTS Playlist_Video (" +
                "playlist_id INT NOT NULL, " +
                "video_id INT NOT NULL, " +
                "PRIMARY KEY (playlist_id, video_id), " +
                "FOREIGN KEY (playlist_id) REFERENCES Playlist(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (video_id) REFERENCES Video(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String Comment = "CREATE TABLE IF NOT EXISTS Comment (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "text TEXT NOT NULL, " +
                "comment_date DATETIME NOT NULL, " +
                "user_id INT NOT NULL, " +
                "video_id INT NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (video_id) REFERENCES Video(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB;";

        String VideoReaction = "CREATE TABLE IF NOT EXISTS Video_Reaction (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "user_id INT NOT NULL, " +
                "video_id INT NOT NULL, " +
                "type ENUM('like', 'dislike') NOT NULL, " +
                "reaction_date DATETIME NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (video_id) REFERENCES Video(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "UNIQUE (user_id, video_id)" +
                ") ENGINE=InnoDB;";

        String CommentReaction = "CREATE TABLE IF NOT EXISTS Comment_Reaction (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "user_id INT NOT NULL, " +
                "comment_id INT NOT NULL, " +
                "type ENUM('like', 'dislike') NOT NULL, " +
                "reaction_date DATETIME NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (comment_id) REFERENCES Comment(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "UNIQUE (user_id, comment_id)" +
                ") ENGINE=InnoDB;";

        String VideoReactionTrigger = "CREATE TRIGGER update_video_likes_dislikes " +
                "AFTER INSERT ON Video_Reaction " +
                "FOR EACH ROW " +
                "BEGIN " +
                "IF NEW.type = 'like' THEN " +
                "    UPDATE Video SET likes = likes + 1 WHERE id = NEW.video_id; " +
                "ELSE " +
                "    UPDATE Video SET dislikes = dislikes + 1 WHERE id = NEW.video_id; " +
                "END IF; " +
                "END;";

        String CommentReactionTrigger = "CREATE TRIGGER update_comment_likes_dislikes " +
                "AFTER INSERT ON Comment_Reaction " +
                "FOR EACH ROW " +
                "BEGIN " +
                "IF NEW.type = 'like' THEN " +
                "    UPDATE Comment SET likes = likes + 1 WHERE id = NEW.comment_id; " +
                "ELSE " +
                "    UPDATE Comment SET dislikes = dislikes + 1 WHERE id = NEW.comment_id; " +
                "END IF; " +
                "END;";

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(User);
            statement.executeUpdate(Video);
            statement.executeUpdate(Tag);
            statement.executeUpdate(VideoTag);
            statement.executeUpdate(Channel);
            statement.executeUpdate(Subscription);
            statement.executeUpdate(Playlist);
            statement.executeUpdate(PlaylistVideo);
            statement.executeUpdate(Comment);
            statement.executeUpdate(VideoReaction);
            statement.executeUpdate(CommentReaction);

            statement.executeUpdate(VideoReactionTrigger);
            statement.executeUpdate(CommentReactionTrigger);

            System.out.println("Tablas y triggers creados con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
