package com.repaskys.microblog.domain;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for the Post domain class.
 * 
 * @author Drew Repasky
 */
public class PostTest {
	private static Validator validator;
	private Post post;
	private ConstraintViolation<Post> violation;
	private Set<ConstraintViolation<Post>> constraintViolations;

	@Before
	public void setUp() {
		post = new Post();
	}

	@After
	public void tearDown() {
		post = null;
	}

	@BeforeClass
	public static void oneTimeSetUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void createPost() {
		final String message = "a sample message";
		post.setMessage(message);
		assertEquals(message, post.getMessage());
		constraintViolations = validator.validate(post);
		assertTrue(constraintViolations.isEmpty());
	}
	
	@Test
	public void createPostMaxLenth() {
		final String message = StringUtils.repeat("A", 500);
		post.setMessage(message);
		assertEquals(message, post.getMessage());
		constraintViolations = validator.validate(post);
		assertTrue(constraintViolations.isEmpty());
	}
	
	@Test
	public void createPostOverMaxLenth() {
		final String message = StringUtils.repeat("A", 501);
		post.setMessage(message);
		assertEquals(message, post.getMessage());
		constraintViolations = validator.validate(post);
		assertEquals(1, constraintViolations.size());
		violation = constraintViolations.iterator().next();
		assertEquals("Post cannot be empty and must be less than 500 characters long",
				violation.getMessage());
		assertEquals("message", violation.getPropertyPath().toString());
	}
	
	@Test
	public void createEmptyPost() {
		final String message = "";
		post.setMessage(message);
		assertEquals(message, post.getMessage());
		constraintViolations = validator.validate(post);
		assertEquals(1, constraintViolations.size());
		violation = constraintViolations.iterator().next();
		assertEquals("Post cannot be empty and must be less than 500 characters long",
				violation.getMessage());
		assertEquals("message", violation.getPropertyPath().toString());
	}
	
}
