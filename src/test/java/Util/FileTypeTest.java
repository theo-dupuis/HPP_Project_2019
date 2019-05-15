package Util;

import static org.junit.Assert.*;

import org.junit.Test;

import util.FileType;

public class FileTypeTest {

	@Test
	public void commentTest() {
		assertEquals("comment",FileType.Comment.toString());
	}
	@Test
	public void friendshipTest() {
		assertEquals("friendship",FileType.Friendship.toString());
	}
	@Test
	public void likeTest() {
		assertEquals("like",FileType.Like.toString());
	}
}
