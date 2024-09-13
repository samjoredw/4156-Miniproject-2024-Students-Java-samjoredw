package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Course class.
 *
 * <p>Tests include verifying the toString method of the Course and
 * Department classes.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  /**
   * Defines and initializes variables for a new test course.
   */
  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course(instructorName, courseLocation, timeSlot, capacity);
  }

  /**
   * Test for enrollStudent()
   */
  @Test
  public void enrollStudentTest() {
    int pre = testCourse.getEnrolledStudentCount();
    boolean result = testCourse.enrollStudent();
    int post = testCourse.getEnrolledStudentCount();
    if (result) {
      assertEquals(++pre, post);
    } else {
      assertNotEquals(++pre, post);
    }
  }

  /**
   * Test for dropStudent()
   */
  @Test
  public void dropStudentTest() {
    int pre = testCourse.getEnrolledStudentCount();
    boolean result = testCourse.dropStudent();
    int post = testCourse.getEnrolledStudentCount();
    assertEquals(--pre, post);
    assertTrue(result);
  }

  /**
   * Test for getCourseLocation()
   */
  @Test
  public void getCourseLocationTest() {
    assertEquals(courseLocation, testCourse.getCourseLocation());
  }

  /**
   * Test for setEnrolledStudentCount()
   */
  @Test
  public void setEnrolledStudentCountTest() {
    int newCount = 100;
    testCourse.setEnrolledStudentCount(newCount);
    assertEquals(newCount, testCourse.getEnrolledStudentCount());
  }

  /**
   * Test for getInstructorName()
   */
  @Test
  public void getInstructorNameTest() {
    assertEquals(instructorName, testCourse.getInstructorName());
  }

  /**
   * Test for getCourseTimeSlot()
   */
  @Test
  public void getCourseTimeSlotTest() {
    assertEquals(timeSlot, testCourse.getCourseTimeSlot());
  }

  /**
   * Test for reassignInstructor()
   */
  @Test
  public void reassignInstructorTest() {
    String newInstructor = "Sam Edwards";
    testCourse.reassignInstructor(newInstructor);
    assertEquals(newInstructor, testCourse.getInstructorName());
  }

  /**
   * Test for reassignLocation()
   */
  @Test
  public void reassignLocationTest() {
    String newLocation = "309 HAV";
    testCourse.reassignLocation(newLocation);
    assertEquals(newLocation, testCourse.getCourseLocation());
  }

  /**
   * Test for reassignTime()
   */
  @Test
  public void reassignTimeTest() {
    String newTime = "8:40-9:55";
    testCourse.reassignTime(newTime);
    assertEquals(newTime, testCourse.getCourseTimeSlot());
  }

  /**
   * Test for isCourseFull()
   */
  @Test
  public void isCourseFullTest() {
    int over = 1000;
    testCourse.setEnrolledStudentCount(over);
    assertFalse(testCourse.isCourseAvailable());

    int under = 100;
    testCourse.setEnrolledStudentCount(under);
    assertTrue(testCourse.isCourseAvailable());

    int equal = 250;
    testCourse.setEnrolledStudentCount(equal);
    assertFalse(testCourse.isCourseAvailable());
  }

  /**
   * Test for toString()
   */
  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  /**
   * The test course instance and test data used for unit testing.
   */
  public static Course testCourse;
  static final String instructorName = "Griffin Newbold";
  static final String courseLocation = "417 IAB";
  static final String timeSlot = "11:40-12:55";
  static final int capacity = 250;
}

