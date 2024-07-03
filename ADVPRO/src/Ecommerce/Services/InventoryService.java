package Ecommerce.Services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryService {
    private static volatile InventoryService instance;
    private ConcurrentHashMap<String, Integer> inventory;
    private ConcurrentHashMap<String, Lock> productLocks;

    private InventoryService() {
        inventory = new ConcurrentHashMap<>();
        productLocks = new ConcurrentHashMap<>();
    }

    public static InventoryService getInstance() {
        if (instance == null) {
            synchronized (InventoryService.class) {
                if (instance == null) {
                    instance = new InventoryService();
                }
            }
        }
        return instance;
    }

    public void addStock(String productId, int quantity) {
        inventory.merge(productId, quantity, Integer::sum);
        productLocks.putIfAbsent(productId, new ReentrantLock());
    }

    public boolean reserveStock(String productId, int quantity) {
        Lock lock = productLocks.get(productId);
        if (lock != null) {
            lock.lock();
            try {
                int currentStock = inventory.getOrDefault(productId, 0);
                if (currentStock >= quantity) {
                    inventory.put(productId, currentStock - quantity);
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public void releaseStock(String productId, int quantity) {
        Lock lock = productLocks.get(productId);
        if (lock != null) {
            lock.lock();
            try {
                inventory.merge(productId, quantity, Integer::sum);
            } finally {
                lock.unlock();
            }
        }
    }

    public int getStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }
}
