import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    private Pane root_pane;
    private HBox controllers_hbox;
    private Label title_lbl, unsignedno_lbl, results_lbl, mainnumber_lbl, mainnumberbase_lbl, convertedno_lbl, convertednobase_lbl, equal_lbl;
    private ComboBox<String> numbersystem_cmb, convertTo_cmb;
    private Button convert_btn, reset_btn;
    private TextField number_txt;
    private Map<String, Integer> base_map;
    private Alert alert;
    @Override
    public void start(Stage primaryStage) throws Exception{
        assign_ui();
        draw_screen();
        link_ui();
        primaryStage.setTitle("NUMBERING SYSTEMS CONVERSION");
        primaryStage.setScene(new Scene(root_pane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void draw_screen(){
        root_pane.setPrefWidth(600);
        root_pane.setPrefHeight(285);

        title_lbl.setLayoutX(128);
        title_lbl.setLayoutY(14);
        title_lbl.setText("NUMBERING SYSTEMS CONVERSION");
        title_lbl.setFont(Font.font("System", FontWeight.BOLD, 17));

        unsignedno_lbl.setLayoutX(14);
        unsignedno_lbl.setLayoutY(64);
        unsignedno_lbl.setText("Unsigned Number");


        number_txt.setLayoutX(139);
        number_txt.setLayoutY(59);
        number_txt.setPrefWidth(222);
        number_txt.setPrefHeight(26);

        numbersystem_cmb.setLayoutX(397);
        numbersystem_cmb.setLayoutY(59);
        numbersystem_cmb.setPrefHeight(26);
        numbersystem_cmb.setPrefWidth(180);
        numbersystem_cmb.setPromptText("Number System");
        for (int i = 2; i <= 16; i++){
            String s = "Base " + i;
            numbersystem_cmb.getItems().add(s);
        }


        controllers_hbox.setLayoutX(79);
        controllers_hbox.setLayoutY(105);
        controllers_hbox.setPrefHeight(26);
        controllers_hbox.setPrefWidth(419);
        controllers_hbox.setSpacing(15);
        controllers_hbox.setPadding(new Insets(10,10,10,10));

        convertTo_cmb.setPrefHeight(26);
        convertTo_cmb.setPrefWidth(180);
        convertTo_cmb.setPromptText("Convert To");
        for (int i = 2; i <= 16; i++){
            String s = "Base " + i;
            convertTo_cmb.getItems().add(s);
            base_map.put(s, i);
        }

        convert_btn.setPrefWidth(117);
        convert_btn.setPrefHeight(26);
        convert_btn.setText("Convert");

        reset_btn.setPrefWidth(117);
        reset_btn.setPrefHeight(26);
        reset_btn.setText("Reset");


        results_lbl.setLayoutX(232);
        results_lbl.setLayoutY(174);
        results_lbl.setText("Results");
        results_lbl.setTextFill(Color.valueOf("#6d1bb9"));
        results_lbl.setFont(Font.font("System", FontWeight.BOLD, 27));


        mainnumber_lbl.setLayoutX(128);
        mainnumber_lbl.setLayoutY(235);
        mainnumber_lbl.setText("");
        mainnumber_lbl.setFont(Font.font("System", 26));

        mainnumberbase_lbl.setLayoutX(245);
        mainnumberbase_lbl.setLayoutY(251);
        mainnumberbase_lbl.setText("");
        mainnumberbase_lbl.setFont(Font.font("System", FontWeight.BOLD, 13));


        equal_lbl.setLayoutX(282);
        equal_lbl.setLayoutY(240);
        equal_lbl.setText("");
        equal_lbl.setFont(Font.font("System", FontWeight.BOLD, 15));

        convertedno_lbl.setLayoutX(346);
        convertedno_lbl.setLayoutY(235);
        convertedno_lbl.setText("");
        convertedno_lbl.setFont(Font.font("System", 26));

        convertednobase_lbl.setLayoutX(482);
        convertednobase_lbl.setLayoutY(251);
        convertednobase_lbl.setText("");
        convertednobase_lbl.setFont(Font.font("System", FontWeight.BOLD, 13));



        controllers_hbox.getChildren().addAll(convertTo_cmb, convert_btn, reset_btn);
        root_pane.getChildren().addAll(controllers_hbox, numbersystem_cmb, title_lbl, unsignedno_lbl, results_lbl, mainnumber_lbl, mainnumberbase_lbl, convertedno_lbl, convertednobase_lbl, equal_lbl, number_txt);


    }
    private void assign_ui(){
        root_pane = new Pane();
        controllers_hbox = new HBox();
        convert_btn = new Button();
        reset_btn = new Button();
        numbersystem_cmb = new ComboBox<>();
        convertTo_cmb = new ComboBox<>();
        title_lbl = new Label();
        unsignedno_lbl= new Label();
        results_lbl= new Label();
        mainnumber_lbl= new Label();
        mainnumberbase_lbl= new Label();
        convertedno_lbl= new Label();
        convertednobase_lbl= new Label();
        equal_lbl= new Label();
        number_txt = new TextField();

        base_map = new HashMap<>();
        alert = new Alert(Alert.AlertType.WARNING);
    }
    private void link_ui(){
        reset_btn.setOnAction(e -> {
            number_txt.setText("");
            mainnumber_lbl.setText("");
            mainnumberbase_lbl.setText("");
            convertedno_lbl.setText("");
            convertednobase_lbl.setText("");
            equal_lbl.setText("");
            numbersystem_cmb.getSelectionModel().clearSelection();
            convertTo_cmb.getSelectionModel().clearSelection();
        });

        convert_btn.setOnAction(e -> {
            String result = null;


            if (numbersystem_cmb.getSelectionModel().isEmpty() && convertTo_cmb.getSelectionModel().isEmpty()){
                alert.setTitle("Base Selection Error");
                alert.setContentText("Please Choose the number and converting base");
                alert.showAndWait();
            } else if (convertTo_cmb.getSelectionModel().isEmpty()){
                alert.setTitle("Base Selection Error");
                alert.setContentText("Please Choose the converting base");
                alert.showAndWait();
            } else if (numbersystem_cmb.getSelectionModel().isEmpty()){
                alert.setTitle("Base Selection Error");
                alert.setContentText("Base Selection Error");
                alert.showAndWait();
            } else {
            String input = number_txt.getText();
            int from_base, to_base;
            if (numbersystem_cmb.getValue() != null && convertTo_cmb.getValue() !=null){
                from_base = base_map.get(numbersystem_cmb.getValue());
                to_base = base_map.get(convertTo_cmb.getValue());
                if(check_error(from_base, input)){
                    result = main_converter(to_base, from_base, input);
                    mainnumber_lbl.setText("(" + input + ")");
                    mainnumberbase_lbl.setText(Integer.toString(from_base));
                    convertedno_lbl.setText("(" + result + ")");
                    convertednobase_lbl.setText(Integer.toString(to_base));
                    equal_lbl.setText("=");

                } else {
                    alert.setTitle("Invalid Input");
                    alert.setContentText("Input and Selected input don't match");
                    alert.showAndWait();
                }

            }
            }
        });

    }

    private boolean check_error(int base, String input){
        if(input.isEmpty()){
            return false;
        }
        for (int i = 0 ; i < input.length();i++){
            int num_val = Character.getNumericValue(input.charAt(i));
            if (num_val >= base){
                return false;
            }
        }
        return true;
    }
    private String main_converter(int toBase, int fromBase, String input){
        return Integer.toString( Integer.parseInt(input, fromBase),toBase);
    }

}
