package resturantmanagement.service;

import resturantmanagement.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void updateMenuItem(MenuItem oldItem, MenuItem newItem) {
        menuItems.remove(oldItem);
        menuItems.add(newItem);
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuItem> filterMenuItems(MenuItem.Type type, MenuItem.Category category) {
        List<MenuItem> filteredItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getType() == type && item.getCategory() == category) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
