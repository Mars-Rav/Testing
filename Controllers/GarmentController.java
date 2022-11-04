import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.GarmentModel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class GarmentController {
		
		static Scanner input = new Scanner(System.in);
		private static ArrayList<GarmentModel> inventory = new ArrayList<GarmentModel>();
		private static File file = new File("C:/Users/High Tech/Desktop/GarmentsInventory.txt");
		
		public static void main(String args[]) throws IOException, ParseException, ClassNotFoundException {
			
						
			
		}

		public static void addToInventory()throws IOException, ParseException {
			
			System.out.println("Enter ID:");
	        int ID = Integer.parseInt(input.nextLine());
	        
	        System.out.println("Enter name:");
			String name = input.nextLine();
						
			System.out.println("Enter the barcode:");
			String barcode = input.nextLine();
						
			System.out.println("Enter brand:");
	        String brand = input.nextLine();
	      
	        System.out.println("Enter size:");
			String size = input.nextLine();
						
			System.out.println("Enter color:");
			String color = input.nextLine();
			
	        System.out.println("Enter model:");
			String model = input.nextLine();
			
			System.out.println("Enter price:");
	        double price = Double.parseDouble(input.nextLine());

	        System.out.println("Enter quantity:");
	        int quantity = Integer.parseInt(input.nextLine());

	        System.out.println("Enter the category:");
	        String category = input.nextLine();
	        
			System.out.println("Enter Description:");
			String description = input.nextLine();
						
			System.out.println("Enter Discount:");
			double discount  = Double.parseDouble(input.nextLine());
			
			LocalDate dateObj = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String date = dateObj.format(formatter);
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
	
			GarmentModel garment = new GarmentModel(ID, name, barcode, brand, size, color, date1, model, price, quantity, category, discount, description);

			inventory.add(garment);			
				        
			try{
                FileOutputStream fout = new FileOutputStream(file, true);
                ObjectOutputStream out=new ObjectOutputStream(fout);
                out.writeObject(garment);
                out.flush();
                out.close();
         }
         catch(Exception e){
                System.out.println(e);
         }
	 
	    }		
			
		public static ArrayList<GarmentModel> getGarment() throws IOException, ClassNotFoundException{
			
			ArrayList<GarmentModel> garmentsList = new ArrayList<GarmentModel>();
			FileInputStream fis = new FileInputStream("C:/Users/High Tech/Desktop/GarmentsInventory.txt");
			boolean cont = true;
			
			while (cont) {
			  try (ObjectInputStream input = new ObjectInputStream(fis)) {
			    Object obj = input.readObject();
			    if (obj != null) {
			    	garmentsList.add((GarmentModel)obj);
			    } else {
			      cont = false;
			    }
			  } catch (Exception e) {
			    // System.out.println(e.printStackTrace());
			  }
			}
			
			return garmentsList;
		}

		public static void removeGarment(String name){

			for(int i = 0; i < inventory.size(); i++){

				if(inventory.get(i).getName().contains(name)){
					inventory.remove(inventory.get(i));
				}
			}
	    }

	    public static void removeGarment(int id) {
	    	for(int i = 0; i < inventory.size(); i++){

				if(inventory.get(i).getID() == id){
					inventory.remove(inventory.get(i));
				}

			}
	    }

	}
