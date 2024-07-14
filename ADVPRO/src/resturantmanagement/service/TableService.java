package resturantmanagement.service;

import resturantmanagement.entity.Table;

import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables = new ArrayList<>();

    public void addTable(Table table) {
        tables.add(table);
    }

    public void removeTable(int tableId) {
        tables.removeIf(table -> table.getTableId() == tableId);
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table getTable(int tableId) {
        for (Table table : tables) {
            if (table.getTableId() == tableId) {
                return table;
            }
        }
        return null;
    }
}
