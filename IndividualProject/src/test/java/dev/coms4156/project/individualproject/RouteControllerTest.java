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
   * Test for retrieveCourseTest().
   */
  @Test
  public void retrieveCourseTest() {
    ResponseEntity<?> realOutput = routeController.retrieveCourse("COMS", 3251);
    String expectedOutput = coms3251.toString();

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for retrieveFakeCourseTest().
   */
  @Test
  public void retrieveFakeCourseTest() {
    ResponseEntity<?> realOutput = routeController.retrieveCourse("COMS", 9837431);

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals("Course Not Found", realOutput.getBody());
  }

  /**
   * Test for retrieveCoursesWithCourseCodeTest().
   */
  @Test
  public void retrieveCoursesWithCourseCodeTest() {
    ResponseEntity<?> realOutput =  routeController.retrieveCoursesWithCourseCode("3251");
    String testOutput = """
        Department: COMS
        Course Info: COMS=
        Instructor: Tony Dear; Location: 402 CHANDLER; Time: 1:10-3:40""";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(testOutput, realOutput.getBody());
  }

  /**
   * Test for retrieveCoursesWithoutCourseCodeTest().
   */
  @Test
  public void retrieveCoursesWithoutCourseCodeTest() {
    ResponseEntity<?> realOutput = routeController.retrieveCoursesWithCourseCode("RANDOM-FAKE");

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals("No courses with code RANDOM-FAKE", realOutput.getBody());
  }

  /**
   * Test for isCourseFullTrueTest().
   */
  @Test
  public void isCourseFullTrueTest() {
    coms3251.setEnrolledStudentCount(99);
    ResponseEntity<?> realOutput = routeController.isCourseFull("COMS", 3251);
    boolean expectedOutput = true;

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for isCourseFullFalseTest().
   */
  @Test
  public void isCourseFullFalseTest() {
    coms3251.setEnrolledStudentCount(125);
    ResponseEntity<?> realOutput = routeController.isCourseFull("COMS", 3251);
    boolean expectedOutput = false;

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for findCourseInstructorTest().
   */
  @Test
  public void findCourseInstructorTest() {
    ResponseEntity<?> realOutput = routeController.findCourseInstructor("COMS", 3251);
    String expectedOutput = "Tony Dear is the instructor for the course.";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for findCourseInstructorFakeDepartmentTest().
   */
  @Test
  public void findCourseInstructorFakeDepartmentTest() {
    ResponseEntity<?> realOutput = routeController.findCourseInstructor("FAKE", 3251);
    String expectedOutput = "Course Not Found";

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for findCourseInstructorFakeCourseTest().
   */
  @Test
  public void findCourseInstructorFakeCourseTest() {
    ResponseEntity<?> realOutput = routeController.findCourseInstructor("COMS", 78547802);

    String expectedOutput = "Course Not Found";

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for findCourseLocation().
   */
  @Test
  public void findCourseLocationTest() {
    ResponseEntity<?> realOutput = routeController.findCourseLocation("COMS", 3251);
    String expectedOutput = "402 CHANDLER is where the course is located.";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for findCourseLocationFakeDepartmentTest().
   */
  @Test
  public void findCourseLocationFakeDepartmentTest() {
    ResponseEntity<?> realOutput = routeController.findCourseLocation("RADNOM-FAKE", 3251);
    String expectedOutput = "Course Not Found";

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());

  }

  /**
   * Test for findCourseLocationFakeCourseTest().
   */
  @Test
  public void findCourseLocationFakeCourseTest() {
    ResponseEntity<?> realOutput = routeController.findCourseLocation("COMS", 98349);
    String expectedOutput = "Course Not Found";

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }


  /**
   * Test for getMajorCtFromDept().
   */
  @Test
  public void getMajorCtFromDeptTest() {
    ResponseEntity<?> realOutput = routeController.getMajorCtFromDept("COMS");
    String expectedOutput = "There are: 2700 majors in the department";

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for getMajorCtFromDeptFakeTest().
   */
  @Test
  public void getMajorCtFromDeptFakeTest() {
    ResponseEntity<?> realOutput = routeController.getMajorCtFromDept("RANDOM-FAKE");
    String expectedOutput = "Department Not Found";

    assertEquals(HttpStatus.FORBIDDEN, realOutput.getStatusCode());
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
   * Test for dropStudentFromCourseTest().
   */
  @Test
  public void dropStudentFromCourseTest() {
    System.out.println(coms3251.getEnrolledStudentCount());
    ResponseEntity<?> realOutput = routeController.dropStudent("COMS", 3251);
    String expectedOutput = "Student has been dropped.";
    System.out.println(coms3251.getEnrolledStudentCount());

    assertEquals(HttpStatus.OK, realOutput.getStatusCode());
    assertEquals(expectedOutput, realOutput.getBody());
  }

  /**
   * Test for dropStudentFromFakeCourseTest().
   */
  @Test
  public void dropStudentFromFakeCourseTest() {
    ResponseEntity<?> realOutput = routeController.dropStudent("COMS", 1023495);

    assertEquals(HttpStatus.NOT_FOUND, realOutput.getStatusCode());
    assertEquals("Course Not Found", realOutput.getBody());
  }

  /**
   * Test for dropStudentFromEmptyCourseTest().
   */
  @Test
  public void dropStudentFromEmptyCourseTest() {
    coms3251.setEnrolledStudentCount(0);
    ResponseEntity<?> realOutput = routeController.dropStudent("COMS", 3251);
    String expectedOutput = "Student has not been dropped.";

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
