package db;


import models.UserDiagram;
import models.UserDiagramPoint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class UserDiagramServiceTest {
    private static final UserDiagramDao service = new UserDiagramDao("192.168.99.100:32768", "server_lab_3");

    private UserDiagram createOne(String username) {
        UserDiagram ud = new UserDiagram();
        ud.setUsername(username);
        ud.setName("pie");
        ud.setType("pie");
        List<UserDiagramPoint> points = new ArrayList<>();
        points.add(new UserDiagramPoint(1, 3));
        points.add(new UserDiagramPoint(2, 5));
        points.add(new UserDiagramPoint(3, 9));
        ud.setDataPoints(points);
        return ud;
    }

    @Test
    public void insert() {
        String username = "Tiago";
        service.insert(createOne(username));
    }

    @Test
    public void delete() {
        String username = "Tiago";
        service.insert(createOne(username));
        UserDiagram diagram = service.getByUser(username).get(0);
        System.out.println("DELETING: " + diagram);
        service.delete(diagram.getId());
        assertNull(service.get(diagram.getId()));
    }

    @Test
    public void getByUser() {
        List<UserDiagram> diagrams = service.getByUser("Tiago");
        assertNotEquals(0, diagrams.size());
        System.out.println(diagrams.get(0).toString());
    }

    @Test
    public void deleteByUser() {
        String username = "Tiago";
        service.insert(createOne(username));
        //
        int before = service.getByUser(username).size();
        System.out.println("before: " + before);
        assertTrue(before != 0);
        service.deleteByUser(username);
        int after = service.getByUser(username).size();
        assertNotEquals(before, after);
        System.out.println("after: " + after);
    }



}
