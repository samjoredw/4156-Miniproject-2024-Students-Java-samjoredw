package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the IndividualProjectApplication class.
 *
 * <p>Tests include verifying various methods within the
 * IndividualProjectApplication classes.
 */
@SpringBootTest
@ContextConfiguration
public class IndividualProjectApplicationTest {

  /**
   * Defines and initializes courses to a new test department.
   */
  @BeforeEach
  public void createNewApplication() {
    testApplication = new IndividualProjectApplication();
  }

  /**
   * Test for main().
   */
  @Test
  public void mainMethodTest() {
    IndividualProjectApplication.main(new String[]{});
  }

  /**
   * Test for run() - setup option.
   */
  @Test
  public void runWithSetupTest() {
    String[] setup = {"setup"};
    testApplication.run(setup);
    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  /**
   * Test for run() - without setup option.
   */
  @Test
  public void runWithoutSetupTest() {
    String[] setup = {};
    testApplication.run(setup);
    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  /**
   * Test for overrideDatabase().
   */
  @Test
  public void overrideDatabaseTest() {
    MyFileDatabase empty = new MyFileDatabase(1, "nothingOnPurpose.txt");
    IndividualProjectApplication.overrideDatabase(empty);
    Map<String, Department> myFileDatabaseMap =
        IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    Map<String, Department> emptyMap =
        IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    assertTrue((myFileDatabaseMap == null && emptyMap == null)
        || (Objects.requireNonNull(myFileDatabaseMap).isEmpty() && emptyMap.isEmpty()));
  }

  /**
   * Test for resetDataFile().
   */
  @Test
  public void resetDataFileTest() {
    IndividualProjectApplication.myFileDatabase = getMyFileDatabaseHelper();
    MyFileDatabase beforeReset = IndividualProjectApplication.myFileDatabase;
    testApplication2 = new IndividualProjectApplication();
    String[] setup = {};
    testApplication2.run(setup);
    MyFileDatabase afterReset = IndividualProjectApplication.myFileDatabase;
    assertNotEquals(beforeReset.getDepartmentMapping(), afterReset.getDepartmentMapping());
  }

  /**
   * Helper for resetDataFileTest().
   */
  private static MyFileDatabase getMyFileDatabaseHelper() {
    final MyFileDatabase myFileDatabase = new MyFileDatabase(1, "nothingOnPurpose.txt");
    Course sam1234 = new Course(
        "Sam Edwards", "600 SAM", "1:00-11:45", 1);
    sam1234.setEnrolledStudentCount(420);
    Map<String, Course> courses = new HashMap<>();
    courses.put("1234", sam1234);
    Department compSci = new Department(
        "SAM", courses, "Jon Snow", 1);
    Map<String, Department> mapping = new HashMap<>();
    mapping.put("SAM", compSci);
    myFileDatabase.setMapping(mapping);
    return myFileDatabase;
  }

  /**
   * The test IndividualProjectApplication instance and test data used for unit testing.
   */
  public IndividualProjectApplication testApplication;
  public IndividualProjectApplication testApplication2;

}
