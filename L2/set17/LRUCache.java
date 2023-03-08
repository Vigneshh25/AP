class LRUCache
{
    
    class Node {
        Node prev, next;
        int key, value;
        Node(int _key, int value) {
            key = _key;
            value = _value;
        }
    }
    
    Node head = new Node(0, 0), tail = new Node(0, 0);
    Map < Integer, Node > map = new HashMap();
    int cap;
    //Constructor for initializing the cache capacity with the given value.
    LRUCache(int _capacity)
    {
        //code here
        cap =  _capacity;
        head.next = tail;
        tail.prev = head;
     
    }

    //Function to return value corresponding to the key.
    public  int get(int key)
    {
        // your code here
         if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            add(node);
            return node.value;
        } else {
            return -1;
        }
    }
    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    //Function for storing key-value pair.
    public  void set(int key, int value)
    {
        // your code here
        if (map.containsKey(key)) {
        remove(map.get(key));
        }
        if (map.size() == cap) {
            remove(tail.prev);
        }
        add(new Node(key, value));

    }
}
