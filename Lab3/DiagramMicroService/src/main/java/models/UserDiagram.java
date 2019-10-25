package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDiagram implements Serializable {

    // Database id
    private String id;
    // Creator
    private String username;
    // User label
    private String label;
    // Created
    private Date timestamp;
    // Diagram name
    private String name;
    // Diagram type
    private  String type;
    // Content
    private  List<UserDiagramPoint> dataPoints;

    public UserDiagram() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<UserDiagramPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<UserDiagramPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    @Override
    public String toString() {
        return "UserDiagram{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dataPoints=" + dataPoints +
                '}';
    }

}
