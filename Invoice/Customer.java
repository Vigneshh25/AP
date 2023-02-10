public class Customer {
    String name;
    String address;
    String mobileNo;

    int[] invoice = new int[10];
    int length;

    public Customer(String name, String address, String mobileNo) {
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
    }

    public void addInvoice(int invoice_id) {
        invoice[length]= invoice_id;
        length++;
    }

    public int[] getArray() {
        return invoice;
    }
}
