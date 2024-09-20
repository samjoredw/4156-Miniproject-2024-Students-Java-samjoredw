package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the RouteController class.
 *
 * <p>Tests include verifying the toString method of the Course and
 * Department classes.
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerTest {
  @Test
  public void retrieveCoursesWithCourseCode() {

    String instructor = "Gail Kaiser";
    String location = "501 NWC";
    String time = "10:10-11:25";
    int cap = 120;

    Course testCourse = new Course(instructor, location, time, cap);

    Map<String, Course> courses = new HashMap<>();
    courses.put("4157", testCourse);

    Department compSci2 = new Department("COMS2", courses, "Luca Carloni", 2700);

    RouteController routeController = new RouteController();
    ResponseEntity<?> realOutput = routeController.retrieveCoursesWithCourseCode("4156");

    String testOutput = "Department: COMS\n" +
        "Course Info: COMS=\n" +
        "Instructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(testOutput, realOutput.getBody());
  }
}
