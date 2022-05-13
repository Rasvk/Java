import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.scene.Scene; 
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
import javafx.geometry.*; 
import javafx.stage.*; 
import javafx.scene.paint.Color; 
import javafx.scene.text.Font;
import javafx.stage.FileChooser; 
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.util.Optional;
import java.io.FileNotFoundException;
import javafx.scene.control.Alert.AlertType;

public class Oblig7 extends Application {
   
    public void start(Stage vindu) throws Exception {
	BorderPane border = new BorderPane();
	HBox hbox = nyHBox(vindu, border);
	border.setTop(hbox);
	Scene scene = new Scene(border, 650, 700 );
	vindu.setScene(scene);
	vindu.show();
	vindu.setTitle("Oblig7: Labyrint");
    }

    private HBox nyHBox(Stage vindu, BorderPane border) {
	HBox hbox = new HBox();
	hbox.setPadding(new Insets(15,12,15,12));
	hbox.setSpacing(30);
	hbox.setStyle("-fx-background-color: #221199");
	
	Button knapp1 = new Button("Velg fil");
	knapp1.setPrefSize(100,20);

	knapp1.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		    public void handle(ActionEvent arg0) {
		    FileChooser fileChooser = new FileChooser();
		    FileChooser.ExtensionFilter ef =
    			new FileChooser.ExtensionFilter("IN files (*.in)","*.in");
     		    fileChooser.getExtensionFilters().add(ef);
		    File file = fileChooser.showOpenDialog(vindu);
		    if(file != null) {
			if(hbox.getChildren().size() > 1) { // fjern tidligere labels
			    hbox.getChildren().remove(1);
			}
			Label l = new Label(file.getPath());
			l.setTextFill(Color.WHITE);
		
			border.setCenter(minGridPane(file, hbox));
		    } else {
			if(hbox.getChildren().size() > 1) { // fjern tidligere labels
			    hbox.getChildren().remove(1);
			}
			Label l = new Label("ERROR! Ingen fil valgt");
			l.setTextFill(Color.RED);
			hbox.getChildren().add(l);
			if(border.getChildren().size() > 1) { // fjern tidligere labels
			    border.getChildren().remove(1);
			}
		    }
		}
	    });
	hbox.getChildren().addAll(knapp1);

        
        return hbox;
    }
    
    private GridPane minGridPane(File fil, HBox hbox) {
	Labyrint l  = null;
	try {
	    l = Labyrint.lesFraFil(fil);
	} catch (FileNotFoundException e) {
	    System.exit(1);
	}
	GridPane rutepanel = new GridPane();
        Rute[][] lab = l.getLabyrint();  
        Rectangle[][] gui = new Rectangle[l.getRader()][l.getKolonner()];
	for(int i = 0; i < lab.length; i++) {
	    for(int j = 0; j < lab[i].length; j++) {
		Rectangle r = new Rectangle(50, 50);
		GridPane.setRowIndex(r, i);
		GridPane.setColumnIndex(r,j);
		if(lab[i][j] instanceof SortRute) {
		    r.setFill(Color.BLACK);
		} else {
		    r.setFill(Color.WHITE);
		}
		gui[i][j] = r;
		rutepanel.getChildren().add(r);
	    }
	} 
	for(int i = 0; i < lab.length; i++) {
	    for(int j = 0; j < lab[i].length; j++) {
		gui[i][j].setOnMouseClicked(new minListner(l, gui, hbox));
	    }
	}
	return rutepanel;
    }

    /**
     * egen klasse for haandtering av museklikk paa rectangle, tar vare paa et par
     * instansvariabler som trengs for losning av oppgaven
     */
    class minListner implements EventHandler<MouseEvent> {
	private Labyrint l;
	private Rectangle[][] rectangleArray;
	private HBox hb;


	/**
	 * Konstruktor minListner
	 * @param l, labyrinten som skal vises og loeses
	 * @param rectangleArray, 2D-array med alle rektanglene i gui-en
	 * @param hb Hboxen i gui-en
	 */
	public minListner(Labyrint l, Rectangle[][] rectangleArray, HBox hb){
	    this.l = l;
	    this.rectangleArray = rectangleArray;
	    this.hb = hb;
	}
	
	@Override
	    public void handle(MouseEvent e) {

	    Rectangle r = (Rectangle)e.getSource();
	    Integer rad = GridPane.getRowIndex(r)+1;
	    //System.out.println("Rad: " + rad);
	    Integer kol = GridPane.getColumnIndex(r)+1;
	    //System.out.println("Kolonne: " + kol);
	    Liste<String> utveier = l.finnUtveiFra(kol, rad);
	    if(utveier.storrelse() > 1) { // hvis mer enn 1 utvei lag label
		if(hb.getChildren().size() > 1) { // hvis en label finnes fra foer fjern denne
		    hb.getChildren().remove(1);
		}
		Label label = new Label("Antall utveier: " + utveier.storrelse());
		label.setTextFill(Color.WHITE);
		hb.getChildren().add(label);
	    } 
	    if(!utveier.erTom()) { // har loesning fra denne ruten
		boolean[][] losning = losningStringTilTabell(utveier.fjern(), l.getKolonner(), l.getRader()); 
		for(int i = 0; i < losning.length; i++) {
		    for(int j = 0; j < losning[i].length; j++) {
			if(losning[i][j]) { // farger utvei
			    rectangleArray[i][j].setFill(Color.GREEN);
			    
			} else {
			    if(l.getLabyrint()[i][j] instanceof HvitRute) {
				rectangleArray[i][j].setFill(Color.WHITE);
			    }
			}
		    }
		}
	    } else {
		for(int i = 0; i < rectangleArray.length; i++) {
		    for(int j = 0; j < rectangleArray[i].length; j++) {
			if(l.getLabyrint()[i][j] instanceof HvitRute) {
			    rectangleArray[i][j].setFill(Color.WHITE);
			}
		    } 	
		    
		    if(hb.getChildren().size() > 1) { // fjern tidligere labels
			hb.getChildren().remove(1);
		    }
		    Label label = new Label("Ingen Utveier!");
		    label.setTextFill(Color.RED);
		    hb.getChildren().add(label);
		
		}
	    }
	}
    }


    
    /**
     * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
     * av losningstien.
     * @param losningString String-representasjon av utveien
     * @param bredde        bredde til labyrinten
     * @param hoyde         hoyde til labyrinten
     * @return              2D-representasjon av rutene der true indikerer at
     *                      ruten er en del av utveien.
     */
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
	boolean[][] losning = new boolean[hoyde][bredde];
	java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
	java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
	while(m.find()) {
	    int x = Integer.parseInt(m.group(1))-1;
	    int y = Integer.parseInt(m.group(2))-1;
	    losning[y][x] = true;
	}
	return losning;	
    }

    /**
     * Main metode
     *
     * @param args
     */
    public static void main(String[] args) { 
	launch(args);
    } 

}
