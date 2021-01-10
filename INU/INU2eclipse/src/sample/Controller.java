package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    public Button loadButton;
    public Button clearButton;
    @FXML
    private Canvas canv;

    @FXML
    private GridPane grid;

    @FXML
    public Canvas[] canvasArray;
    public Pane[] paneArray;

    @FXML
    public Image image;
    public PixelReader reader;
    public ArrayList<ImagePiece> imagePieces;

    @FXML
    public void initialize() {
        int rows = grid.getRowCount();
        int cols = grid.getColumnCount();
        
        
        imagePieces = new ArrayList<>(rows * cols);
        paneArray = new Pane [rows * cols];
        canvasArray = new Canvas[rows * cols];

//        BorderStrokeStyle borderStrokeStyle =
//                new BorderStrokeStyle(
//                        StrokeType.CENTERED,
//                        StrokeLineJoin.BEVEL,
//                        StrokeLineCap.BUTT,
//                        1,
//                        0,
//                        null
//                );
//
//
//        BorderStroke borderStroke =
//                new BorderStroke(
//                        Color.BLACK,
//                        borderStrokeStyle.SOLID,
//                        new CornerRadii(0),
//                        new BorderWidths(1)
//                );
//
//        Border border = new Border(borderStroke);
        
        for (int i = 0; i < rows * cols; i++){
        	paneArray[i] = new Pane();
        	paneArray[i].getStyleClass().add("canvasPane");
        	paneArray[i].setPrefSize(43, 43);
            canvasArray[i] = new Canvas(41, 41);
            canvasArray[i].getStyleClass().add("canvas");
            paneArray[i].getChildren().add(canvasArray[i]);
            grid.add(paneArray[i], (i % cols), (i / cols));
        }
    }

    @FXML
    void clearAction(ActionEvent event) {
        imagePieces.clear();
        for (Canvas c : canvasArray) {
            c.getGraphicsContext2D().clearRect(0,0,41,41);
        }
    }

    @FXML
    void loadAction(ActionEvent event) {
        clearAction(new ActionEvent());
        GraphicsContext gc = canv.getGraphicsContext2D();
        //pick file
        try {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(canv.getScene().getWindow());

            image = new Image(selectedFile.toURI().toString());
            if (image.getException() != null) {
                System.out.println(image.getException());
                image = null;
            }
            else {
                gc.drawImage(image, 0, 0);
            }
        }
        catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("File was not selected");
        }
    }

    @FXML
    void canvasClickAction(MouseEvent event) {
        if (null != image) {
            var clickX = event.getX();
            var clickY = event.getY();

            int startX;
            int startY;
            System.out.println("Clicked : X =" + clickX + ", Y =" + clickY);

            //set piece start coordinates
            if (clickX < 20) startX = 0;
            else if (clickX > canv.getWidth()-41) startX = (int) canv.getWidth()-41;
            else startX = (int) clickX - 20;

            if (clickY < 20) startY = 0;
            else if (clickY > canv.getHeight()-41) startY = (int) canv.getHeight()-41;
            else startY = (int) clickY - 20;

            //read average red
            reader = image.getPixelReader();
            double redness = 0;
            for (int x = startX; x < startX+41; x++) {
                for (int y = startY; y < startY+41; y++) {
                    redness += reader.getColor(x, y).getRed();
                }
            }
            redness = redness/(41*41);

            //add to collection or replace if it's full and new piece has more red
            ImagePiece piece = new ImagePiece(startX, startY, redness);
            Collections.sort(imagePieces);
            if (imagePieces.size() != 25)
                imagePieces.add(piece);
            else if (imagePieces.get(24).redness < piece.redness) {
                imagePieces.remove(24);
                imagePieces.add(piece);
                Collections.sort(imagePieces);
            }

            //paint images to grid
            for (int i = 0; i < imagePieces.size(); i++) {
            canvasArray[i].getGraphicsContext2D().drawImage(
                    image, imagePieces.get(i).startX, imagePieces.get(i).startY, 41, 41,
                    0, 0, 41, 41
                    );
            }

        } else {
            System.out.println("No image");
        }

    }

    private class ImagePiece implements Comparable<ImagePiece> {
        private int startX;
        private int startY;
        private double redness;

        public ImagePiece(int X, int Y, double red){
            startX = X;
            startY = Y;
            redness = red;
            System.out.println("obiekt X:" + startX + " Y:" +startY + " red:" + redness);
        }

        @Override
        public int compareTo(ImagePiece o) {
            return Double.compare(o.redness, redness);
        }

    }

}

