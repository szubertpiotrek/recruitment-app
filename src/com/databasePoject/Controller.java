package com.databasePoject;

import com.mysql.cj.util.StringUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
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
    private TextField inputExamScore1;

    @FXML
    private TextField inputExamScore2;

    @FXML
    private TextField inputExamScore3;

    @FXML
    private TextField inputExamScore4;

    @FXML
    private TextField inputExamScore5;

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

    @FXML
    private ListView<String> results;

    private int idStudent;

    private Student student;

    private List<University> universityList;


    private University university1;

    private University university2;

    private University university3;

    private University university4;

    private University university5;

    private FieldOfStudy fieldOfStudy1;

    private FieldOfStudy fieldOfStudy2;

    private FieldOfStudy fieldOfStudy3;

    private FieldOfStudy fieldOfStudy4;

    private FieldOfStudy fieldOfStudy5;

    private List<FieldOfStudy> fieldOfStudyList;

    private List<Student> studentsList;

    private List<FieldOfStudy> facultyList;

    private List<Declaration> fieldOfStudyTempList;

    private List<String> resultsTempList;

    private List<Declaration> declarationList;

    private List<Declaration> declarationTempList;

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


            fieldOfStudyTempList = new ArrayList<Declaration>();

            for(int i=0; i < student.getDeclarationList().size();i++){
                if(newValue.equals(student.getDeclarationList().get(i).getIdUniversity().getUniversityName())){
                    fieldOfStudyTempList.add(student.getDeclarationList().get(i));
                }
            }


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

        if(editFalg && validInputText(inputName.getText()) && validInputText(inputSurname.getText())){
            editStudentDataButton.setText("Edit");
            inputName.setVisible(false);
            inputSurname.setVisible(false);
            labelName.setText(inputName.getText());
            labelSurname.setText(inputSurname.getText());

            student.setName(inputName.getText());
            student.setSurname(inputSurname.getText());

            editFalg=false;
        }
        else{
            System.out.println("gg");
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
        if(fieldOfStudyTempList==null) {
            university1 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
            fieldOfStudy1 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy1.getValue() + "'").getSingleResult();

            university2 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
            fieldOfStudy2 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy2.getValue() + "'").getSingleResult();

            university3 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
            fieldOfStudy3 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy3.getValue() + "'").getSingleResult();

            university4 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
            fieldOfStudy4 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy4.getValue() + "'").getSingleResult();

            university5 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
            fieldOfStudy5 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy5.getValue() + "'").getSingleResult();


            Declaration declaration1 = new Declaration(1, Integer.parseInt(inputExamScore1.getText()));
            Declaration declaration2 = new Declaration(2, Integer.parseInt(inputExamScore2.getText()));
            Declaration declaration3 = new Declaration(3, Integer.parseInt(inputExamScore3.getText()));
            Declaration declaration4 = new Declaration(4, Integer.parseInt(inputExamScore4.getText()));
            Declaration declaration5 = new Declaration(5, Integer.parseInt(inputExamScore5.getText()));

            student.add(declaration1);
            university1.add(declaration1);
            fieldOfStudy1.add(declaration1);
            session.save(declaration1);

            student.add(declaration2);
            university1.add(declaration2);
            fieldOfStudy1.add(declaration2);
            session.save(declaration2);

            student.add(declaration3);
            university1.add(declaration3);
            fieldOfStudy1.add(declaration3);
            session.save(declaration3);

            student.add(declaration4);
            university1.add(declaration4);
            fieldOfStudy1.add(declaration4);
            session.save(declaration4);

            student.add(declaration5);
            university1.add(declaration5);
            fieldOfStudy1.add(declaration5);
            session.save(declaration5);

//            session.getTransaction().commit();
        }else{
            declarationTempList = new ArrayList<Declaration>();

            declarationList = student.getDeclarationList();

            for (Declaration declar: declarationList) {
                System.out.println(declar.getIdUniversity().getUniversityName());
                System.out.println(choiceUniversity.getValue());
                if(declar.getIdUniversity().getUniversityName().equals(choiceUniversity.getValue())){
                    declarationTempList.add(declar);
                }
            }


            if(fieldOfStudyTempList.size()>0 && inputExamScore1.getText().length()>0){
                declarationTempList.get(0).getIdFieldOfStudy().setFaculty((String) choiceFaculty1.getValue());
                declarationTempList.get(0).setExamsScore(Integer.parseInt(inputExamScore1.getText()));
                declarationTempList.get(0).getIdFieldOfStudy().setFieldOfStudyName((String) choiceFieldOfStudy1.getValue());
            }

            if(fieldOfStudyTempList.size()>1 && inputExamScore2.getText().length()>0) {
                declarationTempList.get(1).getIdFieldOfStudy().setFaculty((String) choiceFaculty2.getValue());
                declarationTempList.get(1).setExamsScore(Integer.parseInt(inputExamScore2.getText()));
                declarationTempList.get(1).getIdFieldOfStudy().setFieldOfStudyName((String) choiceFieldOfStudy2.getValue());
            }

            if(fieldOfStudyTempList.size()>2 && inputExamScore3.getText().length()>0) {
                declarationTempList.get(2).getIdFieldOfStudy().setFaculty((String) choiceFaculty3.getValue());
                declarationTempList.get(2).setExamsScore(Integer.parseInt(inputExamScore3.getText()));
                declarationTempList.get(2).getIdFieldOfStudy().setFieldOfStudyName((String) choiceFieldOfStudy3.getValue());
            }

            if(fieldOfStudyTempList.size()>3 && inputExamScore4.getText().length()>0) {
                declarationTempList.get(3).getIdFieldOfStudy().setFaculty((String) choiceFaculty4.getValue());
                declarationTempList.get(3).setExamsScore(Integer.parseInt(inputExamScore4.getText()));
                declarationTempList.get(3).getIdFieldOfStudy().setFieldOfStudyName((String) choiceFieldOfStudy4.getValue());
            }

            if(fieldOfStudyTempList.size()>4 && inputExamScore5.getText().length()>0) {
                declarationTempList.get(4).setExamsScore(Integer.parseInt(inputExamScore5.getText()));
                declarationTempList.get(4).getIdFieldOfStudy().setFieldOfStudyName((String) choiceFieldOfStudy5.getValue());
                declarationTempList.get(4).getIdFieldOfStudy().setFaculty((String) choiceFaculty5.getValue());
            }

//            session.getTransaction().commit();
        }
    }

    @FXML
    public void clickInformation(){
    }

    @FXML
    public void clickColumnUniversity(){
        universityList = session.createQuery("select uni from University uni").getResultList();
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

    @FXML
    public void clickResults(){
        resultsTempList = new ArrayList<String>();
        studentsList = new ArrayList<Student>();
        results.getItems().clear();

        declarationList = session.createQuery("select decl from Declaration decl").getResultList();

        int k = 1;
        int universityLastName=0;

        for (int i =0;i<declarationList.size();i++) {

            if(declarationList.get(i).getPriority()==1 && student.getId()==declarationList.get(i).getIdStudent().getId()){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=1 order by decl.examsScore ").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                    }
                }
            }
            else if(declarationList.get(i).getPriority()==2 && student.getId()==declarationList.get(i).getIdStudent().getId() && declarationList.get(i).getIdUniversity().getIdUniversity()!=universityLastName){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=2 order by decl.examsScore ").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==3 && student.getId()==declarationList.get(i).getIdStudent().getId() && declarationList.get(i).getIdUniversity().getIdUniversity()!=universityLastName){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=3 order by decl.examsScore ").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==4 && student.getId()==declarationList.get(i).getIdStudent().getId() && declarationList.get(i).getIdUniversity().getIdUniversity()!=universityLastName){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=4 order by decl.examsScore ").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==5 && student.getId()==declarationList.get(i).getIdStudent().getId() && declarationList.get(i).getIdUniversity().getIdUniversity()!=universityLastName){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=5 order by decl.examsScore ").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                    }
                }

            }
        }


        ObservableList<String> resultsList = FXCollections.<String>observableArrayList(resultsTempList);

        results.getItems().addAll(resultsList);
    }

    public boolean validInputText(String text){
        System.out.println(text.length() + "  " + StringUtils.isEmptyOrWhitespaceOnly(text));
        if(text.length()>0 && !StringUtils.isEmptyOrWhitespaceOnly(text)){
            return true;
        }
       return false;
    }


    public void exitApp() {
        session.getTransaction().commit();
        session.close();
        factory.close();
    }


}
