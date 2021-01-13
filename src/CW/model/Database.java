package CW.model;

import CW.view.IView;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database implements IModel{
    private final ArrayList<Admin> adminList;
    private final ArrayList<StockItem> items;
    private final ArrayList<StockItem> orderItems;
    private String separator = "\\|";
    ArrayList<IView> viewObservers = new ArrayList<>();


    public Database(){
        adminList = new ArrayList<>();
        items = new ArrayList<>();
        orderItems = new ArrayList<>();
    }
    //
    /* Admin Section */
    //
    public void addAdmin(Admin admin){
        adminList.add(admin);
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void loadAdmin(File infile){
        try {
            File file = infile;

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String data = scanner.nextLine();

                String[] adminData = data.split(separator);

                Admin admin = new Admin();

                admin.setLoginName(adminData[0]);

                admin.setPassword(adminData[1]);

                adminList.add(admin);
            }

            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void printAdmins(){
        for(Admin admin : adminList){
            System.out.println(admin.getLoginName() + " " + admin.getPassword());
        }
    }

    public boolean adminCheck(String login, String pass){
        boolean loginStatus = false;

        for(Admin admin : adminList){
            if((admin.getLoginName().equals(login)) && (admin.getPassword().equals(pass))){
                loginStatus = true;
                break;
            }
        }

        return loginStatus;
    }

    public void saveAdmin(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);

            for(int i = 0; i < adminList.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += adminList.get(i).getLoginName();

                data += "|" + adminList.get(i).getPassword();

                fileWriter.write(data);
            }

            fileWriter.close();

            System.out.println("Admin file successfully saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //
    /*Stock Item Section*/
    //

    public void addItem(StockItem stockItem){
        items.add(stockItem);
    }

    public void removeItem(StockItem item){
        items.remove(item);
    }

    public StockItem getItemAt(int index){
        if(index < 0 && index >= items.size()){
            return null;
        }
        return items.get(index);
    }

    public ArrayList<StockItem> getStockItems(){
        return items;
    }

    public void loadStockItems(File infile){
        try {
            File file = infile;

            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();

                String[] itemData = data.split(separator);

                StockItem item = new StockItem();

                item.setBarcode(itemData[0]);

                item.setName(itemData[1]);

                double priceToDouble = Double.parseDouble(itemData[2]);
                item.setPrice(priceToDouble);

                items.add(item);
            }
            System.out.println("StockItem file successfully loaded");

            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveStockItem(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);

            for(int i = 0; i < items.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += items.get(i).getBarcode();

                data += "|" + items.get(i).getName();

                String stringPrice = String.valueOf(items.get(i).getPrice());
                data += "|"  + stringPrice;

                fileWriter.write(data);
            }

            fileWriter.close();

            System.out.println("Stock Item file successfully saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadOrderItems(File infile){
        try {
            File file = infile;

            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();

                String[] itemData = data.split(separator);

                StockItem item = new StockItem();

                item.setBarcode(itemData[0]);

                item.setName(itemData[1]);

                double priceToDouble = Double.parseDouble(itemData[2]);
                item.setPrice(priceToDouble);

                orderItems.add(item);
            }

            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public void saveOrderItem(File file){

        try {
            FileWriter fileWriter = new FileWriter(file);

            for(int i = 0; i < orderItems.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += orderItems.get(i).getBarcode();

                data += "|" + orderItems.get(i).getName();

                String stringPrice = String.valueOf(orderItems.get(i).getPrice());
                data += "|"  + stringPrice;

                fileWriter.write(data);
            }

            fileWriter.close();

            System.out.println("order file successfully saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public double getCount(){
        double total = 0.0;

        for(int i = 0; i < orderItems.size(); i++){
            total += orderItems.get(i).getPrice();
        }

        return total;
    }

    public void addOrderItems(StockItem item){
        orderItems.add(item);
    }

    public ArrayList<StockItem> getOrderItems() {
        return orderItems;
    }

    public ArrayList<StockItem> getItems() {
        return items;
    }

    public StockItem getStockItemAt(String name){
        for (StockItem orderItem : items) {
            if (orderItem.getName().equals(name)) {
                return orderItem;
            }
        }

        return null;
    }

    @Override
    public void registerObserver(IView view) {
        viewObservers.add(view);
    }

    @Override
    public void removeObserver(IView view) {
        int i = viewObservers.indexOf(view);
        if(i >= 0){
            viewObservers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < viewObservers.size(); i++){
            IView viewObserver = viewObservers.get(i);
            viewObserver.update();
        }
    }

    public StockItem getStockItemAt(int index){
        if(index < 0 && index > items.size()){
            return null;
        }

        return items.get(index);
    }


    public void StockItemRemove(int index){
        if(index < 0 && index > items.size()){
            System.out.println("Error");
        }
        items.remove(index);
    }

    public void addElementToStock(int index, StockItem newItem){
        if(index < 0 && index > items.size()){
            System.out.println("Error");
        }
        items.add(index, newItem);
    }
}
