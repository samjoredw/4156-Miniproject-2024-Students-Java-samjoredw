package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the MyFileDatabase class.
 *
 * <p>Tests include verifying the toString method of the Course and
 * Department classes.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTest {

  /**
   * Defines and initializes variables for a new MyFileDatabase.
   */
  @BeforeEach
  public void setupMyFileDatabase() {
    serializedFileDatabase = new MyFileDatabase(0, "./data.txt");
    unserializedFileDatabase = new MyFileDatabase(1, "./data.txt");
  }

  /**
   * Test for setMapping().
   */
  @Test
  public void setMappingTest() {
    Map<String, Department> departmentHashMap = new HashMap<>();
    Map<String, Course> emptyOne = new HashMap<>();
    Map<String, Course> emptyTwo = new HashMap<>();
    departmentHashMap.put("COMS", new Department(
        "COMS", emptyOne, "Luca Carloni", 2700));
    departmentHashMap.put("ECON", new Department(
        "ECON", emptyTwo, "Michael Woodford", 2345));
    unserializedFileDatabase.setMapping(departmentHashMap);
    assertEquals(departmentHashMap, unserializedFileDatabase.getDepartmentMapping());
  }

  /**
   * The test MyFileDatabase instance and test data used for unit testing.
   */
  public static MyFileDatabase serializedFileDatabase;
  public static MyFileDatabase unserializedFileDatabase;
}
