package Views.Cells;

import Modules.Annoucement;
import Modules.Post;
import Services.AnnounceServices;
import Services.PostServices;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;

public class AnnounceCell extends Form {
    public AnnounceCell(Resources theme, Form previous) {
        this.setTitle("Announces");
        this.setLayout(BoxLayout.y());
        Container by = new Container();
        ArrayList<Annoucement> annoucementss = AnnounceServices.getInstance().getAllann();
        for(int i=0;i<annoucementss.size();i++){
            by = BoxLayout.encloseY(postCell(annoucementss.get(i),theme));
            add(by);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public Container postCell(Annoucement e,Resources theme){
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round-mask.png");
        mask.scaled(20,20);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        ImageViewer profil = new ImageViewer(mask.scaled(50, 50));
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setWidth(20);
        profilePicLabel.setHeight(20);
        profilePicLabel.setMask(mask.createMask());

        Container EventCmp = BoxLayout.encloseY(
                new Label(e.getSubjectAnn(), "StatusBarSideMenu"),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                        )
                ),
                // new TextArea(Events.get(0).getDescription());
                new Label(e.getContentAnn(), "TodayEntry")
        );

        EventCmp.setUIID("EventCmp");

        Container OrganizerCmp = BoxLayout.encloseY(
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Organizer Name", "UserName"),
                                new Label("12/12/2021", "Role")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(1, EventCmp)
        );
        OrganizerCmp.setUIID("EventCell");

        OrganizerCmp.getAllStyles().setPadding(80, 80, 80, 80);

        // OrganizerCmp.getAllStyles().setMargin(LEFT, 25);
        OrganizerCmp.getAllStyles().setBorder(
                RoundRectBorder.create().
                        shadowOpacity(90).
                        stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 4)).
                        shadowSpread(Display.getInstance().convertToPixels(4)).
                        strokeOpacity(120)
        );
        return OrganizerCmp;
    }
}
