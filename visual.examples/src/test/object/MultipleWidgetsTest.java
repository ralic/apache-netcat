package test.object;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.netbeans.api.visual.model.*;
import org.openide.util.Utilities;
import test.SceneSupport;

import java.awt.*;
import java.util.Set;
import java.util.Collections;

/**
 * @author David Kaspar
 */
public class MultipleWidgetsTest extends ObjectScene implements ObjectSceneListener {

    private static final Image IMAGE = Utilities.loadImage ("test/resources/displayable_64.png"); // NOI18N

    private LayerWidget mainLayer;
    private LabelWidget selectionLabel;

    private WidgetAction moveAction = ActionFactory.createMoveAction ();

    public MultipleWidgetsTest () {
        LayerWidget backgroundLayer = new LayerWidget (this);
        mainLayer = new LayerWidget (this);

        addChild (backgroundLayer);
        addChild (mainLayer);

        selectionLabel = new LabelWidget (this);
        addChild (selectionLabel);

        getActions ().addAction (ActionFactory.createZoomAction ());
        getActions ().addAction (ActionFactory.createPanAction ());
        getActions ().addAction (ActionFactory.createRectangularSelectAction (this, backgroundLayer));

        addObjectSceneListener (this, ObjectSceneEventType.OBJECT_SELECTION_CHANGED);
        selectionChanged (null, null, Collections.emptySet ());
    }

    public Widget createAddWidget (String label, int x, int y) {
        IconNodeWidget widget = new IconNodeWidget (this);
        widget.setImage (MultipleWidgetsTest.IMAGE);
        widget.setLabel (label);
        widget.setPreferredLocation (new Point (x, y));
        mainLayer.addChild (widget);

        widget.getActions ().addAction (createSelectAction ());
        widget.getActions ().addAction (createObjectHoverAction ());
        widget.getActions ().addAction (moveAction);

        return widget;
    }

    public static void main (String[] args) {
        MultipleWidgetsTest scene = new MultipleWidgetsTest ();

        scene.addObject ("First Object",
            scene.createAddWidget ("First widget - mapped to the first object", 100, 100),
            scene.createAddWidget ("Second widget - mapped to the first object", 450, 100)
        );

        scene.addObject ("Second Object",
            scene.createAddWidget ("Third widget - mapped to the second object", 100, 200),
            scene.createAddWidget ("Fourth widget - mapped to the second object", 450, 200)
        );

        SceneSupport.show (scene);
    }

    public void objectAdded (ObjectSceneEvent event, Object addedObject) {
    }

    public void objectRemoved (ObjectSceneEvent event, Object removedObject) {
    }

    public void objectStateChanged (ObjectSceneEvent event, Object changedObject, ObjectState previousState, ObjectState newState) {
    }

    public void selectionChanged (ObjectSceneEvent event, Set<Object> previousSelection, Set<Object> newSelection) {
        selectionLabel.setLabel ("Selected: " + newSelection);
    }

    public void highlightingChanged (ObjectSceneEvent event, Set<Object> previousHighlighting, Set<Object> newHighlighting) {
    }

    public void hoverChanged (ObjectSceneEvent event, Object previousHoveredObject, Object newHoveredObject) {
    }

    public void focusChanged (ObjectSceneEvent event, Object previousFocusedObject, Object newFocusedObject) {
    }
}
