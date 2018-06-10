package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by evan_ on 24/06/2017.
 */
@Entity
public class Testimony extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private String id;
    private String title;
    private String description;


    public static Finder<String, Testimony> find = new Finder<String, Testimony>(Testimony.class);

    public static List<Testimony> findAll() {
        return Testimony.find.all();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}