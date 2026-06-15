package com.javaprogramming.hotelreservationsystem;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class HotelReservationSystem {


		static ArrayList<Room> rooms = new ArrayList<>();
		static ArrayList<Reservation> reservations = new ArrayList<>();
		 static Scanner sc = new Scanner(System.in);
		
		public static void main(String[] args) {
			
			rooms.add(new Room(101, "Standard" , 1500));
			rooms.add(new Room(102, "Deluxe" , 2500));
			rooms.add(new Room(103, "Suite" , 5000));
		
		  int choice;
		  
		  do {
	          System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
	          System.out.println("1. View Available Rooms");
	          System.out.println("2. Book Room");
	          System.out.println("3. Cancel Reservation");
	          System.out.println("4. View Bookings");
	          System.out.println("5. Exit");

	          System.out.print("Enter Choice: ");
	          choice = sc.nextInt();
	           
	          switch (choice) {
	          case 1:
	              viewRooms();
	              break;

	          case 2:
	              bookRoom();
	              break;

	          case 3:
	              cancelReservation();
	              break;

	          case 4:
	              viewBookings();
	              break;
	          
	          case 5:
	              System.out.println("Thank You!");
	              break;

	          default:
	              System.out.println("Invalid Choice!");
	      }

	  } while (choice != 5);
	 }
		public static void viewRooms() {

	        System.out.println("\n===== AVAILABLE ROOMS =====");

	        for (Room room : rooms) {
	            if (room.isAvailable()) {
	                System.out.println(
	                        room.getRoomNumber() + " | " +
	                        room.getCategory() + " | ₹" +
	                        room.getPrice());
	                
	            }
	        }
	    }

	    public static void bookRoom() {

	        viewRooms();

	        System.out.print("\nEnter Customer Name: ");
	        sc.nextLine();
	        String name = sc.nextLine();

	        System.out.print("Enter Room Number: ");
	        int roomNo = sc.nextInt();

	        for (Room room : rooms) {
	        	
	        	  if (room.getRoomNumber() == roomNo &&
	                      room.isAvailable()) {

	                  System.out.println("Room Price: ₹" + room.getPrice());

	                  System.out.print("Proceed Payment? (Y/N): ");
	                  char payment = sc.next().toUpperCase().charAt(0);

	                  if (payment == 'Y') {

	                      room.setAvailable(false);

	                      Reservation reservation =
	                              new Reservation(name, room);

	                      reservations.add(reservation);

	                      saveBooking(reservation);
	                      
	                      System.out.println("Booking Successful!");
	                  } else {
	                      System.out.println("Payment Cancelled.");
	                  }

	                  return;
	              }
	          }

	          System.out.println("Room Not Available!");
	      }

	      public static void cancelReservation() {

	          System.out.print("Enter Room Number to Cancel: ");
	          int roomNo = sc.nextInt();
	          
	          Iterator<Reservation> iterator =
	                  reservations.iterator();

	          while (iterator.hasNext()) {

	              Reservation reservation = iterator.next();

	              if (reservation.getRoom()
	                      .getRoomNumber() == roomNo) {

	                  reservation.getRoom()
	                          .setAvailable(true);

	                  iterator.remove();
	                  
	                  System.out.println(
	                          "Reservation Cancelled Successfully!");

	                  return;
	              }
	          }

	          System.out.println("Booking Not Found!");
	      }

	      public static void viewBookings() {

	          System.out.println("\n===== BOOKING DETAILS =====");

	          if (reservations.isEmpty()) {
	              System.out.println("No Bookings Found.");
	              return;
	              
	          }

	          for (Reservation reservation : reservations) {

	              Room room = reservation.getRoom();

	              System.out.println("----------------------------");
	              System.out.println("Customer Name : "
	                      + reservation.getCustomerName());
	              System.out.println("Room Number   : "
	                      + room.getRoomNumber());
	              System.out.println("Category      : "
	                      + room.getCategory());
	              System.out.println("Price         : ₹"
	                      + room.getPrice());
	          }
	      }
	      public static void saveBooking(
	              Reservation reservation) {

	          try {

	              FileWriter writer =
	                      new FileWriter("bookings.txt", true);

	              writer.write(
	                      reservation.toString() + "\n");

	              writer.close();

	          } catch (IOException e) {

	              System.out.println(
	                      "Error Saving Booking!");
	    }
	  }
	}  

