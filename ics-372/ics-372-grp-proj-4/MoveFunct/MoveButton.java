import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates a button for moving items.
 *
 */
public class MoveButton extends JButton implements ActionListener {
    private static final long serialVersionUID = 1L;
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
 
    /**
     * Creates a MoveButton.
     * @param jFrame the jFrame it should be part of.
     * @param jPanel the jPanel it should be part of.
     */
    public MoveButton(View jFrame, JPanel jPanel) {
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
    }    
    
   
    /**
     * Handle click for creating a new line
     */
    public void actionPerformed(ActionEvent event) {
        drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
    }
    
    
    /**
     * Handles mouse clicks for selecting the item and then move point.
     */
    private class MouseHandler extends MouseAdapter {
        /**
         * Handles when the mouse is first pressed, selects items on click.
         */
        public void mousePressed(MouseEvent event) {
            Controller.instance().unselectItems();
            Controller.instance().selectItem(View.mapPoint(event.getPoint()));
            Controller.instance().setFirstPoint(View.mapPoint(event.getPoint()));
        }
        
        /**
         * Handles when the mouse is released, moving items to the release point.
         */
        public void mouseReleased(MouseEvent event) {
            Controller.instance().moveItems(View.mapPoint(event.getPoint()));
            Controller.instance().unselectItems();
            drawingPanel.removeMouseListener(this);
        }
        
    }    
    
    
    
}
