package us.repasky.microblog.domain;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
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
	
	private void calculateAgeEighteenMinuteAgo(DateTime dateTime) {
		DateTime timeAgo = dateTime.minus(Period.minutes(18));
		String age = Post.getAge(dateTime.toDate(), timeAgo.toDate());
		assertEquals("18m", age);
	}
	
	private void calculateAgeOneDayAgo(DateTime dateTime) {
		DateTime timeAgo = dateTime.minus(Period.days(1));
		String age = Post.getAge(dateTime.toDate(), timeAgo.toDate());
		assertEquals("1d 0m", age);
	}
	
	private void calculateAgeSixHoursThirteenMinutesAgo(DateTime dateTime) {
		DateTime timeAgo = dateTime.minus(Period.hours(6)).minus(Period.minutes(13));
		String age = Post.getAge(dateTime.toDate(), timeAgo.toDate());
		assertEquals("6h 13m", age);
	}
	
	private void calculateAgeFiveDaysFortyThreeMinutesAgo(DateTime dateTime) {
		DateTime timeAgo = dateTime.minus(Period.days(5)).minus(Period.minutes(43));
		String age = Post.getAge(dateTime.toDate(), timeAgo.toDate());
		assertEquals("5d 43m", age);
	}
	
	private void calculateAgeOneWeekAgo(DateTime dateTime) {
		DateTime timeAgo = dateTime.minus(Period.weeks(1));
		String age = Post.getAge(dateTime.toDate(), timeAgo.toDate());
		assertEquals("a long while ago", age);
	}
	
	private void testDifferentPostAges(DateTime dateTime) {
		calculateAgeEighteenMinuteAgo(dateTime);
		calculateAgeOneDayAgo(dateTime);
		calculateAgeSixHoursThirteenMinutesAgo(dateTime);
		calculateAgeFiveDaysFortyThreeMinutesAgo(dateTime);
		calculateAgeOneWeekAgo(dateTime);
	}
	
	@Test
	public void testJulyFirst2012() {
		testDifferentPostAges(new DateTime(2012, 7, 1, 12, 0, 0, 0));
	}
	
	@Test
	public void testOctoberFourth2010() {
		testDifferentPostAges(new DateTime(2010, 10, 4, 12, 0, 0, 0));
	}
}
