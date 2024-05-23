package Invoice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer[] customer=new Customer[10];
        Invoice[] invoice=new Invoice[20];
        Item[] item=new Item[20];

        item[0]=new Item(400,"HeadPhone");
        item[1]=new Item(1000,"Speaker");
        item[2]=new Item(500,"SD card");
        item[3]=new Item(600,"Battery");

        int cust_Id = 0;
        int invoice_Id = 0;
        int cutomerId,invoiceid;

        boolean loop = true;
        while (loop)
        {
            System.out.println("_____________INVOICE SYSTEM______________");
            System.out.println("1.Add Customer");
            System.out.println("2.Add Invoice");
            System.out.println("3.Add Item to Invoice");
            System.out.println("4.List all Customers");
            System.out.println("5.List all Invoices");
            System.out.println("6.List all invoices of a Customer");
            System.out.println("7.Display an invoice");
            System.out.print("Enter the Option :");
            int choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Enter customer name:");
                     String naame=sc.next();
                    System.out.print("Enter address:");
                     String address=sc.next();
                    System.out.print("Enter Mobile no:");
                    String mbileNo=sc.next();
                    customer[cust_Id] = new Customer(naame,address,mbileNo);
                    System.out.println("your id is "+cust_Id);
                    cust_Id++;

                    break;
                case 2:
                    System.out.println("1.Existing Customer");
                    System.out.println("2.New Customer");
                    int choose = sc.nextInt();
                    if(choose == 1)
                    {
                        System.out.print("Enter Customer ID:");
                        cutomerId = sc.nextInt();
                        if(cutomerId>=cust_Id)
                        {
                            System.out.println("Enter Valid id");
                            break;
                        }
                    }
                    else if (choose == 2)
                    {
                        System.out.println("Enter customer name:");
                        String iname=sc.nextLine();
                        System.out.println("Enter address:");
                        String iaddress=sc.nextLine();
                        System.out.println("Enter Mobile no:");
                        String imbileNo=sc.nextLine();
                        customer[cust_Id] = new Customer(iname,iaddress,imbileNo);
                        cutomerId = cust_Id;
                        cust_Id++;
                    }
                    else
                    {
                        System.out.println("Enter Valid options");
                        break;
                    }
                    invoice[invoice_Id] = new Invoice();
                    customer[cutomerId].addInvoice(invoice_Id);
                    int select = 1 ;
                    while (select==1)
                    {
                        System.out.println("Select the Item");
                        System.out.println("1.HeadPhone Rs.400");
                        System.out.println("2.Speaker Rs.1000");
                        System.out.println("3.SD card Rs.500");
                        System.out.println("4.Battery Rs.6000");
                        int n  = sc.nextInt();
                        if(n>4)
                        {
                            System.out.println("Invalid Option");
                            break;
                        }
                        invoice[invoice_Id].addInvoice(n-1);
                        invoice[invoice_Id].price+=item[n-1].price;
                        invoice[invoice_Id].noOfItem+=1;
                        System.out.println("Are you want to add one more item yes 1 or no 0");
                        select=sc.nextInt();
                    }
                    System.out.format("%-20s%-10d","Your invoice no:",invoice_Id);
                    System.out.println("");
                    invoice_Id++;
                    break;
                case 3:
                    System.out.println("Enter Invoice Numeber");
                    invoiceid= sc.nextInt();
                    if(invoiceid>=invoice_Id)
                    {
                        System.out.println("Enter Valid id");
                        break;
                    }
                    int k = 1;
                    while (k==1) {
                        System.out.println("Select the Item");
                        System.out.println("1.HeadPhone Rs.400");
                        System.out.println("2.Speaker Rs.1000");
                        System.out.println("3.SD card Rs.500");
                        System.out.println("4.Battery Rs.6000");
                        int j = sc.nextInt();
                        if (j > 4) {
                            System.out.println("Invaid option");
                            break;
                        }
                        invoice[invoiceid].addInvoice(j - 1);
                        invoice[invoiceid].price += item[j - 1].price;
                        invoice[invoiceid].noOfItem += 1;
                        System.out.println("Are you want to add one more item yes 1 or no 0");
                        k = sc.nextInt();
                    }
                    break;
                case 4:
                    System.out.format("%-10s%-12s%-15s%-12s","Cust_id","Cust_name","Mobile","Address");
                    for (int j=0;j<cust_Id;j++)
                    {
                        System.out.println("");

                        System.out.format("%-10s%-12s%-15s%-12s",j,customer[j].name,customer[j].mobileNo,customer[j].address);
                    }
                    System.out.println("");
                    break;
                case 5:
                    for(int j=0;j<invoice_Id;j++)
                    {
                        displayItem(j,invoice,item);
                    }
                    break;
                case 6 :
                    int price=0,noOfItem=0;
                    System.out.println("Enter Customer id:");
                    int customer_Id=sc.nextInt();
                    if(cust_Id<=customer_Id)
                    {
                        System.out.println("Invalid Id!!");
                        return;
                    }
                    int[] invoices=new int[10];
                    invoices=customer[customer_Id].getArray();;
                    System.out.format("%-10s%-10d","Customer:",customer_Id);
                    System.out.println("");
                    System.out.println("__________________________________");
                    System.out.println("----------------------------------");
                    System.out.println("");
                    for(int j=0;j<customer[customer_Id].length;j++)
                    {
                        invoiceid=invoices[j];
                        displayItem(invoiceid,invoice,item);
                        price+=invoice[invoiceid].price;
                        noOfItem+=invoice[invoiceid].noOfItem;
                    }
                    System.out.println("---------------------------------------------------------------------");
                    System.out.println("");
                    System.out.format("%-30s%-10d","Total number of Items Bought by this customer :",noOfItem);
                    System.out.println("");
                    System.out.format("%-30s%-10d","Total  amount spent by this customer :",price);
                    System.out.println("");
                    System.out.println("---------------------------------------------------------------------");
                    break;
            case 7:
                    {
                        System.out.println("Enter the invoice No:");
                        invoiceid=sc.nextInt();
                        if(invoiceid>=invoice_Id)
                        {
                            System.out.println("Invalid id!!!!");
                        }
                        else
                        {
                            displayItem(invoiceid,invoice,item);
                        }
                    }
                    break;
                default:
                    System.out.println("Enter Correct Option");




            }
        }
    }

    private static void displayItem(int Invoice_id, Invoice[] invoice, Item[] item) {
        int[] items=new int[10];
        int item_id=0,j;
        items=invoice[Invoice_id].getArray();
        System.out.format("%-15s%-10d","Invoice_no",Invoice_id);
        System.out.println("");
        System.out.println("-----------------------------");
        System.out.format("%-12s%-15s","item_name","unit_price");
        for(j=0;j<invoice[Invoice_id].length;j++)
        {
            item_id=items[j];
            System.out.println("");
            System.out.format("%-15s%-20d",item[item_id].itemName,item[item_id].price);
        }
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.format("%-15s%-10d%-15s%-10d","TotalItems:",invoice[Invoice_id].noOfItem,"TotalPrice:",invoice[Invoice_id].price);
        System.out.println("");
        System.out.println("-----------------------------------------");
    }
    }
