package pl.gran.gran.trail;

import jakarta.persistence.*;

@Table(name = "trail")
@Entity
public class Trail  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String startPoint;
    private Double distance;
    private double technicalDifficulty;
    private double exposure;
    private double condition;

    private String mapUrl;

    protected Trail() {
    }

    public Trail(String name, String startPoint, Double distance, double technicalDifficulty, double exposure, double condition) {
        this(name, startPoint, distance, technicalDifficulty, exposure, condition, null);
    }

    public Trail(String name, String startPoint, Double distance, double technicalDifficulty, double exposure, double condition, String mapUrl) {
        this.name = name;
        this.startPoint = startPoint;
        this.distance = distance;
        this.technicalDifficulty = technicalDifficulty;
        this.exposure = exposure;
        this.condition = condition;
        this.mapUrl = mapUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public Double getDistance() {
        return distance;
    }

    public double getTechnicalDifficulty() {
        return technicalDifficulty;
    }

    public double getExposure() {
        return exposure;
    }

    public double getCondition() {
        return condition;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void update(String name, String startPoint, Double distance, double technicalDifficulty, double exposure, double condition, String mapUrl) {
        this.name = name;
        this.startPoint = startPoint;
        this.distance = distance;
        this.technicalDifficulty = technicalDifficulty;
        this.exposure = exposure;
        this.condition = condition;
        this.mapUrl = mapUrl;
    }
}
