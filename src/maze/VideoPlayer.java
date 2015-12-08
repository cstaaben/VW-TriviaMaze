package maze;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoPlayer extends Application {
	public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	List<String> cmdLineArgs = this.getParameters().getUnnamed();
    	
    	if(cmdLineArgs.isEmpty()) {
    		System.out.println("USAGE: add one argument of video path");
    		System.exit(0);
    	}
    	
    	String videoPath = cmdLineArgs.get(0);
    	
    	System.out.println(videoPath);
    	//String videoPath = "src/maze/mp4/test.mp4";
    	
        StackPane root = new StackPane();

        Media videoMedia = new Media(new File(videoPath).toURI().toString());
        MediaPlayer player = new MediaPlayer(videoMedia);
        MediaView mediaView = new MediaView(player);

        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(false);
        
        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, mediaView.getFitWidth(), mediaView.getFitHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
        player.play();
    }
}
