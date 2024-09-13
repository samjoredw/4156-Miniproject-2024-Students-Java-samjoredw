package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a course within a department.
 *
 * <p>This class provides methods regarding specific courses.
 */
public class Course implements Serializable {

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * @param instructorName     The name of the instructor teaching the course.
   * @param courseLocation     The location where the course is held.
   * @param timeSlot           The time slot of the course.
   * @param capacity           The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    this.courseLocation = courseLocation;
    this.instructorName = instructorName;
    this.courseTimeSlot = timeSlot;
    this.enrollmentCapacity = capacity;
    this.enrolledStudentCount = 500;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public boolean enrollStudent() {
    if (enrollmentCapacity > enrolledStudentCount) {
      enrolledStudentCount++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public boolean dropStudent() {
    if (enrolledStudentCount > 0) {
      enrolledStudentCount--;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the course location associated with the course.
   *
   * @return courseLocation
   */
  public String getCourseLocation() {
    return this.courseLocation;
  }

  /**
   * Gets the instructor's name associated with the course.
   *
   * @return instructorName
   */
  public String getInstructorName() {
    return this.instructorName;
  }

  /**
   * Gets the time slot associated with the course.
   *
   * @return coursrTimeSlot
   */
  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }

  /**
   * Returns a string representation of the course.
   *
   * @return A string representing the course.
   */
  @Override
  public String toString() {
    return "\nInstructor: " + instructorName +  "; Location: "  + courseLocation
        +  "; Time: " + courseTimeSlot;
  }

  /**
   * Reassigns the instructor of a course.
   */
  public void reassignInstructor(String newInstructorName) {
    this.instructorName = newInstructorName;
  }

  /**
   * Reassigns the location of a course.
   */
  public void reassignLocation(String newLocation) {
    this.courseLocation = newLocation;
  }

  /**
   * Reassigns the time slot of a course.
   */
  public void reassignTime(String newTime) {
    this.courseTimeSlot = newTime;
  }

  /**
   * Gets the number of students currently enrolled.
   *
   * @return enrolledStudentCount
   */
  public int getEnrolledStudentCount() {
    return enrolledStudentCount;
  }

  /**
   * Sets the number of students currently enrolled.
   */
  public void setEnrolledStudentCount(int count) {
    this.enrolledStudentCount = count;
  }


  /**
   * Determines if the course is available.
   *
   * @return true if the course is available
   */
  public boolean isCourseAvailable() { // Renamed: As the original did not make sense logically
    return enrollmentCapacity > enrolledStudentCount;
  }

  @Serial
  private static final long serialVersionUID = 123456L;
  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String instructorName;
  private String courseTimeSlot;
}