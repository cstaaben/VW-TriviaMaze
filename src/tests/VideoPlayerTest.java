package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import maze.VideoPlayer;



public class VideoPlayerTest extends VideoPlayer {
	
	public static final String TEST_VIDEO_RELATIVE_PATH = "src/maze/mp4/0001.mp4";
	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(Platform.isFxApplicationThread());
		this.playVideo(new Stage(), TEST_VIDEO_RELATIVE_PATH);
		assertTrue(this.videoHasPlayed());
	}

}
