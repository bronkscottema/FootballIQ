import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

public class UndoActionListener implements UndoableEditListener, ActionListener {
    private Handler handler;
    private Route route;

    public UndoActionListener(Route route, Handler handler) {
        this.route = route;
        this.handler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        route.undo();
    }
}
