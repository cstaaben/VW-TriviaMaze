package maze;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VideoPlayer extends Application {
	
	private boolean videoHasPlayed = false;
	
	public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	playVideo(primaryStage);
    	
    }
    
    public boolean videoHasPlayed() { return videoHasPlayed; }
    
    private void playVideo(Stage primaryStage) {
    	List<String> cmdLineArgs = this.getParameters().getUnnamed();
    	
    	if(cmdLineArgs.isEmpty()) {
    		System.out.println("USAGE: add one argument of video path");
    		System.exit(0);
    	}
    	
    	String videoPath = cmdLineArgs.get(0);
    	
        StackPane root = new StackPane();

        Media videoMedia = new Media(new File(videoPath).toURI().toString());
        MediaPlayer player = new MediaPlayer(videoMedia);
        MediaView mediaView = new MediaView(player);

        mediaView.setSmooth(true);
        mediaView.setPreserveRatio(true);
        Rectangle2D screenBoundary = Screen.getPrimary().getVisualBounds();
        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, mediaView.getFitWidth(), mediaView.getFitHeight());
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        
        
        primaryStage.setScene(scene);
        primaryStage.setX(screenBoundary.getMinX());
        primaryStage.setY(screenBoundary.getMinY());
        primaryStage.setWidth(screenBoundary.getWidth());
        primaryStage.setHeight(screenBoundary.getHeight());
        primaryStage.show();
        primaryStage.toFront();
        
        setToCloseOnFinish(player);
        
        player.play();
        videoHasPlayed = true;
    }
    
    public void playVideo(Stage primaryStage, String videoPath) {
    	
        StackPane root = new StackPane();

        Media videoMedia = new Media(new File(videoPath).toURI().toString());
        MediaPlayer player = new MediaPlayer(videoMedia);
        MediaView mediaView = new MediaView(player);

        mediaView.setSmooth(true);
        mediaView.setPreserveRatio(true);
        Rectangle2D screenBoundary = Screen.getPrimary().getVisualBounds();
        root.getChildren().add(mediaView);

        Scene scene = new Scene(root, mediaView.getFitWidth(), mediaView.getFitHeight());
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        
        
        primaryStage.setScene(scene);
        primaryStage.setX(screenBoundary.getMinX());
        primaryStage.setY(screenBoundary.getMinY());
        primaryStage.setWidth(screenBoundary.getWidth());
        primaryStage.setHeight(screenBoundary.getHeight());
        primaryStage.show();
        primaryStage.toFront();
        
        setToCloseOnFinish(player);
        
        player.play();
        videoHasPlayed = true;
    }
    
    private void setToCloseOnFinish(MediaPlayer player) {
    	player.setOnEndOfMedia(new Runnable() {
        	@Override
        	public void run() {
        		while(!videoHasPlayed) {
        			
        		}
        		System.exit(0);
        	}
        });
    }
}
