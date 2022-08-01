package com.example.b07project;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

//Base class to communicate with database that can be inherited by both Admin and Customer
public abstract class User {
    public String username;
    public String password; //Hashed password

    private static ArrayList<Event> allEvents = new ArrayList<Event>();
    private static ArrayList<Venue> allVenues = new ArrayList<Venue>();
    private static ArrayList<User> allCustomers = new ArrayList<User>();
    private static ArrayList<User> allAdmins = new ArrayList<User>();


    public static ArrayList<Event> fetchAllEvents(){
        return allEvents;
    }

    public static ArrayList<Venue> fetchAllVenues(){
        return allVenues;
    }

    public static ArrayList<User> fetchAllCustomers(){
        return allCustomers;
    }

    public static ArrayList<User> fetchAllAdmins(){
        return allAdmins;
    }

    //Initialize data connection
    public static void initialize(){

        //Initialise Firebase connection
        FirebaseDatabase fire = FirebaseDatabase.getInstance("https://b07project-696e9-default-rtdb.firebaseio.com/");
        DatabaseReference ref;

        //Create test values
        Log.d("test", "initializing database");
//        Customer testCustomer = new Customer("test", "test", "Test 1");
//        ArrayList<String> tempList = new ArrayList<String>();
//        tempList.add(testCustomer.username);
//        Event testEvent = new Event("test", "test", "test", "0", "0", 42, tempList);
//
//        ref = fire.getReference();
//        ref.child("customers").child(testCustomer.username).setValue(testCustomer);
//        ref.child("events").child(testEvent.eventID).setValue(testEvent);

//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot child: snapshot.getChildren()){
//                    Customer customer = child.getValue(Customer.class);
//                    if (!allCustomers.contains(customer)){
//                        allCustomers.add(customer);
//                    }
//                    Log.d("test", customer.username);
//                    Log.d("test", customer.fullName);
//                }
//            }

        //Initialise synchronization between allCustomers and Firebase
        ref = fire.getReference("customers");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey){
                Customer customer = dataSnapshot.getValue(Customer.class);
                if (!allCustomers.contains(customer)){
                    allCustomers.add(customer);
                }
                Log.d("test", "Found customer: " + customer.username);
//                Log.d("test", customer.fullName);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                for (User c : User.fetchAllCustomers()) {
                    if (c.username == customer.username) {
                        Customer cu = (Customer)c;
                        c.password = customer.password;
                        cu.fullName = customer.fullName;
                        cu.joinedEvents = customer.joinedEvents;
                        cu.scheduledEvents = customer.scheduledEvents;
                        break;
                    }
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                allCustomers.remove(customer);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test", error.toString());
            }
        });

        //Initialise synchronization between allAdmins and Firebase
        ref = fire.getReference("admins");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey){
                Admin admin = dataSnapshot.getValue(Admin.class);
                if (!allAdmins.contains(admin)){
                    allAdmins.add(admin);
                }
                Log.d("test", "Found admin: " + admin.username);
//                Log.d("test", customer.fullName);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Admin admin = dataSnapshot.getValue(Admin.class);
                for (User a : User.fetchAllAdmins()) {
                    if (a.username == admin.username) {
                        Admin ac = (Admin)a;
                        ac.venues = admin.venues;
                        ac.password = admin.password;
                        break;
                    }
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Admin admin = dataSnapshot.getValue(Admin.class);
                allAdmins.remove(admin);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test", error.toString());
            }
        });


        //Initialise synchronization between allEvents and Firebase
        ref = fire.getReference("events");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey){
                Event event = dataSnapshot.getValue(Event.class);
                if (!allEvents.contains(event)){
                    allEvents.add(event);
                }
                Log.d("test", "Found event: " + event.eventID);
//                Log.d("test", customer.fullName);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Event event = dataSnapshot.getValue(Event.class);
                for (Event e : User.fetchAllEvents()) {
                    if (e.eventID == event.eventID) {
                        e.startTime = event.startTime;
                        e.venueID = event.venueID;
                        e.endTime = event.endTime;
                        e.capacity = event.capacity;
                        e.creator = event.creator;
                        e.customers = event.customers;
                        break;
                    }
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);
                allEvents.remove(event);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test", error.toString());
            }
        });
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot child: snapshot.getChildren()){
//                    Event event = child.getValue(Event.class);
//                    if (!allEvents.contains(event)){
//                        allEvents.add(event);
//                    }
//                    Log.d("test", event.eventID);
//                    Log.d("test",event.customers.toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("test",error.toString());
//            }
//        });

        //Initialise synchronization between allVenues and Firebase
        ref = fire.getReference("venues");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey){
                Venue venue = dataSnapshot.getValue(Venue.class);
                if (!allVenues.contains(venue)){
                    allVenues.add(venue);
                }
                Log.d("test", "Found venue: " + venue.venueName);
//                Log.d("test", customer.fullName);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Venue venue = dataSnapshot.getValue(Venue.class);
                for (Venue v : User.fetchAllVenues()) {
                    if (v.venueID == venue.venueID) {
                        v.venueName = venue.venueName;
                        v.venueLocation = venue.venueLocation;
                        v.events = venue.events;
                        break;
                    }
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Venue venue = dataSnapshot.getValue(Venue.class);
                allVenues.remove(venue);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("test", error.toString());
            }
        });
    }


}