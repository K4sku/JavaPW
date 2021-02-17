package getMail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    public static final String appName = "Gmail Read Client";

    @Override
    public void start(Stage primaryStage) {

        ViewLoader<AnchorPane, AppController> viewLoader = new ViewLoader<>("EmailReader.fxml");
        viewLoader.getController().run();
        Scene scene = new Scene(viewLoader.getLayout());
        primaryStage.setScene(scene);
        primaryStage.setTitle(appName);
        primaryStage.setOnHiding(e -> primaryStage_Hiding(e, viewLoader.getController()));
        primaryStage.show();

    }

    private void primaryStage_Hiding(WindowEvent e, AppController controller) {
        try {
            controller.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
