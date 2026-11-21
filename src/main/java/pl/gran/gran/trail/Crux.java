package pl.gran.gran.trail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crux")
public class Crux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trail_id")
    private Trail trail;

    private String name;

    private int position;

    private String description;

    private String youtubeId;

    protected Crux() {
    }

    public Crux(Trail trail, String name, int position, String description, String youtubeId) {
        this.trail = trail;
        this.name = name;
        this.position = position;
        this.description = description;
        this.youtubeId = blankToNull(youtubeId);
    }

    public Long getId() {
        return id;
    }

    public Trail getTrail() {
        return trail;
    }

    public String getName() {
        return name;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public int getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public void update(String name, int position, String description, String youtubeId) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.youtubeId = blankToNull(youtubeId);
    }

    private static String blankToNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }

}
