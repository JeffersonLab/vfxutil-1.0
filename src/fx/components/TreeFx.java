package fx.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Custom Tree
 * Accepts only String items
 * <p>
 *
 * @author gurjyan
 *         Date 9/16/16
 * @version 1.x
 */
public class TreeFx {
    private TreeItem<String> rootItem;
    private TreeView<String> tree;

    private TreeFx(Builder builder) {
        //build root
        if (builder.rootImage != null) {
            rootItem = new TreeItem<>(builder.root, builder.rootImage);
        } else {
            rootItem = new TreeItem<>(builder.root);
        }
        rootItem.setExpanded(builder.expand);

        // add items
        builder.items.forEach(e -> {
            TreeItem<String> item = new TreeItem<>(e);
            rootItem.getChildren().add(item);
        });
        tree = new TreeView<>(rootItem);
        tree.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void addItem(String itemName) {
        boolean b = true;
        TreeItem<String> item = new TreeItem<>(itemName);
        for(TreeItem<String> e:rootItem.getChildren()){
            if (e.toString().equals(item.toString())) b= false;
        }
        if(b)rootItem.getChildren().add(item);
    }

    public void removeSelected() {
       ObservableList<TreeItem<String>> c = tree.getSelectionModel().getSelectedItems();
        List<TreeItem<String>> r = new ArrayList<>();

        if (c!=null) {
            c.forEach(r::add);

            c.forEach(e -> {
                if(e.getParent()!=null) {
                    e.getParent().getChildren().remove(e);
                    r.remove(e);
                }
            });
            if(!r.isEmpty()) removeSelected();
        }
    }

    public Set<String> getSelected(){
        Set<String> res = new HashSet<>();
        ObservableList<TreeItem<String>> c = tree.getSelectionModel().getSelectedItems();
        if (c!=null) {
            c.forEach(e -> {
                if (e.getParent() != null) {
                    res.add(e.toString());
                }
            });
        }
        return res;
    }

    public Node show() {
        return tree;
    }

    public static class Builder {
        private String root;
        private ImageView rootImage = null;
        private boolean expand = true;
        private Set<String> items = new HashSet<>();

        public Builder(String root) {
            this.root = root;
        }

        public Builder item(String item) {
            items.add(item);
            return this;
        }

        public Builder rootImage(ImageView rootImage) {
            this.rootImage = rootImage;
            return this;
        }

        public Builder expand(boolean expand) {
            this.expand = expand;
            return this;
        }

        public TreeFx build(){
            return new TreeFx(this);
        }
    }
}
