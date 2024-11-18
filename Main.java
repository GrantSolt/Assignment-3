import java.io.File;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RedBlackBST<Product> rbt = new RedBlackBST<>();


        File file = new File("/Users/grantsolt/Desktop/49205929/Assignment3/amazon-product-data.csv");
        Scanner sc = new Scanner(file);
        Scanner sc2= new Scanner(System.in);

        String line = sc.nextLine();

        String id;
        String name;
        String catagory;
        String price;



        int count=0;
//  While loop take each line in the csv file and divides it based upon different possibilities of
//  of how the values seperated. I could not just seperate them by looking for commas, as some
//  of the names and catagories had commas in them.
        while(sc.hasNextLine()) {
            id="";
            name="";
            catagory="";
            price="";
            count++;
            line = sc.nextLine();
            int x=0;
            char a = line.charAt(x);
            while(true){
                if(a==',')
                    break;
                id = id+a;
                x++;
                a = line.charAt(x);
            }
            x++;
            a = line.charAt(x);
            while(true){
                if(a==','&&line.charAt(x+1)!=' ')
                    break;
                name = name+a;
                x++;
                a = line.charAt(x);
            }
            if(x<=line.length()-4)
                x++;
            a = line.charAt(x);
            while(true){
                if((x>=line.length()-2))
                    break;
                if(a==','&&line.charAt(x+1)!=' ')
                    break;
                catagory = catagory+a;
                x++;
                a = line.charAt(x);
            }
            x+=2;
            price=line.substring(x);
            Product item = new Product(id, name, catagory, price);
            rbt.insert(item);
        }


//      Inserting 2 products that I made into the tree
        id="aae5319e38fcff7a9fe5764e7969a827";
        name="Iphone case";
        catagory="electronics";
        price="11.99";
        Product item3 = new Product(id, name, catagory, price);
        try {
            rbt.insert(item3);
            System.out.println("Item added with id: "+id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()+" "+id);
        }

        id="2b4";
        name="Ping Pong Ball";
        catagory="Sports";
        price=".99";
        Product item2 = new Product(id, name, catagory, price);
        try {
            rbt.insert(item2);
            System.out.println("Item added with id: "+id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()+" "+id);
        }




//  Searching for 3 products and printing the information about them.
        for(int x=0;x<3;x++){
            System.out.println("Enter an ID to search for: ");
            String search=sc2.nextLine();
            Product look=new Product(search, null, null, null);
            Product found=rbt.search(look);
            if(found!=null){
                System.out.println("Product exists");
                found.printInfo();
            }
            else
                System.out.println("Product does not exist");
        }
    }
}