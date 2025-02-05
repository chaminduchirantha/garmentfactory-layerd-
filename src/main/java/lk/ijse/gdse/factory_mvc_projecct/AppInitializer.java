package lk.ijse.gdse.factory_mvc_projecct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/View/LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 721);
            stage.setTitle("Garment Factory Management System");

            Image image = new Image(getClass().getResourceAsStream("/image/logoDesign.png"));
            stage.getIcons().add(image);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "App Is not found").show();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
