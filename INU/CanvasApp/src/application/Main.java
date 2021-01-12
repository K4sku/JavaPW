package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,512,256);
			
			Canvas canvas = new Canvas(512,256);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);
			
			Image image = new Image("http://galera.ii.pw.edu.pl/~zsz/javafx/img/lenna256px.png");
			gc.drawImage(image, 0, 0);
			
			canvas.addEventHandler(
					MouseEvent.MOUSE_PRESSED,
					event->{
						System.out.println(
								"x="+event.getX()+
								",y="+event.getY()
								);
					});
			
//			canvas.setOnMousePressed(event->{
//				System.out.println(
//						"x="+event.getX()+
//						",y="+event.getY()
//						);
//			});
			
			int height = (int) image.getHeight();
			int width = (int) image.getWidth();
			
			WritableImage dstImage = new WritableImage(width, height);
			
			PixelReader reader = image.getPixelReader();
			PixelWriter writer = dstImage.getPixelWriter();
			
			for (int x=0; x < width; x++ ) {
				for (int y=0; y<height; y++) {
					Color color = reader.getColor(x, y);
					writer.setColor(x, y, 
									Color.color(
											color.getRed()/2,
											color.getBlue()/2,
											color.getBlue()/2)
							);
					}
			}
			
			gc.drawImage(dstImage, 256, 0);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
