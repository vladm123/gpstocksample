package dk.itu.gp2013.homework7.exercise1.room.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.itu.gp2013.homework7.exercise1.room.feature.Employee;

/**
 * Employee test.
 * 
 * @author Vlad Manea (vlam@itu.dk).
 */
public class EmployeeTest {
	private final String expectedName = "Harrison Ford";

	@Test
	public void testGetName() {
		Employee employee = new Employee(expectedName);
		final String actualName = employee.getName();
		assertEquals(expectedName, actualName);
	}
}
