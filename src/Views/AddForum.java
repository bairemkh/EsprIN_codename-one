package Views;

import Modules.Forum;
import Services.ForumServices;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.Resources;

public class AddForum extends Form {
    public AddForum(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        // setUIID("AddEvent");
       // setLayout(BoxLayout.y());
        Container AddNew = FlowLayout.encloseCenter(
                new Label("Add New Forum ", "EventTitle")

        );
        //AddNew.getAllStyles().setPadding(TOP,0);

        Button backButton = new Button("");
        backButton.setUIID("EventCell");
        FontImage.setMaterialIcon(backButton, FontImage.MATERIAL_ARROW_BACK);
        backButton.addActionListener(e ->
                new EventForm(theme).show()
        );



        TextField title = new TextField("", "Enter title") ;
         //TextField content = new TextField("", "Description!..", 20, TextField.ANY);
        TextArea content =new TextArea("content..",4, 20);
        TextField tag = new TextField("", "Tag") ;

        Button addButton = new Button("ADD FORUM");
        addButton.setUIID("LoginButton");

        addButton.getUnselectedStyle().setBorder(
                RoundBorder.create().rectangle(true).stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 4)).
                        strokeColor(0xff).strokeOpacity(120)
        );

        int color=addButton.getUnselectedStyle().getBgColor();
        addButton.getPressedStyle().setBgColor(color);

        addButton.getPressedStyle().setBorder(
                RoundBorder.create().rectangle(true).stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 4)).
                        strokeColor(0xff).strokeOpacity(120)
        );





        title.setUIID("AddEvent");
        content.setUIID("AddEvent");
        tag.setUIID("AddEvent");

        title.getAllStyles().setBorder(RoundRectBorder.create());
        content.getAllStyles().setBorder(RoundRectBorder.create());
        tag.getAllStyles().setBorder(RoundRectBorder.create());


        title.getAllStyles().setMargin(LEFT,0);
        title.getAllStyles().setMargin(BOTTOM,50);

        content.getAllStyles().setMargin(LEFT, 0);
        content.getAllStyles().setMargin(BOTTOM,50);

        tag.getAllStyles().setMargin(LEFT, 0);
        tag.getAllStyles().setMargin(BOTTOM,50);


        Container by = BoxLayout.encloseY(
                FlowLayout.encloseIn(backButton),
                AddNew,
                title,
                content,
                tag,
                addButton
        );


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(title.getText().isEmpty() || content.getText().isEmpty() || tag.getText().isEmpty()){
                    Dialog.show("Alert", "Please fill all the fields",new Command("OK"));
                }else {
                    Forum forum = new Forum();
                    forum.setTitle(title.getText());
                    forum.setContent(content.getText());
                    forum.setCategoryForum(tag.getText());
                    forum.setIdOwner(10020855);
                    if( ForumServices.getInstance().addForum(forum)){
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                    }else {
                        Dialog.show("Failed","Connection rejected",new Command("OK"));
                    }
                }

            }
        });

        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

    }
}
