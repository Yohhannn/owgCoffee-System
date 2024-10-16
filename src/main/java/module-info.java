module me.yohhan.dev.owgrevamp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens me.yohhan.dev.owgrevamp to javafx.fxml;
    exports me.yohhan.dev.owgrevamp;
    exports owgdata;
    opens owgdata to javafx.fxml;
}