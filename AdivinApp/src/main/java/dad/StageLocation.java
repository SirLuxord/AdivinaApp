package dad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.Random;

public class StageLocation extends Application {

    private Label adivinaLabel;
    private TextField numeroText;
    private Button comprobarButton;
    private Random random = new Random();
    private int numero = random.nextInt(100) + 1;
    private int intentos = 0;

    @Override
    public void start (Stage stage) throws Exception {

        adivinaLabel = new Label();
        adivinaLabel.setText("Introduce un número del 1 al 100");


        numeroText = new TextField();
        numeroText.setPromptText("Di tú número");


        comprobarButton = new Button();
        comprobarButton.setText("Comprobar");
        comprobarButton.setDefaultButton(true);
        comprobarButton.setOnAction(this::onComprobarAction);


        VBox root = new VBox(5);
        root.setFillWidth(false);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(adivinaLabel, numeroText, comprobarButton);

        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("AdivinAPP");
        stage.setScene(scene);
        stage.show();

    }

    private  void onComprobarAction (ActionEvent e) {
        if (!numeroText.getText().matches("^\\d+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El número introducido no es valido");

            alert.showAndWait();
        } else {

            int numeroU = Integer.parseInt(numeroText.getText());

            if ((numeroU > 100) || (numeroU < 1)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("El número introducido no es valido");

                alert.showAndWait();
            } else if (numeroU == numero) {
                intentos++;
                numero = random.nextInt(100) + 1;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AdivinApp");
                alert.setHeaderText("¡Has ganado!");
                alert.setContentText("Soló has necesitado " + intentos + ".\nVuelve a jugar y hazlo mejor");
                alert.showAndWait();
                intentos = 0;
            } else if ((numeroU < numero)) {
                intentos++;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AdivinApp");
                alert.setHeaderText("¡Has fallado!\nEl número a adivinar es mayor que " + numeroU);
                alert.setContentText("Vuelve a intentarlo");

                alert.showAndWait();
            } else {
                intentos++;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AdivinApp");
                alert.setHeaderText("¡Has fallado!El número a adivinar es menor que " + numeroU);
                alert.setContentText("Vuelve a intentarlo");

                alert.showAndWait();
            }
        }
    }
}
