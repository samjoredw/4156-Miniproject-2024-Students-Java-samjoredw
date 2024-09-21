package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * <p>Tests include verifying the various methods of the router controls anc
 * connection protocols located in RouteController.java
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerTest {

  /**
   * Defines and initializes variables for a new MyFileDatabase.
   */
  @BeforeEach
  public void setupRouteController() {
    routeController = new RouteController();

    coms3251 = new Course("Tony Dear", "402 CHANDLER", "1:10-3:40", 125);
    coms3251.setEnrolledStudentCount(99);
    courses = new HashMap<>();
    courses.put("3251", coms3251);

    testDepartment = new Department(deptCode, courses, departmentChair, numberOfMajors);

    IndividualProjectApplication.myFileDatabase.getDepartmentMapping()
        .put(deptCode, testDepartment);
  }

  /**
   * Test for retrieveCoursesWithCourseCodeTest().
   */
  @Test
  public void retrieveCoursesWithCourseCodeTest() {

    ResponseEntity<?> realOutput = routeController.retrieveCoursesWithCourseCode("3251");

    String testOutput = """
        Department: COMS
        Course Info: COMS=
        Instructor: Tony Dear; Location: 402 CHANDLER; Time: 1:10-3:40""";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(testOutput, realOutput.getBody());
  }

  /**
   * Test for retrieveCoursesWithoutCourseCodeTest() with invalid course code.
   */
  @Test
  public void retrieveCoursesWithoutCourseCodeTest() {
    // Call the method with an invalid course code
    ResponseEntity<?> realOutput = routeController.retrieveCoursesWithCourseCode("NONE");

    // Verify that the response is NOT_FOUND
    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals("No courses with code NONE", realOutput.getBody());
  }

  /**
   * Test for enrollStudentInCourseTest().
   */
  @Test
  public void enrollStudentInCourseTest() {

    ResponseEntity<?> realOutput = routeController.enrollStudentInCourse("COMS", "3251");

    String expectedOutput = "Student enrolled: 100 students now enrolled in the course.";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for enrollStudentInCourseFullTest().
   */
  @Test
  public void enrollStudentInCourseFullTest() {

    coms3251.setEnrolledStudentCount(300);
    ResponseEntity<?> realOutput = routeController.enrollStudentInCourse("COMS", "3251");

    String expectedOutput = "Cannot enroll student.";

    assertEquals(HttpStatus.BAD_REQUEST, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * The test route controller instance and test data used for unit testing.
   */
  public RouteController routeController;
  public Department testDepartment;
  public Map<String, Course> courses;
  public Course coms3251;
  public static final String deptCode = "COMS";
  public static final String departmentChair = "Luca Carloni";
  public static final int numberOfMajors = 2700;
}
