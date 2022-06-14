module cz.cvut.fel.doudamar.localmultiplayergame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens cz.cvut.fel.doudamar.localmultiplayergame to javafx.fxml;
    exports cz.cvut.fel.doudamar.localmultiplayergame.core;
    opens cz.cvut.fel.doudamar.localmultiplayergame.core to javafx.fxml;
    exports cz.cvut.fel.doudamar.localmultiplayergame.scenes;
    opens cz.cvut.fel.doudamar.localmultiplayergame.scenes to javafx.fxml;
}