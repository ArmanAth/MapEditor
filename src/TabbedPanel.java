import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JPanel {

    //Constructor
    TabbedPanel(){

        //Creating tabbed pane to have tabs for main editor and tile selector
        JTabbedPane tabs = new JTabbedPane();

        //Setting up map panel and tile selector panel
        JPanel mapTab = new JPanel();
        JPanel tileSelectTab = new JPanel();

        //Adding panels to tabbedpane
        tabs.add("Edit Map", mapTab);
        tabs.add("Select Tile", tileSelectTab);

        //Adding tabbedpane to panel
        this.add(tabs);

    }
}
