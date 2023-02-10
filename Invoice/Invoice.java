public class Invoice {

    int price;
    int noOfItem;
    int length =0;
    int item[] = new int[10];


    public void addInvoice(int i) {
        item[length++] = i;
    }

    public int[] getArray() {
        return item;
    }
}
