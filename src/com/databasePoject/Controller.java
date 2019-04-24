package com.databasePoject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button editStudentDataButton;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TextField inputExamScore;

    @FXML
    private Label labelName;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labelDeclaration1;

    @FXML
    private Label labelDeclaration2;

    @FXML
    private Label labelDeclaration3;

    @FXML
    private Label labelDeclaration4;

    @FXML
    private Label labelDeclaration5;

    @FXML
    private ChoiceBox choiceUniversity;

    @FXML
    private ChoiceBox choiceFieldOfStudy1;

    @FXML
    private ChoiceBox choiceFieldOfStudy2;

    @FXML
    private ChoiceBox choiceFieldOfStudy3;

    @FXML
    private ChoiceBox choiceFieldOfStudy4;

    @FXML
    private ChoiceBox choiceFieldOfStudy5;

    @FXML
    private ChoiceBox choiceFaculty1;

    @FXML
    private ChoiceBox choiceFaculty2;

    @FXML
    private ChoiceBox choiceFaculty3;

    @FXML
    private ChoiceBox choiceFaculty4;

    @FXML
    private ChoiceBox choiceFaculty5;

    @FXML
    private TableView<TableCells> tableUniversity;

    @FXML
    private TableView<TableCells> tableFaculty;

    @FXML
    private TableView<TableCells> tableFieldOfStudy;

    @FXML
    private TableColumn<TableCells, String> columnUniversity;

    @FXML
    private TableColumn<TableCells, String> columnFaculty;

    @FXML
    private TableColumn<TableCells, String> columnFieldOfStudy;

    @FXML
    private TableColumn<TableCells, Integer> columnMinPoints;

    @FXML
    private TableColumn tableMinPoints;

    private int idStudent;

    private Student student;

    private List<University> universityList;

    private University university;

    private FieldOfStudy fieldOfStudy;

    private List<FieldOfStudy> fieldOfStudyList;

    private List<FieldOfStudy> facultyList;

    private List<Declaration> fieldOfStudyTempList;

    private boolean editFalg;

    private Session session;

    private SessionFactory factory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editFalg=false;
        factory = new Configuration()
                .configure("com/databasePoject/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Declaration.class)
                .addAnnotatedClass(FieldOfStudy.class)
                .buildSessionFactory();

        choiceUniversity.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            labelDeclaration1.setText("");
            labelDeclaration2.setText("");
            labelDeclaration3.setText("");
            labelDeclaration4.setText("");
            labelDeclaration5.setText("");

            System.out.println(student.getDeclarationList());

            fieldOfStudyTempList = new ArrayList<Declaration>();

            for(int i=0; i < student.getDeclarationList().size();i++){
                if(newValue.equals(student.getDeclarationList().get(i).getIdUniversity().getUniversityName())){
                    fieldOfStudyTempList.add(student.getDeclarationList().get(i));
                }
            }

            System.out.println(fieldOfStudyTempList);

            labelDeclaration1.setText(fieldOfStudyTempList.get(0).getIdUniversity().getUniversityName() + ", " +
                    fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFaculty() + ", " +
                    fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFieldOfStudyName());

            if(fieldOfStudyTempList.size()>0) {
                labelDeclaration1.setText(fieldOfStudyTempList.get(0).getIdUniversity().getUniversityName() + ", " +
                        fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFaculty() + ", " +
                        fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFieldOfStudyName());
            }
            if(fieldOfStudyTempList.size()>1) {
                labelDeclaration2.setText(fieldOfStudyTempList.get(1).getIdUniversity().getUniversityName() + ", " +
                        fieldOfStudyTempList.get(1).getIdFieldOfStudy().getFaculty() + ", " +
                        fieldOfStudyTempList.get(1).getIdFieldOfStudy().getFieldOfStudyName());
            }
            if(fieldOfStudyTempList.size()>2) {
                labelDeclaration3.setText(fieldOfStudyTempList.get(2).getIdUniversity().getUniversityName() + ", " +
                        fieldOfStudyTempList.get(2).getIdFieldOfStudy().getFaculty() + ", " +
                        fieldOfStudyTempList.get(2).getIdFieldOfStudy().getFieldOfStudyName());
            }
            if(fieldOfStudyTempList.size()>3) {
                labelDeclaration4.setText(fieldOfStudyTempList.get(3).getIdUniversity().getUniversityName() + ", " +
                        fieldOfStudyTempList.get(3).getIdFieldOfStudy().getFaculty() + ", " +
                        fieldOfStudyTempList.get(3).getIdFieldOfStudy().getFieldOfStudyName());
            }
            if(fieldOfStudyTempList.size()>4) {
                labelDeclaration5.setText(fieldOfStudyTempList.get(4).getIdUniversity().getUniversityName() + ", " +
                        fieldOfStudyTempList.get(4).getIdFieldOfStudy().getFaculty() + ", " +
                        fieldOfStudyTempList.get(4).getIdFieldOfStudy().getFieldOfStudyName());
            }

            int Id = (int) session.createQuery("select uni.idUniversity from University uni where uni.universityName = '" + newValue +"'").getSingleResult();
            String query = "select field.faculty from FieldOfStudy field where field.idUniversity = '" + Id +"'";
            facultyList = session.createQuery(query).getResultList();
            choiceFaculty1.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty1.getSelectionModel().selectFirst();
            choiceFaculty2.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty2.getSelectionModel().selectFirst();
            choiceFaculty3.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty3.getSelectionModel().selectFirst();
            choiceFaculty4.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty4.getSelectionModel().selectFirst();
            choiceFaculty5.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty5.getSelectionModel().selectFirst();
        });

        choiceFaculty1.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy1.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy1.getSelectionModel().selectFirst();
        });

        choiceFaculty2.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy2.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy2.getSelectionModel().selectFirst();
        });

        choiceFaculty3.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy3.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy3.getSelectionModel().selectFirst();
        });

        choiceFaculty4.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy4.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy4.getSelectionModel().selectFirst();
        });

        choiceFaculty5.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy5.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy5.getSelectionModel().selectFirst();
        });

        tableUniversity.setOnMouseClicked((MouseEvent event) -> {
            clickColumnUniversity();
        });

        tableFaculty.setOnMouseClicked((MouseEvent event) -> {
            clickColumnFaculty();
        });

        session = factory.getCurrentSession();
        idStudent=1;
        session.beginTransaction();
        student = session.get(Student.class, idStudent);
        universityList = session.createQuery("select uni from University uni").getResultList();

        ObservableList<TableCells> dane = FXCollections.observableArrayList();
        for (int i=0; i<universityList.size();i++){
            dane.add(new TableCells(String.valueOf(universityList.get(i).getUniversityName())));
        }
        columnUniversity.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        tableUniversity.setEditable(true);
        tableUniversity.itemsProperty().setValue(dane);
    }

    @FXML
    public void clickDataStudent(){
        inputName.setVisible(false);
        inputSurname.setVisible(false);

        labelName.setText(student.getName());
        labelSurname.setText(student.getSurname());
    }

    @FXML
    public void editStudentData(){
        if(editFalg){
            editStudentDataButton.setText("Edit");
            inputName.setVisible(false);
            inputSurname.setVisible(false);
            labelName.setText(inputName.getText());
            labelSurname.setText(inputSurname.getText());

            student.setName(inputName.getText());
            student.setSurname(inputSurname.getText());

            editFalg=false;
        }else{
            editStudentDataButton.setText("Save");
            inputName.setVisible(true);
            inputSurname.setVisible(true);
            editFalg=true;
        }
    }

    @FXML
    public void setInputName(){

    }

    @FXML
    public void setInputSurname(){

    }

    @FXML
    public void clickDeclaration(){
        universityList = session.createQuery("select universityName from University uni").getResultList();
        choiceUniversity.setItems(FXCollections.observableArrayList(universityList));
        choiceUniversity.getSelectionModel().selectFirst();
    }

    @FXML
    public void saveDeclaration(){
        university = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() +"'").getSingleResult();
        fieldOfStudy = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy1.getValue() +"'").getSingleResult();

        Declaration declaration = new Declaration(3,Integer.parseInt(inputExamScore.getText()));
        student.add(declaration);
        university.add(declaration);
        fieldOfStudy.add(declaration);
        session.save(declaration);
        session.getTransaction().commit();
    }

    @FXML
    public void clickInformation(){

    }

    @FXML
    public void clickColumnUniversity(){
        TableCells university = tableUniversity.getSelectionModel().getSelectedItem();
        tableFieldOfStudy.setItems(null);
        for (int i=0; i<universityList.size();i++){
            if(universityList.get(i).getUniversityName().equals(university.getUniversity().getValue())){
                facultyList=universityList.get(i).getFieldOfStudyList();
            }
        }

        ObservableList<TableCells> dane = FXCollections.observableArrayList();
        for (int i=0; i<facultyList.size();i++){
            dane.add(new TableCells(String.valueOf(facultyList.get(i).getFaculty())));
        }
        columnFaculty.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        tableFaculty.setEditable(true);
        tableFaculty.itemsProperty().setValue(dane);
    }

    @FXML
    public void clickColumnFaculty(){
        TableCells faculty = tableFaculty.getSelectionModel().getSelectedItem();

        fieldOfStudyList= new ArrayList<FieldOfStudy>();

        for (int i=0; i<facultyList.size();i++){
            if(facultyList.get(i).getFaculty().equals(faculty.getUniversity().getValue())){
                fieldOfStudyList.add(facultyList.get(i));
            }
        }

        ObservableList<TableCells> dane = FXCollections.observableArrayList();

        for (int i=0; i<fieldOfStudyList.size();i++){
            dane.add(new TableCells(String.valueOf(fieldOfStudyList.get(i).getFieldOfStudyName()), Integer.valueOf(fieldOfStudyList.get(i).getMinPoints())));
        }
        columnFieldOfStudy.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        columnMinPoints.setCellValueFactory(new PropertyValueFactory<TableCells, Integer>("points"));
        tableFieldOfStudy.setEditable(true);
        tableFieldOfStudy.itemsProperty().setValue(dane);
    }

    @FXML
    public void closedInformation(){

    }


}
