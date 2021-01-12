package shapes;
	
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class ShapesMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Group root = new Group();
			// RED LINE
			Line redLine = new Line(10,10,200,10);
			redLine.setStroke(Color.RED);
			redLine.setStrokeWidth(10);
			redLine.setStrokeLineCap(StrokeLineCap.ROUND);
			root.getChildren().add(redLine);
			
			// BLUE dashed LINE
			Line blueLine = new Line(10,40,200,40);
			blueLine.setStroke(Color.BLUE);
			blueLine.setStrokeWidth(10);
			blueLine.setStrokeLineCap(StrokeLineCap.BUTT);
			blueLine.getStrokeDashArray().addAll(30d,15d,5d,30d);
			blueLine.setStrokeDashOffset(45);
			root.getChildren().add(blueLine);
			
			//Suwak powiazany z StrokeDashOffset
			Slider suwak = new Slider(0, 80, 45);
			suwak.setLayoutX(10);
			suwak.setLayoutY(70);
			suwak.setOrientation(Orientation.HORIZONTAL);
			blueLine.strokeDashOffsetProperty().bind(suwak.valueProperty());
			root.getChildren().add(suwak);
			
			//cubic curve
			CubicCurve cubicCurve = new CubicCurve(
					50, 75, // pkt startowy
					80, -25, // pkt kontrolny 1
					110, 175, //pkt kontrolny 2
					140, 75  // pky koncowy
					);
			cubicCurve.setTranslateX(220);
			cubicCurve.setTranslateY(-40);
			cubicCurve.setStrokeWidth(3);
			cubicCurve.setStroke(Color.AQUAMARINE);
			cubicCurve.setFill(Color.TRANSPARENT);
			root.getChildren().add(cubicCurve);
			
			//Donut
			Ellipse bigElipse = new Ellipse(
					100,100, //center
					50,	//Rx
					36 //Ry
					);
			
			Ellipse smallElipse = new Ellipse(
					100,100, //center
					17, //Rx
					12 //Ry
					);
			
			Shape donut = Path.subtract(bigElipse, smallElipse);
			donut.setTranslateX(200);
			donut.setFill(Color.rgb(255, 200, 0));
			donut.setStroke(Color.BLACK);
			donut.setStrokeWidth(2);
			root.getChildren().add(donut);
			
			//efekt cienia
			DropShadow dropShadow = new DropShadow(
					5, //promien cienia
					12.0, //przesuniecie X
					12.0, // przesuniecie Y
					Color.grayRgb(200)
					);
			donut.setEffect(dropShadow);
			
			//wypelnienie gradientowe liniowe
			Rectangle roundRectangle = new Rectangle(
					250, 250, //położenie
					100, 70); //szerokosc
			roundRectangle.setArcHeight(20);
			roundRectangle.setArcWidth(20);
			root.getChildren().add(roundRectangle);
			LinearGradient lgrandient = new LinearGradient(
					0.25, 0.25, //pkt poczatkowy
					0.75, 0.75, //pkt koncowy
					true, //proportinal
					CycleMethod.REPEAT,
					new Stop(0, Color.RED),
					new Stop(1, Color.BEIGE)
					);
			roundRectangle.setFill(lgrandient);
			
			Scene scene = new Scene(root,400,400, Color.GRAY);
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
